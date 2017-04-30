package controllers;

import java.io.BufferedReader;
//import java.io.InputStreamReader;
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
//import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.stage.Modality;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;


public class RegisterController {
	@FXML
	private TextField name,email,password,address,mobile,passport;
	
	//private Label show1;
	BufferedReader in1;
	PrintWriter out1;
	Socket s;
	@FXML private Button register;
	public void registration(){
		String n,e,a,m,pass,p;
		try{ 
			String hostName = "127.0.0.1";
			int portNumber = 6050;
	        s = new Socket(hostName, portNumber);
	        PrintWriter out1 = new PrintWriter(s.getOutputStream(), true);
	        //BufferedReader in1 = new BufferedReader(new InputStreamReader(s.getInputStream()));;
	        //out1.println("register");
	        n = name.getText();
	        e = email.getText();
	        p = password.getText();
	        pass = passport.getText();
	        a = address.getText();
	        m = mobile.getText();
	        
	        
	        out1.println(n);
	        out1.println(e);
	        out1.println(p);
	        out1.println(pass);
	        out1.println(a);
	        out1.println(m);
	        
	        	Stage stage1 = (Stage) register.getScene().getWindow();
	            // do what you have to do
	            stage1.close();
		        Parent root = FXMLLoader.load(getClass().getResource("../fxml/newlogin1.fxml"));
	            Stage stage = new Stage();
	            //Stage main = stage;
	            stage.setTitle("Login");
	            stage.setScene(new Scene(root, 600, 400));
	            stage.show();
	            // Hide this current window (if this is what you want)
	            //((Node)(event.getSource())).getScene().getWindow().hide();
	        
	        s.close();
		}catch(Exception ex){}
		
	}
	/*public void forgetpass(){
		
		try{ 
			
		        Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
	            Stage stage = new Stage();
	            //Stage main = stage;
	            stage.setTitle("HOME");
	            stage.setScene(new Scene(root, 600, 400));
	            stage.show();
	            // Hide this current window (if this is what you want)
	            //((Node)(event.getSource())).getScene().getWindow().hide();
	        
	        
	       
		}catch(Exception e){}
		
	}
	public void register(){
		
		try{ 
			
		        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Register.fxml"));
	            Stage stage = new Stage();
	            //Stage main = stage;
	            stage.setTitle("HOME");
	            stage.setScene(new Scene(root, 600, 450));
	            stage.show();
	            // Hide this current window (if this is what you want)
	            //((Node)(event.getSource())).getScene().getWindow().hide();
	       
		}catch(Exception e){}
		
	}
	public void flightrepregister(){
		
		try{ 
			
		        Parent root = FXMLLoader.load(getClass().getResource("../fxml/frregister.fxml"));
	            Stage stage = new Stage();
	            //Stage main = stage;
	            stage.setTitle("HOME");
	            stage.setScene(new Scene(root, 600, 450));
	            stage.show();
	            // Hide this current window (if this is what you want)
	            //((Node)(event.getSource())).getScene().getWindow().hide();
	       
		}catch(Exception e){}
		
	}*/
}
