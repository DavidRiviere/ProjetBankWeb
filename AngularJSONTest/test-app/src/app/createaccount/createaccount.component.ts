import { Component, OnInit } from '@angular/core';

import { Http, Headers, RequestOptions } from '@angular/http';

import { Account }    from '../model/account';

import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'createaccount-form',
  templateUrl: './createaccount.component.html',
  styleUrls: ['./createaccount.component.css']
})


export class CreateaccountComponent implements OnInit {

    timestamp = new Date();
    
    model = new Account('', this.timestamp.toISOString().slice(0,10).replace(/-/g,"")+"000000+0200", '', 0);

    submitted = false;


    cpvilles;

    constructor(private http: Http) { }

    newAccount() {
        this.model = new Account('', '', '', 0);
    }

    onSubmit() { 
        this.submitted = true;

        let headers = new Headers({ 'Content-Type': 'application/json' });

        let options = new RequestOptions({ headers: headers });

        this.model.creationDate = this.timestamp.toISOString().slice(0,10).replace(/-/g,"")+"000000+0200";

        this.http.post("http://localhost:8080/bankProjectWeb/rs/accounts/", JSON.stringify(this.model), options)
            .subscribe(
                data => console.log("success!", data),
                error => console.error("couldn't post because", error)
            );

    }

    ngOnInit(){

    }

}