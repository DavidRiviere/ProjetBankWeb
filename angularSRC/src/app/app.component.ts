import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'CpVille';

  cpvilles = [{id:"1",zip:"34750",city:"vergeze"}];

  constructor(private http:Http) {}

  ngOnInit(){
    this.http.get("http://localhost:8080/bankProjectWeb/rs/cpville/").toPromise().then(r => r.json()).then(r => this.cpvilles = r);
  }
}
