package builders;

import java.util.Date;
import java.util.List;

import model.Account;
import model.AccountType;
import model.Address;
import model.Agency;
import model.Bank;
import model.CountryCode;
import model.CpVille;
import model.Owner;
import model.Transaction;

public class AccountBuilder {
	private Long id;
	private String number;
	private String description;
	private double initialBalance;
	private double overdraft;
	private double interestRate;
	private double agioRate;
	private double alertThreshold;
	private CountryCode countryCode;
	private Date creationDate;
	private Agency agency;
	private AccountType accountType;
	private List<Transaction> transactions;
	private List<Owner> owners;

	public AccountBuilder() {
		this(null, "000", "nulllll", 0.0, 0.0, 0.0, 0.0, 0.0, new CountryCode("XX"), new Date(),
				new Agency("XX", "000", new Address("XXX", null, new CpVille("000", "XXX")), new Bank("XXX", "000")),
				new AccountType("X"), null, null);

	}

	public AccountBuilder(Long id, String number, String description, double initialBalance, double overdraft,
			double interestRate, double agioRate, double alertThreshold, CountryCode countryCode, Date creationDate,
			Agency agency, AccountType accountType, List<Transaction> transactions, List<Owner> owners) {
		super();
		this.id = id;
		this.number = number;
		this.description = description;
		this.initialBalance = initialBalance;
		this.overdraft = overdraft;
		this.interestRate = interestRate;
		this.agioRate = agioRate;
		this.alertThreshold = alertThreshold;
		this.countryCode = countryCode;
		this.creationDate = creationDate;
		this.agency = agency;
		this.accountType = accountType;
		this.transactions = transactions;
		this.owners = owners;
	}

	public AccountBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public AccountBuilder setNumber(String number) {
		this.number = number;
		return this;
	}

	public AccountBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	public AccountBuilder setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
		return this;
	}

	public AccountBuilder setOverdraft(double overdraft) {
		this.overdraft = overdraft;
		return this;
	}

	public AccountBuilder setInterestRate(double interestRate) {
		this.interestRate = interestRate;
		return this;
	}

	public AccountBuilder setAgioRate(double agioRate) {
		this.agioRate = agioRate;
		return this;
	}

	public AccountBuilder setAlertThreshold(double alertThreshold) {
		this.alertThreshold = alertThreshold;
		return this;
	}

	public AccountBuilder setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	public AccountBuilder setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public AccountBuilder setAgency(Agency agency) {
		this.agency = agency;
		return this;
	}

	public AccountBuilder setAccountType(AccountType accountType) {
		this.accountType = accountType;
		return this;
	}

	public AccountBuilder setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
		return this;
	}

	public AccountBuilder setOwners(List<Owner> owners) {
		this.owners = owners;
		return this;
	}

	public Account build() {
		Account a = new Account();
		a.setId(id);
		a.setOwners(owners);
		a.setAccountType(accountType);
		a.setAgency(agency);
		a.setAgioRate(agioRate);
		a.setAlertThreshold(alertThreshold);
		a.setCountryCode(countryCode);
		a.setCreationDate(creationDate);
		a.setDescription(description);
		a.setInitialBalance(initialBalance);
		a.setNumber(number);
		a.setInterestRate(interestRate);
		a.setTransactions(transactions);
		a.setOverdraft(overdraft);

		return a;

	}
}
