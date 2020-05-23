import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate2 {

@SerializedName("no")
@Expose
private String no;
@SerializedName("effectiveDate")
@Expose
private String effectiveDate;
@SerializedName("bid")
@Expose
private Double bid;
@SerializedName("ask")
@Expose
private Double ask;

public String getNo() {
return no;
}

public void setNo(String no) {
this.no = no;
}

public String getEffectiveDate() {
return effectiveDate;
}

public void setEffectiveDate(String effectiveDate) {
this.effectiveDate = effectiveDate;
}

public Double getBid() {
return bid;
}

public void setBid(Double bid) {
this.bid = bid;
}

public Double getAsk() {
return ask;
}

public void setAsk(Double ask) {
this.ask = ask;
}

}
