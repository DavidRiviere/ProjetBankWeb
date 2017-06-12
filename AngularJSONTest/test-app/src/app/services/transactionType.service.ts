import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { TransactionType } from '../model/transactionType';

@Injectable()
export class TransactionTypeService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private headersGet = new Headers({'Accept': 'text/plain'});
  private url = 'https://localhost:8443/bankProjectWeb/rs/transactiontype/';  // URL to web api

  constructor(private http: Http) { 
    //this.headers.append('Authorization','Basic');
    //this.headers.append('dG90bw==', 'dG90bw==');
    this.headers.append("Authorization", "Basic " + btoa("toto" + ":" + "toto"));
  }

  getTransactionTypeList(): Promise<TransactionType[]> {
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json() as TransactionType[])
               .catch(this.handleError);
  }

  getTransactionTypeById(id: number): Promise<TransactionType> {
    const urla = `${this.url}${id}`;
    return this.http.get(urla)
      .toPromise()
      .then(response => response.json() as TransactionType)
      .catch(this.handleError);
  }

  deleteTransactionTypeId(id: number): Promise<void> {
    const url = `${this.url}${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createTransactionType(transactionType: TransactionType): Promise<any> {
    return this.http
      .post(this.url, JSON.stringify(transactionType), {headers: this.headers})
      .toPromise()
      .then(res=>this.http.get(res.url).toPromise().then(response => response.json() as TransactionType))
      .catch(this.handleError);
  }
  

  updateTransactionType(transactionType: TransactionType): Promise<TransactionType> {
    const url = `${this.url}/${transactionType.id}`;
    return this.http
      .put(url, JSON.stringify(transactionType), {headers: this.headers})
      .toPromise()
      .then(() => transactionType)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

