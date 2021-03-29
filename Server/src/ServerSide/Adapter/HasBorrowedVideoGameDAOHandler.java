package ServerSide.Adapter;

import Shared.SharedObjects.MyItem;
import Shared.SharedObjects.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HasBorrowedVideoGameDAOHandler implements HasBorrowedVideoGameDAO
{
  private Connection connection;

  public HasBorrowedVideoGameDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  @Override public void hasBorrowed(long cpr, int id, LocalDate dateFrom,
      LocalDate dateDue)
  {
    try
    {
      String insertsql = "INSERT INTO HAS_BORROWEDVIDEOGAME VALUES(?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement insertHasBorrowed = connection
          .prepareStatement(insertsql);
      insertHasBorrowed.setLong(1, cpr);
      insertHasBorrowed.setInt(2, id);
      insertHasBorrowed.setDate(3, Date.valueOf(dateFrom));
      insertHasBorrowed.setDate(4, Date.valueOf(dateDue));
      insertHasBorrowed.setDate(5, Date.valueOf(dateDue));
      insertHasBorrowed.setInt(6, 0);
      insertHasBorrowed.setInt(7, 0);

      insertHasBorrowed.executeUpdate();
      insertHasBorrowed.close();
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
      while (resultSet.next())
      {
        long cpr = resultSet.getLong("cpr");
        int item_id = resultSet.getInt("item_id");
        LocalDate datefrom = LocalDate
            .parse(resultSet.getDate("datefrom").toString());
        LocalDate dateDue = LocalDate
            .parse(resultSet.getDate("datedue").toString());
        LocalDate dateTo = LocalDate
            .parse(resultSet.getDate("dateto").toString());
        int fine = resultSet.getInt("fine");
        int finePaid = resultSet.getInt("finepaid");
        Reservation reservation = new Reservation(cpr, item_id, datefrom,
            dateDue, dateTo, fine, finePaid);
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
      String querysql = "SELECT HAS_BORROWEDVIDEOGAME.* FROM HAS_BORROWEDVIDEOGAME WHERE ITEM_ID = ?";
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

  protected List<MyItem> convertMyVideoGames(ResultSet resultSet)
  {
    ArrayList<MyItem> myItems = new ArrayList<>();
    try
    {
      while (resultSet.next())
      {
        int id = resultSet.getInt("item_id");
        String title = resultSet.getString("game_title");
        LocalDate datefrom = LocalDate
            .parse(resultSet.getDate("datefrom").toString());
        LocalDate dateDue = LocalDate
            .parse(resultSet.getDate("datedue").toString());
        int fine = resultSet.getInt("fine");

        MyItem myItem = new MyItem(id, title, datefrom, dateDue, fine, "Game");
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

  @Override public ArrayList<MyItem> getMyVideoGames(long cpr)
  {
    try
    {
      String querysql = "SELECT HAS_BORROWEDVIDEOGAME.ITEM_ID, VIDEOGAME.GAME_TITLE, HAS_BORROWEDVIDEOGAME.DATEFROM, HAS_BORROWEDVIDEOGAME.DATEDUE, HAS_BORROWEDVIDEOGAME.FINE FROM HAS_BORROWEDVIDEOGAME JOIN VIDEOGAME ON HAS_BORROWEDVIDEOGAME.ITEM_ID = VIDEOGAME.ITEM_ID WHERE HAS_BORROWEDVIDEOGAME.ITEM_ID = VIDEOGAME.ITEM_ID AND HAS_BORROWEDVIDEOGAME.CPR = ?;";
      PreparedStatement selectVideoGame = connection.prepareStatement(querysql);
      selectVideoGame.setLong(1, cpr);
      ResultSet resultSet = selectVideoGame.executeQuery();

      ArrayList<MyItem> myItems = (ArrayList<MyItem>) convertMyVideoGames(resultSet);
      resultSet.close();
      selectVideoGame.close();
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
      String querysql = "DELETE FROM HAS_BORROWEDVIDEOGAME WHERE CPR = ? AND ITEM_ID = ?";
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
      String querysql = "UPDATE HAS_BORROWEDVIDEOGAME SET DATEFROM = ?, DATEDUE = ?, DATETO = ? WHERE CPR = ? AND ITEM_ID = ?";
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
      String querysql = "SELECT COUNT(CPR) AS TOTAL FROM HAS_BORROWEDVIDEOGAME WHERE CPR = ?";
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
      String querysql = "SELECT HAS_BORROWEDVIDEOGAME.* FROM HAS_BORROWEDVIDEOGAME ORDER BY(DATEFROM)";
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
