import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SigninComponent} from "./signin/signin.component";
import {ChoiceComponent} from "./choice/choice.component";

const routes: Routes = [
  {path: '', component: SigninComponent},
  {path: 'choice', component: ChoiceComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
