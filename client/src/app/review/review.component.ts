import { Component, OnInit, inject } from '@angular/core';
import { BookService } from '../book.service';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Review } from '../models';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
bookSvc = inject(BookService)
review$!: Observable<any>
activatedRoute = inject(ActivatedRoute)
title: string = ''
review !: Review
copyright: string = ''


  ngOnInit(): void {
    const title = this.activatedRoute.snapshot.params['title']
    this.title = title
  
      this.review$ = this.bookSvc.getBookReview(title)
      this.review$.subscribe(v => {
     
        this.review = v.results[0]
  
        this.copyright = v.copyright
      
      })
  }
}
