package ServerSide.Adapter;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerMovieDAOHandler implements CustomerMovieDAO
{
  private Connection connection;

  public CustomerMovieDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  @Override public void borrowMovie(long cpr, int id)
  {
    try
    {
      String insertsql = "INSERT INTO CUSTOMERMOVIE VALUES(?, ?)";
      PreparedStatement insertCustomerBook = connection.prepareStatement(insertsql);
      insertCustomerBook.setLong(1, cpr);
      insertCustomerBook.setInt(2, id);

      insertCustomerBook.executeUpdate();
      insertCustomerBook.close();
    }
    catch (SQLException e)
    {
      JOptionPane.showMessageDialog(null, "You already reserved this item");
    }
  }

  @Override public void delete(long cpr, int id)
  {
    try
    {
      String querysql = "DELETE FROM CUSTOMERMOVIE WHERE CPR = ? AND ITEM_ID = ?";
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
}
