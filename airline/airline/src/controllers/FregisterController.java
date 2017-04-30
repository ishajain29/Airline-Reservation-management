package controllers;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.stage.StageStyle;


public class FregisterController {
	@FXML
	private TextField name, source, destination,fare,seat,dept,arr;
	//private Label show1;
	BufferedReader in;
	PrintWriter out;
	Socket s;
	@FXML private Button login;
	public void reg(){
		String n,so,des,dep,a,f,se;
		try{ 
			String hostName = "127.0.0.1";
			int portNumber = 7000;
	        s = new Socket(hostName, portNumber);
			//SocketClass sc=new SocketClass();
			//s=sc.getSocket();
	        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
	        //BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));;
	        //out.println("login");
	        n = name.getText();
	        so = source.getText();
	        des = destination.getText();
	        dep = dept.getText();
	        a = arr.getText();
	        f = fare.getText();
	        se = seat.getText();
	        
	        
	        out.println(n);
	        out.println(so);
	        out.println(des);
	        out.println(se);
	        out.println(f);
	        out.println(dep);
	        out.println(a);
	        
	      
	        
	        //show1.setText(sh);
	        name.setText("Success");
	        
		}catch(Exception e){}
		
	}


}
