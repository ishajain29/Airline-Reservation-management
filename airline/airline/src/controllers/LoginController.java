package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.stage.Modality;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;


public class LoginController {
	@FXML
	private TextField username, password;
	//private Label show1;
	BufferedReader in;
	PrintWriter out;
	Socket s;
	@FXML private Button login;
	public void verify(){
		String u,p,sh;
		try{ 
			String hostName = "127.0.0.1";
			int portNumber = 6000;
	        s = new Socket(hostName, portNumber);
			//SocketClass sc=new SocketClass();
			//s=sc.getSocket();
	        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));;
	        //out.println("login");
	        u = username.getText();
	        p = password.getText();
	        
	        out.println(u);
	        out.println(p);
	        sh = in.readLine();
	        if(sh.contentEquals("Login Successful")){
	        	Stage stage1 = (Stage) login.getScene().getWindow();
	            // do what you have to do
	            stage1.close();
		        Parent root = FXMLLoader.load(getClass().getResource("../fxml/search.fxml"));
	            Stage stage = new Stage();
	            //Stage main = stage;
	            stage.setTitle("HOME");
	            stage.setScene(new Scene(root, 600, 450));
	            stage.show();
	            // Hide this current window (if this is what you want)
	            //((Node)(event.getSource())).getScene().getWindow().hide();
	        }
	        //show1.setText(sh);
	        username.setText(sh);
	        
		}catch(Exception e){}
		
	}
	public void forgetpass(){
		
		try{ 
				Stage stage1 = (Stage) login.getScene().getWindow();
	            // do what you have to do
	            stage1.close();
		        Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
	            Stage stage = new Stage();
	            //Stage main = stage;
	            stage.setTitle("Home");
	            stage.setScene(new Scene(root, 600, 400));
	            stage.show();
	            // Hide this current window (if this is what you want)
	            //((Node)(event.getSource())).getScene().getWindow().hide();
	        
	        
	       
		}catch(Exception e){}
		
	}
	public void register(){
		
		try{ 
				Stage stage1 = (Stage) login.getScene().getWindow();
	            // do what you have to do
	            stage1.close();
			
		        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Register.fxml"));
	            Stage stage = new Stage();
	            //Stage main = stage;
	            stage.setTitle("Register");
	            stage.setScene(new Scene(root, 600, 450));
	            stage.show();
	            // Hide this current window (if this is what you want)
	            //((Node)(event.getSource())).getScene().getWindow().hide();
	       
		}catch(Exception e){}
		
	}
	public void flightrepregister(){
		
		try{ 
				Stage stage1 = (Stage) login.getScene().getWindow();
	            // do what you have to do
	            stage1.close();
		        Parent root = FXMLLoader.load(getClass().getResource("../fxml/frregister.fxml"));
	            Stage stage = new Stage();
	            //Stage main = stage;
	            stage.setTitle("HOME");
	            stage.setScene(new Scene(root, 600, 450));
	            stage.show();
	            // Hide this current window (if this is what you want)
	            //((Node)(event.getSource())).getScene().getWindow().hide();
	       
		}catch(Exception e){}
		
	}
}
