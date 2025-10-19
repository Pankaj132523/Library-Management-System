package inventory;
import book.Book;

import java.util.*;

public class Inventory {
    private Map<String,Book> allBooksByISBN;
    private Map<String ,List<Book>> allBooksByTitle;
    private Map<String,List<Book>> allBooksByAuthor;
    private List<Book> allBooks;

    public Inventory() {
        this.allBooksByISBN = new HashMap<>();
        this.allBooksByTitle = new HashMap<>();
        this.allBooksByAuthor = new HashMap<>();
        this.allBooks = new ArrayList<>();
        
    }

    public Inventory(List<Book> books) {
        this();
        for (Book book : books) {
            addBook(book);
        }
    }
    public void addBook(Book book) {
        allBooks.add(book);
        allBooksByISBN.put(book.getIsbn(), book);
        allBooksByTitle.computeIfAbsent(book.getTitle(), k -> new ArrayList<>()).add(book);
        allBooksByAuthor.computeIfAbsent(book.getAuthor(), k -> new ArrayList<>()).add(book);
    }

    public void removeBook(Book book) {
        allBooks.remove(book);
        allBooksByISBN.remove(book.getIsbn());
        List<Book> booksWithTitle = allBooksByTitle.get(book.getTitle());
        if (booksWithTitle != null) {
            booksWithTitle.remove(book);
            if (booksWithTitle.isEmpty()) {
                allBooksByTitle.remove(book.getTitle());
            }
        }
        List<Book> booksWithAuthor = allBooksByAuthor.get(book.getAuthor());
        if (booksWithAuthor != null) {
            booksWithAuthor.remove(book);
            if (booksWithAuthor.isEmpty()) {
                allBooksByAuthor.remove(book.getAuthor());
            }
        }
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public Map<String, Book> getAllBooksByISBN() {
        return allBooksByISBN;
    }

    public Map<String, List<Book>> getAllBooksByTitle() {
        return allBooksByTitle;
    }

    public Map<String, List<Book>> getAllBooksByAuthor() {
        return allBooksByAuthor;
    }

    
}
