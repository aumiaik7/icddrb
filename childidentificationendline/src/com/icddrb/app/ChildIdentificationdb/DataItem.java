package com.icddrb.app.ChildIdentificationdb;

public class DataItem {

	public String getEntryBy() {
		return EntryBy;
	}

	public String getEntryDate() {
		return EntryDate;
	}

	public String getEditBy() {
		return EditBy;
	}

	public String getEditDate() {
		return EditDate;
	}

	public String getChildNo() {
		return childNo;
	}

	private String childNo;

	private String numberofchildren;
	private String q12;
	private String q13;
	private String q14;
	private String q15;
	private String q16;
	private String q17;
	private String q18_days;
	private String q18_months;
	private String q18;
	private String q19_no_of_time;
	private String q19;
	private String q20_time1;
	private String q20_other_time1;
	private String q21_days_time1;
	private String q21_dontknow_time1;
	private String q20_time2;
	private String q20_other_time2;
	private String q21_days_time2;
	private String q21_dontknow_time2;
	private String q20_time3;
	private String q20_other_time3;
	private String q21_days_time3;
	private String q21_dontknow_time3;
	private String q20_time4;
	private String q20_other_time4;
	private String q21_days_time4;
	private String q21_dontknow_time4;
	private String q20_time5;
	private String q20_other_time5;
	private String q21_days_time5;
	private String q21_dontknow_time5;
	private String q22;
	private String q23;
	private String q24;
	private String q25;
	private String q26_days;
	private String q26_months;
	private String q26;
	private String q27;
	private String q27_other;
	private String q28;
	private String q29;

	private String EntryBy;
	private String EntryDate;
	private String EditBy;
	private String EditDate;
	
	private String q30A; private String q30B; private String q30C; private String q30D; private String q30E;

	private String q31A; private String q31B; private String q31C; private String q31D; private String q31E;

	private String q32A; private String q32B; private String q32C; private String q32D;private String q32E;

	private String q33A; private String q33B; private String q33C; private String q33D; private String q33E;

	private String q34A; private String q34B; private String q34C; private String q34D; private String q34E;

	private String q35A; private String q35B; private String q35C; private String q35D; private String q35E;

	private String q36A; private String q36B; private String q36C; private String q36D; private String q36E;

	private String q37A; private String q37B; private String q37C; private String q37D; private String q37E;

	private String q38A; private String q38B; private String q38C; private String q38D; private String q38E;

	private String q39A; private String q39B; private String q39C; private String q39D; private String q39E;

	private String q40A; private String q40B; private String q40C; private String q40D; private String q40E;

	private String q41A; private String q41B; private String q41C; private String q41D; private String q41E;

	private String q42_Days, q43_Days,  q42_Weeks, q43_Weeks;

	public DataItem(String childNo,

	String numberofchildren, String q12, String q13, String q14, String q15,
			String q16, String q17, String q18_days, String q18_months,
			String q18, String q19_no_of_time, String q19, String q20_time1,
			String q20_other_time1, String q21_days_time1,
			String q21_dontknow_time1, String q20_time2,
			String q20_other_time2, String q21_days_time2,
			String q21_dontknow_time2, String q20_time3,
			String q20_other_time3, String q21_days_time3,
			String q21_dontknow_time3, String q20_time4,
			String q20_other_time4, String q21_days_time4,
			String q21_dontknow_time4, String q20_time5,
			String q20_other_time5, String q21_days_time5,
			String q21_dontknow_time5, String q22, String q23, String q24,
			String q25, String q26_days, String q26_months, String q26,
			String q27, String q27_other, String q28, String q29,
			String EntryBy, String EntryDate, String EditBy, String EditDate, 
			String q30A,  String q30B,  String q30C,  String q30D, String q30E,

			 String q31A,  String q31B,  String q31C,  String q31D, String q31E,

			 String q32A,  String q32B,  String q32C,  String q32D,String q32E,

			 String q33A,  String q33B,  String q33C,  

			 String q34A,  String q34B,  String q34C,  String q34D, String q34E,

			 String q35A,  String q35B,  String q35C,  String q35D, String q35E,

			 String q36A,  String q36B,  String q36C,  String q36D, String q36E,

			 String q37A,  String q37B,  String q37C,  String q37D, String q37E,

			 String q38A,  String q38B,  String q38C,  String q38D, String q38E,

			 String q39A,  String q39B,  String q39C,  String q39D, String q39E,

			 String q40A,  String q40B,  String q40C,  String q40D, String q40E,

			 String q41A,  String q41B,  String q41C,  String q41D, String q41E,

			 String q42_Days,  String q43_Days, String q42_Weeks,  String q43_Weeks) {

		this.childNo = childNo;
		this.numberofchildren = numberofchildren;

		this.q12 = q12;
		this.q13 = q13;
		this.q14 = q14;
		this.q15 = q15;
		this.q16 = q16;
		this.q17 = q17;
		this.q18_days = q18_days;
		this.q18_months = q18_months;
		this.q18 = q18;
		this.q19_no_of_time = q19_no_of_time;
		this.q19 = q19;
		this.q20_time1 = q20_time1;
		this.q20_other_time1 = q20_other_time1;
		this.q21_days_time1 = q21_days_time1;
		this.q21_dontknow_time1 = q21_dontknow_time1;
		this.q20_time2 = q20_time2;
		this.q20_other_time2 = q20_other_time2;
		this.q21_days_time2 = q21_days_time2;
		this.q21_dontknow_time2 = q21_dontknow_time2;
		this.q20_time3 = q20_time3;
		this.q20_other_time3 = q20_other_time3;
		this.q21_days_time3 = q21_days_time3;
		this.q21_dontknow_time3 = q21_dontknow_time3;
		this.q20_time4 = q20_time4;
		this.q20_other_time4 = q20_other_time4;
		this.q21_days_time4 = q21_days_time4;
		this.q21_dontknow_time4 = q21_dontknow_time4;
		this.q20_time5 = q20_time5;
		this.q20_other_time5 = q20_other_time5;
		this.q21_days_time5 = q21_days_time5;
		this.q21_dontknow_time5 = q21_dontknow_time5;
		this.q22 = q22;
		this.q23 = q23;
		this.q24 = q24;
		this.q25 = q25;
		this.q26_days = q26_days;
		this.q26_months = q26_months;
		this.q26 = q26;
		this.q27 = q27;
		this.q27_other = q27_other;
		this.q28 = q28;
		this.q29 = q29;
		this.EntryBy = EntryBy;
		this.EntryDate = EntryDate;
		this.EditBy = EditBy;
		this.EditDate = EditDate;
		
		this.q30A=q30A;  
		this.q30B=q30B;
		this.q30C=q30C;
		this.q30D=q30D;
		this.q30E=q30E;
		
   	    this.q31A=q31A;  
		 this.q31B=q31B;  
		 this.q31C=q31C;  
		 this.q31D=q31D;
		 this.q31E=q31E;

		 this.q32A=q32A;  
		 this.q32B=q32B;  
		 this.q32C=q32C;  
		 this.q32D=q32D;
		 this.q32E=q32E;

		 this.q33A=q33A;  
		 this.q33B=q33B;  
		 this.q33C=q33C;  
		/* this.q33D=q33D;
		 this.q33E=q33E;*/
		 
		 this.q34A=q34A;  
		 this.q34B=q34B;  
		 this.q34C=q34C;  
		 this.q34D=q34D;
		 this.q34E=q34E;
		 
		 this.q35A=q35A;  
		 this.q35B=q35B;  
		 this.q35C=q35C;  
		 this.q35D=q35D;
		 this.q35E=q35E;
		 
		 this.q36A=q36A;  
		 this.q36B=q36B;  
		 this.q36C=q36C;  
		 this.q36D=q36D;
		 this.q36E=q36E;
		 

		 this.q37A=q37A;  
		 this.q37B=q37B;  
		 this.q37C=q37C;  
		 this.q37D=q37D;
		 this.q37E=q37E;
		 
		 this.q38A=q38A;  
		 this.q38B=q38B;  
		 this.q38C=q38C;  
		 this.q38D=q38D;
		 this.q38E=q38E;
		 

		 this.q39A=q39A;  
		 this.q39B=q39B;  
		 this.q39C=q39C;  
		 this.q39D=q39D;
		 this.q39E=q39E;

		 this.q40A=q40A;  
		 this.q40B=q40B;  
		 this.q40C=q40C;  
		 this.q40D=q40D;
		 this.q40E=q40E;

		 this.q41A=q41A;  
		 this.q41B=q41B;  
		 this.q41C=q41C;  
		 this.q41D=q41D;
		 this.q41E=q41E;

		 this.q42_Days=q42_Days;
		 this.q43_Days=q43_Days; 
		 this.q42_Weeks=q42_Weeks;
		 this.q43_Weeks=q43_Weeks;
	}

	public String getQ13() {
		return q13;
	}

	public void setQ13(String q13) {
		
	}

	public String getQ12() {
		return q12;
	}

	public void setQ12(String q12) {
		
	}

	public String getNumberofchildren() {
		return numberofchildren;
	}

	public void setNumberofchildren(String numberofchildren) {
		
	}

	public String getQ14() {
		return q14;
	}

	public void setQ14(String q14) {
		
	}

	public String getQ15() {
		return q15;
	}

	public void setQ15(String q15) {
		
	}

	public String getQ16() {
		return q16;
	}

	public void setQ16(String q16) {
		
	}

	public String getQ17() {
		return q17;
	}

	public void setQ17(String q17) {
		
	}

	public String getQ18_days() {
		return q18_days;
	}

	public void setQ18_days(String q18_days) {
		
	}

	public String getQ18_months() {
		return q18_months;
	}

	public void setQ18_months(String q18_months) {
		
	}

	public String getQ18() {
		return q18;
	}

	public void setQ18(String q18) {
		
	}

	public String getQ19_no_of_time() {
		return q19_no_of_time;
	}

	public void setQ19_no_of_time(String q19_no_of_time) {
		
	}

	public String getQ19() {
		return q19;
	}

	public void setQ19(String q19) {
		
	}

	public String getQ20_time1() {
		return q20_time1;
	}

	public void setQ20_time1(String q20_time1) {
		
	}

	public String getQ20_other_time1() {
		return q20_other_time1;
	}

	public void setQ20_other_time1(String q20_other_time1) {
		
	}

	public String getQ21_days_time1() {
		return q21_days_time1;
	}

	public void setQ21_days_time1(String q21_days_time1) {
		
	}

	public String getQ21_dontknow_time1() {
		return q21_dontknow_time1;
	}

	public void setQ21_dontknow_time1(String q21_dontknow_time1) {
		
	}

	public String getQ20_time2() {
		return q20_time2;
	}

	public void setQ20_time2(String q20_time2) {
		
	}

	public String getQ20_other_time2() {
		return q20_other_time2;
	}

	public void setQ20_other_time2(String q20_other_time2) {
	
	}

	public String getQ21_days_time2() {
		return q21_days_time2;
	}

	public void setQ21_days_time2(String q21_days_time2) {
		
	}

	public String getQ21_dontknow_time2() {
		return q21_dontknow_time2;
	}

	public void setQ21_dontknow_time2(String q21_dontknow_time2) {
	
	}

	public String getQ20_time3() {
		return q20_time3;
	}

	public void setQ20_time3(String q20_time3) {
		
	}

	public String getQ20_other_time3() {
		return q20_other_time3;
	}

	public void setQ20_other_time3(String q20_other_time3) {
		
	}

	public String getQ21_days_time3() {
		return q21_days_time3;
	}

	public void setQ21_days_time3(String q21_days_time3) {
		
	}

	public String getQ21_dontknow_time3() {
		return q21_dontknow_time3;
	}

	public void setQ21_dontknow_time3(String q21_dontknow_time3) {
		
	}

	public String getQ20_time4() {
		return q20_time4;
	}

	public void setQ20_time4(String q20_time4) {
		
	}

	public String getQ20_other_time4() {
		return q20_other_time4;
	}

	public void setQ20_other_time4(String q20_other_time4) {
		
	}

	public String getQ21_days_time4() {
		return q21_days_time4;
	}

	public void setQ21_days_time4(String q21_days_time4) {
		
	}

	public String getQ21_dontknow_time4() {
		return q21_dontknow_time4;
	}

	public void setQ21_dontknow_time4(String q21_dontknow_time4) {
		
	}

	public String getQ20_time5() {
		return q20_time5;
	}

	public void setQ20_time5(String q20_time5) {
		
	}

	public String getQ20_other_time5() {
		return q20_other_time5;
	}

	public void setQ20_other_time5(String q20_other_time5) {
	
	}

	public String getQ21_days_time5() {
		return q21_days_time5;
	}

	public void setQ21_days_time5(String q21_days_time5) {
		
	}

	public String getQ21_dontknow_time5() {
		return q21_dontknow_time5;
	}

	public void setQ21_dontknow_time5(String q21_dontknow_time5) {
		
	}

	public String getQ22() {
		return q22;
	}

	public void setQ22(String q22) {
		
	}

	public String getQ23() {
		return q23;
	}

	public void setQ23(String q23) {
	
	}

	public String getQ24() {
		return q24;
	}

	public void setQ24(String q24) {
		
	}

	public String getQ25() {
		return q25;
	}

	public void setQ25(String q25) {
		
	}

	public String getQ26_days() {
		return q26_days;
	}

	public void setQ26_days(String q26_days) {
		
	}

	public String getQ26_months() {
		return q26_months;
	}

	public void setQ26_months(String q26_months) {
		
	}

	public String getQ26() {
		return q26;
	}

	public void setQ26(String q26) {
		
	}

	public String getQ27() {
		return q27;
	}

	public void setQ27(String q27) {
		
	}

	public String getQ27_other() {
		return q27_other;
	}

	public void setQ27_other(String q27_other) {
		
	}

	public String getQ28() {
		return q28;
	}

	public void setQ28(String q28) {
		
	}

	public String getQ29() {
		return q29;
	}

	public void setQ29(String q29) {
		
	}

	public String getQ30A() {
		return q30A;
	}

	public void setQ30A(String q30A) {
		this.q30A = q30A;
	}

	public String getQ30B() {
		return q30B;
	}

	public void setQ30B(String q30B) {
		this.q30B = q30B;
	}

	public String getQ30C() {
		return q30C;
	}

	public void setQ30C(String q30C) {
		this.q30C = q30C;
	}

	public String getQ30D() {
		return q30D;
	}
	public String getQ30E() {
		return q30E;
	}

	public void setQ30D(String q30D) {
		this.q30D = q30D;
	}

	public String getQ31A() {
		return q31A;
	}

	public void setQ31A(String q31A) {
		this.q31A = q31A;
	}

	public String getQ31B() {
		return q31B;
	}

	public void setQ31B(String q31B) {
		this.q31B = q31B;
	}

	public String getQ31C() {
		return q31C;
	}

	public void setQ31C(String q31C) {
		this.q31C = q31C;
	}

	public String getQ31D() {
		return q31D;
	}
	

	public void setQ31D(String q31D) {
		this.q31D = q31D;
	}

	public String getQ32A() {
		return q32A;
	}

	public void setQ32A(String q32A) {
		this.q32A = q32A;
	}

	public String getQ32B() {
		return q32B;
	}

	public void setQ32B(String q32B) {
		this.q32B = q32B;
	}

	public String getQ32C() {
		return q32C;
	}

	public void setQ32C(String q32C) {
		this.q32C = q32C;
	}

	public String getQ32D() {
		return q32D;
	}

	public void setQ32D(String q32D) {
		this.q32D = q32D;
	}

	public String getQ33A() {
		return q33A;
	}

	public void setQ33A(String q33A) {
		this.q33A = q33A;
	}

	public String getQ33B() {
		return q33B;
	}

	public void setQ33B(String q33B) {
		this.q33B = q33B;
	}

	public String getQ33C() {
		return q33C;
	}

	public void setQ33C(String q33C) {
		this.q33C = q33C;
	}

	public String getQ33D() {
		return q33D;
	}

	public void setQ33D(String q33D) {
		this.q33D = q33D;
	}

	public String getQ34A() {
		return q34A;
	}

	public void setQ34A(String q34A) {
		this.q34A = q34A;
	}

	public String getQ34B() {
		return q34B;
	}

	public void setQ34B(String q34B) {
		this.q34B = q34B;
	}

	public String getQ34C() {
		return q34C;
	}

	public void setQ34C(String q34C) {
		this.q34C = q34C;
	}

	public String getQ34D() {
		return q34D;
	}

	public void setQ34D(String q34D) {
		this.q34D = q34D;
	}

	public String getQ35A() {
		return q35A;
	}

	public void setQ35A(String q35A) {
		this.q35A = q35A;
	}

	public String getQ35B() {
		return q35B;
	}

	public void setQ35B(String q35B) {
		this.q35B = q35B;
	}

	public String getQ35C() {
		return q35C;
	}

	public void setQ35C(String q35C) {
		this.q35C = q35C;
	}

	public String getQ35D() {
		return q35D;
	}

	public void setQ35D(String q35D) {
		this.q35D = q35D;
	}

	public String getQ36A() {
		return q36A;
	}

	public void setQ36A(String q36A) {
		this.q36A = q36A;
	}

	public String getQ36B() {
		return q36B;
	}

	public void setQ36B(String q36B) {
		this.q36B = q36B;
	}

	public String getQ36C() {
		return q36C;
	}

	public void setQ36C(String q36C) {
		this.q36C = q36C;
	}

	public String getQ36D() {
		return q36D;
	}

	public void setQ36D(String q36D) {
		this.q36D = q36D;
	}

	public String getQ37A() {
		return q37A;
	}

	public void setQ37A(String q37A) {
		this.q37A = q37A;
	}

	public String getQ37B() {
		return q37B;
	}

	public void setQ37B(String q37B) {
		this.q37B = q37B;
	}

	public String getQ37C() {
		return q37C;
	}

	public void setQ37C(String q37C) {
		this.q37C = q37C;
	}

	public String getQ37D() {
		return q37D;
	}

	public void setQ37D(String q37D) {
		this.q37D = q37D;
	}

	public String getQ38A() {
		return q38A;
	}

	public void setQ38A(String q38A) {
		this.q38A = q38A;
	}

	public String getQ38B() {
		return q38B;
	}

	public void setQ38B(String q38B) {
		this.q38B = q38B;
	}

	public String getQ38C() {
		return q38C;
	}

	public void setQ38C(String q38C) {
		this.q38C = q38C;
	}

	public String getQ38D() {
		return q38D;
	}

	public void setQ38D(String q38D) {
		this.q38D = q38D;
	}

	public String getQ39A() {
		return q39A;
	}

	public void setQ39A(String q39A) {
		this.q39A = q39A;
	}

	public String getQ39B() {
		return q39B;
	}

	public void setQ39B(String q39B) {
		this.q39B = q39B;
	}

	public String getQ39C() {
		return q39C;
	}

	public void setQ39C(String q39C) {
		this.q39C = q39C;
	}

	public String getQ39D() {
		return q39D;
	}

	public void setQ39D(String q39D) {
		this.q39D = q39D;
	}

	public String getQ40A() {
		return q40A;
	}

	public void setQ40A(String q40A) {
		this.q40A = q40A;
	}

	public String getQ40B() {
		return q40B;
	}

	public void setQ40B(String q40B) {
		this.q40B = q40B;
	}

	public String getQ40C() {
		return q40C;
	}

	public void setQ40C(String q40C) {
		this.q40C = q40C;
	}

	public String getQ40D() {
		return q40D;
	}

	public void setQ40D(String q40D) {
		this.q40D = q40D;
	}

	public String getQ41A() {
		return q41A;
	}

	public void setQ41A(String q41A) {
		this.q41A = q41A;
	}

	public String getQ41B() {
		return q41B;
	}

	public void setQ41B(String q41B) {
		this.q41B = q41B;
	}

	public String getQ41C() {
		return q41C;
	}

	public void setQ41C(String q41C) {
		this.q41C = q41C;
	}

	public String getQ41D() {
		return q41D;
	}

	public void setQ41D(String q41D) {
		this.q41D = q41D;
	}

	public String getQ42_Days() {
		return q42_Days;
	}

	public void setQ42_Days(String q42_Days) {
		this.q42_Days = q42_Days;
	}

	public String getQ42_Weeks() {
		
		return q42_Weeks;
	}

	public String getQ43_Days() {
		return q43_Days;
	}

	public void setQ43_Days(String q43_Days) {
		this.q43_Days = q43_Days;
	}
	
	public void setQ42_Weeks(String q43_Weeks) {
		this.q42_Weeks = q42_Weeks;
	}
	
public String getQ43_Weeks() {
		
		return q43_Weeks;
	}

	public void setQ43_Weeks(String q43_Weeks) {
		this.q43_Weeks = q43_Weeks;
	}
	

	public String getQ41E() {
		return q41E;
	}

	public void setQ41E(String q41E) {
		this.q41E = q41E;
	}

	public String getQ40E() {
		return q40E;
	}

	public void setQ40E(String q40E) {
		this.q40E = q40E;
	}

	public String getQ39E() {
		return q39E;
	}

	public void setQ39E(String q39E) {
		this.q39E = q39E;
	}

	public String getQ38E() {
		return q38E;
	}

	public void setQ38E(String q38E) {
		this.q38E = q38E;
	}

	public String getQ37E() {
		return q37E;
	}

	public void setQ37E(String q37E) {
		this.q37E = q37E;
	}

	public String getQ36E() {
		return q36E;
	}

	public void setQ36E(String q36E) {
		this.q36E = q36E;
	}

	public String getQ35E() {
		return q35E;
	}

	public void setQ35E(String q35E) {
		this.q35E = q35E;
	}

	public String getQ34E() {
		return q34E;
	}

	public void setQ34E(String q34E) {
		this.q34E = q34E;
	}

	public String getQ33E() {
		return q33E;
	}

	public void setQ33E(String q33E) {
		this.q33E = q33E;
	}

	public String getQ32E() {
		return q32E;
	}

	public void setQ32E(String q32E) {
		this.q32E = q32E;
	}

	public String getQ31E() {
		return q31E;
	}

	public void setQ31E(String q31E) {
		this.q31E = q31E;
	}

}
