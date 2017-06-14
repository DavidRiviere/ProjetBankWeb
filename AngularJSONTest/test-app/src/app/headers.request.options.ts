
import { Injectable } from '@angular/core';
import { Headers, BaseRequestOptions } from '@angular/http';


@Injectable()
export class DefaultRequestOptions extends BaseRequestOptions {
    headers = new Headers({
        'Accept': 'application/json',
        'X-Requested-By': 'Angular 2',
        "Authorization": "Basic bHU6bHU=",
    });

}