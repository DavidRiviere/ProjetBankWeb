import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Account } from '../model/account';

@Injectable()
export class AccountService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private headersGet = new Headers({'Accept': 'text/plain'});
  private url = 'https://localhost:8443/bankProjectWeb/rs/account/';  // URL to web api
  private url2 = 'https://localhost:8443/bankProjectWeb/rs/owner/';  // URL to web api';
  private options = new RequestOptions({headers: this.headers});

  constructor(private http: Http) {
     this.headers.append("Authorization", "Basic bHU6bHU=");
     this.headersGet.append("Authorization", "Basic bHU6bHU=");
   }
   
  getAccountList(): Promise<Account[]> {
    return this.http.get(this.url, this.options)
               .toPromise()
               .then(response => response.json() as Account[])
               .catch(this.handleError);
  }

  getAccountById(id: number): Promise<Account> {
    const url = `${this.url}${id}`;
    return this.http.get(url, this.options)
      .toPromise()
      .then(response => response.json() as Account)
      .catch(this.handleError);
  }

  getAccountListById(id: number): Promise<Account[]> {
    const url = `${this.url2}${id}/accounts`;
    return this.http.get(url, this.options)
      .toPromise()
      .then(response => response.json() as Account[])
      .catch(this.handleError);
  }

  getAccountBalanceById(id: number) {
    const urla = `${this.url}${id}/balance/`;
    return this.http.get(urla, {headers: this.headersGet})
    .toPromise()
    .then(response => (response.text())).catch(this.handleError);
  }

  deleteAccountId(id: number): Promise<void> {
    const url = `${this.url}${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createAccount(account: Account): Promise<Account> {
    return this.http
      .post(this.url, JSON.stringify(account), {headers: this.headers})
      .toPromise()
      .then(res=>this.http.get(res.url).toPromise().then(response => response.json() as Account))
      .catch(this.handleError);
  }  

  updateAccount(account: Account): Promise<Account> {
    const url = `${this.url}/${account.id}`;
    return this.http
      .put(url, JSON.stringify(account), {headers: this.headers})
      .toPromise()
      .then(() => account)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

