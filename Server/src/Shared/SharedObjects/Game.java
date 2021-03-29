package Shared.SharedObjects;

public class Game extends Item
{
  private int publishedby;

  public Game(int id, String name, int year, int publishedby)
  {
    super(id, name, year);
    this.publishedby = publishedby;
  }

  @Override public String getType()
  {
    return "Game";
  }

  @Override public boolean equals(Object obj)
  {
    if(!(obj instanceof Game))
    {
      return false;
    }
    Game other = (Game) obj;
    return super.equals(obj)&&publishedby==other.publishedby;
  }

  public int getPublishedby()
  {
    return publishedby;
  }

  @Override public String toString()
  {
    return "Id: "+super.getId() + " Name: "+super.getName();
  }
}
