package com.jakartans.beans;

public class Trayek {

	private int id;
	private String hackJackId;
	private String jenisAngkutan;
	private String jenisTrayek;
	private String noTrayek;
	private String namaTrayek;
	private String terminal;
	private String ruteBerangkat;
	private String ruteKembali;
	private int overallRate;
	private int avgTrafficRate;
	private int avgBusRate;
	private int avgBusAvailRate;
	private Status status;

	public int getOverallRate() {
		return overallRate;
	}

	public void setOverallRate(int overallRate) {
		this.overallRate = overallRate;
	}

	public int getAvgTrafficRate() {
		return avgTrafficRate;
	}

	public void setAvgTrafficRate(int avgTrafficRate) {
		this.avgTrafficRate = avgTrafficRate;
	}

	public int getAvgBusRate() {
		return avgBusRate;
	}

	public void setAvgBusRate(int avgBusRate) {
		this.avgBusRate = avgBusRate;
	}

	public int getAvgBusAvailRate() {
		return avgBusAvailRate;
	}

	public void setAvgBusAvailRate(int avgBusAvailRate) {
		this.avgBusAvailRate = avgBusAvailRate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHackJackId() {
		return hackJackId;
	}

	public void setHackJackId(String hackJackId) {
		this.hackJackId = hackJackId;
	}

	public String getJenisAngkutan() {
		return jenisAngkutan;
	}

	public void setJenisAngkutan(String jenisAngkutan) {
		this.jenisAngkutan = jenisAngkutan;
	}

	public String getNoTrayek() {
		return noTrayek;
	}

	public void setNoTrayek(String noTrayek) {
		this.noTrayek = noTrayek;
	}

	public String getNamaTrayek() {
		return namaTrayek;
	}

	public void setNamaTrayek(String namaTrayek) {
		this.namaTrayek = namaTrayek;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getRuteBerangkat() {
		return ruteBerangkat;
	}

	public void setRuteBerangkat(String ruteBerangkat) {
		this.ruteBerangkat = ruteBerangkat;
	}

	public String getRuteKembali() {
		return ruteKembali;
	}

	public void setRuteKembali(String ruteKembali) {
		this.ruteKembali = ruteKembali;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getJenisTrayek() {
		return jenisTrayek;
	}

	public void setJenisTrayek(String jenisTrayek) {
		this.jenisTrayek = jenisTrayek;
	}

}
