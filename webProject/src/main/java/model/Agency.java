package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;

@Entity
@Table(name = "agency")
@NamedQuery(name = "Agency.findAll", query = "SELECT t FROM Agency t")
public class Agency implements Serializable, Identifiable {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

	private static final long serialVersionUID = 458612991376198713L;
	private Long id;
	private String name;
	private String counterCode;
	private Address adress;
	private Bank bank;


	public Agency() {

	}

	/**
	 * Constructor
	 * 
	 * @param agencyName
	 * @param counterCode
	 */
	@Deprecated
	public Agency(String agencyName, String counterCode) {

		this.setCounterCode(Formater.removeUsualSeparators(counterCode));
		this.setName(Formater.formatNameCase(agencyName));
	}

	/**
	 * @param name
	 * @param counterCode
	 * @param adress
	 * @param bank
	 */
	public Agency(String name, String counterCode, Address adress, Bank bank) {
		this.setCounterCode(Formater.removeUsualSeparators(counterCode));
		this.setName(Formater.formatNameCase(name));
		this.setAdress(adress);
		this.setBank(bank);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	private static void checkName(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("The name of the agency cannot be empty");
		}
	}

	private static void checkCounterCode(String counterCode) throws IllegalArgumentException {
		if (counterCode.isEmpty()) {
			throw new IllegalArgumentException("The countercode cannot be empty");
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		//checkName(name);

		this.name = name;
	}

	public String getCounterCode() {
		return this.counterCode;
	}

	public void setCounterCode(String counterCode) {
		//checkCounterCode(counterCode);
		this.counterCode = counterCode;
	}

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "idAddress")
	public Address getAdress() {
		return this.adress;
	}

	public void setAdress(Address address) {
//		if (address == null) {
//			throw new NullPointerException("address cannot be null");
//		}
		this.adress = address;
	}

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "idBank")
	public Bank getBank() {
		return this.bank;
	}

	public void setBank(Bank bank) {
//		if (bank == null) {
//			throw new NullPointerException("bank cannot be null");
//		}
		this.bank = bank;
	}

}
