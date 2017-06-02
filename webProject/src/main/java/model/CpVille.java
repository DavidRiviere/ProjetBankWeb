package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.ws.rs.core.Link;

import util.Formater;

/**
 * The persistent class for the cpville database table.
 * 
 */
@Entity
@Table(name = "cpville")
@NamedQuery(name = "CpVille.findAll", query = "SELECT t FROM CpVille t")
public class CpVille implements Serializable, Identifiable {

	private static final long serialVersionUID = -7208703135643719589L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String zip;
	private String city;
	@Transient
	private Link selfLink;

	public CpVille() {}

	/**
	 * @param zip
	 * @param city
	 */
	public CpVille(String zip, String city) {
		this.setZip(zip);
		this.setCity(city); 
	}

	
	public Long getId() {
		return this.id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		checkZip(zip);
		this.zip = zip;//Formater.removeUsualSeparators(zip);
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		//checkCity(city);
		this.city =city;// Formater.formatNameCase(city);
	}

	private static void checkZip(String zip) throws IllegalArgumentException {
		if (zip.isEmpty() || !isValidZip(zip)) {
			throw new IllegalArgumentException("zip must be valid");
		}
	}

	private static void checkCity(String city) throws IllegalArgumentException {
		if (city.isEmpty()) {
			throw new IllegalArgumentException("city cannot be empty");
		}
	}

	public static boolean isValidZip(String zip) {
		return zip.length() <= 50;
	}

	public Link getSelfLink() {
		return selfLink;
	}

	public void setSelfLink(Link selfLink) {
		this.selfLink = selfLink;
	}
}