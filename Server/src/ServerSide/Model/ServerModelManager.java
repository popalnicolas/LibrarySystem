package ServerSide.Model;

import ServerSide.Adapter.*;
import Shared.SharedObjects.*;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServerModelManager implements ServerModel
{
  private CustomerDAO customerDAO = new CustomerDAOHandler();
  private BookDAO bookDAO = new BookDAOHandler();
  private PropertyChangeSupport support;
  private List<Item> items = new ArrayList<>();
  private AuthorDAO authorDAO;
  private MovieDAO movieDAO;
  private VideoGameDAO videoGameDAO;
  private DeveloperDAO developerDAO;
  private ProductionCompanyDAO productionCompanyDAO;
  private CustomerBookDAO customerBookDAO;
  private CustomerVideoGameDAO customerVideoGameDAO;
  private CustomerMovieDAO customerMovieDAO;
  private HasBorrowedVideoGameDAO hasBorrowedVideoGameDAO;
  private HasBorrowedMovieDAO hasBorrowedMovieDAO;
  private HasBorrowedBookDAO hasBorrowedBookDAO;
  private BookGenreDAO bookGenreDAO;
  private MovieGenreDAO movieGenreDAO;
  private VideoGameGenreDAO videoGameGenreDAO;

  public ServerModelManager(AuthorDAO authorDAO, MovieDAO movieDAO,
      VideoGameDAO videoGameDAO, DeveloperDAO developerDAO,
      ProductionCompanyDAO productionCompanyDAO,
      CustomerBookDAO customerBookDAO,
      CustomerVideoGameDAO customerVideoGameDAO,
      CustomerMovieDAO customerMovieDAO, HasBorrowedBookDAO hasBorrowedBookDAO,
      HasBorrowedMovieDAO hasBorrowedMovieDAO,
      HasBorrowedVideoGameDAO hasBorrowedVideoGameDAO,
      BookGenreDAO bookGenreDAO, VideoGameGenreDAO videoGameGenreDAO,
      MovieGenreDAO movieGenreDAO)
  {
    this.authorDAO = authorDAO;
    this.movieDAO = movieDAO;
    this.videoGameDAO = videoGameDAO;
    this.developerDAO = developerDAO;
    this.customerBookDAO = customerBookDAO;
    this.productionCompanyDAO = productionCompanyDAO;
    this.customerVideoGameDAO = customerVideoGameDAO;
    this.customerMovieDAO = customerMovieDAO;
    this.hasBorrowedVideoGameDAO = hasBorrowedVideoGameDAO;
    this.hasBorrowedMovieDAO = hasBorrowedMovieDAO;
    this.hasBorrowedBookDAO = hasBorrowedBookDAO;
    this.bookGenreDAO = bookGenreDAO;
    this.movieGenreDAO = movieGenreDAO;
    this.videoGameGenreDAO = videoGameGenreDAO;
    this.support = new PropertyChangeSupport(this);
  }

  @Override public synchronized boolean register(Customer customer)
  {
    if (customerDAO.addUser(customer).equals("ok"))
    {
      return true;
    }
    else
      return false;
  }

  @Override public synchronized boolean login(Customer customer)
  {
    ArrayList<Customer> customers = customerDAO.getCustomer();
    for (int i = 0; i < customers.size(); i++)
    {
      if (customers.get(i).getPassword().equals(customer.getPassword())
          && customers.get(i).getCpr() == customer.getCpr())
      {
        return true;
      }
    }

    return false;

  }

  @Override public synchronized boolean change(Customer customer)
  {
    if (customerDAO.setName(customer) && customerDAO.setaddress(customer)
        && customerDAO.setCreditcard(customer) && customerDAO
        .setpassword(customer))
    {
      return true;
    }
    else
      return false;
  }

  @Override public synchronized String getname(Customer customer)
  {
    String name = customerDAO.findName(customer);
    if (name.equals("") || name == null)
    {
      return "";
    }
    else
    {
      return name;
    }
  }

  @Override public synchronized void delete(Item item)
  {
    items.remove(item);
    support.firePropertyChange("Del", null, items);
  }

  @Override public synchronized List<Item> getList()
  {
    ArrayList<Book> books = bookDAO.getBooks();
    ArrayList<Movie> movies = movieDAO.getMovies();
    ArrayList<Game> games = videoGameDAO.getGames();
    ArrayList<Item> all = new ArrayList<>();
    all.addAll(books);
    all.addAll(movies);
    all.addAll(games);
    return all;
  }

  @Override public synchronized String getAuthor(int id)
  {
    String returnString = "";
    ArrayList<String> names = authorDAO.getAuthor(id);
    if (names.size() == 1)
    {
      return names.get(0);
    }
    else if (names.size() > 1)
    {
      for (int i = 0; i < names.size(); i++)
      {
        returnString += names.get(i) + ",";
      }
      return returnString;
    }
    else
    {
      return null;
    }
  }

  @Override public synchronized Item getItem(int id)
  {
    ArrayList<Book> books = bookDAO.getBooks();
    ArrayList<Game> games = videoGameDAO.getGames();
    ArrayList<Movie> movie = movieDAO.getMovies();
    ArrayList<Item> all = new ArrayList<>();
    all.addAll(books);
    all.addAll(games);
    all.addAll(movie);
    for (int i = 0; i < all.size(); i++)
    {
      if (all.get(i).getId() == id)
      {
        return all.get(i);
      }
    }
    return null;
  }

  @Override public synchronized String getDeveloper(int id)
  {
    String returnString = "";
    ArrayList<String> names = developerDAO.getDeveloper(id);
    if (names.size() == 1)
    {
      return names.get(0);
    }
    else if (names.size() > 1)
    {
      for (int i = 0; i < names.size(); i++)
      {
        returnString += names.get(i) + ",";
      }
      return returnString;
    }
    else
    {
      return null;
    }
  }

  @Override public synchronized String getProductionCompany(int id)
  {
    String returnString = "";
    ArrayList<String> names = productionCompanyDAO.getProductionCompanies(id);
    if (names.size() == 1)
    {
      return names.get(0);
    }
    else if (names.size() > 1)
    {
      for (int i = 0; i < names.size(); i++)
      {
        returnString += names.get(i) + ", ";
      }
      return returnString;
    }
    else
    {
      return null;
    }
  }

  @Override public synchronized Info borrow(long cpr, int id, String type)
  {
    ArrayList<Reservation> reservations = new ArrayList<>();
    ArrayList<Reservation> reservationbook = hasBorrowedBookDAO
        .getReservations(id);
    ArrayList<Reservation> reservationgame = hasBorrowedVideoGameDAO
        .getReservations(id);
    ArrayList<Reservation> reservationmovie = hasBorrowedMovieDAO
        .getReservations(id);
    reservations.addAll(reservationbook);
    reservations.addAll(reservationgame);
    reservations.addAll(reservationmovie);
    if (reservations.size() >= 1)
    {
      LocalDate datedue = reservations.get(reservations.size() - 1).getDatedue()
          .plusDays(1);
      return new Info(reservations.size() + 1, datedue);
    }
    else
    {
      LocalDate now = LocalDate.now();
      LocalDate due = LocalDate.now().plusDays(1);
      switch (type)
      {
        case "Book":
          customerBookDAO.borrowBook(cpr, id);
          hasBorrowedBookDAO.hasBorrowed(cpr, id, now, due);
          break;
        case "Movie":
          customerMovieDAO.borrowMovie(cpr, id);
          hasBorrowedMovieDAO.hasBorrowed(cpr, id, now, due);
          break;
        case "Game":
          customerVideoGameDAO.borrowVideoGame(cpr, id);
          hasBorrowedVideoGameDAO.hasBorrowed(cpr, id, now, due);
          break;
      }
    }
    return new Info(-1, null);
  }

  @Override public synchronized void borrowconfirm(long cpr, int id, String type,
      LocalDate date)
  {

    LocalDate from = date;
    LocalDate due = date.plusDays(1);
    switch (type)
    {
      case "Book":
        customerBookDAO.borrowBook(cpr, id);
        hasBorrowedBookDAO.hasBorrowed(cpr, id, from, due);
        break;
      case "Movie":
        customerMovieDAO.borrowMovie(cpr, id);
        hasBorrowedMovieDAO.hasBorrowed(cpr, id, from, due);
        break;
      case "Game":
        customerVideoGameDAO.borrowVideoGame(cpr, id);
        hasBorrowedVideoGameDAO.hasBorrowed(cpr, id, from, due);
        break;
    }
  }

  @Override public synchronized List<MyItem> getitems(long cpr)
  {
    ArrayList<MyItem> books = hasBorrowedBookDAO.getMyBooks(cpr);
    ArrayList<MyItem> movies = hasBorrowedMovieDAO.getMyMovies(cpr);
    ArrayList<MyItem> games = hasBorrowedVideoGameDAO.getMyVideoGames(cpr);

    List<MyItem> list = new ArrayList<>();
    list.addAll(books);
    list.addAll(movies);
    list.addAll(games);

    return list;
  }

  @Override public synchronized boolean returnItem(MyItem item, long cpr)
  {
      switch (item.getType())
      {
        case "Book":
          ArrayList<Reservation> reservations = hasBorrowedBookDAO
              .getReservations(item.getId());
          if (cpr != reservations.get(0).getCpr())
          {
            JOptionPane.showMessageDialog(null,
                "You do not have this book. You are on queue");
            return false;
          }
          reservations.get(0).setDatedue(LocalDate.now());
          for (int j = 0; j < reservations.size() - 1; j++)
          {
            reservations.get(j + 1)
                .setDatefrom(reservations.get(j).getDatedue().plusDays(1));
            reservations.get(j + 1)
                .setDatedue(reservations.get(j + 1).getDatefrom().plusDays(1));
            hasBorrowedBookDAO.updateDates(reservations.get(j + 1).getCpr(),
                reservations.get(j + 1).getId(),
                reservations.get(j + 1).getDatefrom(),
                reservations.get(j + 1).getDatedue());
          }
          hasBorrowedBookDAO.delete(reservations.get(0).getCpr(),
              reservations.get(0).getId());
          customerBookDAO.delete(reservations.get(0).getCpr(),
              reservations.get(0).getId());
          support.firePropertyChange("return", null, null);
          break;
        case "Game":
          ArrayList<Reservation> reservations1 = hasBorrowedVideoGameDAO
              .getReservations(item.getId());
          if (cpr != reservations1.get(0).getCpr())
          {
            JOptionPane.showMessageDialog(null,
                "You do not have this video game. You are on queue");
            return false;
          }
          reservations1.get(0).setDatedue(LocalDate.now());
          for (int j = 0; j < reservations1.size() - 1; j++)
          {
            reservations1.get(j + 1)
                .setDatefrom(reservations1.get(j).getDatedue().plusDays(1));
            reservations1.get(j + 1)
                .setDatedue(reservations1.get(j + 1).getDatefrom().plusDays(1));
            hasBorrowedVideoGameDAO
                .updateDates(reservations1.get(j + 1).getCpr(),
                    reservations1.get(j + 1).getId(),
                    reservations1.get(j + 1).getDatefrom(),
                    reservations1.get(j + 1).getDatedue());
          }
          hasBorrowedVideoGameDAO.delete(reservations1.get(0).getCpr(),
              reservations1.get(0).getId());
          customerVideoGameDAO.delete(reservations1.get(0).getCpr(),
              reservations1.get(0).getId());
          support.firePropertyChange("return", null, null);
          break;
        case "Movie":
          ArrayList<Reservation> reservations2 = hasBorrowedMovieDAO
              .getReservations(item.getId());
          if (cpr != reservations2.get(0).getCpr())
          {
            JOptionPane.showMessageDialog(null,
                "You do not have this movie. You are on queue");
            return false;
          }
          reservations2.get(0).setDatedue(LocalDate.now());
          for (int j = 0; j < reservations2.size() - 1; j++)
          {
            reservations2.get(j + 1)
                .setDatefrom(reservations2.get(j).getDatedue().plusDays(1));
            reservations2.get(j + 1)
                .setDatedue(reservations2.get(j + 1).getDatefrom().plusDays(1));
            hasBorrowedMovieDAO.updateDates(reservations2.get(j + 1).getCpr(),
                reservations2.get(j + 1).getId(),
                reservations2.get(j + 1).getDatefrom(),
                reservations2.get(j + 1).getDatedue());
          }
          hasBorrowedMovieDAO.delete(reservations2.get(0).getCpr(),
              reservations2.get(0).getId());
          customerMovieDAO.delete(reservations2.get(0).getCpr(),
              reservations2.get(0).getId());
          support.firePropertyChange("return", null, null);
          break;
      }
      return true;
  }

  @Override public synchronized boolean checkCount(long cpr)
  {

    if (hasBorrowedBookDAO.checkCount(cpr) + hasBorrowedVideoGameDAO
        .checkCount(cpr) + hasBorrowedMovieDAO.checkCount(cpr) >= 3)
    {
      return false;
    }
    else
      return true;
  }

  @Override public synchronized List<Item> searchItem(String category, String searchtext)
  {
    ArrayList<Book> books = bookDAO.searchBooks(searchtext);
    ArrayList<Movie> movies = movieDAO.searchMovies(searchtext);
    ArrayList<Game> games = videoGameDAO.searchBooks(searchtext);

    List<Item> list = new ArrayList<>();

    switch (category)
    {
      case "Books":
        list.addAll(books);
        break;
      case "VideoGames":
        list.addAll(games);
        break;
      case "Movies":
        list.addAll(movies);
        break;
      case "All":
        list = getList();
        break;
    }
    return list;
  }

  @Override public synchronized String getGenre(int id, String type)
  {
    String genre = "";
    switch (type)
    {
      case "Book":
        ArrayList<String> strings = bookGenreDAO.getGenre(id);
        if (strings.size() == 1)
        {
          genre = strings.get(0);
        }
        else
        {
          for (int i = 0; i < strings.size(); i++)
          {
            genre += strings.get(i) + ", ";
          }
        }
        break;
      case "Movie":
        ArrayList<String> strings1 = movieGenreDAO.getGenre(id);
        if (strings1.size() == 1)
        {
          genre = strings1.get(0);
        }
        else
        {
          for (int i = 0; i < strings1.size(); i++)
          {
            genre += strings1.get(i) + ", ";
          }
        }

        break;
      case "Game":
        ArrayList<String> strings2 = videoGameGenreDAO.getGenre(id);
        if (strings2.size() == 1)
        {
          genre = strings2.get(0);
        }
        else
        {
          for (int i = 0; i < strings2.size(); i++)
          {
            genre += strings2.get(i) + ", ";
          }
        }
        break;
    }
    return genre;
  }

  @Override public void checkDate()
  {
    ArrayList<Reservation> bookreservations = hasBorrowedBookDAO.getReservations();
    ArrayList<Reservation> moviereservations = hasBorrowedMovieDAO.getReservations();
    ArrayList<Reservation> videogamereservations = hasBorrowedVideoGameDAO.getReservations();


    for (int i = 0; i < bookreservations.size(); i++)
    {
      if(bookreservations.get(i).getDatedue().isBefore(LocalDate.now()))
      {

        hasBorrowedBookDAO.delete(bookreservations.get(i).getCpr(),bookreservations.get(i).getId());
        customerBookDAO.delete(bookreservations.get(i).getCpr(),bookreservations.get(i).getId());
      }
    }
    for (int i = 0; i < moviereservations.size(); i++)
    {
      if(moviereservations.get(i).getDatedue().isBefore(LocalDate.now()))
      {
        hasBorrowedMovieDAO.delete(moviereservations.get(i).getCpr(),moviereservations.get(i).getId());
        customerMovieDAO.delete(moviereservations.get(i).getCpr(),moviereservations.get(i).getId());
      }
    }
    for (int i = 0; i < videogamereservations.size(); i++)
    {
      if(videogamereservations.get(i).getDatedue().isBefore(LocalDate.now()))
      {
        hasBorrowedVideoGameDAO.delete(videogamereservations.get(i).getCpr(),videogamereservations.get(i).getId());
        customerVideoGameDAO.delete(videogamereservations.get(i).getCpr(),videogamereservations.get(i).getId());
      }
    }
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
