package ServerSide.Adapter;

import Shared.SharedObjects.MyItem;
import Shared.SharedObjects.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HasBorrowedBookDAOHandler implements HasBorrowedBookDAO
{
  private Connection connection;

  public HasBorrowedBookDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  @Override public void hasBorrowed(long cpr, int id, LocalDate dateFrom,
      LocalDate dateDue)
  {
    try
    {
      String insertsql = "INSERT INTO HAS_BORROWEDBOOK VALUES(?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement insertHasBorrowedBook = connection.prepareStatement(insertsql);
      insertHasBorrowedBook.setLong(1, cpr);
      insertHasBorrowedBook.setInt(2, id);
      insertHasBorrowedBook.setDate(3, Date.valueOf(dateFrom));
      insertHasBorrowedBook.setDate(4, Date.valueOf(dateDue));
      insertHasBorrowedBook.setDate(5, Date.valueOf(dateDue));
      insertHasBorrowedBook.setInt(6, 0);
      insertHasBorrowedBook.setInt(7, 0);

      insertHasBorrowedBook.executeUpdate();
      insertHasBorrowedBook.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  protected List<Reservation> convertReservations(ResultSet resultSet)
  {
    ArrayList<Reservation> customerReservation = new ArrayList<>();
    try
    {
      while(resultSet.next())
      {
        long cpr = resultSet.getLong("cpr");
        int item_id = resultSet.getInt("item_id");
        LocalDate datefrom = LocalDate.parse(resultSet.getDate("datefrom").toString());
        LocalDate dateDue = LocalDate.parse(resultSet.getDate("datedue").toString());
        LocalDate dateTo = LocalDate.parse(resultSet.getDate("dateto").toString());
        int fine = resultSet.getInt("fine");
        int finePaid = resultSet.getInt("finepaid");
        Reservation reservation = new Reservation(cpr, item_id, datefrom, dateDue, dateTo, fine, finePaid);
        customerReservation.add(reservation);
      }
      return customerReservation;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<Reservation> getReservations(int id)
  {
    try
    {
      String querysql = "SELECT HAS_BORROWEDBOOK.* FROM HAS_BORROWEDBOOK WHERE ITEM_ID = ? ORDER BY(DATEFROM)";
      PreparedStatement selectReservations = connection.prepareStatement(querysql);
      selectReservations.setInt(1, id);
      ResultSet resultSet = selectReservations.executeQuery();

      ArrayList<Reservation> customerReservation = (ArrayList<Reservation>) convertReservations(resultSet);
      resultSet.close();
      selectReservations.close();
      return customerReservation;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  protected List<MyItem> convertMyBooks(ResultSet resultSet)
  {
    ArrayList<MyItem> myItems = new ArrayList<>();
    try
    {
      while(resultSet.next())
      {
        int id = resultSet.getInt("item_id");
        String title = resultSet.getString("book_title");
        LocalDate datefrom = LocalDate.parse(resultSet.getDate("datefrom").toString());
        LocalDate dateDue = LocalDate.parse(resultSet.getDate("datedue").toString());
        int fine = resultSet.getInt("fine");

        MyItem myItem = new MyItem(id, title,datefrom, dateDue, fine, "Book");
        myItems.add(myItem);
      }
      return myItems;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<MyItem> getMyBooks(long cpr)
  {
    try
    {
      String querysql = "SELECT HAS_BORROWEDBOOK.ITEM_ID, BOOK.BOOK_TITLE, HAS_BORROWEDBOOK.DATEFROM, HAS_BORROWEDBOOK.DATEDUE, HAS_BORROWEDBOOK.FINE FROM HAS_BORROWEDBOOK JOIN BOOK ON HAS_BORROWEDBOOK.ITEM_ID = BOOK.ITEM_ID WHERE HAS_BORROWEDBOOK.ITEM_ID = BOOK.ITEM_ID AND HAS_BORROWEDBOOK.CPR = ?;";
      PreparedStatement selectBooks = connection.prepareStatement(querysql);
      selectBooks.setLong(1, cpr);
      ResultSet resultSet = selectBooks.executeQuery();

      ArrayList<MyItem> myItems = (ArrayList<MyItem>) convertMyBooks(resultSet);
      resultSet.close();
      selectBooks.close();
      return myItems;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void delete(long cpr, int id)
  {
    try
    {
      String querysql = "DELETE FROM HAS_BORROWEDBOOK WHERE CPR = ? AND ITEM_ID = ?";
      PreparedStatement delete = connection.prepareStatement(querysql);
      delete.setLong(1, cpr);
      delete.setInt(2, id);
      delete.executeUpdate();
      delete.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void updateDates(long cpr, int id, LocalDate dateFrom,
      LocalDate dateDue)
  {
    try
    {
      String querysql = "UPDATE HAS_BORROWEDBOOK SET DATEFROM = ?, DATEDUE = ?, DATETO = ? WHERE CPR = ? AND ITEM_ID = ?";
      PreparedStatement update = connection.prepareStatement(querysql);
      update.setDate(1, Date.valueOf(dateFrom));
      update.setDate(2, Date.valueOf(dateDue));
      update.setDate(3, Date.valueOf(dateDue));
      update.setLong(4, cpr);
      update.setInt(5, id);
      update.executeUpdate();
      update.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  protected int convertCount(ResultSet resultSet)
  {
    try
    {
      while(resultSet.next())
      {
        int counts = resultSet.getInt("TOTAL");
        return counts;
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return 0;
  }

  @Override public int checkCount(long cpr)
  {
    try
    {
      String querysql = "SELECT COUNT(CPR) AS TOTAL FROM HAS_BORROWEDBOOK WHERE CPR = ?";
      PreparedStatement count = connection.prepareStatement(querysql);
      count.setLong(1, cpr);
      ResultSet resultSet = count.executeQuery();

      int countRows = convertCount(resultSet);
      resultSet.close();
      count.close();
      return countRows;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return 0;
  }

  @Override public ArrayList<Reservation> getReservations()
  {
    try
    {
      String querysql = "SELECT HAS_BORROWEDBOOK.* FROM HAS_BORROWEDBOOK ORDER BY(DATEFROM)";
      PreparedStatement selectReservations = connection.prepareStatement(querysql);
      ResultSet resultSet = selectReservations.executeQuery();

      ArrayList<Reservation> customerReservation = (ArrayList<Reservation>) convertReservations(resultSet);
      resultSet.close();
      selectReservations.close();
      return customerReservation;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

}
