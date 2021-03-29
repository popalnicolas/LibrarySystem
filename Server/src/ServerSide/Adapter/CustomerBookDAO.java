package ServerSide.Adapter;

public interface CustomerBookDAO
{
  void borrowBook(long cpr, int id);
  void delete(long cpr, int id);
}
