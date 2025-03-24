import { Component } from '@angular/core';
import { ConfigService } from '../config.service';
import { eventNames } from 'process';
import { response } from 'express';

@Component({
  selector: 'app-vendors',
  templateUrl: './vendors.component.html',
  styleUrl: './vendors.component.css'
})
export class VendorsComponent {
  vendor={firstName:'',lastName:'',eventName:''};
  vendors:any[]=[];

  constructor(private api: ConfigService){}

  addVendor(){
    this.api.addVendor(this.vendor).subscribe(response=>{
      alert('Vendors added successfully');
      this.vendor={firstName:'',lastName:'',eventName:''};
      this.fetchVendors();
    })
  }

  fetchVendors(){
    this.api.getAllVendors().subscribe(data =>(this.vendors=data));
  }
  
  ngOnInit(){
    this.fetchVendors();
  }

}