package controllers;
import java.net.Socket;

public class SocketClass {
	Socket s;
	String hostName = "127.0.0.1";
    int portNumber = 6000;
       public Socket getSocket()
    {
    	try{
        	s=new Socket(hostName,portNumber);
        }
        catch(Exception e)
        {
        	
        }

    	return s;
    	
    	
    }
}
