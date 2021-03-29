package ServerSide.Adapter;

import Shared.SharedObjects.Game;

import java.util.ArrayList;

public interface VideoGameDAO
{
  ArrayList<Game> getGames();
  ArrayList<Game> searchBooks(String searchString);
}
