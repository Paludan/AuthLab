import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;

public class ApplicationServer {
    public static final String password = "81dc9bdb52d04dc20036dbd8313ed055";
    public static final String password2 = "d93591bdf7860e1e4ee2fca799911215";

    public static void main(String[] args) throws RemoteException {

        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("Printer", new Printer());
    }

    static boolean isAuthenticated (String s) {
        Boolean reply = false;
        Boolean u1 = false, u2 = false;
        try {
            byte[] bytesOfMessage = s.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            String hashString = "";

            for (int i = 0; i < thedigest.length; i++){
                hashString += Integer.toHexString((0x000000ff & thedigest[i]) | 0xffffff00).substring(6);
                u1 = password.charAt(i) == hashString.charAt(i);
                u2 = password2.charAt(i) == hashString.charAt(i);
            }
            if(u1){
                System.out.println("Server accessed by user: Lee");
                return u1;}
            else {
                System.out.println("Server accessed by user: Phil");
                return u2; }
        } catch (Exception e)
        {
            System.out.println(e);
        }

        return false;
    }

 /*   static  void setUser(String s) {
        user = s;
    }
    static String getUser(){
        System.out.println("Print server accessed by user: " + user);
        return user;
    }*/
}
