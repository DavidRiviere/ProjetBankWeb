import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
 
import { AlertService, UserService } from '../services/index';

import {OwnerService} from '../services/owner.service';
import {AddressService} from '../services/address.service';
import {Account} from '../model/account';
import {Owner}  from '../model/owner';
import {Address} from '../model/address';
 
@Component({
    moduleId: module.id,
    templateUrl: 'register.component.html'
})
 
export class RegisterComponent {
    model: Owner = new Owner();
    //model: any = {};
    loading = false;

    newUser : boolean = true;
    salt : string = '';
    accounts : Array<Account>;
    addressesList : Address[];

    timestamp = new Date();
 
    constructor(
        private router: Router,
        private userService: UserService,
        private ownerService : OwnerService,
        private addressService: AddressService,
        private alertService: AlertService) {
            this.model.firstName = "lulu";
            this.model.email = 'lulu@lulu.lulu';
            this.model.phoneNumber = '0612121212';
            this.model.name = "lulu";
            this.model.login = "lulu";
            this.model.pswd = "lulu";
         }
 
    register() {
        this.model.birthday = this.timestamp.toString().slice(0, 10).replace(/-/g, "") + "000000+0200";
        this.loading = true;
        this.model.accounts = this.accounts;
        this.model.newUser = this.newUser;
        this.model.salt = this.salt;
        this.ownerService.createOwner(this.model)
            .then(
                data => {
                    // set success message and pass true paramater to persist the message after redirecting to the login page
                    this.alertService.success('Registration successful', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
       /* this.userService.create(this.model)
            .subscribe(
                data => {
                    // set success message and pass true paramater to persist the message after redirecting to the login page
                    this.alertService.success('Registration successful', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });*/
    }

ngOnInit() {
    this.addressService.getAddressList().then(r => this.addressesList = r);
}

}