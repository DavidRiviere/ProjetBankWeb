import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Transaction } from '../model/transaction';

@Injectable()
export class TransactionService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private transactionUrl = 'http://localhost:8080/bankProjectWeb/rs/account/';  // URL to web api

  constructor(private http: Http) { }

  getTransactionList(id:number): Promise<Transaction[]> {
    const url = `${this.transactionUrl}/${id}/transactions/`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Transaction[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

