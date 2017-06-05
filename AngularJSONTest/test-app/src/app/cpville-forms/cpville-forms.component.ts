import { Component, OnInit } from '@angular/core';

import { Http, Headers, RequestOptions } from '@angular/http';

import { Cpville }    from '../model/cpville';
import { CpvilleService } from '../services/cpville.service';

import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'cpville-form',
  templateUrl: './cpville-forms.component.html',
  styleUrls: ['./cpville-forms.component.css']
})


export class CpvilleFormComponent implements OnInit {

    model = new Cpville('34577', 'Somewhere');

    submitted = false;

    cpvilles: Cpville[];

    constructor(
        private http: Http, 
        private cpvilleService: CpvilleService 
        ) { 
        }


    getCpvilleList(): void {
        this.cpvilleService.getCpvilleList()
        .then(cpvilles => this.cpvilles = cpvilles);
        console.log(this.cpvilles);
    }

    onSubmit() { 
        this.submitted = true;

        let headers = new Headers({ 'Content-Type': 'application/json' });

        let options = new RequestOptions({ headers: headers });
        
        this.http.post("http://localhost:8080/bankProjectWeb/rs/cpville/", JSON.stringify(this.model), options)
            .subscribe(
                data => console.log("success!", data),
                error => console.error("couldn't post because", error)
            );

        console.log(this.model.city);
    }

    newCpville() {
        this.model = new Cpville( '', '');
    }

    get diagnostic() { return JSON.stringify(this.model); }


    ngOnInit(){
        this.getCpvilleList();
        console.log(this.cpvilles);
        //this.http.get("http://localhost:8080/bankProjectWeb/rs/cpville/").toPromise().
          //  then(r => r.json()).then(r => this.cpvilles = r).catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred while fetching CpVille data from server: ', error); 
        return Promise.reject(error.message || error);
  }
}