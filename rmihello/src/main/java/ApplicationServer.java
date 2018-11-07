import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;

public class ApplicationServer {
    public static final String password = "81dc9bdb52d04dc20036dbd8313ed055";

    public static void main(String[] args) throws RemoteException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the password");
        if(!isAuthenticated(scanner.nextLine())) {
            System.out.println("Authentication failed!");

            try {
                System.in.read();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }            

        System.out.println("Authentication successful!");

        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("hello", new HelloServant());

        scanner.close();
    }

    static boolean isAuthenticated (String s) {
        Boolean reply = false;

        try {
            byte[] bytesOfMessage = s.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            String hashString = "";

            for (int i = 0; i < thedigest.length; i++){
                hashString += Integer.toHexString((0x000000ff & thedigest[i]) | 0xffffff00).substring(6);
                reply = password.charAt(i) == hashString.charAt(i);
            }

            return reply;
        } catch (Exception e)
        {
            System.out.println(e);
        }

        return false;
    }
}
