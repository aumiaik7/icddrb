package com.icddrb.app.wbendLineenvsamplingd2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MyListTracker implements Serializable {

	public String pName;
	public boolean addCycleStarted;
	public String userSpecificId;
	public String dataId;
	public int totalHHMember;
	public ArrayList<Integer> truetracker = new ArrayList<Integer>();
	public boolean checker;
	public ArrayList<Integer> SLNOSTACK = new ArrayList<Integer>();
	public ArrayList<String> secMap1 = new ArrayList<String>();
	public ArrayList<Integer> secMap2 = new ArrayList<Integer>();
	public String houseHoldToLook;
	public LinkedHashMap<Integer, QuestionData> questionMap = new LinkedHashMap<Integer, QuestionData>();
	public String mode;
	public ArrayList<String> qskipList = new ArrayList<String>();
	public int currentSLNo;
	public boolean langBng;

	public MyListTracker() {
		this.pName = CommonStaticClass.pName;
		this.addCycleStarted = CommonStaticClass.addCycleStarted;
		this.userSpecificId = CommonStaticClass.userSpecificId;
		this.dataId = CommonStaticClass.dataId;
		this.totalHHMember = CommonStaticClass.totalHHMember;
		this.truetracker = CommonStaticClass.truetracker;
		this.checker = CommonStaticClass.checker;
		this.SLNOSTACK = CommonStaticClass.SLNOSTACK;
		this.secMap1 = CommonStaticClass.secMap1;
		this.secMap2 = CommonStaticClass.secMap2;
		this.houseHoldToLook = CommonStaticClass.houseHoldToLook;
		this.questionMap = CommonStaticClass.questionMap;
		this.mode = CommonStaticClass.mode;
		this.qskipList = CommonStaticClass.qskipList;
		this.currentSLNo = CommonStaticClass.currentSLNo;
		this.langBng = CommonStaticClass.langBng;
	}

	public String getpName() {
		return pName;
	}

	public boolean isAddCycleStarted() {
		return addCycleStarted;
	}

	public String getUserSpecificId() {
		return userSpecificId;
	}

	public String getDataId() {
		return dataId;
	}

	// public int getCurrentHHMemberLine() {
	// return currentHHMemberLine;
	// }

	public int getTotalHHMember() {
		return totalHHMember;
	}

	public ArrayList<Integer> getTruetracker() {
		return truetracker;
	}

	public boolean isChecker() {
		return checker;
	}

	public ArrayList<Integer> getSLNOSTACK() {
		return SLNOSTACK;
	}

	public ArrayList<String> getSecMap1() {
		return secMap1;
	}

	public ArrayList<Integer> getSecMap2() {
		return secMap2;
	}

	public String getHouseHoldToLook() {
		return houseHoldToLook;
	}

	public HashMap<Integer, QuestionData> getQuestionMap() {
		return questionMap;
	}

	public String getMode() {
		return mode;
	}

	public ArrayList<String> getQskipList() {
		return qskipList;
	}

	public int getCurrentSLNo() {
		return currentSLNo;
	}

	public boolean isLangBng() {
		return langBng;
	}
}
