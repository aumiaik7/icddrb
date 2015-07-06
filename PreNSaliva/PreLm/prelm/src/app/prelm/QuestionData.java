package app.prelm;

import java.io.Serializable;

public class QuestionData implements Serializable {

	public int getSLNo() {
		return SLNo;
	}

	public String getQvar() {
		return Qvar;
	}

	public String getFormname() {
		return Formname;
	}

	public String getQdesceng() {
		return Qdesceng;
	}

	public String getQnext() {
		return Qnext;
	}

	private int SLNo = 0;
	private String Qvar = "";
	private String Formname = "";
	private String Qdesceng = "";	
	private String Qnext = "";
	
	public QuestionData(int SLNo,String Qvar,String Formname,String Qdesceng,String Qnext) {
		// TODO Auto-generated constructor stub
		this.SLNo = SLNo;
		this.Qvar = Qvar;
		this.Formname = Formname;
		this.Qdesceng = Qdesceng;
	}		
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return 	SLNo+Qvar+Formname+Qdesceng+Qnext;
	}
	
}
