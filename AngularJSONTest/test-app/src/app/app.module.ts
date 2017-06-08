import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppRoutingModule } from './app-routing.module';

import { HeroService }          from './hero.service';
import { CpvilleService }          from './services/cpville.service';
import { OwnerService }          from './services/owner.service';

import{ TransactionService }   from './services/transaction.service';
import { AccountService }          from './services/account.service';
import { AccountTypeService }          from './services/accountType.service';
import { AgencyService }          from './services/agency.service';
import { BankService }          from './services/bank.service';
import { CountryCodeService }          from './services/countryCode.service';


import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { HeroesComponent }      from './heroes/heroes.component';
import { HeroDetailComponent }  from './hero-detail/hero-detail.component';
import { HeroSearchComponent }  from './hero-search/hero-search.component';
import { CpvilleFormComponent }  from './cpville-forms/cpville-forms.component';
import { CreateaccountComponent }  from './createaccount/createaccount.component';
import { TransactionComponent }  from './transaction/transaction.component';

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
    HeroDetailComponent,
    HeroesComponent,
    HeroSearchComponent,
    CpvilleFormComponent,
    CreateaccountComponent,
    TransactionComponent
  ],
  providers: [ HeroService, CpvilleService, OwnerService, 
              TransactionService, AccountService, BankService,
              AccountTypeService, AgencyService, CountryCodeService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
