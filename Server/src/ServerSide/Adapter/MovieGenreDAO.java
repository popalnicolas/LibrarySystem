package ServerSide.Adapter;

import java.util.ArrayList;

public interface MovieGenreDAO
{
  ArrayList<String> getGenre(int id);
}
