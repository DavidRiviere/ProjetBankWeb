import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    constructor(private http: Http) { }

    login(username: string, password: string) {
        return this.http.post('/api/authenticate', JSON.stringify({ login: username, pswd: password }))
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