package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
//import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;


public class SearchController {
	@FXML
	private TextField from, to, depart;
	//private Label show1;
	BufferedReader in;
	PrintWriter out;
	Socket s;
	@FXML private Button search, cbook;
	@FXML Label ffrom,fto,fdepart,head1,head2,head3, row00,row01,row02,row03,row04,row10,row11,row12,row13,row14,row20,row21,row22,row23,row24,row30,row31,row32,row33,row34,row40,row41,row42,row43,row44;
	@FXML Button book;
	@FXML ComboBox<String> cb;
	
	@FXML
    public void initialize(){
	row00.setVisible(false);row01.setVisible(false);row02.setVisible(false);row03.setVisible(false);row04.setVisible(false);
	row10.setVisible(false);row11.setVisible(false);row12.setVisible(false);row13.setVisible(false);row14.setVisible(false);
	row20.setVisible(false);row21.setVisible(false);row22.setVisible(false);row23.setVisible(false);row24.setVisible(false);
	row30.setVisible(false);row31.setVisible(false);row32.setVisible(false);row33.setVisible(false);row34.setVisible(false);
	row40.setVisible(false);row41.setVisible(false);row42.setVisible(false);row43.setVisible(false);row44.setVisible(false);
	head1.setVisible(false);book.setVisible(false);head3.setVisible(false);cb.setVisible(false);cbook.setVisible(false);
	}
	public void searching(){
		String f,t,d; 
		
		try{ 
			String hostName = "127.0.0.1";
			int portNumber = 6070;
	        s = new Socket(hostName, portNumber);
			//SocketClass sc=new SocketClass();
			//s=sc.getSocket();
	        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));;
	        //out.println("login");
	        f = from.getText();
	        t = to.getText();
	        d = depart.getText();
	       
	        
	        out.println(f);
	        out.println(t);
	        out.println(d);
	        
	        if(true){
	        	head2.setVisible(false);ffrom.setVisible(false);fto.setVisible(false);fdepart.setVisible(false);search.setVisible(false);
	        	from.setVisible(false);to.setVisible(false);depart.setVisible(false);
	        	row00.setVisible(true);row01.setVisible(true);row02.setVisible(true);row03.setVisible(true);row04.setVisible(true);
	    		row10.setVisible(true);row11.setVisible(true);row12.setVisible(true);row13.setVisible(true);row14.setVisible(true);
	    		row20.setVisible(true);row21.setVisible(true);row22.setVisible(true);row23.setVisible(true);row24.setVisible(true);
	    		row30.setVisible(true);row31.setVisible(true);row32.setVisible(true);row33.setVisible(true);row34.setVisible(true);
	    		row40.setVisible(true);row41.setVisible(true);row42.setVisible(true);row43.setVisible(true);row44.setVisible(true);
	    		head1.setVisible(true);book.setVisible(true);
	            row00.setText(in.readLine());
	            row01.setText(in.readLine());row02.setText(in.readLine());row03.setText(in.readLine());row04.setText(in.readLine());
	            row10.setText( in.readLine());row11.setText( in.readLine());row12.setText( in.readLine());row13.setText( in.readLine());row14.setText( in.readLine());
	            row20.setText( in.readLine());row21.setText( in.readLine());row22.setText( in.readLine());row23.setText( in.readLine());row24.setText( in.readLine());
	            row30.setText( in.readLine());row31.setText( in.readLine());row32.setText( in.readLine());row33.setText( in.readLine());row34.setText( in.readLine());
	            row40.setText( in.readLine());row41.setText( in.readLine());row42.setText( in.readLine());row43.setText( in.readLine());row44.setText( in.readLine());
	        }  
	        
	        
		}catch(Exception e){}
		
	}
	public void booking(){
		row00.setVisible(false);row01.setVisible(false);row02.setVisible(false);row03.setVisible(false);row04.setVisible(false);
		row10.setVisible(false);row11.setVisible(false);row12.setVisible(false);row13.setVisible(false);row14.setVisible(false);
		row20.setVisible(false);row21.setVisible(false);row22.setVisible(false);row23.setVisible(false);row24.setVisible(false);
		row30.setVisible(false);row31.setVisible(false);row32.setVisible(false);row33.setVisible(false);row34.setVisible(false);
		row40.setVisible(false);row41.setVisible(false);row42.setVisible(false);row43.setVisible(false);row44.setVisible(false);
		head1.setVisible(false);book.setVisible(false);
		head2.setVisible(false);ffrom.setVisible(false);fto.setVisible(false);fdepart.setVisible(false);search.setVisible(false);
    	from.setVisible(false);to.setVisible(false);depart.setVisible(false);
    	head3.setVisible(true);cb.setVisible(true);cbook.setVisible(true);
    	ObservableList<String> options = 
    		    FXCollections.observableArrayList(
    		        "Emirates",
    		        "Air America"
    		        //row20.getText(),
    		        //row30.getText(),
    		       //row40.getText()
    		    );
    	cb.setItems(options);
	}
	public void done(){
		try{ 
			String sh;
			String hostName = "127.0.0.1";
			int portNumber = 6080;
	        s = new Socket(hostName, portNumber);
			//SocketClass sc=new SocketClass();
			//s=sc.getSocket();
	        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));;String sel =cb.getSelectionModel().getSelectedItem();
	        out.println(sel);
	        sh = in.readLine();
	        if(sh.equalsIgnoreCase("Success")){
	        	row22.setText("Booking Done");
	        }
	        
		}catch(Exception ex){}
	}
}
