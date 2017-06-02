package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the transactiontype database table.
 * 
 */
@Entity
@Table(name = "transactiontype")
@NamedQuery(name = "TransactionType.findAll", query = "SELECT t FROM TransactionType t")
public class TransactionType implements Serializable, Identifiable {

	private static final long serialVersionUID = 6948430153082483301L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

	public TransactionType() {}

	/**
	 * @param description
	 */
	public TransactionType(String description) {
		this.setDescription(description);;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		checkTransactionTypeDescription(description);
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
	private static void checkTransactionTypeDescription(String description) throws IllegalArgumentException {
		if (description.isEmpty()) {
			throw new IllegalArgumentException("The transaction type description must be valid");
		}
	}

}