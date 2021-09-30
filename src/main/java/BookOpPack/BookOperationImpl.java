package BookOpPack;

import Model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookOperationImpl implements BookOperations {
    public static Connection con=null;

    @Override
    public String addABook(Book book) {
        /*blist.add(book);
        return "book added successfully";*/
        PreparedStatement ps=null;
        con=DBManager.getConnection();
        String str="insert into book values (?,?,?)";
        try
        {
            ps=con.prepareStatement(str);
            ps.setInt(1,book.getBookId());
            ps.setString(2,book.getBookName());
            ps.setInt(3,book.getBookPrice());
            ps.executeUpdate();
            return "1 row inserted:book inserted successfully";

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> blist=new ArrayList<Book>();
        con = DBManager.getConnection();
        try
        {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from book");
            while(rs.next())
            {
                Book book=new Book(rs.getInt(1),rs.getString(2),rs.getInt(3));
                blist.add(book);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return blist;
    }

    @Override
    public Book getABook(int bookId) {
       /* for(Book book:blist)
        {
            if(book.getBookId()==bookId)
                return book;
        }*/
        con=DBManager.getConnection();
        Book book=null;
        try
        {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from book where bookid="+bookId);
            while(rs.next())
            {
                book=new Book(rs.getInt(1),rs.getString(2),rs.getInt(3) );

            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return book;

    }

    @Override
    public String setBookPrice(int bookId, int upPrice) {
        /*Book book=getABook(bookId);
        book.setBookPrice(upPrice);
        return "Book price updated successfully";*/
        PreparedStatement ps=null;
        String str="update book set bookprice= ? where bookid= ?";
        con=DBManager.getConnection();
        try {
            ps=con.prepareStatement(str);
            ps.setInt(1,upPrice);
            ps.setInt(2,bookId);
            ps.executeUpdate();
            return "bookprice updated successfully. 1 row updated";
        } catch (SQLException e) {
            e.printStackTrace();
            return ("updation fail due to exception");
        }

    }
}
