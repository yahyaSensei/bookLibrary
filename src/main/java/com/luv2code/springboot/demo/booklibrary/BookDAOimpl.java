package com.luv2code.springboot.demo.booklibrary;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDAOimpl implements BookDAO {
    private EntityManager entityManager;
    @Autowired
    public BookDAOimpl(EntityManager entityManager) {
      this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public boolean addBook(Books book) {
        this.entityManager.persist(book);
        if (book.getAuthor() != null) {
            return true;
        }
        return false;
    }

    @Override
    public Books getBookByID(int bookId){
        return this.entityManager.find(Books.class, bookId);
    }

    @Override
    public List<Books> getBooksByAuthor(String author) {
        return this.entityManager.createQuery("FROM Books WHERE author='"+author+"'", Books.class).getResultList();
    }
    @Override
    @Transactional
    public boolean updateBook(int bookId, String bookName){
        Books oldBook = getBookByID(bookId);
        oldBook.setBookName(bookName);
        entityManager.merge(oldBook);
        return true;
    }
@Transactional
    public boolean deleteBook(int bookId){
        Books oldBook = getBookByID(bookId);
        entityManager.remove(oldBook);
        if (oldBook != null) {
            return true;
        }
        return false;
    }
    @Override
    public List<Books> getAllBooks(){
        return this.entityManager.createQuery("FROM Books", Books.class).getResultList();
    }

}
