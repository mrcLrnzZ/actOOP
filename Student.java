import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User {
    private int studentId;
    private String password;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Student(int studentId, String username, String password) {
        super(username);
        this.studentId = studentId;
        this.password = password;
    }

    public boolean login(int id, String password) {
        return this.studentId == id && this.password.equals(password);
    }

    @Override
    public void menu() {
        System.out.println("\nWelcome, " + super.getUsername() + "!");
        while (true) {
            System.out.println("\n========[ \033[32m Student Menu \033[0m ]========\n");
            System.out.println("[1] View Available Books");
            System.out.println("[2] Borrow Book");
            System.out.println("[3] Return Book");
            System.out.println("[4] Logout\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAvailableBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    System.out.println("\nLogging out...");
                    return;
                default:
                    System.out.println("\nInvalid choice. Try again.");
            }
        }
    }

    private void viewAvailableBooks() {
        boolean foundAvailable = false;
        for (Book book : LibrarySystem.books) {
            if (!book.isBorrowed()) {
                book.displayInfo();
                foundAvailable = true;
            }
        }

        if (!foundAvailable) {
            System.out.println("\nNo available books at the moment.");
        }
    }

    private void borrowBook() {
        System.out.print("\nEnter Book ID to borrow: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        boolean bookFound = false;
        for (Book book : LibrarySystem.books) {
            if (book.getId() == bookId && !book.isBorrowed()) {
                book.borrow();
                borrowedBooks.add(book);
                System.out.println("\nYou have borrowed: " + book.getTitle());
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            System.out.println("\nBook not found or is already borrowed.");
        }
    }

    private void returnBook() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("\nYou have no borrowed books.");
            return;
        }

        System.out.println("\nYour borrowed books:");
        for (Book book : borrowedBooks) {
            book.displayInfo();
        }

        System.out.print("\nEnter Book ID to return: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); 

        boolean bookFound = false;
        for (Book book : borrowedBooks) {
            if (book.getId() == bookId) {
                book.returnBook();
                borrowedBooks.remove(book); 
                System.out.println("\nYou have returned: " + book.getTitle());
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            System.out.println("\nYou didn't borrow this book or it doesn't exist.");
        }
    }
}
