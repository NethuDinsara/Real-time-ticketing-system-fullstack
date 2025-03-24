import { Component } from '@angular/core';
import { ConfigService } from '../config.service';
// import{TIcketProcessingService} from ''
@Component({
  selector: 'app-control-panel',
  templateUrl: './control-panel.component.html',
  styleUrl: './control-panel.component.css'
})
export class ControlPanelComponent {
  isProcessing: boolean=false;
  logs:string[]=[];

  constructor(private configService: ConfigService){}

  startTicketProcessing():void{
    if(!this.isProcessing){
      this.isProcessing=true;
      console.log("Ticket Processing Started")
      this.configService.startProcessing().subscribe(response => {
        console.log(response);
      });
    }
  }

  stopTicketProcessing(): void {
    if (this.isProcessing) {
      this.isProcessing = false;
      console.log("Ticket Processing Stopped");
      this.configService.stopProcessing().subscribe(response => {
        console.log(response);
        this.logs = response.split("\n"); // Parse logs from response string
      });
    }
  }

  getLogs(): string[] {
    return this.logs;
  }

}
