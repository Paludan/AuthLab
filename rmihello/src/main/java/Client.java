import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
 public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        PrintService service = (PrintService) Naming.lookup("rmi://localhost:5099/Printer");
    
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the password");
        if(!service.auth(scanner.nextLine())) {
            System.out.println("Authentication failed!");

            try {
                System.in.read();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }            

        System.out.println("Authentication successful!");

        begin(service);

        scanner.close();
    }

    static void begin (PrintService service) throws RemoteException
    {
        boolean c = true;
        Scanner scanner = new Scanner(System.in);

        while (c) 
        {
            setupCLI();
            String input = scanner.nextLine();
            clearConsole();

            switch(Integer.parseInt(input))
            {
                case 1:
                    System.out.println("Please enter file name to print");
                    System.out.println(service.print(scanner.nextLine(), "Printer 1"));
                    break;
                case 2:
                    System.out.println(service.queue());
                    break;
                case 3:
                    System.out.println("Please enter job to move to the top of the queue");
                    service.topQueue(Integer.parseInt(scanner.nextLine()));
                    break;
                case 4:
                    System.out.println(service.start());
                    break;
                case 5:
                    System.out.println(service.stop());
                    break;
                case 6:
                    System.out.println(service.restart());
                    break;
                case 7:
                    System.out.println(service.status());
                    break;
                case 8:
                    System.out.println("Please input parameter string");
                    System.out.println(service.readConfig(scanner.nextLine()));
                    break;
                case 9:
                    String parameter, value;
                        
                    System.out.println("Please input parameter string");
                    parameter = scanner.nextLine();

                    System.out.println("Please input value to set parameter");
                    value = scanner.nextLine();

                    service.setConfig(parameter, value);
                    break;
                case 10:
                    c = false;
                    System.exit(0);
                default:
                    System.out.println("Your input must match the selection menu, please try again!");
            }

            try {
                System.out.println("Press enter to return to the menu");
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    static void setupCLI ()
    {
        System.out.println("1) Print");
        System.out.println("2) Queue");
        System.out.println("3) Move job to the top of the queue");
        System.out.println("4) Start print server");
        System.out.println("5) Stop print server");
        System.out.println("6) Restart print server");
        System.out.println("7) Status of print server");
        System.out.println("8) Read configuration");
        System.out.println("9) Set configuration");
        System.out.println("10) Exit");
    }

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
}