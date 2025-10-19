package patron;

import java.util.*;
import book.Book;

public class Patron {
    private String id;
    private String name;
    private String email;
    private List<Book> borrowedBooks;
    private List<BorrowRecord> borrowHistory;

    public Patron(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<Book>();
        this.borrowHistory = new ArrayList<BorrowRecord>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<BorrowRecord> getBorrowHistory() {
        return borrowHistory;
    }

    public void addBorrowedBook(Book book) {
        if (book != null) {
            borrowedBooks.add(book);
            borrowHistory.add(new BorrowRecord(book, new Date()));
        }
    }

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }
}
