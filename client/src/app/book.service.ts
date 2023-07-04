import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from './models';

@Injectable({
  providedIn: 'root'
})
export class BookService {


  http = inject(HttpClient)
  
  
  constructor() { 

  }

  getBooks(value: string, limit: number, offset: number): Observable<any>{

    return this.http.get<any>(`/books?character=${value}&limit=${limit}&offset=${offset}`)
  }

  getBookByTitle(title: string, limit: number, offset: number): Observable<any>{

    return this.http.get<any>(`/book?title=${title}&limit=${limit}&offset=${offset}`)
  }

  getBookReview(title: string){

    return this.http.get<any>(`https://api.nytimes.com/svc/books/v3/reviews.json?title=${title}&api-key=QzRWOWc0KZHJlDVqxS1I6icgTdXFf3hO`)
  }
}
