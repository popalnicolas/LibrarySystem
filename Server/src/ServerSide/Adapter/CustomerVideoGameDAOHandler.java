package ServerSide.Adapter;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerVideoGameDAOHandler implements CustomerVideoGameDAO
{
  private Connection connection;

  public CustomerVideoGameDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  @Override public void borrowVideoGame(long cpr, int id)
  {

    try
    {
      String insertsql = "INSERT INTO CUSTOMERVIDEOGAME VALUES(?, ?)";
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
      String querysql = "DELETE FROM CUSTOMERVIDEOGAME WHERE CPR = ? AND ITEM_ID = ?";
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
