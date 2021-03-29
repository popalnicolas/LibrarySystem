package Shared.Network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
void updateMyItems()throws RemoteException;
}
