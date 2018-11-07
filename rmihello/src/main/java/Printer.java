import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Printer extends UnicastRemoteObject implements PrintService{
    boolean isOn = false;

    String[] printQueue = new String[0];
    List<String> pQL = new ArrayList<String>();
    public Printer() throws RemoteException{
        super();
    }

    public void print(String filename, String printer) throws RemoteException{
        pQL.add(filename);
        System.out.println("Filename: " + filename + ", Printer : " + printer);
    }

    public void queue() throws RemoteException{

       pQL.stream().forEach(e -> System.out.println(e));
    }
    public void topQueue(int job) throws RemoteException{
        String jobToMove = pQL.get(job);
        pQL.remove(jobToMove);
        pQL.add(0, jobToMove);
    }

    public void start() throws RemoteException{
        isOn = true;
        System.out.println("Print server has started");
    }

    public void stop() throws RemoteException{
        isOn = false;
        System.out.println("Print server has stopped");
    }

    public void restart() throws RemoteException{
        stop();
        pQL.clear();
        start();

        System.out.println("Print server has been restarted");
    }

    public void status() throws RemoteException{
        String pOff = "The print server is offline", pOn = "The print server is online";

        System.out.println(isOn ? pOn : pOff);
    }

    public void readConfig(String parameter) throws RemoteException{
        System.out.println(parameter);
    }

    public void setConfig(String parameter, String value) throws RemoteException{
        parameter = value;

        System.out.println(parameter + " has been set to: " + value);
    }
}

