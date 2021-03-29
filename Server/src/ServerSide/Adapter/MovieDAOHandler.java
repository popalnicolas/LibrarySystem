package ServerSide.Adapter;

import Shared.SharedObjects.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieDAOHandler implements MovieDAO
{
  private Connection connection;

  public MovieDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  protected List<Movie> convertMovies(ResultSet resultSet)
  {
    ArrayList<Movie> movieList = new ArrayList<>();
    try
    {
      while (resultSet.next())
      {
        int id = resultSet.getInt("item_id");
        String title = resultSet.getString("movie_title");
        int year = resultSet.getInt("yearofpublishing");
        Movie movie = new Movie(id, title, year);
        movieList.add(movie);
      }
      return movieList;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override

  public ArrayList<Movie> getMovies()
  {
    try
    {
      String querysql = "SELECT MOVIE.* FROM MOVIE";
      PreparedStatement querySelectMovies = connection
          .prepareStatement(querysql);
      ResultSet resultSet = querySelectMovies.executeQuery();

      ArrayList<Movie> movieList = (ArrayList<Movie>) convertMovies(resultSet);
      resultSet.close();
      querySelectMovies.close();
      return movieList;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } return null;
  }

  @Override public ArrayList<Movie> searchMovies(String searchString)
  {
    try
    {
      String querysql = "SELECT MOVIE.* FROM MOVIE WHERE MOVIE_TITLE LIKE ?";
      PreparedStatement search = connection.prepareStatement(querysql);
      search.setString(1, "%" + searchString + "%");
      ResultSet resultSet = search.executeQuery();

      ArrayList<Movie> movies = (ArrayList<Movie>) convertMovies(resultSet);
      resultSet.close();
      search.close();
      return movies;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}