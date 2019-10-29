package ex6;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;

public class HistoryWeb {
private InetAddress address;
private ArrayList<Date> Visits;
public InetAddress getAddress() {
	return address;
}
public void setAddress(InetAddress address) {
	this.address = address;
}

public HistoryWeb(InetAddress address, Date lastVisit) {
	super();
	Visits=new ArrayList<>();
	Visits.add(new Date());
	this.address = address;
}
public void addDate(Date visit) {
	Visits.add(visit);
}
public void showAllDateVisited() {
	System.out.println("Visit: ");
	for (Date date : Visits) {
		System.out.println(date);
	}
}
public HistoryWeb() {
	super();
}

}
