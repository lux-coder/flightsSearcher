import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ServerConstant } from '../constant/server-constant';
import { Flight } from '../model/flight';

@Injectable({
  providedIn: 'root'
})
export class FlightService {


  private _reqOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'application/json') };
  //BASE_URL: string = 'http://localhost:8080/api/flights/';
  constant: ServerConstant = new ServerConstant();
  public host: string = this.constant.host;

  flights: Observable<Flight[]>;

  constructor(private http: HttpClient) { }

  //searchFlights(flight: Flight): Observable<Flight | HttpErrorResponse> {
  searchFlights(flight: Flight): Observable<any | HttpErrorResponse> {
    console.log("from sevice");
    console.log(flight);

    let fly = JSON.stringify(flight);
    console.log(fly);

    //return this.http.get<Flight[]>(this.BASE_URL + 'fetchFlights');
    console.log(this.host + '/api/flights/fetchFlights');
    //return this.http.post<Flight>(this.BASE_URL + 'fetchFlights', fly, this._reqOptionsArgs);
    return this.http.post<any>(this.host + '/api/flights/fetchFlights', fly, this._reqOptionsArgs);

  }

}
