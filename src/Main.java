import book.Book;
import inventory.Inventory;
import inventory.InventoryManager;
import searchService.SearchService;
import patron.Patron;
import patron.PatronManagerService;
import lending.LendingService;
import library.Library;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("111", "Java Basics", "Alice", 2020);
        Book book2 = new Book("222", "Python Essentials", "Bob", 2021);
        Book book3 = new Book("333", "C++ Primer", "Charlie", 2019);

        List<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        Inventory inventory = new Inventory(books);
        InventoryManager inventoryManager = new InventoryManager(inventory);
        SearchService searchService = new SearchService(inventory);

        PatronManagerService patronManagerService = new PatronManagerService();
        Patron patron1 = new Patron("P1", "John Doe", "john@example.com");
        Patron patron2 = new Patron("P2", "Jane Smith", "jane@example.com");
        
        paymentsService.CashPayment cashPayment = new paymentsService.CashPayment(100.0, patron1.getId());
        paymentsService.UpiPayment upiPayment = new paymentsService.UpiPayment(100.0, patron2.getId(), "patron2@upi");

        patronManagerService.addPatron(patron1, cashPayment);
        patronManagerService.addPatron(patron2, upiPayment);

        LendingService lendingService = new LendingService(inventoryManager, patronManagerService);

        Library library = new Library(inventoryManager, searchService, patronManagerService, lendingService);

        Book foundBook = library.searchByISBN("111");
        if (foundBook != null) {
            System.out.println("Search by ISBN 111: [Title=" + foundBook.getTitle() + ", Author=" + foundBook.getAuthor() + ", ISBN=" + foundBook.getIsbn() + ", Year=" + foundBook.getPublicationYear() + "]");
        } else {
            System.out.println("Search by ISBN 111: No book found");
        }

        List<Book> titleBooks = library.searchByTitle("Python");
        System.out.print("Search by Title 'Python': [");
        for (int i = 0; i < titleBooks.size(); i++) {
            Book b = titleBooks.get(i);
            System.out.print("Title=" + b.getTitle() + ", Author=" + b.getAuthor() + ", ISBN=" + b.getIsbn() + ", Year=" + b.getPublicationYear());
            if (i < titleBooks.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

        List<Book> authorBooks = library.searchByAuthor("Charlie");
        System.out.print("Search by Author 'Charlie': [");
        for (int i = 0; i < authorBooks.size(); i++) {
            Book b = authorBooks.get(i);
            System.out.print("Title=" + b.getTitle() + ", Author=" + b.getAuthor() + ", ISBN=" + b.getIsbn() + ", Year=" + b.getPublicationYear());
            if (i < authorBooks.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

        List<Book> availableBooks = library.getAvailableBooks();
        System.out.print("Available books: [");
        for (int i = 0; i < availableBooks.size(); i++) {
            Book b = availableBooks.get(i);
            System.out.print("Title=" + b.getTitle() + ", Author=" + b.getAuthor() + ", ISBN=" + b.getIsbn() + ", Year=" + b.getPublicationYear());
            if (i < availableBooks.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

        List<Book> borrowedBooks = library.getBorrowedBooks();
        System.out.print("Borrowed books: [");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book b = borrowedBooks.get(i);
            System.out.print("Title=" + b.getTitle() + ", Author=" + b.getAuthor() + ", ISBN=" + b.getIsbn() + ", Year=" + b.getPublicationYear());
            if (i < borrowedBooks.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

        List<Book> p1Books = library.getPatronBorrowedBooks("P1");
        System.out.print("P1 borrowed books: [");
        for (int i = 0; i < p1Books.size(); i++) {
            Book b = p1Books.get(i);
            System.out.print("Title=" + b.getTitle() + ", Author=" + b.getAuthor() + ", ISBN=" + b.getIsbn() + ", Year=" + b.getPublicationYear());
            if (i < p1Books.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

        System.out.println("Borrow book 111 for P1: " + library.borrowBook("P1", "111"));
        System.out.println("Borrow book 222 for P2: " + library.borrowBook("P2", "222"));
        System.out.println("Return book 111 for P1: " + library.returnBook("P1", "111"));

        availableBooks = library.getAvailableBooks();
        System.out.print("Available books after return: [");
        for (int i = 0; i < availableBooks.size(); i++) {
            Book b = availableBooks.get(i);
            System.out.print("Title=" + b.getTitle() + ", Author=" + b.getAuthor() + ", ISBN=" + b.getIsbn() + ", Year=" + b.getPublicationYear());
            if (i < availableBooks.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

        p1Books = library.getPatronBorrowedBooks("P1");
        System.out.print("P1 borrowed books after return: [");
        for (int i = 0; i < p1Books.size(); i++) {
            Book b = p1Books.get(i);
            System.out.print("Title=" + b.getTitle() + ", Author=" + b.getAuthor() + ", ISBN=" + b.getIsbn() + ", Year=" + b.getPublicationYear());
            if (i < p1Books.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
