
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dolar {

@SerializedName("table")
@Expose
private String table;
@SerializedName("currency")
@Expose
private String currency;
@SerializedName("code")
@Expose
private String code;
@SerializedName("rates")
@Expose
private List<Rate2> rates = null;

public String getTable() {
return table;
}

public void setTable(String table) {
this.table = table;
}

public String getCurrency() {
return currency;
}

public void setCurrency(String currency) {
this.currency = currency;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public List<Rate2> getRates() {
	return rates;
}

public void setRates(List<Rate2> rates) {
	this.rates = rates;
}



}