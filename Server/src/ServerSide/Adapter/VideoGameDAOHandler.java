package ServerSide.Adapter;

import Shared.SharedObjects.Game;
import javafx.beans.property.StringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoGameDAOHandler implements VideoGameDAO
{
  private Connection connection;

  public VideoGameDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  protected List<Game> convertGames(ResultSet resultSet)
  {
    ArrayList<Game> videoGameList = new ArrayList<>();
    try
    {
      while(resultSet.next())
      {
        int id = resultSet.getInt("item_id");
        String title = resultSet.getString("game_title");
        int year = resultSet.getInt("yearofpublishing");
        int publishedBy = resultSet.getInt("published_by");
        Game game = new Game(id, title, year, publishedBy);
        videoGameList.add(game);
      }
      return videoGameList;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<Game> getGames()
  {

    try
    {
      String querysql = "SELECT VIDEOGAME.* FROM VIDEOGAME";
      PreparedStatement querySelectMovie = connection.prepareStatement(querysql);
      ResultSet resultSet = querySelectMovie.executeQuery();

      ArrayList<Game> videoGameList = (ArrayList<Game>) convertGames(resultSet);
      resultSet.close();
      querySelectMovie.close();
      return videoGameList;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Game> searchBooks(String searchString)
  {
    try
    {
      String querysql = "SELECT VIDEOGAME.* FROM VIDEOGAME WHERE GAME_TITLE LIKE ?";
      PreparedStatement search = connection.prepareStatement(querysql);
      search.setString(1, "%" + searchString + "%");
      ResultSet resultSet = search.executeQuery();

      ArrayList<Game> games = (ArrayList<Game>) convertGames(resultSet);
      resultSet.close();
      search.close();
      return games;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
