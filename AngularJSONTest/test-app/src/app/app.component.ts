import { Component }          from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
    <h1>Money bank</h1>
    <nav>
      <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
      <a routerLink="/heroes" routerLinkActive="active">Heroes</a>
      <a routerLink="/cpvilleform" routerLinkActive="active">Cpvilleform</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {

}
