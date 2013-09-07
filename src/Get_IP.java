
import java.net.*;

public class Get_IP
{
    public static void main(String args[])
    {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            System.out.println(addr.getHostAddress());
            System.out.println(hostname);
        }catch(UnknownHostException e)
        {
             //throw Exception
        }


    }
}