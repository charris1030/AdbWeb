import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OurWorkComponent } from './our-work/our-work.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'our-work',
    component: OurWorkComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
      path: 'about',
      component: AboutComponent
  },
  {
     path: 'contact',
     component: ContactComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
