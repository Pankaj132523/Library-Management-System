package patron;

import book.Book;
import java.util.Date;

public class BorrowRecord {
    private Book book;
    private Date borrowedDate;

    public BorrowRecord(Book book, Date borrowedDate) {
        this.book = book;
        this.borrowedDate = borrowedDate;
    }

    public Book getBook() {
        return book;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }
}
