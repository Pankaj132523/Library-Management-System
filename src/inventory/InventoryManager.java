package inventory;

import book.Book;
import java.util.*;

public class InventoryManager {
    private final Inventory inventory;
    private final Set<String> borrowedIsbns;

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
        this.borrowedIsbns = new HashSet<>();
    }

    public boolean checkoutBook(String isbn) {
        Book book = inventory.getAllBooksByISBN().get(isbn);
        if (book != null && !borrowedIsbns.contains(isbn)) {
            borrowedIsbns.add(isbn);
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        if (borrowedIsbns.contains(isbn)) {
            borrowedIsbns.remove(isbn);
            return true;
        }
        return false;
    }

    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book book : inventory.getAllBooks()) {
            if (!borrowedIsbns.contains(book.getIsbn())) {
                available.add(book);
            }
        }
        return available;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> borrowed = new ArrayList<>();
        for (String isbn : borrowedIsbns) {
            Book book = inventory.getAllBooksByISBN().get(isbn);
            if (book != null) {
                borrowed.add(book);
            }
        }
        return borrowed;
    }

    public int getAvailableCount() {
        int count = 0;
        for (Book book : inventory.getAllBooks()) {
            if (!borrowedIsbns.contains(book.getIsbn())) {
                count++;
            }
        }
        return count;
    }

    public int getBorrowedCount() {
        return borrowedIsbns.size();
    }
}
