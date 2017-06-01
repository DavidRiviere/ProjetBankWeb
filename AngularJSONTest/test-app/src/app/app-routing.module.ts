import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CpvilleFormComponent }  from './cpville-forms.component';
import { SomewhereComponent }  from './somewhere.component';

const routes: Routes = [
  { path: '', redirectTo: '/cpvilleForm', pathMatch: 'full' },
  { path: 'cpvilleForm',  component: CpvilleFormComponent },
  { path: 'somewhere',  component: SomewhereComponent },
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}