import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule }     from './app-routing.module';

import { AppComponent } from './app.component';
import { CpvilleFormComponent } from './cpville-forms.component'
import { SomewhereComponent } from './somewhere.component'

@NgModule({
  declarations: [
    AppComponent,
    CpvilleFormComponent,
    SomewhereComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 
  
}
