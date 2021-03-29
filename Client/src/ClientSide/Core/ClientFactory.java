package ClientSide.Core;

import ClientSide.Network.Client;
import ClientSide.Network.RMIClient;

public class ClientFactory
{
  private Client client;

  public Client getClient()
  {
    if(client==null)
    {
      client = new RMIClient();
    }
    return client;
  }
}
