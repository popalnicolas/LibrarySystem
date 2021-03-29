package ServerSide.Adapter;

public interface CustomerVideoGameDAO
{
  void borrowVideoGame(long cpr, int id);
  void delete(long cpr, int id);
}
