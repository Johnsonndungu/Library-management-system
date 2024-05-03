import java.util.*;
 public class LibrarySystem {
   private static Map<String, Book> books = new HashMap<>();
   public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     int choice;
     do {
         System.out.println("\nLibrary System Menu:");
         System.out.println("1. Add Book(s)");
         System.out.println("2. Borrow Book(s)");
         System.out.println("3. Return Book(s)");
         System.out.println("4. Exit");
         System.out.print("Enter your choice: ");
         choice = scanner.nextInt();
         scanner.nextLine(); // Consume newline character
         switch (choice) {
                case 1:
                    addBooks(scanner);
                    break;
                case 2:
                    borrowBooks(scanner);
                    break;
                case 3:
                    returnBooks(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
    }
    } while (choice != 4);
    scanner.close();
}
private static void addBooks(Scanner scanner) {
    System.out.println("\nAdding Books:");
    System.out.print("Enter the title of the book: ");
    String title = scanner.nextLine();
    System.out.print("Enter the author of the book: ");
    String author = scanner.nextLine();
    System.out.print("Enter the quantity of the book: ");
    int quantity = scanner.nextInt();
    scanner.nextLine(); // Consume newline character
    if (books.containsKey(title)) {
        Book existingBook = books.get(title);
        existingBook.setQuantity(existingBook.getQuantity() + quantity);
        System.out.println("Quantity updated for existing book: " + title);
  } else {
        Book newBook = new Book(title, author, quantity);
        books.put(title, newBook);
        System.out.println("New book added: " + title);
}
}
private static void borrowBooks(Scanner scanner) {
      System.out.println("\nBorrowing Books:");
      System.out.print("Enter the title of the book to borrow: ");
      String title = scanner.nextLine();
      if (books.containsKey(title)) {
          Book book = books.get(title);
          System.out.print("Enter the number of books to borrow: ");
          int quantityToBorrow = scanner.nextInt();
          scanner.nextLine(); // Consume newline character
          if (quantityToBorrow <= book.getQuantity()) {
              book.setQuantity(book.getQuantity() - quantityToBorrow);
              System.out.println("Successfully borrowed " + quantityToBorrow + " book(s) of " + title);
          } else {
            System.out.println("Insufficient quantity. Available quantity: " + book.getQuantity());
}
    } else {
        System.out.println("Book not found in the library.");
}
}
private static void returnBooks(Scanner scanner) {
      System.out.println("\nReturning Books:");
      System.out.print("Enter the title of the book to return: ");
      String title = scanner.nextLine();
      if (books.containsKey(title)) {
          Book book = books.get(title);
          System.out.print("Enter the number of books to return: ");
          int quantityToReturn = scanner.nextInt();
          scanner.nextLine(); // Consume newline character
          book.setQuantity(book.getQuantity() + quantityToReturn);
          System.out.println("Successfully returned " + quantityToReturn + " book(s) of " + title);
    } else {
          System.out.println("Book not found in the library.");
            }
      }
}
