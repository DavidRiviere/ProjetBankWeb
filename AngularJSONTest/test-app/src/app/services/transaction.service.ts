import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Transaction } from '../model/transaction';

@Injectable()
export class TransactionService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private accountURL = 'http://localhost:8080/bankProjectWeb/rs/account/';  // URL to web api
  private transactionURL = 'http://localhost:8080/bankProjectWeb/rs/transaction/';  // URL to web api

  constructor(private http: Http) { }

  getTransactionList(id:number): Promise<any> {
    const url = `${this.accountURL}${id}/transactions/`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  createTransaction(transaction: Transaction): Promise<Transaction> {
    return this.http
      .post(this.transactionURL, JSON.stringify(transaction), {headers: this.headers})
      .toPromise()
      .then(res=>this.http.get(res.url).toPromise().then(response => response.json() as Transaction))
      .catch(this.handleError);
  }  

  
  deleteTransactionId(id: number): Promise<void> {
    const url = `${this.transactionURL}${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

