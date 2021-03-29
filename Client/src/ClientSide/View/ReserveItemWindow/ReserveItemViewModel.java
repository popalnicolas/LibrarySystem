package ClientSide.View.ReserveItemWindow;

import ClientSide.Model.BorrowModel;
import Shared.SharedObjects.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class ReserveItemViewModel
{
  private BorrowModel borrowModel;
  private StringProperty authorname;
  private StringProperty developer;
  private StringProperty productioncomp;
  private StringProperty authorLabel;
  private StringProperty developerLabel;
  private StringProperty productionCompanyLabel;
  private StringProperty name;
  private StringProperty type;
  private StringProperty year;
  private StringProperty id;
  private StringProperty dialogtext;
  private LocalDate date;
  private StringProperty genre;

  public ReserveItemViewModel(BorrowModel borrowModel)
  {
    this.borrowModel = borrowModel;
    authorname = new SimpleStringProperty("");
    authorLabel = new SimpleStringProperty("");
    developerLabel = new SimpleStringProperty("");
    productionCompanyLabel = new SimpleStringProperty("");
    productioncomp = new SimpleStringProperty("");
    developer = new SimpleStringProperty("");
    id = new SimpleStringProperty("");
    year = new SimpleStringProperty("");
    type = new SimpleStringProperty("");
    name = new SimpleStringProperty("");
    dialogtext = new SimpleStringProperty("");
    genre = new SimpleStringProperty("");
  }

  public StringProperty genreProperty()
  {
    return genre;
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public StringProperty typeProperty()
  {
    return type;
  }

  public StringProperty yearProperty()
  {
    return year;
  }

  public StringProperty idProperty()
  {
    return id;
  }

  public StringProperty developerProperty()
  {
    return developer;
  }

  public StringProperty productioncompProperty()
  {
    return productioncomp;
  }

  public StringProperty authorLabelProperty()
  {
    return authorLabel;
  }

  public StringProperty developerLabelProperty()
  {
    return developerLabel;
  }

  public StringProperty dialogtextProperty()
  {
    return dialogtext;
  }

  public StringProperty productionCompanyLabelProperty()
  {
    return productionCompanyLabel;
  }

  public StringProperty authornameProperty()
  {
    return authorname;
  }

  public void getItem()
  {
    Item item = borrowModel.getItem();
    switch (item.getType())
    {
      case "Book":
        Book book = (Book) item;
        name.set((book.getName()));
        id.set(Integer.toString(book.getId()));
        year.set(Integer.toString(book.getYear()));
        type.set(item.getType());
        authorname.set(borrowModel.getAuthor(item.getId()));
        authorLabel.set("Author:");
        developerLabel.set("");
        productionCompanyLabel.set("");
        developer.set("");
        productioncomp.set("");
        genre.set(borrowModel.getGenre(item.getId(),"Book"));
        break;
      case "Game":
        Game game = (Game) item;
        name.set(game.getName());
        id.set(Integer.toString(game.getId()));
        year.set(Integer.toString(game.getYear()));
        type.set(game.getType());
        developer.set(borrowModel.getDeveloper(item.getId()));
        authorLabel.set("");
        developerLabel.set("Developer:");
        productionCompanyLabel.set("");
        authorname.set("");
        productioncomp.set("");
        genre.set(borrowModel.getGenre(item.getId(),"Game"));
        break;
      case "Movie":
        Movie movie = (Movie) item;
        name.set(movie.getName());
        id.set(Integer.toString(movie.getId()));
        year.set(Integer.toString(movie.getYear()));
        type.set(movie.getType());
        productioncomp.set(borrowModel.getProductionCompany(item.getId()));
        authorLabel.set("");
        productionCompanyLabel.set("Production Company:");
        developerLabel.set("");
        authorname.set("");
        developer.set("");
        genre.set(borrowModel.getGenre(item.getId(),"Movie"));
        break;
    }
  }

  public String borrow()
  {
    if(borrowModel.checkCount(borrowModel.getCpr()))
    {
      Item item = borrowModel.getItem();
      long cpr = borrowModel.getCpr();
      Info info = borrowModel.borrow(cpr,item.getId(),item.getType());
      if (info.getPosition() != -1)
      {
        date = info.getDateDue();
        dialogtext.set(
            "Are you sure you want to go in queue? \n"+"Position: "
                + info.getPosition() + " From: " + info.getDateDue() + " To: "
                + info.getDateDue().plusDays(1));
        return "Queue";
      }
      else
      {
        return "Borrow";
      }
    }
    else
      {
        return "Count";
      }
  }

  public void borrowconfirm()
  {
    Item item = borrowModel.getItem();
    long cpr = borrowModel.getCpr();
    borrowModel.borrowconfirm(cpr,item.getId(),item.getType(),date);
  }
}
