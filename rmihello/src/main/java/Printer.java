import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import sun.rmi.server.UnicastRef2;

public class Printer extends UnicastRemoteObject implements PrintService{

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

       // pQL.stream().forEach(e -> System.out.println(e));
    }

    public void topQueue(int job) throws RemoteException{
        int m = pQL.indexOf(job);
        String jobToMove = pQL.get(m);
        pQL.remove(jobToMove);
        pQL.add(0, jobToMove);
    }

    public void start() throws RemoteException{
        System.out.println("Print server has started");
    }

    public void stop() throws RemoteException{
        System.out.println("Print server has stopped");
    }

    public void restart() throws RemoteException{
        stop();
        pQL.clear();
        start();
    }

    public void status() throws RemoteException{

    }

    public void readConfig(String parameter) throws RemoteException{

    }

    public void setConfig(String parameter, String value) throws RemoteException{

    }
}

