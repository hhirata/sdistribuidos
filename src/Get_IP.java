
import java.io.File;
import java.net.*;

public class Get_IP
{
    public static void main(String args[])
    {
        try
        {
        	File fl = new File("D:\\NotasMD.pdf");
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            System.out.println(addr.getHostAddress());
            System.out.println(hostname);
            System.out.println(fl.getClass().getSimpleName());
            System.out.println(fl.getName());
            
        }catch(UnknownHostException e)
        {
             //throw Exception
        }


    }
}