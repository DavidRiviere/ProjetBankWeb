import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { User } from  '../model/user';

import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {

    user: User = new User();

    private headers = new Headers({'Content-Type': 'application/json'});
    constructor(private http: Http) { }

    login(username: string, password: string) {
        this.user.login = username;
        this.user.pswd = password;
        return this.http.post('https://localhost:8443/bankProjectWeb/login/', JSON.stringify(this.user),  {headers:this.headers})
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let owner = response.json();
                if (owner) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentOwner', JSON.stringify(owner));
                }
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentOwner');
    }
}