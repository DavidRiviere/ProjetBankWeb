import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { Http, Headers, RequestOptions } from '@angular/http';

import { Account }    from '../model/account';
import { Cpville }    from '../model/cpville';

import { CountryCode }    from '../model/countryCode';
import { AccountType }    from '../model/accountType';
import { Agency }    from '../model/agency';
import { Owner }    from '../model/owner';
import { Bank }    from '../model/bank';

import { OwnerService } from '../services/owner.service';
import { AccountService } from '../services/account.service';

import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'createaccount-form',
  templateUrl: './createaccount.component.html',
  styleUrls: ['./createaccount.component.css']
})


export class CreateaccountComponent implements OnInit {
    
    timestamp = new Date(); 
    model : Account = new Account();
    submitted = false;

    createdAccount : Account ;

    accountTypeList: AccountType[];
    agencyList : Agency[];
    countryCodeList : CountryCode[];
    bankList : Bank[];
    ownerList : Owner[];

    constructor(private http: Http,
        private ownerservice : OwnerService,
        private accountService : AccountService,
        private router : Router) { }

    newAccount() {
        this.submitted = false;
        this.model = new Account();
    }

    onSubmit() { 
        this.submitted = true;
        this.model.creationDate = this.timestamp.toString().slice(0,10).replace(/-/g,"")+"000000+0200";
        this.accountService.createAccount(this.model).then(createdAccount => this.createdAccount = createdAccount);;
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

    goToTransactionList(){

            //let link = ['/detail', hero.id];
            let link = ['/transactions', this.createdAccount.id]
            this.router.navigate(link);
    }   

    private handleError(error: any): Promise<any> {
        console.error('An error occurred while fetching data from server: ', error); 
        return Promise.reject(error.message || error);
  }

}