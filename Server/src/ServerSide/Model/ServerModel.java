package ServerSide.Model;

import Shared.SharedObjects.*;
import Shared.Util.Subject;

import java.time.LocalDate;
import java.util.List;

public interface ServerModel extends Subject
{
boolean register(Customer customer);
boolean login(Customer customer);
boolean change(Customer customer);
String getname(Customer customer);
void delete(Item item);
List<Item> getList();
String getAuthor(int id);
Item getItem(int id);
String getDeveloper(int id);
String getProductionCompany(int id);
Info borrow(long cpr, int id, String type);
void borrowconfirm(long cpr, int id, String type, LocalDate date);
  List<MyItem> getitems(long cpr);
  boolean returnItem(MyItem item, long cpr);
  boolean checkCount(long cpr);
  List<Item> searchItem(String category, String searchtext);
  String getGenre(int id, String type);
  void checkDate();
}
