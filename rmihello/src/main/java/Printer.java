import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Printer extends UnicastRemoteObject implements PrintService{
    boolean isOn = false;

    String[] printQueue = new String[0];
    List<String> pQL = new ArrayList<String>();
    public Printer() throws RemoteException{
        super();
    }

    public String print(String filename, String printer) throws RemoteException{
        pQL.add(filename);
        System.out.println("File: " + filename + " added to " + printer);
        return "Filename: " + filename + " added to queue on Printer : " + printer;
    }

    public String queue() throws RemoteException{
        AtomicInteger atomicInteger = new AtomicInteger(1);
        return "Print queue:\n"+pQL.stream().map(s -> 
                "Job " + atomicInteger.getAndIncrement() + ": " + s + "\n").collect(Collectors.joining());
    }

    public void topQueue(int job) throws RemoteException{
        String jobToMove = pQL.get(job-1);
        pQL.remove(jobToMove);
        pQL.add(0, jobToMove);
    }

    public String start() throws RemoteException{
        isOn = true;
        return "Print server has started";
    }

    public String stop() throws RemoteException{
        isOn = false;
        return "Print server has stopped";
    }

    public String restart() throws RemoteException{
        stop();
        pQL.clear();
        start();

        return "Print server has been restarted";
    }

    public String status() throws RemoteException{
        String pOff = "The print server is offline", pOn = "The print server is online";

        return isOn ? pOn : pOff;
    }

    public String readConfig(String parameter) throws RemoteException{
        return parameter;
    }

    public void setConfig(String parameter, String value) throws RemoteException{
        parameter = value;

        System.out.println(parameter + " has been set to: " + value);
    }

    public boolean auth (String password)
    {
       // ApplicationServer.getUser();
        return ApplicationServer.isAuthenticated(password);
    }
}

