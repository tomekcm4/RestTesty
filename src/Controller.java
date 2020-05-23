

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
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
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private Label temp;
    @FXML
    private Label realTemp;
    @FXML
    private Button btn;
    @FXML LineChart<String, Double> lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	

    }

    @FXML
    void akcjaa() {
    	 lineChart.getData().clear();
    	 lineChart.setAnimated(false);
		 XYChart.Series<String, Double> series = new XYChart.Series<String,Double>(); 
		 series.setName("Wykres funta z ostatnich 15 dni");
		 
    	
		LocalDate lt1 = LocalDate.now();
		LocalDate lt2 = LocalDate.now().minusDays(20);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString1 = lt1.format(formatter);
		String formattedString2 = lt2.format(formatter);
    	
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.nbp.pl/api/exchangerates/rates/a/gbp/" + formattedString2 + "/" + formattedString1);

        
        System.out.println(target.request(MediaType.APPLICATION_JSON).get(String.class));


        Response response  = target.request(MediaType.APPLICATION_JSON).get();
        String r = response.readEntity(String.class);
        Gson gson = new Gson();
        ExchangeRatesSeries exchangeRatesSeries = gson.fromJson(r, ExchangeRatesSeries.class);
        
        List<Rate> lr = new ArrayList<Rate>();
        lr = exchangeRatesSeries.getRates();
        int i = 1;
        for(Rate x: lr) {
        	
        realTemp.setText("Sredni kurs Funta na dzis to " + x.getMid().toString() + "zl");
        System.out.println(x.getMid());
        LocalDate lt3 = lt2.plusDays(i);
		i++;
		String formattedString3 = lt3.format(formatter);
        series.getData().add(new XYChart.Data<String, Double>(formattedString3, x.getMid()));
		System.out.println(formattedString3);
        }
        lineChart.getData().add(series);
        
		/*
		 * List<ExchangeRatesSeries> cenazlota = gson.fromJson(r, new
		 * TypeToken<List<ExchangeRatesSeries>>() {}.getType());
		 * List<ExchangeRatesSeries> cz = new ArrayList<ExchangeRatesSeries>();
		 * cz.add(cenazlota.get(0));
		 * 
		 * for(ExchangeRatesSeries o: cz) {
		 * 
		 * realTemp.setText(o.getCode()); }
		 */

    }
    
    
    @FXML
    void dodaj(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("dolar.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root, 500, 475));
        stage1.setTitle("Dollaa");
        stage1.show();
    }
    @FXML
    void pogoda(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Pog.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root, 650, 175));
        stage1.setTitle("Dollaa");
        stage1.show();
    }
    
    @FXML
    void idz(ActionEvent event) throws IOException {
    	
        	Parent root = FXMLLoader.load(getClass().getResource("Info.fxml"));
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root, 650, 375));
            stage1.setTitle("Info");
            stage1.show();
    }

}
