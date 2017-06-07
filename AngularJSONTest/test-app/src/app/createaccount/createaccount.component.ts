import { Component, OnInit } from '@angular/core';

import { Http, Headers, RequestOptions } from '@angular/http';

import { Account }    from '../model/account';
import { Cpville }    from '../model/cpville';

import { CountryCode }    from '../model/countryCode';
import { AccountType }    from '../model/accountType';
import { Agency }    from '../model/agency';
import { Owner }    from '../model/owner';
import { Transaction }    from '../model/transaction';
import { Address }    from '../model/address';
import { Bank }    from '../model/bank';

import { TransactionType }    from '../model/transactionType';
import { Category }    from '../model/category';
import { TargetTransaction }    from '../model/targetTransaction';
import { PeriodicTransaction }    from '../model/periodicTransaction';
import { Frequency }    from '../model/frequency';

import { OwnerService } from '../services/owner.service';

import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'createaccount-form',
  templateUrl: './createaccount.component.html',
  styleUrls: ['./createaccount.component.css']
})


export class CreateaccountComponent implements OnInit {
    
    timestamp = new Date(); 
    /*cpVille = new Cpville("34567", "truc");
    bank = new Bank("bank", "code");
    address = new Address("la", "", this.cpVille);
    countryCode = new CountryCode('ess');
    agency = new Agency("ag", "guichet", this.bank, this.address);
    accountType = new AccountType('prout');
    
    owner = new Owner("jm","jm", this.timestamp.toISOString().slice(0,10).replace(/-/g,"")+"000000+0200",
    "qsd","salt","azeaze@azeae.aze", "0712121212", "login", this.address,
    true, []);
    owners = [this.owner];

    transactionType = new TransactionType("hebdoma");
    category = new Category ("az");
    targetTransaction = new TargetTransaction("sdqsd", "qsdqsd");

    frequency = new Frequency("qsd");

    periodicTransaction = new PeriodicTransaction(12, this.frequency, 
     this.timestamp.toISOString().slice(0,10).replace(/-/g,"")+"000000+0200");

    model2 = new Account();

    transaction = new Transaction('tr', 123, this.model2, this.transactionType,
    this.category, this.targetTransaction, this.periodicTransaction );
    transactions = [this.transaction];*/


    model : Account = new Account();
    submitted = false;


    accountTypeList: AccountType[];
    agencyList : Agency[];
    countryCodeList : CountryCode[];
    bankList : Bank[];
    transactionList : Transaction[];
    ownerList : Owner[];

    constructor(private http: Http,
        private ownerservice : OwnerService) { }

    newAccount() {
        this.submitted = false;
        this.model = new Account();
    }

    onSubmit() { 
        this.submitted = true;

        let headers = new Headers({ 'Content-Type': 'application/json' });

        let options = new RequestOptions({ headers: headers });

        this.model.creationDate = this.timestamp.toString().slice(0,10).replace(/-/g,"")+"000000+0200";

        this.http.post("http://localhost:8080/bankProjectWeb/rs/account/", JSON.stringify(this.model), options)
            .subscribe(
                data => console.log("success!", data),
                error => console.error("couldn't post because", error)
            );

    }

    ngOnInit(){
        
        this.http.get("http://localhost:8080/bankProjectWeb/rs/accounttype/").toPromise().
            then(r => r.json()).then(r => this.accountTypeList = r).catch(this.handleError);

        this.http.get("http://localhost:8080/bankProjectWeb/rs/agency/").toPromise().
            then(r => r.json()).then(r => this.agencyList = r).catch(this.handleError);

        this.http.get("http://localhost:8080/bankProjectWeb/rs/countryCode/").toPromise().
            then(r => r.json()).then(r => this.countryCodeList = r).catch(this.handleError);

        this.http.get("http://localhost:8080/bankProjectWeb/rs/bank/").toPromise().
            then(r => r.json()).then(r => this.bankList = r).catch(this.handleError);

        this.http.get("http://localhost:8080/bankProjectWeb/rs/owner/").toPromise().
            then(r => r.json()).then(r => this.ownerList = r).catch(this.handleError);
            
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred while fetching data from server: ', error); 
        return Promise.reject(error.message || error);
  }

}