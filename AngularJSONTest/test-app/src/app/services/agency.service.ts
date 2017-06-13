import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Agency } from '../model/agency';

@Injectable()
export class AgencyService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private headersGet = new Headers({'Accept': 'text/plain'});
  private url = 'https://localhost:8443/bankProjectWeb/rs/agency/';  // URL to web api
 
  private options = new RequestOptions({headers: this.headers});

  constructor(private http: Http) {
     this.headers.append("Authorization", "Basic bHU6bHU=");
   }
  getAgencyList(): Promise<Agency[]> {
    return this.http.get(this.url, this.options)
               .toPromise()
               .then(response => response.json() as Agency[])
               .catch(this.handleError);
  }

  getAgencyById(id: number): Promise<Agency> {
    const urla = `${this.url}${id}`;
    return this.http.get(urla)
      .toPromise()
      .then(response => response.json() as Agency)
      .catch(this.handleError);
  }

  deleteAgencyId(id: number): Promise<void> {
    const url = `${this.url}${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createAgency(agency: Agency): Promise<any> {
    return this.http
      .post(this.url, JSON.stringify(agency), {headers: this.headers})
      .toPromise()
      .then(res=>this.http.get(res.url).toPromise().then(response => response.json() as Agency))
      .catch(this.handleError);
  }
  

  updateAgency(agency: Agency): Promise<Agency> {
    const url = `${this.url}/${agency.id}`;
    return this.http
      .put(url, JSON.stringify(agency), {headers: this.headers})
      .toPromise()
      .then(() => agency)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

