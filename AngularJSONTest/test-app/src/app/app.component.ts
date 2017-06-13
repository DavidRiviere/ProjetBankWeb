import { Component }          from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'my-app',
  template: `
    <h1>Money bank manager</h1>
    <nav>
      <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
      <a routerLink="/cpvilleform" routerLinkActive="active">Cpvilleform</a>
      <a routerLink="/createaccountform" routerLinkActive="active">Create Account Form</a>
    </nav>
   <alert></alert>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {

}
