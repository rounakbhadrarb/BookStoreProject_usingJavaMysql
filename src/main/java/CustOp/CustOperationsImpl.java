package CustOp;

import BookOpPack.DBManager;
import Model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustOperationsImpl implements CustOperation {

    private static int count=1;
    private final int tid;
    private ArrayList<Book> slist=new ArrayList<Book>();
    private Map<Integer,Integer> smap=new HashMap<Integer,Integer>();
    public CustOperationsImpl() {
        this.tid = count++;
    }

    public int getTid() {
        return tid;
    }

    @Override
    public void addToCart(Book book,int qty) {
        slist.add(book);
        smap.put(book.getBookId(),book.getBookPrice()*qty);


    }

    @Override
    public double getBill() {
        int totamt=0;
        for (Integer key: smap.keySet())
        {
            totamt=smap.get(key);
        }
        PreparedStatement ps=null;
        Connection con = DBManager.getConnection();
        String str="insert into custtransaction values(?,?)";
        try
        {
            ps=con.prepareStatement(str);
            ps.setInt(1,tid);
            ps.setDouble(2,totamt);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return totamt;
    }
}
