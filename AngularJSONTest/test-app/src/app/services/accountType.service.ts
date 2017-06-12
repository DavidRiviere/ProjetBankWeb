import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { AccountType } from '../model/accountType';

@Injectable()
export class AccountTypeService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private headersGet = new Headers({'Accept': 'text/plain'});
  private url = 'http://localhost:8080/bankProjectWeb/rs/accounttype/';  // URL to web api

  constructor(private http: Http) { }

  getAccountTypeList(): Promise<AccountType[]> {
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json() as AccountType[])
               .catch(this.handleError);
  }

  getAccountTypeById(id: number): Promise<AccountType> {
    const urla = `${this.url}${id}`;
    return this.http.get(urla)
      .toPromise()
      .then(response => response.json() as AccountType)
      .catch(this.handleError);
  }

  deleteAccountTypeId(id: number): Promise<void> {
    const url = `${this.url}${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createAccountType(accountType: AccountType): Promise<any> {
    return this.http
      .post(this.url, JSON.stringify(accountType), {headers: this.headers})
      .toPromise()
      .then(res=>this.http.get(res.url).toPromise().then(response => response.json() as AccountType))
      .catch(this.handleError);
  }
  

  updateAccountType(accountType: AccountType): Promise<AccountType> {
    const url = `${this.url}/${accountType.id}`;
    return this.http
      .put(url, JSON.stringify(accountType), {headers: this.headers})
      .toPromise()
      .then(() => accountType)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

