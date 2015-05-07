package com.icddrb.app.wbendLineenvsamplingd1;

import java.io.Serializable;

public class OptionData implements Serializable {

	public String getQID() {
		return QID;
	}

	public String getCaptionEng() {
		return CaptionEng;
	}

	public String getCaptionBang() {
		return CaptionBang;
	}

	public String getCode() {
		return Code;
	}

	public String getQNext() {
		return QNext;
	}

	private String QID = "";
	private String CaptionEng = "";
	private String CaptionBang = "";
	private String Code = "";
	private String QNext = "";

	public OptionData(String QID, String CaptionEng, String CaptionBang,
			String Code, String QNext) {
		// TODO Auto-generated constructor stub
		this.QID = QID;
		this.CaptionEng = CaptionEng;
		this.CaptionBang = CaptionBang;
		this.Code = Code;
		this.QNext = QNext;
	}
}
