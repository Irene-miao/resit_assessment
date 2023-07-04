export interface Book {
bookId: string
     title: string
    authors: string
   description: string
     format: string
 pages: number
    rating: number
     ratingCount: number
reviewCount: number
     genres: string
     imagUrl: string
}


export interface Review {
    book_author: string
    book_title: string
    byline: string
    isbn13: string
    publication_dt: string
    summary: string
    uri: string
    url: string
    uuid: string
}