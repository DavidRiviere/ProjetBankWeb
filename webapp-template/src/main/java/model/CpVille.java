package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;

/**
 * The persistent class for the cpville database table.
 * 
 */
@Entity
@Table(name = "cpville")
@NamedQuery(name = "CpVille.findAll", query = "SELECT t FROM CpVille t")
public class CpVille implements Serializable {

	private static final long serialVersionUID = -7208703135643719589L;
	private int id;
	private String zip;
	private String city;

	@SuppressWarnings("unused")
	public CpVille() {}

	/**
	 * @param zip
	 * @param city
	 */
	public CpVille(String zip, String city) {
		this.setZip();
		this.setCity(); 
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	public void setId(int id) {
		this.id = id;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip() {
		checkZip(getZip());
		this.zip = Formater.removeUsualSeparators(getZip());
	}

	public String getCity() {
		return this.city;
	}

	public void setCity() {
		checkCity(getCity());
		this.city = Formater.formatNameCase(getCity());
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
}