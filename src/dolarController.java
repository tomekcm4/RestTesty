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

import com.google.gson.Gson;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class dolarController implements Initializable {
	
	   @FXML
	    private Label K;

	    @FXML
	    private Label S;
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		LocalDate lt1 = LocalDate.now().minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString1 = lt1.format(formatter);

	
	        Client client = ClientBuilder.newClient();
	        System.out.println("https://api.nbp.pl/api/exchangerates/rates/c/usd/" + formattedString1);
	        WebTarget target = client.target("https://api.nbp.pl/api/exchangerates/rates/c/usd/" + formattedString1);

	        
	        System.out.println(target.request(MediaType.APPLICATION_JSON).get(String.class));


	        Response response  = target.request(MediaType.APPLICATION_JSON).get();
	        String r = response.readEntity(String.class);
	        Gson gson = new Gson();
	        Dolar d = gson.fromJson(r, Dolar.class);

	        List<Rate2> ll = new ArrayList<Rate2>();
	        ll = d.getRates();

	      for(Rate2 o: ll) {
	    	 
	    	  K.setText(o.getBid().toString());
	    	  S.setText(o.getAsk().toString());
	      }
	        
	        
		 
		
	}

}
