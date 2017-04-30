package controllers;

import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;


public class ResultController {
	@FXML
	private TextField from, to, seat, depart;
	//private Label show1;
	BufferedReader in;
	PrintWriter out;
	Socket s;
	@FXML private Button search;
	@FXML Label ffrom,fto,fdepart,fseat,head1,head2, row00,row01,row02,row03,row04,row10,row11,row12,row13,row14,row20,row21,row22,row23,row24,row30,row31,row32,row33,row34,row40,row41,row42,row43,row44;
	@FXML Button r0,r1,r2,r3,r4;
	public void searching(){}
}