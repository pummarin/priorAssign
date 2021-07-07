import { Component } from '@angular/core';
import { MessageService } from './service/message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'dailytemperature';
  input: any;
  constructor(public messageService: MessageService) {}

  sendMessage() {
    if (this.input) {
      this.messageService.sendMessage(this.input);
      this.input = '';
    }
  }
  toggleTodo(id: number){
    this.messageService.toggleTodo(id);
  }
}
