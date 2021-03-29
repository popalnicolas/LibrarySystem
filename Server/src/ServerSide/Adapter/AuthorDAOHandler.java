package ServerSide.Adapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthorDAOHandler implements AuthorDAO
{
  private Connection connection;

  public  AuthorDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  protected List<String> convertAuthors(ResultSet resultSet)
  {
    ArrayList<String> result = new ArrayList<>();
    try
    {
      while (resultSet.next())
      {
        String name = resultSet.getString("authorname");
        result.add(name);
      }
      return result;
    }
    catch (SQLException sqlException)
    {
      sqlException.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<String> getAuthor(int id)
  {
    try
    {
      String querysql = "SELECT AUTHORNAME FROM AUTHOR WHERE AUTHOR_ID IN(SELECT AUTHOR_ID FROM BOOKAUTHOR WHERE ITEM_ID = ?)";
      PreparedStatement getAuthorName = connection.prepareStatement(querysql);
      getAuthorName.setInt(1, id);
      ResultSet resultset = getAuthorName.executeQuery();

      ArrayList<String> result = (ArrayList<String>) convertAuthors(resultset);
      resultset.close();
      getAuthorName.close();
      return result;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

}
