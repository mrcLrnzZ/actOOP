import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    static Admin admin = new Admin("admin");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        books.add(new Book(100, "The Kenji Jetpack", "Kenji Paras"));
        books.add(new Book(101, "Ang Isang kaibigan", "Sarah Duterte"));
        books.add(new Book(102, "Random books", "Randall"));

      
        students.add(new Student(1, "student1", "password1"));
        students.add(new Student(2, "student2", "password2"));

        while (true) {
            System.out.println("\n========[ \033[34m Library System Login \033[0m ]========\n");
            System.out.println("[1] Admin Login");
            System.out.println("[2] Student Login");
            System.out.println("[3] Exit\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.menu(); 
                    break;
                case 2:
                    studentLogin(scanner); 
                    break;
                case 3:
                    System.out.println("\nExiting system. Goodbye!");
                    return;
                default:
                    System.out.println("\nInvalid choice. Try again.");
            }
        }
    }

    private static void studentLogin(Scanner scanner) {
        System.out.print("\nEnter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();


        for (Student student : students) {
            if (student.login(studentId, password)) {
                student.menu(); 
                return;
            }
        }
        System.out.println("\nInvalid Student ID or Password.");
    }
}
