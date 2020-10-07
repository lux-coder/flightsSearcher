import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { SpinnerService } from '../service/spinner.service';

@Injectable()
export class CustomHttpInterceptor implements HttpInterceptor {

  constructor(private spinnerService: SpinnerService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

   // if (req.method === "POST") {

      this.spinnerService.show();

      return next
        .handle(req)
        .pipe(
          tap((event: HttpEvent<any>) => {
            if (event instanceof HttpResponse) {
              this.spinnerService.hide();
            }
          }, (error) => {
            this.spinnerService.hide();
          })
        );
    }
  //}
}
