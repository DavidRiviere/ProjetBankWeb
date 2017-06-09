import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

import { Hero }                from '../model/hero';


@Component({
  selector: 'my-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: [ './heroes.component.css' ]
})
export class HeroesComponent implements OnInit {

  constructor(
    private router: Router) { }

  ngOnInit(): void {
 
  }

}
