package com.gbce.market.model.enums;

/**
 * Enum representing the type of stock (Common or Preferred).
 */
public enum StockType {
	COMMON("Common"), PREFERRED("Preferred");

	private final String displayName;

	StockType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
