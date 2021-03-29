package ServerSide.Adapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieGenreDAOHandler implements MovieGenreDAO
{
  private Connection connection;

  public MovieGenreDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  protected List<String> convertGenres(ResultSet resultSet)
  {
    ArrayList<String> genres = new ArrayList<>();
    try
    {
      while(resultSet.next())
      {
        String genre = resultSet.getString("genrename");
        genres.add(genre);
      }
      return genres;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<String> getGenre(int id)
  {
    try
    {
      String querysql = "SELECT GENRENAME FROM MOVIEGENRE WHERE ITEM_ID = ?";
      PreparedStatement selectGenre = connection.prepareStatement(querysql);
      selectGenre.setInt(1, id);
      ResultSet resultSet = selectGenre.executeQuery();

      ArrayList<String> genres = (ArrayList<String>) convertGenres(resultSet);
      resultSet.close();
      selectGenre.close();
      return genres;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
