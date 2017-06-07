import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Account } from '../model/account';

@Injectable()
export class AccountService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private url = 'http://localhost:8080/bankProjectWeb/rs/account/';  // URL to web api

  constructor(private http: Http) { }

  getAccountList(): Promise<Account[]> {
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json() as Account[])
               .catch(this.handleError);
  }

 /* get(urlRes) {
    return this.http.get(urlRes)
      .toPromise();
  }*/

  getAccountById(id: number): Promise<Account> {
    const url = `${this.url}${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Account)
      .catch(this.handleError);
  }

  getAccountBalanceById(id: number): Promise<number> {
    const urla = `${this.url}${id}/balance/`;
    return this.http.get(urla)
      .toPromise()
      .then(response => Number(response.toString()))
      .catch(this.handleError);
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

