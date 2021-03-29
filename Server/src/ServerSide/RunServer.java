package ServerSide;

import ServerSide.Adapter.*;
import ServerSide.Model.ServerModelManager;
import ServerSide.Network.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args)
      throws AlreadyBoundException, RemoteException
  {
    Server rmiServer = new Server(
        new ServerModelManager(new AuthorDAOHandler(), new MovieDAOHandler(),
            new VideoGameDAOHandler(), new DeveloperDAOHandler(),
            new ProductionCompanyDAOHandler(), new CustomerBookDAOHandler(),
            new CustomerVideoGameDAOHandler(), new CustomerMovieDAOHandler(),
            new HasBorrowedBookDAOHandler(), new HasBorrowedMovieDAOHandler(),
            new HasBorrowedVideoGameDAOHandler(), new BookGenreDAOHandler(),
            new VideoGameGenreDAOHandler(), new MovieGenreDAOHandler()));
    rmiServer.startServer();
  }
}
