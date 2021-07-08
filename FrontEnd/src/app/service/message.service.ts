import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Booking } from '../model/booking.model';
import { RequestBooking } from '../model/requestBooking';
// Declare SockJS and Stomp
declare var SockJS: any;
declare var Stomp: any;
@Injectable({
  providedIn: 'root'
})
export class MessageService {
  serverUrl = environment.serverUrl;
  public stompClient: any;
  public bookings: any[] = [];
  public findBooking: any[] = [];
  public user: string;
  public userBooking: BehaviorSubject<any> = new BehaviorSubject(null);

  public currentQ: BehaviorSubject<any> = new BehaviorSubject(0);

  constructor(private http: HttpClient) {
    this.initializeWebSocketConnection();
    this.getCurrentQueue();
  }

  initializeWebSocketConnection() {
    const serverUrl = this.serverUrl + '/socket';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    // tslint:disable-next-line:only-arrow-functions
    this.stompClient.connect({}, function (frame: any) {
      that.stompClient.subscribe('/message', (message: any) => {

        if (message.body) {
          console.log("mess", parseInt(message.body));
          that.currentQ.next(parseInt(message.body));
        }
      });
    });
  }


  increase() {
    this.http.get(`${this.serverUrl}/increaseQueue`, {}).subscribe(res => {
      console.log(res);
    })
  }

  decrease() {
    this.http.get(`${this.serverUrl}/decreaseQueue`, {}).subscribe(res => {
      console.log(res);
    })
  }

  getCurrentQueue() {
    this.http.get<number>(`${this.serverUrl}/get/currentQueue`, {}).subscribe(res => {
      console.log(res);
      this.currentQ.next(res);
    })
  }

  saveBooking(requestBooking: RequestBooking) {
    return new Promise<Booking>((ok, notok) => {
      this.http.post<Booking>(`${this.serverUrl}/saveBooking`, requestBooking, {}).subscribe(res => {
        console.log(res); 
        ok(res);
        
      },error => {
        notok(error)
      })
    })

  }
}
interface Todo {
  id: number | null;
  title: string;
  isCompleted: boolean;
}
