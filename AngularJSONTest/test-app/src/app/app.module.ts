import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppRoutingModule } from './app-routing.module';


import { CpvilleService }          from './services/cpville.service';
import { OwnerService }          from './services/owner.service';

import{ TransactionService }   from './services/transaction.service';
import { AccountService }          from './services/account.service';
import { AccountTypeService }          from './services/accountType.service';
import { AgencyService }          from './services/agency.service';
import { BankService }          from './services/bank.service';
import { CountryCodeService }          from './services/countryCode.service';
import { CategoryService }          from './services/category.service';
import { TransactionTypeService }          from './services/transactionType.service'
import {AddressService} from './services/address.service';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { CpvilleFormComponent }  from './cpville-forms/cpville-forms.component';
import { CreateaccountComponent }  from './createaccount/createaccount.component';
import { TransactionComponent }  from './transaction/transaction.component';

import { AlertComponent } from './alert/alert.component';
import { AuthGuard } from './guards/auth.guard';
import { AlertService, AuthenticationService, UserService } from './services/index';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';


import { DefaultRequestOptions} from './headers.request.options';

import { RequestOptions } from '@angular/http';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    CpvilleFormComponent,
    CreateaccountComponent,
    TransactionComponent,
    AlertComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent
  ],
  providers: [ AuthGuard,
        AlertService,
        AuthenticationService,
        UserService,
              CpvilleService, OwnerService, CategoryService,
              TransactionService, AccountService, BankService,
              AccountTypeService, AgencyService, CountryCodeService,
              TransactionTypeService, AddressService,
              {provide : RequestOptions, useClass : DefaultRequestOptions} ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
