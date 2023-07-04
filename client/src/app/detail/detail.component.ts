import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../book.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit{
  activatedRoute = inject(ActivatedRoute)
  bookSvc = inject(BookService)
  book$ !: Observable<any>
  title: string = ''
limit: number = 10
offset: number = 0
authors: string = ''
genres: string = ''

  ngOnInit(): void {
    const title = this.activatedRoute.snapshot.params['title']
    this.title = title
    this.book$ = this.bookSvc.getBookByTitle(title, this.limit, this.offset)
    this.book$.subscribe(v => {
     
      const string = v.authors.replace('|', ',')
  
    this.authors = string
    const genres = v.genres.replaceAll('|', ',')
    
    this.genres = genres
    })
  }


}
