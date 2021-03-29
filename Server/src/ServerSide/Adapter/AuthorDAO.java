package ServerSide.Adapter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AuthorDAO
{
  ArrayList<String> getAuthor(int id);
}
