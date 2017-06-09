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
import { AccountTypeService } from '../services/accountType.service';
import { AgencyService }          from '../services/agency.service';
import { BankService }          from '../services/bank.service';
import { CountryCodeService }          from '../services/countryCode.service';



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

    notNewAccount = false;

    createdAccount : Account = new Account();

    accountList : Account[];
    accountTypeList: AccountType[];
    agencyList : Agency[];
    countryCodeList : CountryCode[];
    bankList : Bank[];
    ownerList : Owner[];

    constructor(private http: Http,
        private ownerService : OwnerService,
        private accountService : AccountService,
        private accountTypeService : AccountTypeService,
        private agencyService : AgencyService,
        private bankService : BankService,
        private countryCodeService : CountryCodeService,
        private router : Router) { }

    newAccount() {
        this.submitted = false;
        this.model = new Account();
    }

    onSubmit() { 

        this.notNewAccount = false;
        
        this.model.creationDate = this.timestamp.toString().slice(0,10).replace(/-/g,"")+"000000+0200";

        this.accountList.forEach(element => { 

            if (element.number == this.model.number) {
                this.notNewAccount = true;
                console.log(this.notNewAccount);
                console.log(element);
            }
            
        });

        console.log("foreach finished");

        if(this.notNewAccount == false ) {
            this.accountService.createAccount(this.model).then(model => this.createFunction(model));
            this.submitted = true; 
        }

    }

    createFunction(model : Account){
        this.createdAccount = model;
    }

    ngOnInit(){

        this.accountService.getAccountList().then(r => this.accountList = r);
        
        this.accountTypeService.getAccountTypeList().then(res => this.accountTypeList = res);

        this.agencyService.getAgencyList().then(res => this.agencyList = res);

        this.bankService.getBankList().then(res => this.bankList = res);

        this.ownerService.getOwnerList().then(r => this.ownerList = r);

        this.countryCodeService.getCountryCodeList().then(r => this.countryCodeList = r);    
    }

    goToTransactionList(){
            let link = ['/account', this.createdAccount.id, 'transactions'];
            this.router.navigate(link);
    }   

}