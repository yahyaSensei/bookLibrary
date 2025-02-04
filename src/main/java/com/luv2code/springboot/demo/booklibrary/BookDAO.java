package com.luv2code.springboot.demo.booklibrary;

import java.util.List;

public interface BookDAO {
    public Books getBookByID(int bookId);
    public List<Books> getBooksByAuthor(String author);
    public List<Books> getAllBooks();
    public boolean addBook(Books book);
    public boolean updateBook(int bookId, String bookName);
    public boolean deleteBook(int bookId);

}
