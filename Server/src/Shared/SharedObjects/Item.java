package Shared.SharedObjects;

import java.io.Serializable;

public abstract class Item implements Serializable
{
  private int id;
  private String name;
  private int year;

  public Item(int id, String name, int year)
  {
    this.id = id;
    this.name = name;
    this.year = year;
  }

  public int getId()
  {
    return id;
  }
  public String getName()
  {
    return name;
  }
  public int getYear()
  {
    return year;
  }
  public abstract String toString();
  public abstract String getType();
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Item))
    {
      return false;
    }
    Item other = (Item) obj;
    return name.equals(other.name)&&year==other.year&&id==other.id;
  }
  public void setName(String name)
  {
    this.name = name;
  }
}
