package ServerSide.Adapter;

public interface CustomerMovieDAO
{
  void borrowMovie(long cpr, int id);
  void delete(long cpr, int id);
}
