package mainUIpack;

import BookOpPack.BookOperationImpl;
import CustOp.CustOperationsImpl;
import Model.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class BookStoreMain {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        BookOperationImpl boi=new BookOperationImpl();
        while(true)
        {
            System.out.println("1.add the book"); //admin
            System.out.println("2.list all the books"); //admin customer
            System.out.println("3.get a book"); //admin customer
            System.out.println("4.update a book price"); //admin
            System.out.println("5.Add to cart and billing"); //customer
            //System.out.println("6.billing");
            System.out.println("7.exit");

            System.out.println("enter ur choice");
            int choice=sc.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.println("enter the details of the book");
                    int bookId=sc.nextInt();
                    String bookName=sc.next();
                    int bookPrice=sc.nextInt();
                    Book book=new Book(bookId,bookName,bookPrice);

                    System.out.println(boi.addABook(book));
                    break;
                }
                case 2:
                {
                    ArrayList<Book> blist=boi.getAllBooks();
                    for(Book book:blist)
                    {
                        System.out.println(book);
                    }

                    break;
                }
                case 3:
                {
                    System.out.println("enter the book id to be searched");
                    int bookId=sc.nextInt();
                    Book book =boi.getABook(bookId);
                    if(book!=null)
                    {
                        System.out.println(book);
                    }
                    else
                    {
                        System.out.println("book doesn't found in the store");
                    }
                    break;

                }
                case 4:
                {
                    System.out.println("enter the bookId");
                    int bookId=sc.nextInt();
                    System.out.println("enter the updated price of the book");
                    int upPrice=sc.nextInt();
                    System.out.println(boi.setBookPrice(bookId,upPrice));
                    System.out.println("updated book list is ");
                   ArrayList<Book> blist=boi.getAllBooks();
                   for(Book b:blist)
                   {
                       System.out.println(b);
                   }
                    break;
                }
                case 5:
                {
                    System.out.println();
                    CustOperationsImpl cust=new CustOperationsImpl();
                    while(true)
                    {

                        String ch="Y";
                        System.out.println("enter the book to add to the cart");
                        int bookId= sc.nextInt();
                        System.out.println("enter the no of copies");
                        int qty=sc.nextInt();
                        Book book=boi.getABook(bookId);
                        cust.addToCart(book,qty);
                        System.out.println("do u want to continue dding");
                        ch=sc.next();
                        if(ch.charAt(0)=='N')
                            break;
                    }

                    System.out.println("bill amount"+cust.getBill());
                    break;
                }

                default:
                    sc.close();
                    System.out.println("bye bye!!!");
                    System.exit(0);

            }
        }
    }
}
