package mvc.model;

import java.util.Currency;

import javax.persistence.Embeddable;

@Embeddable
public class Amount {
	private static final Currency defaultCurrency = Currency.getInstance("EUR");
	private long value;

	
	public Amount() {
		super();
	}

	public Amount(String integerPart, String fractionPart) throws NumberFormatException{
		long fractionPartAsLong =Long.valueOf(fractionPart);
		long integerPartAsLong = Long.valueOf(integerPart);
		
		if(fractionPart.length()<2){
			fractionPartAsLong*=10;
		}
		this.setValue((long) (integerPartAsLong * Math.pow(10, defaultCurrency.getDefaultFractionDigits())) + fractionPartAsLong);
	}

	public long getValue() {
		return value;
	}

	public void setValue(long balance) {
		this.value = balance;
	}

	public double getValueWithFractionDigits() {
		return this.value / Math.pow(10, defaultCurrency.getDefaultFractionDigits());
	}

	public Currency getCurrency() {
		return defaultCurrency;
	}

}
