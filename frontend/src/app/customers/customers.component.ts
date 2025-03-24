import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../config.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customer = { firstName: '', lastName: '', noOfTickets: 0 };
  customers: any[] = [];

  constructor(private api: ConfigService) {}

  ngOnInit(): void {
    this.fetchCustomers();
  }

  addCustomer() {
    this.api.addCustomer(this.customer).subscribe(response => {
      alert('Customer added successfully!');
      this.customer = { firstName: '', lastName: '', noOfTickets: 0 };
      this.fetchCustomers();
    }, error => {
      alert('Failed to add customer.');
      console.error(error);
    });
  }

  fetchCustomers() {
    this.api.getAllCustomers().subscribe(data => (this.customers = data));
  }
}
