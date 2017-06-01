import { Component } from '@angular/core';
import { Http } from '@angular/http';

import { Cpville }    from './cpville';

import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'CpVille';

}
