package ClientSide.Model;

import Shared.SharedObjects.Customer;
import Shared.Util.Subject;


public interface LoginModel extends Subject
{
    void setCpr(long cpr);
    boolean register(Customer customer);
    boolean login(Customer customer);
    boolean change(Customer customer);
    String getName();
    long getCpr();
}
