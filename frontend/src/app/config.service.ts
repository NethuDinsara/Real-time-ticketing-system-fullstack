import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  private apiUrl = 'http://localhost:8080/api';
  

  constructor(private http: HttpClient) { }

  startProcessing(){
    return this.http.get(`${this.apiUrl}/start`,{});
  }

  stopProcessing(){
    return this.http.get<string>(`${this.apiUrl}/stop`, { responseType: 'text' as 'json' });
}


  // To save configuration
  saveConfig(config: any): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/config/set`, config);
  }

  // To get current configuration
  getConfig(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/config/get`);
  }

  // Cusomer APIs
  addCustomer(customer: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/customers`, customer);
  }

  getAllCustomers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/customers`);
  }

  // Vendor APIs
  addVendor(vendor: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/vendors`, vendor);
  }

  getAllVendors(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/vendors`);
  }

// for ticket status
getStatus(): Observable<string> {
  return this.http.get<string>(`${this.apiUrl}/status`);
}

}
