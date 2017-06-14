import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Address } from '../model/address';

@Injectable()
export class AddressService {

  private addressUrl = 'https://localhost:8443/bankProjectWeb/rs/address/';  // URL to web api
  
  constructor(private http: Http) {}

  getAddressList(): Promise<Address[]> {

    return this.http.get(this.addressUrl, this.jwt())
               .toPromise()
               .then(response => response.json() as Address[])
               .catch(this.handleError);
  }


  private jwt() {

        let headers = new Headers({ 'Content-Type': 'application/json' });
        headers.append("Authorization", "Basic bHU6bHU=");
        return new RequestOptions({ headers: headers });
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

