package com.donalwebapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "wwwdata")
public class WorldWideWebData implements Serializable{

	@Temporal(TemporalType.TIMESTAMP)
	private int test_id;	
	private Date test_date;	
	private int client_ip_id;
	private int download_kbps;
	private int upload_kbps;
	private int latency;
	private String server_name;
	private String server_country;
	private String server_country_code;
	private Double server_latitude;
	private Double server_longitude;
	private String server_sponsor_name;
	private String user_agent;
	private String isp_name;
	private int client_net_speed;
	private int is_isp;
	private String client_country;
	private String client_country_code;
	private String client_region_name;
	private String client_region_code;
	private String client_city;
	private Double client_latitude;
	private Double client_longitude;
	private int miles_between;
	
	
	/**
	 * ================
	 * Getters & Setters Begin
	 * ================
	 */
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public Date getTest_date() {
		return test_date;
	}
	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}
	public int getClient_ip_id() {
		return client_ip_id;
	}
	public void setClient_ip_id(int client_ip_id) {
		this.client_ip_id = client_ip_id;
	}
	public int getDownload_kbps() {
		return download_kbps;
	}
	public void setDownload_kbps(int download_kbps) {
		this.download_kbps = download_kbps;
	}
	public int getUpload_kbps() {
		return upload_kbps;
	}
	public void setUpload_kbps(int upload_kbps) {
		this.upload_kbps = upload_kbps;
	}
	public int getLatency() {
		return latency;
	}
	public void setLatency(int latency) {
		this.latency = latency;
	}
	public String getServer_name() {
		return server_name;
	}
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}
	public String getServer_country() {
		return server_country;
	}
	public void setServer_country(String server_country) {
		this.server_country = server_country;
	}
	public String getServer_country_code() {
		return server_country_code;
	}
	public void setServer_country_code(String server_country_code) {
		this.server_country_code = server_country_code;
	}
	public Double getServer_latitude() {
		return server_latitude;
	}
	public void setServer_latitude(Double server_latitude) {
		this.server_latitude = server_latitude;
	}
	public Double getServer_longitude() {
		return server_longitude;
	}
	public void setServer_longitude(Double server_longitude) {
		this.server_longitude = server_longitude;
	}
	public String getServer_sponsor_name() {
		return server_sponsor_name;
	}
	public void setServer_sponsor_name(String server_sponsor_name) {
		this.server_sponsor_name = server_sponsor_name;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public String getIsp_name() {
		return isp_name;
	}
	public void setIsp_name(String isp_name) {
		this.isp_name = isp_name;
	}
	public int getClient_net_speed() {
		return client_net_speed;
	}
	public void setClient_net_speed(int client_net_speed) {
		this.client_net_speed = client_net_speed;
	}
	public int getIs_isp() {
		return is_isp;
	}
	public void setIs_isp(int is_isp) {
		this.is_isp = is_isp;
	}
	public String getClient_country() {
		return client_country;
	}
	public void setClient_country(String client_country) {
		this.client_country = client_country;
	}
	public String getClient_country_code() {
		return client_country_code;
	}
	public void setClient_country_code(String client_country_code) {
		this.client_country_code = client_country_code;
	}
	public String getClient_region_name() {
		return client_region_name;
	}
	public void setClient_region_name(String client_region_name) {
		this.client_region_name = client_region_name;
	}
	public String getClient_region_code() {
		return client_region_code;
	}
	public void setClient_region_code(String client_region_code) {
		this.client_region_code = client_region_code;
	}
	public String getClient_city() {
		return client_city;
	}
	public void setClient_city(String client_city) {
		this.client_city = client_city;
	}
	public Double getClient_latitude() {
		return client_latitude;
	}
	public void setClient_latitude(Double client_latitude) {
		this.client_latitude = client_latitude;
	}
	public Double getClient_longitude() {
		return client_longitude;
	}
	public void setClient_longitude(Double client_longitude) {
		this.client_longitude = client_longitude;
	}
	public int getMiles_between() {
		return miles_between;
	}
	public void setMiles_between(int miles_between) {
		this.miles_between = miles_between;
	}
	/**
	 * ================
	 * Getters & Setters Ends
	 * ================
	 */
	
	
}
