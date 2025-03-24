import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConfigComponent } from './config/config.component';
import { FormsModule } from '@angular/forms';  // Import FormsModule
import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { CustomersComponent } from './customers/customers.component';
import { VendorsComponent } from './vendors/vendors.component';
import { TicketStatusComponent } from './ticket-status/ticket-status.component';
import { ControlPanelComponent } from './control-panel/control-panel.component';  // Import HttpClientModule
import { LogDisplayComponent } from './log-display/log-display.component';

@NgModule({
  declarations: [
    AppComponent,
    ConfigComponent,
    CustomersComponent,
    VendorsComponent,
    TicketStatusComponent,
    ControlPanelComponent,
    LogDisplayComponent,
    TicketStatusComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,        //to handle two-way binding
    HttpClientModule    //to make HTTP requests
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
