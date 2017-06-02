package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the frequency database table.
 * 
 */
@Entity
@Table(name = "frequency")
@NamedQuery(name = "Frequency.findAll", query = "SELECT f FROM Frequency f")

public class Frequency implements Serializable, Identifiable {

	private static final long serialVersionUID = 2903748555649709137L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String unit;

	public Frequency() {}

	/**
	 * @param unit
	 */
	public Frequency(String unit) {
		this.setUnit(unit);
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		checkUnit(unit);
		this.unit = unit;
	}

	@Override
	public String toString() {
		return unit;
	}

	private static void checkUnit(String unit) throws IllegalArgumentException {
		if (unit.isEmpty()) {
			throw new IllegalArgumentException("The unit frequency must be valid");
		}
	}
	


}