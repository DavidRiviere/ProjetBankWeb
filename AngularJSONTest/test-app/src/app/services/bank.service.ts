import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Bank } from '../model/bank';

@Injectable()
export class BankService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private headersGet = new Headers({'Accept': 'text/plain'});
  private url = 'https://localhost:8443/bankProjectWeb/rs/bank/';  // URL to web api

  private options = new RequestOptions({headers: this.headers});

  constructor(private http: Http) {
     this.headers.append("Authorization", "Basic bHU6bHU=");
   }
  getBankList(): Promise<Bank[]> {
    return this.http.get(this.url, this.options)
               .toPromise()
               .then(response => response.json() as Bank[])
               .catch(this.handleError);
  }

  getBankById(id: number): Promise<Bank> {
    const urla = `${this.url}${id}`;
    return this.http.get(urla, this.options)
      .toPromise()
      .then(response => response.json() as Bank)
      .catch(this.handleError);
  }

  deleteBankId(id: number): Promise<void> {
    const url = `${this.url}${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createBank(bank: Bank): Promise<any> {
    return this.http
      .post(this.url, JSON.stringify(bank), {headers: this.headers})
      .toPromise()
      .then(res=>this.http.get(res.url).toPromise().then(response => response.json() as Bank))
      .catch(this.handleError);
  }
  

  updateBank(bank: Bank): Promise<Bank> {
    const url = `${this.url}/${bank.id}`;
    return this.http
      .put(url, JSON.stringify(bank), {headers: this.headers})
      .toPromise()
      .then(() => bank)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

