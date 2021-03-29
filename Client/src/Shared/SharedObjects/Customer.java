package Shared.SharedObjects;

import java.io.Serializable;

public class Customer implements Serializable
{
  private long cpr;
  private String name;
  private String password;
  private String address;
  private long card;

  public Customer(long cpr, String name, String password, String address,
      long card)
  {
    this.cpr = cpr;
    this.name = name;
    this.password = password;
    this.address = address;
    this.card = card;
  }

  public long getCpr()
  {
    return cpr;
  }

  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }

  public String getAddress()
  {
    return address;
  }

  public long getCard()
  {
    return card;
  }

  public void setCpr(long cpr)
  {
    this.cpr = cpr;
  }

  @Override public String toString()
  {
    return "Customer{" + "cpr=" + cpr + ", name='" + name + '\''
        + ", password='" + password + '\'' + ", address='" + address + '\''
        + ", card=" + card + '}';
  }
}
