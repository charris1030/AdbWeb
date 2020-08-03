import { Component } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Router} from '@angular/router';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent {

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) {}

  routeToContact() {
    this.router.navigate(['/contact']);
  }

}
