import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse
} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ServerConstant } from '../constant/server-constant';
import { FlightService } from '../service/flight.service';
import { CacheService } from '../service/cache.service';
import { tap } from 'rxjs/operators';

@Injectable()
export class CacheInterceptor implements HttpInterceptor {

  constant: ServerConstant = new ServerConstant();
  private host: string = this.constant.host;

  constructor(private flightService: FlightService, private cacheService: CacheService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("from cache interceptor");
    //return next.handle(request);

    const cachedResponse: HttpResponse<any> = this.cacheService.getCache(request.url);

    if(cachedResponse) {
      return of (cachedResponse);
    }

    return next.handle(request).pipe(tap(event => {
      if (event instanceof HttpResponse) {
        this.cacheService.cacheRequest(request.url, event);
      }
    }));
  }
}
