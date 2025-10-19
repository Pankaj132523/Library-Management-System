# 📚 Library Management System

A comprehensive Java-based Library Management System demonstrating object-oriented design principles, clean architecture, and service-oriented patterns. This system manages books, patrons, lending operations, and payment processing.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design_Patterns-Facade_|_Strategy-blue)
![Status](https://img.shields.io/badge/Status-Active-success)

---

## 📋 Table of Contents

- [Features](#-features)
- [System Architecture](#-system-architecture)
- [Prerequisites](#-prerequisites)
- [Installation & Setup](#-installation--setup)
- [How to Run](#-how-to-run)
- [Usage Examples](#-usage-examples)
- [Project Structure](#-project-structure)
- [Class Diagram](#-class-diagram)
- [Design Patterns](#-design-patterns)
- [Technologies Used](#-technologies-used)
- [Contributing](#-contributing)

---

## ✨ Features

### 📖 Book Management
- Add and remove books from inventory
- Track book availability and borrowing status
- Multiple indexing (by ISBN, title, and author)

### 🔍 Search Functionality
- Search books by ISBN (exact match)
- Search books by title (partial match)
- Search books by author (partial match)

### 👥 Patron Management
- Register new patrons with payment verification
- Search patrons by ID or name
- Track patron borrowing history
- Remove patrons from the system

### 📑 Lending Service
- Borrow books with validation
- Return books with automatic status updates
- Track currently borrowed books per patron
- View borrowing history with timestamps

### 💳 Payment Processing
- **Cash Payment**: Direct cash payment processing
- **UPI Payment**: Digital UPI payment with UPI ID verification
- Extensible payment system for adding new payment methods

### 📊 Reporting
- View all available books
- View all borrowed books
- View patron-specific borrowed books
- Get availability and borrowing statistics

---

## 🏗️ System Architecture

The system follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────────────┐
│              Main (Entry Point)             │
└───────────────────┬─────────────────────────┘
                    │
┌───────────────────▼─────────────────────────┐
│           Library (Facade Layer)            │
│  Provides unified interface to all services │
└─────┬─────────┬──────────┬─────────┬────────┘
      │         │          │         │
┌─────▼───┐ ┌──▼────────┐ ┌▼────────▼────┐ ┌─▼──────────────┐
│ Search  │ │ Inventory │ │   Patron     │ │    Lending     │
│ Service │ │  Manager  │ │   Manager    │ │    Service     │
└─────┬───┘ └──┬────────┘ └┬────────┬────┘ └────────────────┘
      │        │            │        │
┌─────▼────────▼────────────▼───┐    │
│         Inventory             │    │
│   (Central Book Repository)   │    │
└───────────────────────────────┘    │
                                     │
                        ┌────────────▼─────────┐
                        │   Payment System     │
                        │  (Strategy Pattern)  │
                        └──────────────────────┘
```

---

## 📦 Prerequisites

Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK)** 8 or higher
  ```bash
  java -version
  javac -version
  ```

> **Note**: You need the full JDK (not just JRE) to compile the source code. 
> 
> If you only have JRE installed, install JDK:
> ```bash
> # On Ubuntu/Debian
> sudo apt update
> sudo apt install openjdk-21-jdk
> 
> # On macOS
> brew install openjdk@21
> 
> # On Windows
> # Download from: https://adoptium.net/
> ```

---

## 🚀 Installation & Setup

### Quick Start (Automatic Setup)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Library-Management-System
   ```

2. **Run the application** (JDK will be installed automatically if needed)
   ```bash
   ./run.sh
   ```

   If JDK is not installed, the script will:
   - Detect your operating system (Ubuntu/Debian/Fedora/CentOS/macOS)
   - Ask if you want to install JDK
   - Install it automatically with the appropriate package manager
   - Continue with compilation and execution

### Manual Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Library-Management-System
   ```

2. **Install JDK manually** (if needed)
   ```bash
   # Ubuntu/Debian
   sudo apt install openjdk-21-jdk
   
   # Fedora
   sudo dnf install java-21-openjdk-devel
   
   # macOS
   brew install openjdk@21
   ```

3. **Verify the project structure**
   ```bash
   ls -la src/
   ```

You should see the following directories:
- `book/`
- `inventory/`
- `lending/`
- `library/`
- `patron/`
- `paymentsService/`
- `searchService/`

---

## ▶️ How to Run

### Option 1: Using the Run Script (Recommended) ⭐

The easiest way to compile and run the project:

```bash
./run.sh
```

This script will:
- ✅ Check if Java and javac are installed
- ✅ **Automatically offer to install JDK if missing** (Ubuntu/Debian/Fedora/macOS)
- ✅ Compile all source files
- ✅ Run the application
- ✅ Display helpful error messages if something goes wrong

**No manual JDK installation needed!** The script handles everything for you.

### Option 2: Compile and Run (Single Command)

```bash
find src -name '*.java' | xargs javac && java -cp src Main
```

This command will:
1. Find all `.java` files in the `src` directory
2. Compile them using `javac`
3. Run the `Main` class

### Alternative: Step-by-Step Compilation

```bash
# Compile all Java files
javac src/book/*.java
javac src/inventory/*.java
javac src/patron/*.java
javac src/paymentsService/*.java
javac src/searchService/*.java
javac src/lending/*.java
javac src/library/*.java
   javac src/Main.java

# Run the application
   java -cp src Main
```

### Expected Output

When you run the application successfully, you should see output similar to:

```
John Doe  added successfully with Cash
Jane Smith  added successfully with UPI
Search by ISBN 111: [Title=Java Basics, Author=Alice, ISBN=111, Year=2020]
Search by Title 'Python': [Title=Python Essentials, Author=Bob, ISBN=222, Year=2021]
Search by Author 'Charlie': [Title=C++ Primer, Author=Charlie, ISBN=333, Year=2019]
Available books: [Title=Java Basics, Author=Alice, ISBN=111, Year=2020, Title=Python Essentials, Author=Bob, ISBN=222, Year=2021, Title=C++ Primer, Author=Charlie, ISBN=333, Year=2019]
Borrowed books: []
P1 borrowed books: []
Borrow book 111 for P1: true
Borrow book 222 for P2: true
Return book 111 for P1: true
Available books after return: [Title=Java Basics, Author=Alice, ISBN=111, Year=2020, Title=C++ Primer, Author=Charlie, ISBN=333, Year=2019]
P1 borrowed books after return: []
```

---

## 💡 Usage Examples

The `Main.java` demonstrates all key features:

### 1. **Creating Books and Inventory**
```java
Book book1 = new Book("111", "Java Basics", "Alice", 2020);
List<Book> books = new ArrayList<>();
books.add(book1);
Inventory inventory = new Inventory(books);
```

### 2. **Registering Patrons with Payment**
```java
Patron patron1 = new Patron("P1", "John Doe", "john@example.com");
CashPayment payment = new CashPayment(100.0, patron1.getId());
patronManagerService.addPatron(patron1, payment);
```

### 3. **Searching for Books**
```java
// Search by ISBN
Book foundBook = library.searchByISBN("111");

// Search by title (partial match)
List<Book> titleBooks = library.searchByTitle("Python");

// Search by author
List<Book> authorBooks = library.searchByAuthor("Charlie");
```

### 4. **Borrowing and Returning Books**
```java
// Borrow a book
boolean success = library.borrowBook("P1", "111");

// Return a book
boolean returned = library.returnBook("P1", "111");
```

### 5. **Viewing Reports**
```java
// Get all available books
List<Book> availableBooks = library.getAvailableBooks();

// Get patron's borrowed books
List<Book> patronBooks = library.getPatronBorrowedBooks("P1");
```

---

## 📁 Project Structure

```
Library-Management-System/
├── README.md                          # Project documentation
├── CLASS_DIAGRAM.md                   # Architecture documentation
├── class-diagram.png                  # Visual class diagram (PNG)
├── class-diagram.svg                  # Visual class diagram (SVG)
├── run.sh                             # Convenience script to compile and run
│
└── src/
    ├── Main.java                      # Application entry point
    │
    ├── book/
    │   └── Book.java                  # Book entity class
    │
    ├── inventory/
    │   ├── Inventory.java             # Book repository with multiple indexes
    │   └── InventoryManager.java      # Manages checkout/return operations
    │
    ├── patron/
    │   ├── Patron.java                # Patron entity class
    │   ├── BorrowRecord.java          # Borrowing history record
    │   └── PatronManagerService.java  # Patron CRUD operations
    │
    ├── paymentsService/
    │   ├── Payment.java               # Abstract payment base class
    │   ├── CashPayment.java           # Cash payment implementation
    │   └── UpiPayment.java            # UPI payment implementation
    │
    ├── searchService/
    │   └── SearchService.java         # Book search functionality
    │
    ├── lending/
    │   └── LendingService.java        # Coordinates borrow/return operations
    │
    └── library/
        └── Library.java               # Facade providing unified interface
```

---

## 🎨 Class Diagram

A comprehensive UML class diagram is available showing all classes, relationships, and interactions:

- **Visual Diagrams**: 
  - [`class-diagram.png`](class-diagram.png) - High-resolution PNG
  - [`class-diagram.svg`](class-diagram.svg) - Scalable vector format
  
- **Detailed Documentation**: [`CLASS_DIAGRAM.md`](CLASS_DIAGRAM.md)

### Quick Overview

```
Main
 └─► Library (Facade)
      ├─► InventoryManager ──► Inventory ──► Book
      ├─► SearchService ──► Inventory
      ├─► PatronManagerService ──► Patron ──► BorrowRecord
      └─► LendingService
           ├─► InventoryManager
           └─► PatronManagerService

Payment (Abstract)
 ├─► CashPayment
 └─► UpiPayment
```

---

## 🎯 Design Patterns

This project demonstrates several software design patterns:

### 1. **Facade Pattern** 🎭
- **Class**: `Library`
- **Purpose**: Provides a simplified interface to the complex subsystem of services
- **Benefits**: Reduces coupling, easier to use for clients

### 2. **Strategy Pattern** 💳
- **Classes**: `Payment` (abstract), `CashPayment`, `UpiPayment`
- **Purpose**: Allows different payment algorithms to be selected at runtime
- **Benefits**: Open for extension, closed for modification

### 3. **Service Layer Pattern** 🔧
- **Classes**: `SearchService`, `LendingService`, `PatronManagerService`
- **Purpose**: Encapsulates business logic in dedicated service classes
- **Benefits**: Clear separation of concerns, reusable business logic

### 4. **Repository Pattern** 📦
- **Class**: `Inventory`
- **Purpose**: Centralizes data access with multiple indexing strategies
- **Benefits**: Efficient queries, encapsulated data structure

### 5. **Single Responsibility Principle** ✅
- Each class has one well-defined responsibility
- `InventoryManager`: Tracks availability
- `SearchService`: Handles search operations
- `LendingService`: Manages lending workflow

---

## 🛠️ Technologies Used

- **Language**: Java 8+
- **Core Concepts**:
  - Object-Oriented Programming (OOP)
  - SOLID Principles
  - Design Patterns
  - Service-Oriented Architecture

---

## 📈 Future Enhancements

Potential improvements for the system:

- [ ] Add fine calculation for late returns
- [ ] Implement book reservation system
- [ ] Add database persistence (JDBC/JPA)
- [ ] Create REST API endpoints
- [ ] Add user authentication and authorization
- [ ] Implement logging framework (Log4j/SLF4J)
- [ ] Add unit tests (JUnit)
- [ ] Create web interface (Spring Boot + Thymeleaf)
- [ ] Add email notifications for due dates
- [ ] Support for multiple book copies with same ISBN

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 🐛 Troubleshooting

### Issue: `javac: command not found`

**Problem**: Only JRE is installed, not the full JDK.

**Solution**: Install the Java Development Kit:
```bash
sudo apt install openjdk-21-jdk
```

### Issue: `UnsupportedClassVersionError`

**Problem**: `.class` files were compiled with a newer Java version than your runtime.

**Solution**: Recompile the source code with your current Java version:
```bash
# Remove old .class files
find src -name '*.class' -delete

# Recompile with current Java version
find src -name '*.java' | xargs javac && java -cp src Main
```

### Issue: Compilation errors with package dependencies

**Problem**: Classes cannot find other classes in different packages.

**Solution**: Ensure you compile all files at once:
```bash
find src -name '*.java' | xargs javac
```

---

## 📄 License

This project is created for educational purposes to demonstrate low-level design principles and object-oriented programming concepts.

---

## 👨‍💻 Author

**Pankaj Sharma**

---

## 📞 Support

If you have any questions or run into issues, please open an issue in the repository.

---

<div align="center">

**⭐ Star this repository if you found it helpful! ⭐**

Made with ❤️ using Java

</div>
