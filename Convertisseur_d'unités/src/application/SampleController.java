package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class SampleController implements Initializable{
    @FXML
    private ComboBox<String> cbo6;

    @FXML
    private ComboBox<String> cbo4;

    @FXML
    private ComboBox<String> cbo5;

    @FXML
    private ComboBox<String> cbo2;

    @FXML
    private ComboBox<String> cbo3;

    @FXML
    private ComboBox<String> cbo1;

    @FXML
    private TextField txt6;

    @FXML
    private TextField txt4;

    @FXML
    private TextField txt5;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt3;

    @FXML
    private TextField txt1;

    @FXML
    private TabPane tabPane;
    
    @FXML
    private Tab Home;
    
    @FXML
    private Tab Pression;
    
    @FXML
    private Tab Temperature;
    
    @FXML
    private Tab Vitesse;
    
    //les list des options d'unites de mesure
    private ObservableList<String> listPression = FXCollections.observableArrayList("Bar","Pascal","Livre par pouce carré","Atmosphère","Torr");
    private ObservableList<String> listTemperature = FXCollections.observableArrayList("Celsius", "Fahrenheit", "Kelvin");
    private ObservableList<String> listVitesse = FXCollections.observableArrayList("Miles par heure", "Pied par seconde", "Mètre par seconde", "Kilomètre par heure, Nœud");
    //valeur des unite de mesure relative au premier unite
    private double[] pression = {1.0,100000.0,14.504,0.986923,750};
    private double[] vitesse = {1.0,1.467,0.44704,1.609,0.868976}; 
    
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//"set" les combobox au list correct
		cbo1.setItems(listPression);
		cbo2.setItems(listPression);
		cbo3.setItems(listTemperature);
		cbo4.setItems(listTemperature);
		cbo5.setItems(listVitesse);
		cbo6.setItems(listVitesse);
		//"set" le default selection des combobox
		cbo1.getSelectionModel().selectFirst();
		cbo2.getSelectionModel().selectFirst();
		cbo3.getSelectionModel().selectFirst();
		cbo4.getSelectionModel().selectFirst();
		cbo5.getSelectionModel().selectFirst();
		cbo6.getSelectionModel().selectFirst();
	}	
	
	private double setTaux(ComboBox a, double tbl[])
	{//methode qui retourne la valeur selecter des unite des mesure
		return tbl[a.getSelectionModel().getSelectedIndex()];
	}
	
	@FXML
	void convert1(ComboBox a, ComboBox b, TextField c, TextField d, double tbl[]) 
	{//methode qui convert les unite de facon multiplicative
		double from = setTaux(a,tbl);
		double to = setTaux(b,tbl);
		double depart=0.0;
		if(c.getText().equals("")) //si le TextField est vide
			depart = 0.0;
		else
			depart = Double.parseDouble(c.getText());
		double dest = (to/from)*depart;//fait le raport entre "from" et "to" et multiplie par la valeur de textfield c
		d.setText(String.valueOf(dest));
	}
	
	@FXML
	void convertTemp(ComboBox a, ComboBox b, TextField c, TextField d) 
	{//methode de conversion pour le temperature
		double dest = 0;
		double cVal = Double.parseDouble(c.getText());
		if(a.getSelectionModel().getSelectedItem().equals("Celsius") && b.getSelectionModel().getSelectedItem().equals("Fahrenheit")) 
			d.setText(String.valueOf((cVal*1.8) + 32));//equation de celsius a fahrenheit
		else if (a.getSelectionModel().getSelectedItem().equals("Celsius") && b.getSelectionModel().getSelectedItem().equals("Kelvin"))
			d.setText(String.valueOf(cVal + 273.15));//equation de celsius a kelvin
		else if (a.getSelectionModel().getSelectedItem().equals("Fahrenheit") && b.getSelectionModel().getSelectedItem().equals("Celsius"))
			d.setText(String.valueOf((cVal-32)*5/9));//equation de fahrenheit a celsius
		else if (a.getSelectionModel().getSelectedItem().equals("Fahrenheit") && b.getSelectionModel().getSelectedItem().equals("Kelvin"))
			d.setText(String.valueOf((cVal-32)*5/9+273.15));//equation de fahrenheit a kelvin
		else if (a.getSelectionModel().getSelectedItem().equals("Kelvin") && b.getSelectionModel().getSelectedItem().equals("Celsius"))
			d.setText(String.valueOf(cVal-273.15));//equation de kelvin a celsius
		else if (a.getSelectionModel().getSelectedItem().equals("Kelvin") && b.getSelectionModel().getSelectedItem().equals("Fahrenheit"))
			d.setText(String.valueOf((cVal-273.15)*1.8+32));//equation de kelvin a fahrenheit
		else//si les combobox sont les meme unite de mesure
			d.setText(String.valueOf(cVal));
	}

	@FXML
	void quitter() {//methode pour quitter le programme
		//creer un alert qui demande au usager si il veut quitter
		Alert alert = new Alert(AlertType.CONFIRMATION, "Confirmer?", ButtonType.YES, ButtonType.CANCEL);
		alert.setHeaderText("Confirmer");
		alert.setTitle("Quitter");
		alert.setContentText("Est ce que t'est sure que tu veut quitter?");
		alert.showAndWait();
		//verifie si l'usager clique sur le bouton "YES"
		if (alert.getResult() == ButtonType.YES) {
		    System.exit(0);//quitter le programme
		}
	}
	
	@FXML
	void homeTab() {//methode qui change au tab "Home"
		tabPane.getSelectionModel().select(Home);
	}
	
	@FXML
	void pressionTab() {//methode qui change au tab "Pression"
	    tabPane.getSelectionModel().select(Pression);
	}
	
	@FXML
	void temperatureTab() {//methode qui change au tab "Temperature"
		tabPane.getSelectionModel().select(Temperature);
	}
	
	@FXML
	void vitesseTab() {//methode qui change au tab "Vitesse"
		tabPane.getSelectionModel().select(Vitesse);
	}
	
	@FXML
	void Convertir1() //methode de conversion fxml pour pression
	{
		convert1(cbo1,cbo2,txt1,txt2,pression);
	}
	
	@FXML
	void Convertir2() //methode de conversion fxml pour pression
	{
		convert1(cbo2,cbo1,txt2,txt1,pression);
	}
	
	@FXML
	void Convertir3() //methode de conversion fxml pour temperature
	{
		convertTemp(cbo3,cbo4,txt4,txt3);
	}
	
	@FXML
	void Convertir4() //methode de conversion fxml pour temperature
	{
		convertTemp(cbo4,cbo3,txt3,txt4);
	}
	
	@FXML
	void Convertir5() //methode de conversion fxml pour vitesse
	{
		convert1(cbo5,cbo6,txt5,txt6,vitesse);
	}
	
	@FXML
	void Convertir6() //methode de conversion fxml pour vitesse
	{
		convert1(cbo6,cbo5,txt6,txt5,vitesse);
	}
	@FXML
	private void verifNum(KeyEvent e) //verifie si le input sont des nombres
	{
		TextField txt = (TextField)e.getSource();

			txt.textProperty().addListener((observable,oldValue,newValue)->
			{
				if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$"))
				{
					txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));
				}
			});
	}
}
