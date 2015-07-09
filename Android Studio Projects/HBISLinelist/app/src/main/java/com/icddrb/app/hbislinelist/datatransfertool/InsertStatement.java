package com.icddrb.app.hbislinelist.datatransfertool;

import java.util.List;

public class InsertStatement {

	 private String Statement;// { get; set; }
     private List<PrimaryClm> PrimaryClmList;// { get; set; }
	public String getStatement() {
		return Statement;
	}
	public void setStatement(String statement) {
		Statement = statement;
	}
	public List<PrimaryClm> getPrimaryClmList() {
		return PrimaryClmList;
	}
	public void setPrimaryClmList(List<PrimaryClm> primaryClmList) {
		PrimaryClmList = primaryClmList;
	}
     
}
