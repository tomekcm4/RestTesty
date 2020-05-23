import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.Example;
import com.example.Main;
import com.example.Wind;
import com.google.gson.Gson;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PogodaController implements Initializable {
	
	
	@FXML
    private Label l;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		LocalDate lt1 = LocalDate.now().minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString1 = lt1.format(formatter);

	
	        Client client = ClientBuilder.newClient();
	        WebTarget target = client.target("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02");

	        
	        System.out.println(target.request(MediaType.APPLICATION_JSON).get(String.class));


	        Response response  = target.request(MediaType.APPLICATION_JSON).get();
	        String r = response.readEntity(String.class);
	        Gson gson = new Gson();
	        Example e = gson.fromJson(r, Example.class);

	        Main listaPogoda = e.getMain();
	        Wind w = e.getWind();
	      
	        Double a =listaPogoda.getTempMax() - 273.15;
	        l.setText("Temperatura maksymala na dzis to " + a.toString() + "°C" + ", wiatr " + w.getSpeed());
		
	}

}
