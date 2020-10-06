import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { Flight } from 'src/app/model/flight';
import { FlightData } from 'src/app/model/flightData/flight-data';
import { FlightService } from 'src/app/service/flight.service';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.scss']
})
export class FlightComponent implements OnInit {
  data: FlightData[];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  obs: Observable<any>;
  dataSource: MatTableDataSource<FlightData> = new MatTableDataSource<FlightData>([]);


  constructor(private flightService: FlightService) {
    console.log("constructor log from flightComponent");
    this.flightService.apiData$.subscribe(data => this.data = data);
    console.log(this.data);
    this.dataSource = new MatTableDataSource(this.data);
    this.obs = this.dataSource.connect();
    this.dataSource.paginator = this.paginator;

  }



  ngOnInit() {    
    this.flightService.apiData$.subscribe(data => {
      console.log(data);
      this.dataSource = new MatTableDataSource(this.data);
      this.obs = this.dataSource.connect();
      this.dataSource.paginator = this.paginator;
    });
  }

}
