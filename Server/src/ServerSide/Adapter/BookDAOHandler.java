package ServerSide.Adapter;

import Shared.SharedObjects.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDAOHandler implements BookDAO
{
  private Connection connection;

  public BookDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  protected List<Book> convertBooks(ResultSet resultSet)
  {
    ArrayList<Book> bookList = new ArrayList<>();
    try
    {
      while (resultSet.next())
      {
        int id = resultSet.getInt("item_id");
        String title = resultSet.getString("book_title");
        int year = resultSet.getInt("yearofpublishing");
        int publishedBy = resultSet.getInt("published_by");
        Book book = new Book(id, title, year, publishedBy);
        bookList.add(book);
      }
      return bookList;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<Book> getBooks()
  {
    try
    {
      String querysql = "SELECT BOOK.* FROM BOOK";
      PreparedStatement querySelectCustomer = connection.prepareStatement(querysql);
      ResultSet resultSet = querySelectCustomer.executeQuery();

      ArrayList<Book> getBooks = (ArrayList<Book>) convertBooks(resultSet);
      resultSet.close();
      querySelectCustomer.close();
      return getBooks;
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Book> searchBooks(String searchString)
  {
    try
    {
      String querysql = "SELECT BOOK.* FROM BOOK WHERE BOOK_TITLE LIKE ?";
      PreparedStatement search = connection.prepareStatement(querysql);
      search.setString(1, "%" + searchString + "%");
      ResultSet resultSet = search.executeQuery();

      ArrayList<Book> books = (ArrayList<Book>) convertBooks(resultSet);
      resultSet.close();
      search.close();
      return books;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
