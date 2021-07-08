import { Component,Input,OnInit, ViewChild} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog} from '@angular/material/dialog';
import { ModalDismissReasons, NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Booking } from './model/booking.model';
import { RequestBooking } from './model/requestBooking';
import { MessageService } from './service/message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'BookingRestaurant';
  input: any ;
  isBooking: boolean;
  request: RequestBooking;
  userName:string;
  userBooking:any;
  currentQ:number;
  closeResult = '';
  @ViewChild("content") content;
  
  
  
  constructor(public messageService: MessageService,public dialog: MatDialog,private modalService: NgbModal) {}
 
  ngOnInit(): void {
    
    // this.open();
    this.messageService.currentQ.subscribe(x => {
      console.log("x:",x);
      this.currentQ = x;
      if (this.currentQ == this.userBooking?.id) {
        this.open();
      }
    })
    
  }

  inputFrom = new FormGroup({
    name: new FormControl('',Validators.required),
    number: new FormControl('',Validators.required)
  })

  increase(){
    this.messageService.increase();
  }

  decrease(){
    this.messageService.decrease();
  }

  handleFinish(modal){
    modal.close();
    sessionStorage.clear();
    location.reload();
  }

  sendMessage() {
    if (this.inputFrom.value) {
      this.userName = this.inputFrom.controls.name.value;      
      this.isBooking = true;
      sessionStorage.setItem("user",this.userName);      
      this.request = this.inputFrom.value;
      console.log(this.request);      
      this.messageService.saveBooking(this.request).then(res => {
        let booking:Booking = res;
        console.log("Booking:",booking);
        this.userBooking = res;
      })
      // this.input = '';
    }
  }
  open() {
    this.modalService.open(this.content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
  
}



