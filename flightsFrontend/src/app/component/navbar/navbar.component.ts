import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { from, Observable } from 'rxjs';

import { AirportService } from '../../service/airport.service';
import { Airport } from 'src/app/model/airport';
import { FlightService } from '../../service/flight.service';
import { FlightData } from '../../model/flightData/flight-data';

import { map, tap, debounceTime, distinctUntilChanged, switchMap, flatMap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

interface Adult {
  value: number;
  viewValue: number;
}

interface Children {
  value: number;
  viewValue: number;
}

interface Currency {
  value: string;
  viewValue: string;
}

interface TravelClass {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  adults: Adult[] = [
    { value: 1, viewValue: 1 },
    { value: 2, viewValue: 2 },
    { value: 3, viewValue: 3 },
    { value: 4, viewValue: 4 },
    { value: 5, viewValue: 5 },
    { value: 6, viewValue: 6 }
  ];

  children: Children[] = [
    { value: 1, viewValue: 1 },
    { value: 2, viewValue: 2 },
    { value: 3, viewValue: 3 }
  ];

  currency: Currency[] = [
    { value: "EUR", viewValue: "EUR" },
    { value: "USD", viewValue: "USD" },
    { value: "HRK", viewValue: "HRK" }
  ];

  travelClass: TravelClass[] = [
    { value: "ECONOMY", viewValue: "Economy Class" },
    { value: "PREMIUM_ECONOMY", viewValue: "Premium Economy Class" },
    { value: "BUSINESS", viewValue: "Business Class" },
    { value: "FIRST", viewValue: "First Class" }
  ];

  flightsForm = new FormGroup({
    originLocation: new FormControl(),
    destinationLocation: new FormControl(),
    departureDate: new FormControl(),
    returnDate: new FormControl(),
    adults: new FormControl(),
    children: new FormControl(),
    currency: new FormControl(),
    travelClass: new FormControl(),
    nonStop: new FormControl()
  })

  airportsDep: Observable<Airport[]>;
  airportsArr: Observable<Airport[]>;
  flightData: FlightData;

  constructor(private airportService: AirportService, private flightService: FlightService) { }

  ngOnInit() {    
    this.airportsDep = this.flightsForm.controls.originLocation.valueChanges.pipe(
      debounceTime(400),
      switchMap(iata => this.airportService.searchAirport(iata))
    );
    this.airportsArr = this.flightsForm.controls.destinationLocation.valueChanges.pipe(
      debounceTime(400),
      switchMap(iata => this.airportService.searchAirport(iata))
    );
  }

  onSubmit() {
    this.flightService.searchFlights(this.flightsForm.value).subscribe(res => {
      this.flightService.setData(res);
      console.log(res);
    });
  }
}
