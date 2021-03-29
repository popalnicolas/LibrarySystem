package ServerSide.Adapter;

import Shared.SharedObjects.Book;

import java.util.ArrayList;

public interface BookDAO
{
  ArrayList<Book> getBooks();
  ArrayList<Book> searchBooks(String searchString);
}
