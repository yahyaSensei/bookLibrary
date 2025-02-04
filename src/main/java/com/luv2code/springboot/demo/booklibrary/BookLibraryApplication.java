package com.luv2code.springboot.demo.booklibrary;
import java.util.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookLibraryApplication {


    public static void main(String[] args) {
        SpringApplication.run(BookLibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(BookDAO bookDAO) {
        return args -> {LibraryControl(bookDAO);};
    }

    private void LibraryControl(BookDAO bookDAO) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        do{
            System.out.println("hello this is library data base system what do you want to do?");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Get All Books");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Book NAME: ");

                    String bookName = scanner.next();

                    System.out.println("Enter Author: ");

                    String author = scanner.next();

                    System.out.println("Enter Book ISBN: ");

                    int bookISBN = scanner.nextInt();
                    if(bookDAO.addBook(new Books(bookName, author, bookISBN)))
                        System.out.println("Book added");
                    else
                        System.out.println("Book not added that is problem in the system");

                    System.out.println("do you want to exit? (y/n)");
                    String answer = scanner.next();
                    if(answer.equals("y"))
                        exit = true;
                    else break;
                case 2:
                    System.out.println("Eneter old book ID: ");
                    int oldBookID = scanner.nextInt();
                    System.out.println("new book name: ");
                    String newBookName = scanner.next();

                    if(bookDAO.updateBook(oldBookID,newBookName))
                        System.out.println("Book updated");
                    else System.out.println("Book not updated that is problem in the system");

                    System.out.println("do you want to exit? (y/n)");
                    String answer2 = scanner.next();
                    if(answer2.equals("y"))
                        exit = true;
                    else break;
                case 3:
                    System.out.println("Enter Book ID you want to delete: ");
                    int bookID = scanner.nextInt();
                    if(bookDAO.deleteBook(bookID))
                        System.out.println("Book deleted");
                    else System.out.println("Book not deleted that is problem in the system");
                    System.out.println("do you want to exit? (y/n)");
                    String answer3 = scanner.next();
                    if(answer3.equals("y"))
                        exit = true;
                    else break;
                case 4:
                    List<Books> allBooks=bookDAO.getAllBooks();

                    for (int i = 0; i < allBooks.size(); i++) {
                        System.out.println(allBooks.get(i));
                        System.out.println("================================");
                    }
                    System.out.println("that is all books list");

                    System.out.println("do you want to exit? (y/n)");
                    String answer4 = scanner.next();
                    if(answer4.equals("y"))
                        exit = true;
                    else break;
                    case 5:exit=true;

                    break;
                    default:
                        System.out.println("Please enter a valid choice");

            }
        }while(!exit);
    }

}
