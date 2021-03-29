package ServerSide.Adapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeveloperDAOHandler implements DeveloperDAO
{
  private Connection connection;

  public DeveloperDAOHandler()
  {
    this.connection  = Database.getInstance().getConnection();
  }

  protected List<String> convertDeveloper(ResultSet resultset)
  {
    ArrayList<String> result = new ArrayList<>();
    try
    {
      while (resultset.next())
      {
        String name = resultset.getString("developername");
        result.add(name);
      }
      return result;
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<String> getDeveloper(int id)
  {
    try
    {
      String querysql = "SELECT DEVELOPERNAME FROM DEVELOPER WHERE DEVELOPER_ID IN(SELECT DEVELOPER_ID FROM VIDEOGAMEDEVELOPER WHERE ITEM_ID = ?)";
      PreparedStatement getDeveloperName = connection.prepareStatement(querysql);
      getDeveloperName.setInt(1, id);
      ResultSet resultset = getDeveloperName.executeQuery();

      ArrayList<String> result = (ArrayList<String>) convertDeveloper(resultset);
      resultset.close();
      getDeveloperName.close();
      return result;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
