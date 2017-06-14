import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Owner } from '../model/owner';

@Injectable()
export class OwnerService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private ownerUrl = 'https://localhost:8443/bankProjectWeb/rs/owner/';  // URL to web api
  
  private options = new RequestOptions({headers: this.headers});

  constructor(private http: Http) {}

  getOwnerList(): Promise<Owner[]> {
    return this.http.get(this.ownerUrl, this.jwt())
               .toPromise()
               .then(response => response.json() as Owner[])
               .catch(this.handleError);
  }


  getOwnerById(id: number): Promise<Owner> {
    const url = `${this.ownerUrl}/${id}`;
    return this.http.get(url, this.jwt())
      .toPromise()
      .then(response => response.json() as Owner)
      .catch(this.handleError);
  }

  deleteOwnerId(id: number): Promise<void> {
    const url = `${this.ownerUrl}${id}`;
    return this.http.delete(url, this.jwt())
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createOwner(owner: Owner): Promise<Owner> {
    return this.http
      .post(this.ownerUrl, JSON.stringify(owner),this.jwt())
      .toPromise()
      .then(res => res.json() as Owner)
      .catch(this.handleError);
  }

  updateOwner(owner: Owner): Promise<Owner> {
    const url = `${this.ownerUrl}/${owner.id}`;
    return this.http
      .put(url, JSON.stringify(owner), this.jwt())
      .toPromise()
      .then(() => owner)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  private jwt() {
    let currentOwner = JSON.parse(localStorage.getItem('currentOwner'));
    if (currentOwner) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        headers.append("Authorization", "Basic " + btoa(currentOwner.login+':'+currentOwner.pswd));
        return new RequestOptions({ headers: headers });
    }
  }

}

