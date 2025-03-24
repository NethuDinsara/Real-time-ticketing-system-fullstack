import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../config.service';

@Component({
  selector: 'app-ticket-status',
  templateUrl: './ticket-status.component.html',
  styleUrls: ['./ticket-status.component.css']
})
export class TicketStatusComponent implements OnInit {
  status: string = ''; // To hold the status message
  loading: boolean = false; // To show loading state

  constructor(private configService: ConfigService) {}

  ngOnInit(): void {
    this.fetchStatus(); // Fetch status on component initialization
  }

  fetchStatus(): void {
    this.loading = true; // Set loading to true while fetching
    this.configService.getConfig().subscribe(
      (response) => {
        this.status = response; // Update status with response
        this.loading = false; // Set loading to false after fetching
      },
      (error) => {
        this.status = 'Error fetching status'; //Handle error
        this.loading = false; 
      }
    );
  }
}
