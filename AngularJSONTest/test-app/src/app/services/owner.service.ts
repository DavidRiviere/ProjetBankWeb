import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Owner } from '../model/owner';

@Injectable()
export class OwnerService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private ownerUrl = 'http://localhost:8080/bankProjectWeb/rs/owner/';  // URL to web api

  constructor(private http: Http) { }

  getCpvilleList(): Promise<Owner[]> {
    return this.http.get(this.ownerUrl)
               .toPromise()
               .then(response => response.json() as Owner[])
               .catch(this.handleError);
  }


  getCpvilleById(id: number): Promise<Owner> {
    const url = `${this.ownerUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Owner)
      .catch(this.handleError);
  }

  deleteCpvilleId(id: number): Promise<void> {
    const url = `${this.ownerUrl}${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createCpville(owner: Owner): Promise<Owner> {
    return this.http
      .post(this.ownerUrl, JSON.stringify(owner), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Owner)
      .catch(this.handleError);
  }

  updateCpville(owner: Owner): Promise<Owner> {
    const url = `${this.ownerUrl}/${owner.id}`;
    return this.http
      .put(url, JSON.stringify(owner), {headers: this.headers})
      .toPromise()
      .then(() => owner)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

