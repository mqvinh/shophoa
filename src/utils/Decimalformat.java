package utils;

import java.text.DecimalFormat;

public class Decimalformat {
	public String change(int price) {
		DecimalFormat df = new DecimalFormat("#,###,###");
		return (df.format(price).replace(",", "."));
	}
}
