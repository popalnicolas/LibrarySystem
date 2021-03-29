package Shared.Network;

import Shared.SharedObjects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface RMIServer extends Remote
{
  boolean registrer(Customer customer) throws RemoteException;
boolean login(Customer customer) throws RemoteException;
boolean change(Customer customer) throws RemoteException;
String getname(Customer customer) throws RemoteException;
List<Item> getlist() throws RemoteException;
void remove(Item item) throws RemoteException;
void registerClientItems(ClientCallback clientCallback)throws RemoteException;
String getAuthor(int id) throws RemoteException;
Item getItem(int id) throws RemoteException;
String getDeveloper(int id) throws RemoteException;
String getProductionCompany(int id) throws RemoteException;
Info borrow(long cpr, int id, String type) throws RemoteException;
  void borrowconfirm(long cpr, int id, String type, LocalDate date)throws RemoteException;
  List<MyItem> getitems(long cpr)throws RemoteException;
  boolean returnItem(MyItem item, long cpr)throws RemoteException;
  boolean checkCount(long cpr)throws RemoteException;
  List<Item> searchItem(String category, String searchtext)throws RemoteException;
  String getGenre(int id, String type)throws RemoteException;
  void checkDate()throws RemoteException;
}
