package ClientSide.Model;


import Shared.SharedObjects.Info;
import Shared.SharedObjects.Item;
import Shared.Util.Subject;

import java.time.LocalDate;
import java.util.List;

public interface BorrowModel extends Subject
{
  void setCpr(long cpr);
  long getCpr();
  void remove(Item item);
  List<Item> getitems();
  void setItem(int id, String type);
  Item getItem();
  String getDeveloper(int id);
  String getAuthor(int id);
  String getProductionCompany(int id);
  Info borrow(long cpr,int id,String type);
  void borrowconfirm(long cpr,int id,String type, LocalDate date);
  boolean checkCount(long cpr);
  List<Item> searchItem(String category,String searchtext);
  String getGenre(int id, String type);
}
