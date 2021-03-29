package Shared.SharedObjects;

public class Movie extends Item
{

  public Movie(int id, String name, int year)
  {
    super(id, name, year);
  }

  @Override public String getType()
  {
    return "Movie";
  }

  @Override public boolean equals(Object obj)
  {
    return super.equals(obj);
  }

  @Override public String toString()
  {
    return "Id: "+ super.getId() + " Name: "+super.getName();
  }

}
