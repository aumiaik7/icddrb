package app.stoolsampleendline;

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
	private String q5="",q6 = "", q7s1 = "", q7s2 = "", q7s3 = "", q7s4 = "",
			q7s5 = "", q7s6 = "", q8s1 = "", q8s2 = "", q8s3 = "", q8s4 = "", q8s5 = "",q8s6 = "",
			q10s1 = "", q10s2 = "", q10s3 = "", q10s4 = "", q10s5 = "",q10s6 = "",
			q11 = "", q11_other = "", q12 = "", q13 = "", q14 = "", q15 = "",
			q16 = "", q17 = "", q17_other = "", q18 = "", q18_other = "",
			q19 = "", q20 = "", q21 = "", q22 = "",q23 = "",q24 = "",q25 = "",q26 = "",q23_other = "";
			
	private String EntryBy;
	private String EntryDate;
	private String EditBy;
	private String EditDate;
	
	public DataItem(String childNo,	String q5,String q6, String q7s1  , String q7s2  , String q7s3  , String q7s4  ,
			String q7s5  , String q7s6  ,String q8s1  , String q8s2  , String q8s3  , String q8s4  , String q8s5  ,String q8s6  ,
			String q10s1  , String q10s2  , String q10s3  , String q10s4  , String q10s5, String q10s6  ,
			String q11  , String q11_other  , String q12  , String q13  , String q14  , String q15  ,
			String q16  , String q17  , String q17_other  , String q18  , String q18_other  ,
			String q19  , String q20  , String q21  , String q22, String q23, String q23_other, String q24, String q25,  String q26,String EntryBy,String EntryDate,
			String EditBy,String EditDate)
	{
		this.childNo = childNo;
		
		this.q5 = q5;
		this.q6 = q6;
		
		this.q7s1 = q7s1;   
		this.q7s2  =q7s2;
		this.q7s3  = q7s3;
		this.q7s4=q7s4;
		this.q7s5=q7s5;
		this.q7s6=q7s6;
		this.q8s1=q8s1;
		this.q8s2=q8s2;
		this.q8s3=q8s3;
		this.q8s4=q8s4;
		this.q8s5=q8s5;
		this.q8s6=q8s6;
		this.q10s1=q10s1;
		this.q10s2=q10s2;
		this.q10s3=q10s3;
		this.q10s4=q10s4;
		this.q10s5=q10s5;
		this.q10s6=q10s6;
		this.q11=q11;
		this.q11_other = q11_other; 
		this.q12=q12; 
		this.q13=q13;
		this.q14=q14;
		this.q15=q15;
		this.q16=q16;
		this.q17=q17;
		this.q17_other=q17_other;
		this.q18=q18;
		this.q18_other=q18_other;
		this.q19=q19;
		this.q20=q20;
		this.q21=q21;
		this.q22=q22;
		this.q23=q23; 
		this.q23_other=q23_other; 
		this.q24=q24; 
		this.q25=q25;
		this.q26=q26; 

		this.EntryBy = EntryBy;
		this.EntryDate = EntryDate;
		this.EditBy = EditBy;
		this.EditDate = EditDate;
		
		
		
		
		
		
	
	}
	public String getQ5() {
		return q5;
	}
	
	public String getQ6() {
		return q6;
	}
	
	public String getQ7s1() {
		return q7s1;
	}
	
	public String getQ7s2() {
		return q7s2;
	}
	
	public String getQ7s3() {
		return q7s3;
	}
	
	public String getQ7s4() {
		return q7s4;
	}
	
	public String getQ7s5() {
		return q7s5;
	}
	public String getQ7s6() {
		return q7s6;
	}
	public String getQ8s1() {
		return q8s1;
	}
	
	public String getQ8s2() {
		return q8s2;
	}
	
	public String getQ8s3() {
		return q8s3;
	}
	
	public String getQ8s4() {
		return q8s4;
	}
	
	public String getQ8s5() {
		return q8s5;
	}
	public String getQ8s6() {
		return q8s6;
	}
	public String getQ10s1() {
		return q10s1;
	}
	
	public String getQ10s2() {
		return q10s2;
	}
	
	public String getQ10s3() {
		return q10s3;
	}
	
	public String getQ10s4() {
		return q10s4;
	}
	
	public String getQ10s5() {
		return q10s5;
	}
	public String getQ10s6() {
		return q10s6;
	}
	public String getQ11() {
		return q11;
	}
	
	public String getQ11_other() {
		return q11_other;
	}
	
	public String getQ12() {
		return q12;
	}
	
	public String getQ13() {
		return q13;
	}
	
	public String getQ14() {
		return q14;
	}
	
	public String getQ15() {
		return q15;
	}
	
	public String getQ16() {
		return q16;
	}
	
	public String getQ17() {
		return q17;
	}
	
	public String getQ17_other() {
		return q17_other;
	}
	
	public String getQ18() {
		return q18;
	}
	
	public String getQ18_other() {
		return q18_other;
	}
	
	public String getQ19() {
		return q19;
	}
	
	public String getQ20() {
		return q20;
	}
	
	public String getQ21() {
		return q21;
	}
	
	public String getQ22() {
		return q22;
	}
	public String getQ23() {
		return q23;
	}
	public String getQ24() {
		return q24;
	}
	
	public String getQ25() {
		return q25;
	}
	public String getQ26() {
		return q26;
	}
	public String getQ23_other() {
		return q23_other;
	}
	
}
