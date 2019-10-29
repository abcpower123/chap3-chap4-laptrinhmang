package ex6;

import java.net.InetAddress;
import java.util.Date;

public class HistoryWeb {
private InetAddress address;
private Date LastVisit;
public InetAddress getAddress() {
	return address;
}
public void setAddress(InetAddress address) {
	this.address = address;
}
public Date getLastVisit() {
	return LastVisit;
}
public void setLastVisit(Date lastVisit) {
	LastVisit = lastVisit;
}
public HistoryWeb(InetAddress address, Date lastVisit) {
	super();
	this.address = address;
	LastVisit = lastVisit;
}
public HistoryWeb() {
	super();
}

}
