import java.util.Scanner;

public class Admin extends User {
    private Scanner scanner = new Scanner(System.in);

    public Admin(String username) {
        super(username);
    }

    @Override
    public void menu() {
        while (true) {
            System.out.println("\n========[ \033[31m Admiin Menu \033[0m]========\n");
            System.out.println("[1] Create Book");
            System.out.println("[2] Update Book");
            System.out.println("[3] Delete Book");
            System.out.println("[4] View Books");
            System.out.println("[5] Create Student");
            System.out.println("[6] Logout\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    viewBooks();
                    break;
                case 5:
                    createStudent();
                    break;
                case 6:
                    System.out.println("\nLogging out...");
                    return;
                default:
                    System.out.println("\nInvalid choice. Try again.");
            }
        }
    }

    private void createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        Book newBook = new Book(id, title, author);
        LibrarySystem.books.add(newBook);
        System.out.println("\nBook added: " + title);
    }

    private void updateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        for (Book book : LibrarySystem.books) {
            if (book.getId() == id) {
                System.out.print("Enter new Title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new Author: ");
                String newAuthor = scanner.nextLine();
              
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                System.out.println("\nBook updated.");
                return;
            }
        }
        System.out.println("\nBook not found.");
    }

    private void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Book bookToRemove = null;
        for (Book book : LibrarySystem.books) {
            if (book.getId() == id) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            LibrarySystem.books.remove(bookToRemove);
            System.out.println("\nBook deleted.");
        } else {
            System.out.println("\nBook not found.");
        }
    }

    private void viewBooks() {
        if (LibrarySystem.books.isEmpty()) {
            System.out.println("\nNo books available.");
        } else {
            for (Book book : LibrarySystem.books) {
                book.displayInfo();
            }
        }
    }

    private void createStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Student newStudent = new Student(studentId, username, password);
        LibrarySystem.students.add(newStudent);
        System.out.println("\nStudent created successfully: " + username);
    }
}
