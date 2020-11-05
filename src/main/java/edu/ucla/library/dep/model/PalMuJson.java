package edu.ucla.library.dep.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PalMuJson {
	
	private int total;
	private int limit;
	private PalMuRecord[] results;
	@Override
	public String toString() {
		return "PalMuJson [total=" + total + ", limit=" + limit + ", results=" + Arrays.toString(results) + "]";
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public PalMuRecord[] getResults() {
		return results;
	}
	public void setResults(PalMuRecord[] results) {
		this.results = results;
	}

}
