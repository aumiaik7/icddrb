package com.icddrb.app.hbislinelist.datatransfertool;

import java.util.ArrayList;

public class TransData {

	 private String DatabaseNm; //{ get; set; }
     private String TableNm; //{ get; set; }
     private String AssetId; //{ get; set; }
     private ArrayList<InsertStatement> IStatement; //{ get; set; }
	
     public TransData()
     {
    	 DatabaseNm =""; //{ get; set; }
         TableNm = ""; //{ get; set; }
         AssetId = ""; //{ get; set; }
         ArrayList<InsertStatement> IStatement = new ArrayList<InsertStatement>(); //{ get; set; }
     }
     public String getDatabaseNm() {
		return DatabaseNm;
	}
	public void setDatabaseNm(String databaseNm) {
		DatabaseNm = databaseNm;
	}
	public String getTableNm() {
		return TableNm;
	}
	public void setTableNm(String tableNm) {
		TableNm = tableNm;
	}
	public String getAssetId() {
		return AssetId;
	}
	public void setAssetId(String assetId) {
		AssetId = assetId;
	}
	public ArrayList<InsertStatement> getIStatement() {
		return IStatement;
	}
	public void setIStatement(ArrayList<InsertStatement> iStatement) {
		IStatement = iStatement;
	}
     
}
