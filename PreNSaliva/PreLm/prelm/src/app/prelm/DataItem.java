package app.prelm;

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

	private String childNo = "", q5 = "", q6 = "", q7 = "", q8 = "",
			q8_other = "", q41 = "", q47 = "", q48 = "", q49 = "", q50 = "",
			q51 = "", q24 = "", q25 = "", q26 = "", q27 = "", q28 = "",
			q29 = "", q30 = "", q52s1hour2 = "", q52s2hour2 = "",
			q52s3hour2 = "", q52s4hour2 = "", q52s5hour2 = "", q52s6hour2 = "",
			q53s1hour2 = "", q53s2hour2 = "", q53s3hour2 = "", q53s4hour2 = "",
			q53s5hour2 = "", q53s6hour2 = "", q54s1hour2 = "", q54s2hour2 = "",
			q54s3hour2 = "", q54s4hour2 = "", q54s5hour2 = "", q54s6hour2 = "",
			q55s1hour2 = "", q55s2hour2 = "", q55s3hour2 = "", q55s4hour2 = "",
			q55s5hour2 = "", q55s6hour2 = "", q56hour2 = "",
			q56hour2_other = "", q57hour2 = "";

	private String EntryBy;
	private String EntryDate;
	private String EditBy;
	private String EditDate;

	public DataItem(String childNo, String q5, String q6, String q7, String q8,
			String q8_other, String q41, String q47, String q48, String q49,
			String q50, String q51, String q52s1hour2, String q52s2hour2,
			String q52s3hour2, String q52s4hour2, String q52s5hour2,
			String q52s6hour2, String q53s1hour2, String q53s2hour2,
			String q53s3hour2, String q53s4hour2, String q53s5hour2,
			String q53s6hour2, String q54s1hour2, String q54s2hour2,
			String q54s3hour2, String q54s4hour2, String q54s5hour2,
			String q54s6hour2, String q55s1hour2, String q55s2hour2,
			String q55s3hour2, String q55s4hour2, String q55s5hour2,
			String q55s6hour2, String q56hour2, String q56hour2_other,
			String q57hour2, String EntryBy, String EntryDate, String EditBy,
			String EditDate) {
		this.childNo = childNo;
		this.q5 = q5;
		this.q6 = q6;
		this.q7 = q7;
		this.q8 = q8;
		this.q8_other = q8_other;
		this.q41 = q41;
		this.q47 = q47;
		this.q48 = q48;
		this.q49 = q49;
		this.q50 = q50;
		this.q51 = q51;
		this.q52s1hour2 = q52s1hour2;
		this.q52s2hour2 = q52s2hour2;
		this.q52s3hour2 = q52s3hour2;
		this.q52s4hour2 = q52s4hour2;
		this.q52s5hour2 = q52s5hour2;
		this.q52s6hour2 = q52s6hour2;
		this.q53s1hour2 = q53s1hour2;
		this.q53s2hour2 = q53s2hour2;
		this.q53s3hour2 = q53s3hour2;
		this.q53s4hour2 = q53s4hour2;
		this.q53s5hour2 = q53s5hour2;
		this.q53s6hour2 = q53s6hour2;
		this.q54s1hour2 = q54s1hour2;
		this.q54s2hour2 = q54s2hour2;
		this.q54s3hour2 = q54s3hour2;
		this.q54s4hour2 = q54s4hour2;
		this.q54s5hour2 = q54s5hour2;
		this.q54s6hour2 = q54s6hour2;
		this.q55s1hour2 = q55s1hour2;
		this.q55s2hour2 = q55s2hour2;
		this.q55s3hour2 = q55s3hour2;
		this.q55s4hour2 = q55s4hour2;
		this.q55s5hour2 = q55s5hour2;
		this.q55s6hour2 = q55s6hour2;
		this.q56hour2 = q56hour2;
		this.q56hour2_other = q56hour2_other;
		this.q57hour2 = q57hour2;
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

	public String getQ8_other() {
		return q8_other;
	}

	public void setQ8_other(String q8_other) {
		this.q8_other = q8_other;
	}

	public String getQ41() {
		return q41;
	}

	public void setQ41(String q41) {
		this.q41 = q41;
	}

	public String getQ47() {
		return q47;
	}

	public void setQ47(String q47) {
		this.q47 = q47;
	}

	public String getQ48() {
		return q48;
	}

	public void setQ48(String q48) {
		this.q48 = q48;
	}

	public String getQ49() {
		return q49;
	}

	public void setQ49(String q49) {
		this.q49 = q49;
	}

	public String getQ50() {
		return q50;
	}

	public void setQ50(String q50) {
		this.q50 = q50;
	}

	public String getQ51() {
		return q51;
	}

	public void setQ51(String q51) {
		this.q51 = q51;
	}

	public String getQ24() {
		return q24;
	}

	public void setQ24(String q24) {
		this.q24 = q24;
	}

	public String getQ25() {
		return q25;
	}

	public void setQ25(String q25) {
		this.q25 = q25;
	}

	public String getQ26() {
		return q26;
	}

	public void setQ26(String q26) {
		this.q26 = q26;
	}

	public String getQ27() {
		return q27;
	}

	public void setQ27(String q27) {
		this.q27 = q27;
	}

	public String getQ28() {
		return q28;
	}

	public void setQ28(String q28) {
		this.q28 = q28;
	}

	public String getQ29() {
		return q29;
	}

	public void setQ29(String q29) {
		this.q29 = q29;
	}

	public String getQ30() {
		return q30;
	}

	public void setQ30(String q30) {
		this.q30 = q30;
	}

	public String getQ52s1hour2() {
		return q52s1hour2;
	}

	public void setQ52s1hour2(String q52s1hour2) {
		this.q52s1hour2 = q52s1hour2;
	}

	public String getQ52s2hour2() {
		return q52s2hour2;
	}

	public void setQ52s2hour2(String q52s2hour2) {
		this.q52s2hour2 = q52s2hour2;
	}

	public String getQ52s3hour2() {
		return q52s3hour2;
	}

	public void setQ52s3hour2(String q52s3hour2) {
		this.q52s3hour2 = q52s3hour2;
	}

	public String getQ52s4hour2() {
		return q52s4hour2;
	}

	public void setQ52s4hour2(String q52s4hour2) {
		this.q52s4hour2 = q52s4hour2;
	}

	public String getQ52s5hour2() {
		return q52s5hour2;
	}

	public void setQ52s5hour2(String q52s5hour2) {
		this.q52s5hour2 = q52s5hour2;
	}

	public String getQ52s6hour2() {
		return q52s6hour2;
	}

	public void setQ52s6hour2(String q52s6hour2) {
		this.q52s6hour2 = q52s6hour2;
	}

	public String getQ53s1hour2() {
		return q53s1hour2;
	}

	public void setQ53s1hour2(String q53s1hour2) {
		this.q53s1hour2 = q53s1hour2;
	}

	public String getQ53s2hour2() {
		return q53s2hour2;
	}

	public void setQ53s2hour2(String q53s2hour2) {
		this.q53s2hour2 = q53s2hour2;
	}

	public String getQ53s3hour2() {
		return q53s3hour2;
	}

	public void setQ53s3hour2(String q53s3hour2) {
		this.q53s3hour2 = q53s3hour2;
	}

	public String getQ53s4hour2() {
		return q53s4hour2;
	}

	public void setQ53s4hour2(String q53s4hour2) {
		this.q53s4hour2 = q53s4hour2;
	}

	public String getQ53s5hour2() {
		return q53s5hour2;
	}

	public void setQ53s5hour2(String q53s5hour2) {
		this.q53s5hour2 = q53s5hour2;
	}

	public String getQ53s6hour2() {
		return q53s6hour2;
	}

	public void setQ53s6hour2(String q53s6hour2) {
		this.q53s6hour2 = q53s6hour2;
	}

	public String getQ54s1hour2() {
		return q54s1hour2;
	}

	public void setQ54s1hour2(String q54s1hour2) {
		this.q54s1hour2 = q54s1hour2;
	}

	public String getQ54s2hour2() {
		return q54s2hour2;
	}

	public void setQ54s2hour2(String q54s2hour2) {
		this.q54s2hour2 = q54s2hour2;
	}

	public String getQ54s3hour2() {
		return q54s3hour2;
	}

	public void setQ54s3hour2(String q54s3hour2) {
		this.q54s3hour2 = q54s3hour2;
	}

	public String getQ54s4hour2() {
		return q54s4hour2;
	}

	public void setQ54s4hour2(String q54s4hour2) {
		this.q54s4hour2 = q54s4hour2;
	}

	public String getQ54s5hour2() {
		return q54s5hour2;
	}

	public void setQ54s5hour2(String q54s5hour2) {
		this.q54s5hour2 = q54s5hour2;
	}

	public String getQ54s6hour2() {
		return q54s6hour2;
	}

	public void setQ54s6hour2(String q54s6hour2) {
		this.q54s6hour2 = q54s6hour2;
	}

	public String getQ55s1hour2() {
		return q55s1hour2;
	}

	public void setQ55s1hour2(String q55s1hour2) {
		this.q55s1hour2 = q55s1hour2;
	}

	public String getQ55s2hour2() {
		return q55s2hour2;
	}

	public void setQ55s2hour2(String q55s2hour2) {
		this.q55s2hour2 = q55s2hour2;
	}

	public String getQ55s3hour2() {
		return q55s3hour2;
	}

	public void setQ55s3hour2(String q55s3hour2) {
		this.q55s3hour2 = q55s3hour2;
	}

	public String getQ55s4hour2() {
		return q55s4hour2;
	}

	public void setQ55s4hour2(String q55s4hour2) {
		this.q55s4hour2 = q55s4hour2;
	}

	public String getQ55s5hour2() {
		return q55s5hour2;
	}

	public void setQ55s5hour2(String q55s5hour2) {
		this.q55s5hour2 = q55s5hour2;
	}

	public String getQ55s6hour2() {
		return q55s6hour2;
	}

	public void setQ55s6hour2(String q55s6hour2) {
		this.q55s6hour2 = q55s6hour2;
	}

	public String getQ56hour2() {
		return q56hour2;
	}

	public void setQ56hour2(String q56hour2) {
		this.q56hour2 = q56hour2;
	}

	public String getQ56hour2_other() {
		return q56hour2_other;
	}

	public void setQ56hour2_other(String q56hour2_other) {
		this.q56hour2_other = q56hour2_other;
	}

	public String getQ57hour2() {
		return q57hour2;
	}

	public void setQ57hour2(String q57hour2) {
		this.q57hour2 = q57hour2;
	}

	public String getQ7() {
		return q7;
	}

	public void setQ7(String q7) {
		this.q7 = q7;
	}

	public String getQ8() {
		return q8;
	}

	public void setQ8(String q8) {
		this.q8 = q8;
	}

}
