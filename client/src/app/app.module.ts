import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { ListComponent } from './list/list.component';
import { DetailComponent } from './detail/detail.component';
import { ReviewComponent } from './review/review.component';
import { BookService } from './book.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router';


const appRoutes: Routes = [
  { path:'', component: MainComponent, title: 'Main'},
  { path: 'list/:bookName', component: ListComponent, title: 'List'},
  { path: 'detail/:title', component: DetailComponent, title: 'Detail'},
  { path: 'review/:title', component: ReviewComponent, title: 'Review'},
  { path: '**', redirectTo: '/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    ListComponent,
    DetailComponent,
    ReviewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true})
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }
