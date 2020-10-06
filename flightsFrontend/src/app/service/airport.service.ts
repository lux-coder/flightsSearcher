import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ServerConstant } from '../constant/server-constant';
import { Airport } from '../model/airport';

@Injectable({
  providedIn: 'root'
})
export class AirportService {

  private _reqOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'application/json') };
  //private _reqOptionsArgs = { headers: new HttpHeaders().set( "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept" ) };

  //BASE_URL: string = 'http://localhost:8080/api/airport/';
  constant: ServerConstant = new ServerConstant();
  public host: string = this.constant.host;

  constructor(private http: HttpClient) { }

  public searchAirport(keyword: string): Observable<Airport[]> {
    if (!keyword) {
      return of([]);
    }
    //console.log(this.BASE_URL + 'search/' + keyword, this._reqOptionsArgs);
    return this.http.get<Airport[]>(this.host + '/api/airport/search/' + keyword, this._reqOptionsArgs);    
  }

}
