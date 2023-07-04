import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../book.service';
import { Book } from '../models';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent  implements OnInit{
  activatedRoute = inject(ActivatedRoute)
bookSvc = inject(BookService)
books$ !: Observable<any>
character: string = ''
limit: number = 10
offset: number = 0


  ngOnInit(): void {
      const character = this.activatedRoute.snapshot.params['bookName']
      this.character = character
    this.books$ = this.bookSvc.getBooks(character, this.limit, this.offset)
    this.books$.subscribe(v => {
      console.info(v)
    })
  }

  prev(){
this.offset -= 10
this.books$ = this.bookSvc.getBooks(this.character, this.limit, this.offset)
this.books$.subscribe(v => {
  console.info(v)
})
  }

  next(){
this.offset += 10
this.books$ = this.bookSvc.getBooks(this.character, this.limit, this.offset)
this.books$.subscribe(v => {
  console.info(v)
})
  }
}
