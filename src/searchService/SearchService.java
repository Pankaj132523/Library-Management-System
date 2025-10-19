package searchService;

import book.Book;
import inventory.Inventory;

import java.util.*;

public class SearchService {
    private final Inventory inventory;

    public SearchService(Inventory inventory) {
        this.inventory = inventory;
    }

    public Book getBookByISBN(String isbn) {
        if (isbn == null) {
            return null;
        }
        return inventory.getAllBooksByISBN().get(isbn);
    }

    public List<Book> getBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        if (title == null) {
            return result;
        }
        String searchTitle = title.toLowerCase();
        for (Map.Entry<String, List<Book>> entry : inventory.getAllBooksByTitle().entrySet()) {
            String key = entry.getKey();
            if (key != null && key.toLowerCase().contains(searchTitle)) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        if (author == null) {
            return result;
        }
        String searchAuthor = author.toLowerCase();
        for (Map.Entry<String, List<Book>> entry : inventory.getAllBooksByAuthor().entrySet()) {
            String key = entry.getKey();
            if (key != null && key.toLowerCase().contains(searchAuthor)) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }
}
