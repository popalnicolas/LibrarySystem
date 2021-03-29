package Shared.SharedObjects;

public class Book extends Item
{
  private int publishedby;

  public Book(int id, String name, int year, int publishedby)
  {
    super(id, name, year);
    this.publishedby = publishedby;
  }

  @Override public String getType()
  {
    return "Book";
  }

  @Override public boolean equals(Object obj)
  {
    if(!(obj instanceof Book))
    {
      return false;
    }
    Book other = (Book) obj;
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
