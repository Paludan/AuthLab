import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintService extends Remote {

    public void print(String filename, String printer) throws RemoteException;

    public void queue() throws RemoteException;

    public void topQueue(int job) throws RemoteException;

    public void start() throws RemoteException;

    public void stop() throws RemoteException;

    public void restart() throws RemoteException;

    public void status() throws RemoteException;

    public void readConfig(String parameter) throws RemoteException;

    public void setConfig(String parameter, String value) throws RemoteException;
}
