import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
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

import { TransactionService } from '../services/transaction.service';
import { AccountService } from '../services/account.service';

import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'transactions',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})


export class TransactionComponent implements OnInit {

    model : Transaction = new Transaction();

    submitted = false;

    bankList : Bank[];
    accountList : Account[];
    transactionList : Transaction[];
    accountId: Account = new Account();

    balanceAccountId : string ;
    constructor(private http: Http,
        private transactionService : TransactionService,
        private accountService : AccountService,
        private route: ActivatedRoute) { }

    newTransaction() {
        this.model = new Transaction();
    }

    onSubmit() {
    }

    ngOnInit(): void{
        this.route.params
          .switchMap((params: Params) => this.transactionService.getTransactionList(+params['id']))
          .subscribe(transactionList => this.transactionList = transactionList);

        this.route.params
          .switchMap((params: Params) => this.accountService.getAccountById(+params['id']))
          .subscribe(res => this.accountId = res);

       /* this.route.params
          .switchMap((params: Params) =>  this.accountService.getAccountBalanceById(+params['id']))
          .subscribe(res => this.balanceAccountId = res);*/

           this.route.params
          .switchMap((params: Params) =>  
          (this.http.get('http://localhost:8080/bankProjectWeb/rs/account/'+params['id']+'/balance')
    .toPromise().then( response => response.text()))).subscribe(res => this.balanceAccountId = res);


         // this.http.get("http://localhost:8080/bankProjectWeb/rs/owner/").toPromise().
           // then(r => r.json()).then(r => this.ownerList = r).catch(this.handleError)
    }

    hack(val) {
        return Array.from(val);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred while fetching data from server: ', error); 
        return Promise.reject(error.message || error);
  }

}