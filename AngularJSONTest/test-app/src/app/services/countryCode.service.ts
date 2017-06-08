import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { CountryCode } from '../model/countryCode';

@Injectable()
export class CountryCodeService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private headersGet = new Headers({'Accept': 'text/plain'});
  private url = 'http://localhost:8080/bankProjectWeb/rs/countryCode/';  // URL to web api

  constructor(private http: Http) { }

  getCountryCodeList(): Promise<CountryCode[]> {
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json() as CountryCode[])
               .catch(this.handleError);
  }

  getCountryCodeById(id: number): Promise<CountryCode> {
    const urla = `${this.url}${id}`;
    return this.http.get(urla)
      .toPromise()
      .then(response => response.json() as CountryCode)
      .catch(this.handleError);
  }

  deleteCountryCodeId(id: number): Promise<void> {
    const url = `${this.url}${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createCountryCode(countryCode: CountryCode): Promise<any> {
    return this.http
      .post(this.url, JSON.stringify(countryCode), {headers: this.headers})
      .toPromise()
      .then(res=>this.http.get(res.url).toPromise().then(response => response.json() as CountryCode))
      .catch(this.handleError);
  }
  

  updateCountryCode(countryCode: CountryCode): Promise<CountryCode> {
    const url = `${this.url}/${countryCode.id}`;
    return this.http
      .put(url, JSON.stringify(countryCode), {headers: this.headers})
      .toPromise()
      .then(() => countryCode)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

