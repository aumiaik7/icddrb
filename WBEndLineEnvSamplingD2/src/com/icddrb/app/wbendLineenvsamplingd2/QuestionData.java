package com.icddrb.app.wbendLineenvsamplingd2;

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
	
	

	public String getQdescbng() {
		return Qdescbng;
	}

	public String getQdesceng() {
		return Qdesceng;
	}

	public String getQType() {
		return QType;
	}

	public String getQnext1() {
		return Qnext1;
	}

	public String getQnext2() {
		return Qnext2;
	}

	public String getQnext3() {
		return Qnext3;
	}

	public String getQnext4() {
		return Qnext4;
	}

	public String getQchoice1eng() {
		return Qchoice1eng;
	}

	public String getQchoice2eng() {
		return Qchoice2eng;
	}

	public String getQchoice3eng() {
		return Qchoice3eng;
	}

	public String getQchoice1bng() {
		return Qchoice1bng;
	}

	public String getQchoice2bng() {
		return Qchoice2bng;
	}

	public String getQchoice3bng() {
		return Qchoice3bng;
	}

	public String getQrange1() {
		return Qrange1;
	}

	public String getQrange2() {
		return Qrange2;
	}

	public String getDataType() {
		return DataType;
	}

	public String getTablename() {
		return Tablename;
	}

	private int SLNo = 0;
	private String Qvar = "";
	private String Formname = "";
	private String Qdescbng = "";
	private String Qdesceng = "";
	private String QType = "";
	private String Qnext1 = "";
	private String Qnext2 = "";
	private String Qnext3 = "";
	private String Qnext4 = "";
	private String Qchoice1eng = "";
	private String Qchoice2eng = "";
	private String Qchoice3eng = "";
	private String Qchoice1bng = "";
	private String Qchoice2bng = "";
	private String Qchoice3bng = "";
	private String Qrange1 = "";
	private String Qrange2 = "";
	private String DataType = "";
	private String Tablename = "";

	public QuestionData(int SLNo, String Qvar, String Formname,
			String Qdescbng, String Qdesceng, String QType, String Qnext1,
			String Qnext2, String Qnext3, String Qnext4, String Qchoice1eng,
			String Qchoice2eng, String Qchoice3eng, String Qchoice1bng,
			String Qchoice2bng, String Qchoice3bng, String Qrange1,
			String Qrange2, String DataType, String Tablename) {
		// TODO Auto-generated constructor stub
		this.SLNo = SLNo;
		this.Qvar = Qvar;
		this.Formname = Formname;
		this.Qdescbng = Qdescbng;
		this.Qdesceng = Qdesceng;
		this.QType = QType;
		this.Qnext1 = Qnext1;
		this.Qnext2 = Qnext2;
		this.Qnext3 = Qnext3;
		this.Qnext4 = Qnext4;
		this.Qchoice1eng = Qchoice1eng;
		this.Qchoice2eng = Qchoice2eng;
		this.Qchoice3eng = Qchoice3eng;
		this.Qchoice1bng = Qchoice1bng;
		this.Qchoice2bng = Qchoice2bng;
		this.Qchoice3bng = Qchoice3bng;
		this.Qrange1 = Qrange1;
		this.Qrange2 = Qrange2;
		this.DataType = DataType;
		this.Tablename = Tablename;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return SLNo + Qvar + Formname + Qdescbng + Qdesceng + QType + Qnext1
				+ Qnext2 + Qnext3 + Qnext4 + Qchoice1eng + Qchoice2eng
				+ Qchoice3eng + Qchoice1bng + Qchoice2bng + Qchoice3bng
				+ Qrange1 + Qrange2 + DataType + Tablename;
	}

}
