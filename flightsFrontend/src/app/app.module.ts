import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { MaterialModule } from './material/material.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlightComponent } from './component/flight/flight.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { HomeComponent } from './component/home/home.component';

import { Form, ReactiveFormsModule, FormControl, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { CacheInterceptor } from './interceptor/cache.interceptor';
import { CacheService } from './service/cache.service';
import { AirportService } from './service/airport.service';
import { FlightService } from './service/flight.service';

@NgModule({
  declarations: [
    AppComponent,
    FlightComponent,
    NavbarComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,    
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [
    AirportService,
    FlightService,
    CacheService,
    { provide: HTTP_INTERCEPTORS, useClass: CacheInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
