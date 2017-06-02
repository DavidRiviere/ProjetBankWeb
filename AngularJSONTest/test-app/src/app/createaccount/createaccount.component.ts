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

    model = new Account('descriptionAccount', new Date(1988, 3, 15), 'accountNumber', 12344);

    submitted = false;

    cpvilles;

    constructor(private http: Http) { }

    newAccount() {
        this.model = new Account('descriptionAccount', new Date(1988, 3, 15), 'accountNumber', 12344);
    }

    onSubmit() { 
        

        console.log(this.model.description);
        console.log(this.model.number);
        console.log(this.model.initialBalance);
        console.log(this.model.creationDate);

        /*this.submitted = true;

        let headers = new Headers({ 'Content-Type': 'application/json' });

        let options = new RequestOptions({ headers: headers });
        
        this.http.post("http://localhost:8080/bankProjectWeb/rs/account/", JSON.stringify(this.model), options)
            .subscribe(
                data => console.log("success!", data),
                error => console.error("couldn't post because", error)
            );

        */
    }

    ngOnInit(){

    }

}