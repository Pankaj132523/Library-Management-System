package library;

import book.Book;
import inventory.InventoryManager;
import searchService.SearchService;
import patron.Patron;
import patron.PatronManagerService;
import lending.LendingService;
import java.util.List;

public class Library {
    private final InventoryManager inventoryManager;
    private final SearchService searchService;
    private final PatronManagerService patronManagerService;
    private final LendingService lendingService;

    public Library(InventoryManager inventoryManager, SearchService searchService, PatronManagerService patronManagerService, LendingService lendingService) {
        this.inventoryManager = inventoryManager;
        this.searchService = searchService;
        this.patronManagerService = patronManagerService;
        this.lendingService = lendingService;
    }

    public void addBook(Book book) {
        inventoryManager.checkoutBook(book.getIsbn());
    }

    public void removeBook(String isbn) {
        Book book = searchService.getBookByISBN(isbn);
        if (book != null) {
            inventoryManager.returnBook(isbn);
        }
    }

    public Book searchByISBN(String isbn) {
        return searchService.getBookByISBN(isbn);
    }

    public List<Book> searchByTitle(String title) {
        return searchService.getBooksByTitle(title);
    }

    public List<Book> searchByAuthor(String author) {
        return searchService.getBooksByAuthor(author);
    }

    public boolean borrowBook(String patronId, String isbn) {
        return lendingService.borrowBook(patronId, isbn);
    }

    public boolean returnBook(String patronId, String isbn) {
        return lendingService.returnBook(patronId, isbn);
    }

    public List<Book> getAvailableBooks() {
        return inventoryManager.getAvailableBooks();
    }

    public List<Book> getBorrowedBooks() {
        return inventoryManager.getBorrowedBooks();
    }

    public List<Book> getPatronBorrowedBooks(String patronId) {
        Patron patron = patronManagerService.searchPatronById(patronId);
        if (patron != null) {
            return patron.getBorrowedBooks();
        }
        return null;
    }
}
