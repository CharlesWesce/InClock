package com.example.clockin.module.clock.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockModel {

	private String id;
	private long inTime;
	private long outTime;
	private int delayTime;
	private String clockDate;
	private int month;

	public ClockModel() {
	}

	public ClockModel(long inTime, long outTime, String clockDate,int month) {
		super();
		this.inTime = inTime;
		this.outTime = outTime;
		this.clockDate = clockDate;
		this.month = month;
	}

	public int getMonth() {
		String s[] = clockDate.split("-");
		return Integer.valueOf(s[1]);
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getInTime() {
		return inTime;
	}

	public void setInTime(long inTime) {
		this.inTime = inTime;
	}

	public long getOutTime() {
		return outTime ;
	}

	public void setOutTime(long outTime) {
		this.outTime = outTime;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public String getClockDate() {
		return clockDate;
	}

	public void setClockDate(String clockDate) {
		this.clockDate = clockDate;
	}
}
