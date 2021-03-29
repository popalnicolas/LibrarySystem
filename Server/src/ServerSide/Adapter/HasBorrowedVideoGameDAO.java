package ServerSide.Adapter;

import Shared.SharedObjects.MyItem;
import Shared.SharedObjects.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;

public interface HasBorrowedVideoGameDAO
{
  void hasBorrowed(long cpr, int id, LocalDate dateFrom, LocalDate dateDue);
  ArrayList<Reservation> getReservations(int id);
  ArrayList<MyItem> getMyVideoGames(long cpr);
  void delete(long cpr, int id);
  void updateDates(long cpr, int id, LocalDate dateFrom, LocalDate dateDue);
  int checkCount(long cpr);
  ArrayList<Reservation> getReservations();
}
