import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { error } from '@angular/compiler/src/util';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  URL = 'http://localhost:9090/';
  private isLoggedin: boolean;
  constructor(private __http: HttpClient) { }

  isLogged(): boolean {
    return this.isLoggedin;
  }

  loggedIn(isLogged: boolean) {
    this.isLoggedin = isLogged;
  }

  submitKyc(data: any): Observable<any> {
    const headers = new HttpHeaders()
        .set('content-type', 'application/json')
        .set('Accept', 'application/json');
    return this.__http.post<any>(this.URL + 'hackathon/ekyc/submiteKyc', data, { headers });
  }

  getUnverifiedKYC(): Observable<any> {
    const headers = new HttpHeaders()
        .set('content-type', 'application/json')
        .set('Accept', 'application/json');
    return this.__http.post<any>(this.URL + '/hackathon/ekyc/getAllUnverified', { headers });
  }

  executeVerifyKyc(data, status) {
    const headers = new HttpHeaders()
        .set('content-type', 'application/json')
        .set('Accept', 'application/json');
    return this.__http.post<any>(this.URL + '/hackathon/ekyc/verifyKyc/' + status, data, { headers });
  }

  executeGetDataUsingHex(hexCode) {
    const headers = new HttpHeaders()
        .set('content-type', 'application/json')
        .set('Accept', 'application/json');
    return this.__http.post<any>(this.URL + '/hackathon/ekyc/getDataUsingHex/' + hexCode, { headers });
  }
}
