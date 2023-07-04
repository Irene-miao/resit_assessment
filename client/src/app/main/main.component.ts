import { Component, OnInit, inject } from '@angular/core';
import { BookService } from '../book.service';
import { Observable } from 'rxjs';
import { Book } from '../models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit{
value: string = ""
router = inject(Router)


process(data: string){
  console.info(data)
  this.value = data
this.router.navigate(['/list', this.value])
}
  ngOnInit(): void {
    
  }
}

