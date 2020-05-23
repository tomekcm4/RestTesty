import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.Clouds;
import com.example.Example;
import com.example.Main;
import com.example.Sys;
import com.example.Weather;
import com.example.Wind;
import com.google.gson.Gson;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class InfoController implements Initializable {
	
	  @FXML
	    private TreeView<String> TreeV;
	  
	  
	  

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	        Client client = ClientBuilder.newClient();
	        WebTarget target = client.target("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02");

	        
	        System.out.println(target.request(MediaType.APPLICATION_JSON).get(String.class));


	        Response response  = target.request(MediaType.APPLICATION_JSON).get();
	        String r = response.readEntity(String.class);
	        Gson gson = new Gson();
	        Example e = gson.fromJson(r, Example.class);

	        Main listaPogoda = e.getMain();
	        Wind w = e.getWind();
	        Sys s  = e.getSys();


	        TreeItem<String> root = new TreeItem<String>("Dane glowne");
	        TreeItem<String> root2 = new TreeItem<String>("Wiatr");
	        TreeItem<String> root3 = new TreeItem<String>("Inne");
	        root.setExpanded(true);
	        root2.setExpanded(true);
	        root3.setExpanded(true);
	        root.getChildren().addAll(
	            new TreeItem<String>("wilgotnosc: " + listaPogoda.getHumidity().toString()),
	            new TreeItem<String>("cisnienie: " + listaPogoda.getPressure().toString()),
	            new TreeItem<String>("temperatura: " +listaPogoda.getTemp().toString())
	            
	        );
	        
	        root2.getChildren().addAll(
		            new TreeItem<String>("Kierunek: " + w.getDeg()),
		            new TreeItem<String>("Predkosc: " + w.getSpeed())
		            
		        );
	        
	        root3.getChildren().addAll(
		            new TreeItem<String>("Kraj: " + s.getCountry()),
		            new TreeItem<String>("ID: " + s.getId()),
		            new TreeItem<String>("Wiadomosc: " + s.getMessage()),
		            new TreeItem<String>("Wschod: " + s.getSunrise()),
		            new TreeItem<String>("Zachod: " + s.getSunset()),
		            new TreeItem<String>("typ: " + s.getType())
		            
		        );
	        
	        
	        TreeItem<String> dummyRoot = new TreeItem<>();
	        dummyRoot.getChildren().addAll(root, root2, root3);

	        TreeV.setRoot(dummyRoot);

System.out.println(TreeV.getTreeItem(1));
	        
	        
		
		
		
	}

}
