package CustOp;

import Model.Book;

public interface CustOperation {
    void addToCart(Book book,int qty);
    double getBill();

}
