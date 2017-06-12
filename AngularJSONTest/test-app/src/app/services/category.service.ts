import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Category } from '../model/category';

@Injectable()
export class CategoryService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private headersGet = new Headers({'Accept': 'text/plain'});
  private url = 'https://localhost:8443/bankProjectWeb/rs/category/';  // URL to web api

  constructor(private http: Http) { }

  getCategoryList(): Promise<Category[]> {
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json() as Category[])
               .catch(this.handleError);
  }

  getCategoryById(id: number): Promise<Category> {
    const urla = `${this.url}${id}`;
    return this.http.get(urla)
      .toPromise()
      .then(response => response.json() as Category)
      .catch(this.handleError);
  }

  deleteCategoryId(id: number): Promise<void> {
    const url = `${this.url}${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  createCategory(category: Category): Promise<any> {
    return this.http
      .post(this.url, JSON.stringify(category), {headers: this.headers})
      .toPromise()
      .then(res=>this.http.get(res.url).toPromise().then(response => response.json() as Category))
      .catch(this.handleError);
  }
  

  updateCategory(category: Category): Promise<Category> {
    const url = `${this.url}/${category.id}`;
    return this.http
      .put(url, JSON.stringify(category), {headers: this.headers})
      .toPromise()
      .then(() => category)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

