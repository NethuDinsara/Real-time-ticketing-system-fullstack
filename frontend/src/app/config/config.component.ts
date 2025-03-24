import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../config.service';
import { Route } from '@angular/router';

@Component({
  selector: 'app-config',
  templateUrl: './config.component.html',
  styleUrls: ['./config.component.css']
})
export class ConfigComponent implements OnInit {
  config = {
    totalTickets: 4,
    ticketReleaseRate: 1,
    customerRetrievalRate: 3,
    maxTicketCapacity: 8
  };

  constructor(private configService: ConfigService) {}


  ngOnInit(): void {}

  // Method to submit the configuration
  saveConfig() {
    this.configService.saveConfig(this.config).subscribe({
      next: (response) => {
        alert('Configuration saved successfully!');
        console.log(response);
      },
      error: (error) => {
        alert('Partially saved!');
        console.error(error);
      }
    });
  }


}
