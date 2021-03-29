package ServerSide.Adapter;

import Shared.SharedObjects.Movie;

import java.util.ArrayList;

public interface MovieDAO
{
  ArrayList<Movie> getMovies();
  ArrayList<Movie> searchMovies(String searchString);
}