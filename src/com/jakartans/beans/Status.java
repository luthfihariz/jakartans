package com.jakartans.beans;

public class Status {

	private int id;
	private User user;
	private int trafficRate;
	private int busRate;
	private int busAvailRate;
	private double latitude;
	private double longitude;
	private String status;
	private String timeStampDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTrafficRate() {
		return trafficRate;
	}

	public void setTrafficRate(int trafficRate) {
		this.trafficRate = trafficRate;
	}

	public int getBusRate() {
		return busRate;
	}

	public void setBusRate(int busRate) {
		this.busRate = busRate;
	}

	public int getBusAvailRate() {
		return busAvailRate;
	}

	public void setBusAvailRate(int busAvailRate) {
		this.busAvailRate = busAvailRate;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeStampDate() {
		return timeStampDate;
	}

	public void setTimeStampDate(String timeStampDate) {
		this.timeStampDate = timeStampDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
