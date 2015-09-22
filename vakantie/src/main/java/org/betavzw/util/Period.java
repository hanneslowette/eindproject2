package org.betavzw.util;

import java.sql.Date;

public class Period {

	private Date start;
	private Date end;

	public Period(Date start, Date end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public boolean overlaptStommeDatum(org.betavzw.util.Period period) {
		// i1.endTime.before(i2.beginTime) || i1.beginTime.after(i2.endTime);
		return end.before(period.start) || start.after(period.end);
	}

	// Tip: van start1.before(end2) && start2.before(end1);

}