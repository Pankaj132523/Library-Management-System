package lending;

import inventory.InventoryManager;
import patron.PatronManagerService;
import patron.Patron;
import book.Book;
import java.util.List;

public class LendingService {
    private final InventoryManager inventoryManager;
    private final PatronManagerService patronManagerService;

    public LendingService(InventoryManager inventoryManager, PatronManagerService patronManagerService) {
        this.inventoryManager = inventoryManager;
        this.patronManagerService = patronManagerService;
    }

    public boolean borrowBook(String patronId, String isbn) {
        Patron patron = patronManagerService.searchPatronById(patronId);
        if (patron == null) {
            return false;
        }
        Book bookToBorrow = null;
        List<Book> availableBooks = inventoryManager.getAvailableBooks();
        for (Book book : availableBooks) {
            if (book.getIsbn().equals(isbn)) {
                bookToBorrow = book;
                break;
            }
        }
        if (bookToBorrow == null) {
            return false;
        }
        boolean success = inventoryManager.checkoutBook(isbn);
        if (success) {
            patron.addBorrowedBook(bookToBorrow);
            return true;
        }
        return false;
    }

    public boolean returnBook(String patronId, String isbn) {
        Patron patron = patronManagerService.searchPatronById(patronId);
        if (patron == null) {
            return false;
        }
        Book bookToReturn = null;
        List<Book> borrowedBooks = patron.getBorrowedBooks();
        for (Book book : borrowedBooks) {
            if (book.getIsbn().equals(isbn)) {
                bookToReturn = book;
                break;
            }
        }
        if (bookToReturn == null) {
            return false;
        }
        boolean success = inventoryManager.returnBook(isbn);
        if (success) {
            patron.removeBorrowedBook(bookToReturn);
            return true;
        }
        return false;
    }
}
