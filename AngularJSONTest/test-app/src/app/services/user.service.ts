import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { Owner } from '../model/owner';

@Injectable()
export class UserService {

    private ownerUrl = 'https://localhost:8443/bankProjectWeb/rs/owner/';  // URL to web api

    constructor(private http: Http) {
    }

    getAll() {
        return this.http.get(this.ownerUrl, this.jwt()).map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get(this.ownerUrl + id, this.jwt()).map((response: Response) => response.json());
    }

    create(owner: Owner) {
        return this.http.post(this.ownerUrl, owner, this.jwt()).map((response: Response) => response.json());
    }

    update(owner: Owner) {
        return this.http.put(this.ownerUrl + owner.id, owner, this.jwt()).map((response: Response) => response.json());
    }

    delete(id: number) {
        return this.http.delete(this.ownerUrl + id, this.jwt()).map((response: Response) => response.json());
    }

    // private helper methods

    private jwt() {
        // create authorization header with jwt token
        let currentOwner = JSON.parse(localStorage.getItem('currentOwner'));
        if (currentOwner) {
            let headers = new Headers({ 'Content-Type': 'application/json' });
            headers.append("Authorization", "Basic " + btoa(currentOwner.login+':'+currentOwner.pswd));
            //let headers = new Headers({ 'Authorization': 'Bearer ' + currentOwner.token });
            return new RequestOptions({ headers: headers });
        }
    }
}