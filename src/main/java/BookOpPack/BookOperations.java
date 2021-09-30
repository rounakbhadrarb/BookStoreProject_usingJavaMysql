package BookOpPack;

import Model.Book;

import java.util.ArrayList;

public interface BookOperations {
    String addABook(Book book);
    ArrayList<Book> getAllBooks();
    Book getABook(int bookId);
    String setBookPrice(int bookId,int upPrice);
}
