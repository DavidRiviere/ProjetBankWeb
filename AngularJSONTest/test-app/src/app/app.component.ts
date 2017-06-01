import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

import { Cpville }    from './cpville';

import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'CpVille';

  cpvilles;

  constructor(private http:Http) {}

  private handleError(error: any): Promise<any> {
    console.error('An error occurred while fetching CpVille data from server: ', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  ngOnInit(){
    this.http.get("http://localhost:8080/bankProjectWeb/rs/cpville/").toPromise().
    then(r => r.json()).then(r => this.cpvilles = r).catch(this.handleError);;
  }
}
