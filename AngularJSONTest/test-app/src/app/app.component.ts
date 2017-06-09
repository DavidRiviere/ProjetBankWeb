import { Component }          from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
    <h1>Money bank manager</h1>
    <nav>
      <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
      <a routerLink="/heroes" routerLinkActive="active">Heroes</a>
      <a routerLink="/cpvilleform" routerLinkActive="active">Cpvilleform</a>
      <a routerLink="/createaccountform" routerLinkActive="active">Create Account Form</a>
      <a routerLink="/account/1/transactions" routerLinkActive="active">List of your transactions</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {

}
