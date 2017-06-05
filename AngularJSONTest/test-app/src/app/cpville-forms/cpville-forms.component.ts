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

    model = new Cpville('', '');

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
        console.log(JSON.stringify(this.cpvilles));
    }

    onSubmit() { 
        this.submitted = true;
        this.cpvilleService.createCpville(this.model.city, this.model.zip);       
    }

    deleteCpville () {
        this.cpvilleService.deleteCpvilleId(this.model.id);
    }

    newCpville() {
        this.model = new Cpville( '', '');
    }

    ngOnInit(){
        this.getCpvilleList();
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred while fetching CpVille data from server: ', error); 
        return Promise.reject(error.message || error);
  }
}