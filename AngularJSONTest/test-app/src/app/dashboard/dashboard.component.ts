import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Router }            from '@angular/router';
import { Http, Headers, RequestOptions } from '@angular/http';

import { Account }    from '../model/account';
import { Owner }    from '../model/owner';

import { OwnerService } from '../services/owner.service';
import { AccountService } from '../services/account.service';
import { TransactionService } from '../services/transaction.service';

import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'my-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {

  ownerList: Owner[];
  allAccountList: Account[];
  ownerAccountList: Account[];

  ownerSelected = false;
  accountSelected = false;

  owner: Owner;
  account: Account;

  constructor(private http: Http,
    private ownerService: OwnerService,
    private accountService: AccountService,
    private route: Router) { }

  ngOnInit() {
    this.ownerService.getOwnerList().then(r => this.ownerList = r);
    this.accountService.getAccountList().then(r => this.allAccountList = r);
  }

  accountSelection(){
    this.ownerSelected = true;
    this.accountService.getAccountListById(this.owner.id).then(r => this.ownerAccountList = r);
  }

  submittedSelection(){
    this.accountSelected = true;  
  }
  getTransactionList(){
    let link = ['/account', this.account.id, 'transactions'];
    this.route.navigate(link);  
  }
}
