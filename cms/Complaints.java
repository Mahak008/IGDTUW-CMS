package cms;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Complaints implements Serializable {
	String dept;
	int cNo;
	String comp;
	String soln;

	public Complaints(int cNo, String dept, String comp, String soln) {
		this.cNo = cNo;
		this.dept = dept;
		this.comp = comp;
		this.soln = soln;
	}

	@Override
	public String toString() {
		return (this.cNo + " " + this.dept + " " + this.comp + " " + this.soln);
	}
}