import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CpvilleFormComponent }  from './cpville-forms/cpville-forms.component';
import { CreateaccountComponent }  from './createaccount/createaccount.component';

const routes: Routes = [
  { path: 'cpvilleform',     component: CpvilleFormComponent },
  { path: 'createaccountform',     component: CreateaccountComponent }

];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
