package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.scene.control.Label;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
@Override
public void start(Stage primaryStage) {
try {
GridPane gp = new GridPane();
Scene scene = new Scene(gp,800,400);
gp.setAlignment(Pos.TOP_LEFT);
    gp.setHgap(10);
    gp.setVgap(10);
    gp.setMinSize(10000,10000);
    gp.setPadding(new Insets(10,10,10,10));
   
    //Menu Bar
    Menu menu1 = new Menu("Home");
    Menu menu2 = new Menu("Book your stay");
    Menu menu3 = new Menu("Help");
   
    MenuBar menuBar = new MenuBar(menu1, menu2, menu3);
    VBox vBox = new VBox(menuBar);
   
    gp.add(vBox, 0, 0);
   
    //Getting the name of the Customer 
    Label name = new Label("Customer name");
    gp.add(name, 0, 1);
   
    TextField namet = new TextField();
    gp.add(namet, 1, 1);

    //Getting Customer details like email id, phone, address
    Label Email = new Label("Email id:");
    gp.add(Email, 0, 2);
    TextField Emailt = new TextField();
    gp.add(Emailt, 1, 2);
    Label Phone= new Label("Phone no:");
    gp.add(Phone, 0, 3);
    TextField Phonet = new TextField();
    gp.add(Phonet, 1, 3);
    Label address= new Label("Address:");
    gp.add(address, 0, 4);
    TextField addresst = new TextField();
    gp.add(addresst, 1, 4);
     
     
     
    //Getting the hotel room to be booked
   
    Label room = new Label("Select  the room:");
    gp.add(room, 0, 5);
   
    ToggleGroup tg = new ToggleGroup();
   
    RadioButton r1 = new RadioButton("Standard suite");
    RadioButton r2 = new RadioButton("Deluxe suite");
    RadioButton r3 = new RadioButton("Executive suite");
    RadioButton r4 = new RadioButton("Presidential suite");
   
    r1.setToggleGroup(tg);
    r2.setToggleGroup(tg);
    r3.setToggleGroup(tg);
    r4.setToggleGroup(tg);
   
    gp.add(r1, 1, 6);
    gp.add(r2, 1, 7);
    gp.add(r3, 1, 8);
    gp.add(r4, 1, 9);
   
   
    //Checkbox for Declaration
   
    Label terms = new Label("I hereby declare that all of the information given above is true to my knowledge");
    gp.add(terms, 0, 10);
    CheckBox cb = new CheckBox();
    gp.add(cb, 1, 10);
   
    //Buttons for submitting and canceling the entries
    Button b1 = new Button("Submit");
    Button b2 = new Button("Clear" );
    gp.add(b1, 0, 11);
    gp.add(b2, 1, 11);                     
   
    EventHandler<ActionEvent> Clearevent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            namet.clear();
            Emailt.clear();
            Phonet.clear();
            addresst.clear();
            r1.setSelected(false);
            r2.setSelected(false);
            r3.setSelected(false);
            r4.setSelected(false);
            cb.setSelected(false);
            cb.setSelected(false);
        }
    };

    // when button is pressed
    b2.setOnAction(Clearevent);
    
    b1.setOnAction(new EventHandler<ActionEvent>() 
	{
		@SuppressWarnings("deprecation")
		public void handle(ActionEvent e)
		{
			try
			{
				//To load driver class
				Class.forName("com.mysql.jdbc.Driver");
				//Establishing connection with mysql
				final Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Taj","root","12345");
				String sql ="insert into booking(CustName, email, phone, address, room) values(?,?,?,?,?)";
				// preparing sql statement 
				PreparedStatement p=con.prepareStatement(sql);
				p.setString(1,namet.getText());
				p.setString(2,Emailt.getText()); 
				p.setString(3,Phonet.getText());
				p.setString(4,addresst.getText());
				RadioButton selectedRadioButton = (RadioButton) tg.getSelectedToggle();
				p.setString(5, selectedRadioButton.getText());
				p.executeUpdate();
			}
			catch(Exception s)
			{
				s.printStackTrace();
			}
		}
	}); 

    
    primaryStage.setTitle("Hotel Booking Page");
    primaryStage.setScene(scene);
    primaryStage.show();
	} catch(Exception e) {
		e.printStackTrace();
	}
	}

	public static void main(String[] args) {
			launch(args);
	}
}