package ClientSide.Model;

import Shared.SharedObjects.MyItem;
import Shared.SharedObjects.Reservation;
import Shared.Util.Subject;

import java.util.List;

public interface MyItemsModel extends Subject
{
  void setCpr(long cpr);
  long getCpr();
  List<MyItem> getitems(long cpr);
  boolean returnItem(MyItem item);
  void checkDate();
}
