import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Cpville } from '../model/cpville';

@Injectable()
export class CpvilleService {

  private cpvilleUrl = 'https://localhost:8443/bankProjectWeb/rs/cpville/';  // URL to web api
  
  constructor(private http: Http) {}

  getCpvilleList(): Promise<Cpville[]> {

    return this.http.get(this.cpvilleUrl, this.jwt())
               .toPromise()
               .then(response => response.json() as Cpville[])
               .catch(this.handleError);
  }


  getCpvilleById(id: number): Promise<Cpville> {
    const url = `${this.cpvilleUrl}/${id}`;
    return this.http.get(url, this.jwt())
      .toPromise()
      .then(response => response.json() as Cpville)
      .catch(this.handleError);
  }

  deleteCpvilleId(id: number): Promise<void> {
    const url = `${this.cpvilleUrl}${id}`;
    return this.http.delete(url, this.jwt())
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createCpville(city: string, zip:string): Promise<Cpville> {
    return this.http
      .post(this.cpvilleUrl, JSON.stringify({city: city, zip: zip}), this.jwt())
      .toPromise()
      .then(res => res.json() as Cpville)
      .catch(this.handleError);
  }

  updateCpville(cpville: Cpville): Promise<Cpville> {
    const url = `${this.cpvilleUrl}/${cpville.id}`;
    return this.http
      .put(url, JSON.stringify(cpville), this.jwt())
      .toPromise()
      .then(() => cpville)
      .catch(this.handleError);
  }

  private jwt() {
    let currentOwner = JSON.parse(localStorage.getItem('currentOwner'));
    if (currentOwner) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        headers.append("Authorization", "Basic " + btoa(currentOwner.login+':'+currentOwner.pswd));
        return new RequestOptions({ headers: headers });
    }
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

