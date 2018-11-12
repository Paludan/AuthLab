import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintService extends Remote {

    public String print(String filename, String printer) throws RemoteException;

    public String queue() throws RemoteException;

    public void topQueue(int job) throws RemoteException;

    public String start() throws RemoteException;

    public String stop() throws RemoteException;

    public String restart() throws RemoteException;

    public String status() throws RemoteException;

    public String readConfig(String parameter) throws RemoteException;

    public void setConfig(String parameter, String value) throws RemoteException;

    public boolean auth(String password) throws RemoteException;
}
