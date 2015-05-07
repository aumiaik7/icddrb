package com.icddrb.app.SpecimenCollectionForm.questions;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.R.color;
import android.content.Context;
import android.database.Cursor;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.icddrb.app.SpecimenCollectionForm.R;
import com.icddrb.app.SpecimenCollectionForm.BaseActivity;
import com.icddrb.app.SpecimenCollectionForm.CommonStaticClass;
import com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper;

public class PatientDetail extends BaseActivity {
	private String PatientID = "";
	private int PatientTY;
	private String RegDate;
	private String RegTime = "";
	private String Name = "";
	private int Sex;
	private int AgeYr;
	private int AgeMo;
	private int AgeDa;
	private String BDate = "";
	private int BDType;
	private String FaSpName = "";
	private String HH = "";
	private String SL = "";
	private String PID = "";
	private String Ward = "";
	private String Area = "";
	private String SecBlock = "";
	private String Road = "";
	private String House = "";
	private String Phone = "";
	private String Phone2 = "";
	private String Vill = "";
	private String UPZ = "";
	private String Dist = "";
	private String WT = "";
	private String HT = "";
	private String MUAC = "";

	private int DS17;
	private int DS18D;
	private int DS18H;
	private int DS19;
	private int DS20;
	private int DS21;
	private int DS22;
	private int DS23;
	private int DS24;
	private int DS25;
	private int DS26;
	private String DS26a;
	private String DS26b;

	// vomiting
	private int DS27;
	private int DS28D;
	private int DS28H;
	private int DS29;
	private int DS30;
	private int DS31;
	private int DS32D;
	private int DS32H;
	private String DS33;
	private String Fahrenheit;
	private int DS34;
	private int DS35D;
	private int DS35H;

	// ORS
	private int DS36;
	private int DS37;
	private int DS38;
	private int DS39;
	private int DS40;
	private int DS41;
	private String DS42;
	private String DS43;
	private int DS44;
	private int DS45;
	private int DS46;
	private int DS47;
	private int DS48;
	private int DS49;
	private int DS50;
	private int DS51;
	private int DS52;
	private int DS53;
	private int DS54;
	private String DS55;
	private int DS56;
	private String DS57;
	private String DS58;
	private String DS59C;
	private String DS59P;

	// Children
	private int DS60;
	private String DS61;
	private int DS62;
	private int DS63;
	private int DS64;
	private int DS65;
	private int DS66;
	private int DS67;
	private int DS68;
	private int DS69;
	private String GISID;

	private String entryTime;
	private String editTime;

	public String getPatientID() {
		return PatientID;
	}

	public void setPatientID(String patientID) {
		PatientID = patientID;
	}

	public int getPatientTY() {
		return PatientTY;
	}

	public void setPatientTY(int patientTY) {
		PatientTY = patientTY;
	}

	public String getRegDate() {
		return RegDate;
	}

	public void setRegDate(String regDate) {
		RegDate = regDate;
	}

	public String getRegTime() {
		return RegTime;
	}

	public void setRegTime(String regTime) {
		RegTime = regTime;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getSex() {
		return Sex;
	}

	public void setSex(int sex) {
		Sex = sex;
	}

	public int getAgeYr() {
		return AgeYr;
	}

	public void setAgeYr(int ageYr) {
		AgeYr = ageYr;
	}

	public int getAgeMo() {
		return AgeMo;
	}

	public void setAgeMo(int ageMo) {
		AgeMo = ageMo;
	}

	public int getAgeDa() {
		return AgeDa;
	}

	public void setAgeDa(int ageDa) {
		AgeDa = ageDa;
	}

	public String getBDate() {
		return BDate;
	}

	public void setBDate(String bDate) {
		BDate = bDate;
	}

	public int getBDType() {
		return BDType;
	}

	public void setBDType(int bDType) {
		BDType = bDType;
	}

	public String getFaSpName() {
		return FaSpName;
	}

	public void setFaSpName(String faSpName) {
		FaSpName = faSpName;
	}

	public String getHH() {
		return HH;
	}

	public void setHH(String hH) {
		HH = hH;
	}

	public String getSL() {
		return SL;
	}

	public void setSL(String sL) {
		SL = sL;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getWard() {
		return Ward;
	}

	public void setWard(String ward) {
		Ward = ward;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getSecBlock() {
		return SecBlock;
	}

	public void setSecBlock(String secBlock) {
		SecBlock = secBlock;
	}

	public String getRoad() {
		return Road;
	}

	public void setRoad(String road) {
		Road = road;
	}

	public String getHouse() {
		return House;
	}

	public void setHouse(String house) {
		House = house;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getVill() {
		return Vill;
	}

	public void setVill(String vill) {
		Vill = vill;
	}

	public String getUPZ() {
		return UPZ;
	}

	public void setUPZ(String uPZ) {
		UPZ = uPZ;
	}

	public String getDist() {
		return Dist;
	}

	public void setDist(String dist) {
		Dist = dist;
	}

	// String.format( "%.2f", dub )
	public Boolean SavePatientDetail(
			com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper dbHelper, ViewGroup v) {

		if (!IsValidPatientDetail(v))
			return false;

		if (!IsValidPatientAge(v))
			return false;

		else {

			PatientDetail pd = new PatientDetail();

			pd.setPatientID(((EditText) v.findViewById(R.id.txtPatientID))
					.getText().toString());

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupPatientTY))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setPatientTY(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupPatientTY))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setPatientTY(2);

			pd.setRegDate(((EditText) v.findViewById(R.id.txtRegDate))
					.getText().toString());
			pd.setRegTime(((EditText) v.findViewById(R.id.txtRegTime))
					.getText().toString());
			pd.setName(((EditText) v.findViewById(R.id.txtName)).getText()
					.toString());
			pd.setBDate(((EditText) v.findViewById(R.id.txtBDate)).getText()
					.toString());

			if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupSex))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setSex(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupSex))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setSex(2);

			pd.setAgeYr(Integer.parseInt(((EditText) v
					.findViewById(R.id.txtAgeYr)).getText().toString()));

			pd.setAgeMo(Integer.parseInt(((EditText) v
					.findViewById(R.id.txtAgeMo)).getText().toString()));
			pd.setAgeDa(Integer.parseInt(((EditText) v
					.findViewById(R.id.txtAgeDa)).getText().toString()));

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupBDType))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setBDType(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupBDType))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setBDType(2);

			pd.setFaSpName(((EditText) v.findViewById(R.id.txtFaSpName))
					.getText().toString());

			pd.setWard(((EditText) v.findViewById(R.id.txtWard)).getText()
					.toString());

			pd.setArea(((EditText) v.findViewById(R.id.txtArea)).getText()
					.toString());
			pd.setSecBlock(((EditText) v.findViewById(R.id.txtSecBlock))
					.getText().toString());
			pd.setRoad(((EditText) v.findViewById(R.id.txtRoad)).getText()
					.toString());
			pd.setHouse(((EditText) v.findViewById(R.id.txtHouse)).getText()
					.toString());
			pd.setHH(((EditText) v.findViewById(R.id.txtHH)).getText()
					.toString());
			pd.setSL(((EditText) v.findViewById(R.id.txtSL)).getText()
					.toString());
			pd.setPID(((EditText) v.findViewById(R.id.txtPID)).getText()
					.toString());

			pd.setPhone(((EditText) v.findViewById(R.id.txtPhone)).getText()
					.toString());
			pd.setPhone2(((EditText) v.findViewById(R.id.txtPhone2)).getText()
					.toString());

			if (pd.Phone2.length() > 0) {
				pd.Phone = pd.Phone + "," + pd.Phone2;

			}

			pd.setVill(((EditText) v.findViewById(R.id.txtVill)).getText()
					.toString());
			/*
			 * pd.setUPZ(((EditText) v.findViewById(R.id.txtUPZ)).getText()
			 * .toString());
			 */

			pd.setDist(String.valueOf(((Spinner) v.findViewById(R.id.txtDist))
					.getSelectedItem().toString()));

			pd.setUPZ(String.valueOf(((Spinner) v.findViewById(R.id.txtUPZ))
					.getSelectedItem().toString()));

			Context con = ParentActivity.thisactivity.getApplicationContext();

			if (pd.Ward.length() <= 1) {
				DisplayToast(con, "Invalid Ward", 1);
				return false;

			} else if (pd.Ward.equalsIgnoreCase("07")
					|| pd.Ward.equalsIgnoreCase("08")
					|| pd.Ward.equalsIgnoreCase("09")
					|| pd.Ward.equalsIgnoreCase("10")
					|| pd.Ward.equalsIgnoreCase("11")
					|| pd.Ward.equalsIgnoreCase("12")
					|| pd.Ward.equalsIgnoreCase("13")
					|| pd.Ward.equalsIgnoreCase("14")
					|| pd.Ward.equalsIgnoreCase("15")
					|| pd.Ward.equalsIgnoreCase("41")
					|| pd.Ward.equalsIgnoreCase("99")) {

			} else {
				DisplayToast(con, "Invalid Ward", 1);
				return false;
			}

			Date d = new Date(System.currentTimeMillis());
			String entryDate = "dd-mmm-yy";
			entryDate = d.toLocaleString();

			if (!validdate(pd, con))
				return false;

			if (pd.SL.length() > 0) {
				if (pd.PID.length() < 11) {
					DisplayToast(con, "PID and serial validation failed", 1);
					return false;
				}

				/*
				 * if (!CommonStaticClass.getSkip("Slno", "SCVBDS", dbHelper)
				 * .equalsIgnoreCase(pd.SL)) { DisplayToast(con,
				 * "SL does not match with database", 1); return false; }
				 */

			}

			if (pd.PID.length() > 0) {
				/*
				 * if (!CommonStaticClass.getSkip("PID", "SCVBDS", dbHelper)
				 * .equalsIgnoreCase(pd.PID)) { DisplayToast(con,
				 * "PID does not match with database", 1); return false; }
				 */

				if (pd.SL.length() < 2) {
					DisplayToast(con, "PID and serial validation failed", 1);
					return false;
				}

			}

			if (pd.HH.length() > 0) {
				if (pd.HH.length() < 8) {
					DisplayToast(con, "HHID validation failed", 1);
					return false;
				}

				/*
				 * if (!CommonStaticClass.getSkip("Hh", "SCVBDS", dbHelper)
				 * .equalsIgnoreCase(pd.HH)) { DisplayToast(con,
				 * "HH does not match with database", 1); return false; }
				 */
			}

			if (pd.Phone.length() > 0) {

				if (pd.Phone.length() < 11) {
					DisplayToast(con, "Phone validation failed", 1);
					return false;
				}
			}
			if (pd.PID.length() > 0 && pd.HH.length() > 0 && pd.SL.length() > 0) {
				if (IsValidMEMSCVB(dbHelper, pd.PID, pd.HH, pd.SL) == false) {
					DisplayToast(con,
							"Credential does not match with database", 1);
					return false;
				}
			}

			if (pd.PID.length() > 0 && pd.SL.length() > 0) {
				if (IsValidMEMSCVB(dbHelper, pd.PID, "", pd.SL) == false) {
					DisplayToast(con,
							"Credential does not match with database", 1);
					return false;
				}
			}
			if (pd.HH.length() > 0) {
				if (IsValidMEMSCVB(dbHelper, "", pd.HH, "") == false) {
					DisplayToast(con,
							"Credential does not match with database", 1);
					return false;
				}
			}

			String s1 = String
					.format("UPDATE SCVBDS SET HOSPID ='%s', PatientTY='%s',RegDate ='%s',RegTime ='%s',"
							+ "Name ='%s',BDate ='%s', Sex ='%s', AgeYr ='%s', AgeMo ='%s', AgeDa ='%s', BDType ='%s', FaSpName ='%s', "
							+ "Ward ='%s', Area ='%s', SecBlock ='%s', Road ='%s', House ='%s', HH ='%s', SL ='%s', PID ='%s', Phone ='%s', "
							+ "Vill ='%s', UPZ ='%s', Dist ='%s', EditBy='%s', EditDate='%s', EditTime = '%s' WHERE dataid = '%s' ",
							pd.PatientID, pd.PatientTY, pd.RegDate, pd.RegTime,
							pd.Name, pd.BDate, pd.Sex, pd.AgeYr, pd.AgeMo,
							pd.AgeDa, pd.BDType, pd.FaSpName, pd.Ward, pd.Area,
							pd.SecBlock, pd.Road, pd.House, pd.HH, pd.SL,
							pd.PID, pd.Phone, pd.Vill, pd.UPZ, pd.Dist,
							CommonStaticClass.userSpecificId,
							CommonStaticClass.GetDate(),
							CommonStaticClass.GetTime(),
							CommonStaticClass.dataId);

			if (dbHelper.executeDMLQuery(s1)) {

				if (pd.AgeYr > 5) {
					ArrayList<String> list = new ArrayList<String>();

					list.add("DS60");
					list.add("DS61");
					list.add("DS62");
					list.add("DS63");
					list.add("DS64");
					list.add("DS65");
					list.add("DS66");
					list.add("DS67");
					list.add("DS68");
					list.add("DS69");

					if (this.SetNull(dbHelper, list)) {
						return true;
					}
				}
				return true;
			}

			return false;
		}
	}

	
	/*
	 * private boolean validpid(PatientDetail pd, Context con) { // TODO
	 * Auto-generated method stub if(pd.PID.length()>0) { if(pd.SL.length()<=0)
	 * { CommonStaticClass.showMyAlert(con, "Message", "Please input Serial");
	 * return } } return false; }
	 */

	public Boolean validdate(PatientDetail pd, Context con) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = pd.BDate;

		try {

			Date date = formatter.parse(dateInString);

			dateInString = pd.RegDate;

			Date dobdate = formatter.parse(dateInString);

			// BDate
			if (futureDateValidator(date)) {
				DisplayToast(con, "Future date violation in Date of birth", 1);
				return false;
			}

			Calendar startdate = Calendar.getInstance();
			Calendar enddate = Calendar.getInstance();

			startdate.setTime(date);
			enddate.setTime(dobdate);
			/*
			 * enddate.set(enddate.get(Calendar.YEAR),
			 * enddate.get(Calendar.MONTH), enddate.get(Calendar.DAY_OF_YEAR));
			 */

			int yeartovalidate = CommonStaticClass.daysBetween(startdate,
					enddate);

			if (pd.AgeYr != yeartovalidate) {
				DisplayToast(con,
						"Date of birth mismatch! Year part of age should be  "
								+ yeartovalidate, 1);
				return false;
			}

			/*
			 * int dateYear = c.get(Calendar.YEAR); //dateInString =
			 * String.valueOf(pd.AgeDa) +"/" + String.valueOf(pd.AgeMo) + "/" +
			 * String.valueOf(dateYear - pd.AgeYr); dateInString = "1/" + "1/" +
			 * String.valueOf(dateYear - pd.AgeYr); dobdate =
			 * formatter.parse(dateInString);
			 * 
			 * 
			 * if(dobdate.getYear() == date.getYear()) {
			 * //CommonStaticClass.showMyAlert(con, "Message",
			 * "Date mismatch in date"); // Toast.makeText(con ,
			 * "Date of birth mismatch", 2000).show(); DisplayToast(con ,
			 * "Date of birth mismatch", 1); return false; }
			 */

			// -----------finish BDate----------------

			// Regdate
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			dateInString = pd.RegDate;
			date = formatter.parse(dateInString);

			if (futureDateValidator(date)) {
				DisplayToast(con, "Future date violation in registration date",
						1);
				return false;
			}

			/*
			 * System.out.println(date);
			 * System.out.println(formatter.format(date));
			 */

		} catch (ParseException e) {
			DisplayToast(con, "Error validating date", 1);
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean IsComplete(String dataid, DatabaseHelper dhelper) {
		try {
			PatientDetail p = new PatientDetail();

			p = p.GetPatientdetail(dhelper, dataid);

			Class<?> c = p.getClass();
			Field[] fields = c.getDeclaredFields();
			// Map<String, Object> temp = new HashMap<String, Object>();

			for (Field field : fields) {
				try {

					// temp.put(field.getName().toString(), field.get(p));
					if (ExcludeFromCompleteIncompleteCondition(field.getName()
							.toString(), dataid, dhelper)) {
						continue;
					} else {
						if (field.get(p) == null) {
							Log.e("Property is : ", field.getName().toString());
							return false;
						}

						String val = String.valueOf(field.get(p));
						if (val.length() <= 0) {
							Log.e("Property is : ", field.getName().toString());
							return false;
						}
					}

				} catch (IllegalArgumentException e1) {

					Log.e("Error", e1.getMessage());
				}
			}

		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			return false;
		}
		return true;
	}

	public static boolean ExcludeFromCompleteIncompleteCondition(
			String propertyName, String dataid, DatabaseHelper dbHelper) {

		CommonStaticClass.dataId = dataid;

		if (propertyName.equalsIgnoreCase("AgeYr")) {
			String v = CommonStaticClass.getSkip("AgeYr", "SCVBDS", dbHelper);
			if (v.length() > 0 && !v.equalsIgnoreCase("null")) {
				if (Integer.parseInt(v) <= 5) {

					String val = CommonStaticClass.getSkip("DS60", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}

					
					/*val = CommonStaticClass.getSkip("DS61", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}

					
					val = CommonStaticClass.getSkip("DS62", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}
					
					val = CommonStaticClass.getSkip("DS63", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}
					
					val = CommonStaticClass.getSkip("DS64", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}
					
					
					val = CommonStaticClass.getSkip("DS65", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}
					
					val = CommonStaticClass.getSkip("DS66", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}
					
					val = CommonStaticClass.getSkip("DS67", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}
					
					
					val = CommonStaticClass.getSkip("DS68", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}
					
					
					val = CommonStaticClass.getSkip("DS69", "SCVBDS",
							dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0)
						{return false;}*/
					
					
					return true;
					

				}
			}
		}

		if (propertyName.equalsIgnoreCase("EditTime")
				|| propertyName.equalsIgnoreCase("EditDate")
				|| propertyName.equalsIgnoreCase("EditBy")
				|| propertyName.equalsIgnoreCase("Fahrenheit")
				|| propertyName.equalsIgnoreCase("Phone2")
				|| propertyName.equalsIgnoreCase("DS26a")
				|| propertyName.equalsIgnoreCase("DS26b")
				|| propertyName.equalsIgnoreCase("GISID")
				|| propertyName.equalsIgnoreCase("DS59P")
				|| propertyName.equalsIgnoreCase("DS59C")) {
			return true;
		}

		if (propertyName.equalsIgnoreCase("DS60")
				|| propertyName.equalsIgnoreCase("DS61")
				|| propertyName.equalsIgnoreCase("DS62")
				|| propertyName.equalsIgnoreCase("DS63")
				|| propertyName.equalsIgnoreCase("DS64")
				|| propertyName.equalsIgnoreCase("DS65")
				|| propertyName.equalsIgnoreCase("DS66")
				|| propertyName.equalsIgnoreCase("DS67")
				|| propertyName.equalsIgnoreCase("DS68")
				|| propertyName.equalsIgnoreCase("DS69")) {

			return true;
		}

		if (propertyName.equalsIgnoreCase("DS26")) {

			String val = CommonStaticClass.getSkip("DS26", "SCVBDS", dbHelper);
			if (val == null) {
				return false;
			}
			if (val.equalsIgnoreCase("2") || val.equalsIgnoreCase("3")) {
				val = CommonStaticClass.getSkip("DS26a", "SCVBDS", dbHelper);
				if (val == null) {
					return false;
				}
				if (val.length() <= 0)
					return false;

				val = CommonStaticClass.getSkip("DS26b", "SCVBDS", dbHelper);
				if (val == null) {
					return false;
				}
				if (val.length() <= 0)
					return false;

			}
			return true;
		}
		if (propertyName.equalsIgnoreCase("DS17")) {
			{

				String val = CommonStaticClass.getSkip("DS17", "SCVBDS",
						dbHelper);
				if (val == null) {
					return false;
				}
				if (val.equalsIgnoreCase("2")) {
					val = CommonStaticClass
							.getSkip("DS18D", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}
					if (val.length() <= 0)
						return false;

					val = CommonStaticClass
							.getSkip("DS18H", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}
					if (val.length() <= 0)
						return false;

					val = CommonStaticClass.getSkip("DS19", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}
					if (val.length() <= 0)
						return false;

					val = CommonStaticClass.getSkip("DS20", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}
					if (val.length() <= 0)
						return false;

					val = CommonStaticClass.getSkip("DS21", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}
					if (val.length() <= 0)
						return false;

					val = CommonStaticClass.getSkip("DS22", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}
					if (val.length() <= 0)
						return false;

					val = CommonStaticClass.getSkip("DS23", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}
					if (val.length() <= 0)
						return false;

					val = CommonStaticClass.getSkip("DS24", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}
					if (val.length() <= 0)
						return false;

					val = CommonStaticClass.getSkip("DS25", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}
					if (val.length() <= 0)
						return false;

				}

			}
			return true;
		}
		if (propertyName.equalsIgnoreCase("DS27")) {
			String val = CommonStaticClass.getSkip("DS27", "SCVBDS", dbHelper);
			if (val == null) {
				return false;
			}

			if (val.length() <= 0) {
				return false;
			}
			if (val.length() > 0) {

				if (Integer.parseInt(val) > 0) {
					val = CommonStaticClass
							.getSkip("DS28D", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0) {
						return false;
					}

					val = CommonStaticClass
							.getSkip("DS28H", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0) {
						return false;
					}

				}
				return false;
			}

			return true;
		}
		if (propertyName.equalsIgnoreCase("DS31")) {
			String val = CommonStaticClass.getSkip("DS31", "SCVBDS", dbHelper);
			if (val == null) {
				return false;
			}

			if (val.length() <= 0) {
				return false;
			}
			if (val.length() > 0) {

				if (Integer.parseInt(val) > 0) {
					val = CommonStaticClass
							.getSkip("DS32D", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0) {
						return false;
					}

					val = CommonStaticClass
							.getSkip("DS32H", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0) {
						return false;
					}

				}
				return false;
			}

			return true;
		}
		if (propertyName.equalsIgnoreCase("DS34")) {
			String val = CommonStaticClass.getSkip("DS34", "SCVBDS", dbHelper);
			if (val == null) {
				return false;
			}

			if (val.length() <= 0) {
				return false;
			}
			if (val.length() > 0) {

				if (Integer.parseInt(val) > 0) {
					val = CommonStaticClass
							.getSkip("DS35D", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0) {
						return false;
					}

					val = CommonStaticClass
							.getSkip("DS35H", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0) {
						return false;
					}

				}
				return false;
			}

			return true;
		}
		if (propertyName.equalsIgnoreCase("DS41")) {
			String val = CommonStaticClass.getSkip("DS41", "SCVBDS", dbHelper);
			if (val == null) {
				return false;
			}

			if (val.length() <= 0) {
				return false;
			}
			if (val.length() > 0) {

				if (Integer.parseInt(val) == 2) {
					val = CommonStaticClass.getSkip("DS42", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0) {
						return false;
					}

				}
				return false;
			}

			return true;
		}
		if (propertyName.equalsIgnoreCase("DS56")) {
			String val = CommonStaticClass.getSkip("DS56", "SCVBDS", dbHelper);
			if (val == null) {
				return false;
			}

			if (val.length() <= 0) {
				return false;
			}
			if (val.length() > 0) {

				if (Integer.parseInt(val) == 5) {
					val = CommonStaticClass
							.getSkip("DS59C", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0) {
						return false;
					}

					val = CommonStaticClass
							.getSkip("DS59P", "SCVBDS", dbHelper);
					if (val == null) {
						return false;
					}

					if (val.length() <= 0) {
						return false;
					}

				}
				return false;
			}

			return true;
		}
		return false;
	}

	public PatientDetail GetPatientdetail(DatabaseHelper dhelper) {
		PatientDetail p = new PatientDetail();
		String sql = "";
		sql = String.format("Select * from  '%s' where dataid='%s'",
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getTablename(),
				CommonStaticClass.dataId);

		Cursor mCursor1 = null;
		try {

			mCursor1 = dhelper.getQueryCursor(sql);

			if (mCursor1.getCount() > 0) {

				if (mCursor1.moveToFirst()) {
					do {

						p.PatientID = mCursor1.getString(mCursor1
								.getColumnIndex("HOSPID")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("HOSPID"));
						p.PatientTY = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("PatientTY")) == -1 ? ""
								: mCursor1.getInt(mCursor1
										.getColumnIndex("PatientTY")));

						p.RegDate = mCursor1.getString(mCursor1
								.getColumnIndex("RegDate")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("RegDate"));
						p.RegTime = mCursor1.getString(mCursor1
								.getColumnIndex("RegTime")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("RegTime"));
						p.Name = mCursor1.getString(mCursor1
								.getColumnIndex("Name")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Name"));
						p.BDate = mCursor1.getString(mCursor1
								.getColumnIndex("BDate")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("BDate"));

						p.Sex = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("Sex")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("Sex")));
						p.AgeYr = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("AgeYr")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("AgeYr")));
						p.AgeMo = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("AgeMo")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("AgeMo")));
						p.AgeDa = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("AgeDa")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("AgeDa")));
						p.BDType = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("BDType")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("BDType")));

						p.FaSpName = mCursor1.getString(mCursor1
								.getColumnIndex("FaSpName")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("FaSpName"));

						p.Ward = mCursor1.getString(mCursor1
								.getColumnIndex("Ward")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Ward"));
						p.Area = mCursor1.getString(mCursor1
								.getColumnIndex("Area")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Area"));
						p.SecBlock = mCursor1.getString(mCursor1
								.getColumnIndex("SecBlock")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("SecBlock"));
						p.Road = mCursor1.getString(mCursor1
								.getColumnIndex("Road")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Road"));
						p.House = mCursor1.getString(mCursor1
								.getColumnIndex("House")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("House"));
						p.HH = mCursor1
								.getString(mCursor1.getColumnIndex("HH")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("HH"));
						p.SL = mCursor1
								.getString(mCursor1.getColumnIndex("SL")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("SL"));
						p.PID = mCursor1.getString(mCursor1
								.getColumnIndex("PID")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("PID"));

						p.Phone = mCursor1.getString(mCursor1
								.getColumnIndex("Phone")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Phone"));

						if (p.Phone != null) {
							if (p.Phone.contains(",")) {
								if (p.Phone.length() >= 23) {
									p.Phone2 = p.Phone.substring(12, 23);
									p.Phone = p.Phone.substring(0, 11);
								}

							}
						}

						p.Vill = mCursor1.getString(mCursor1
								.getColumnIndex("Vill")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Vill"));
						p.UPZ = mCursor1.getString(mCursor1
								.getColumnIndex("UPZ")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("UPZ"));
						p.Dist = mCursor1.getString(mCursor1
								.getColumnIndex("Dist")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Dist"));

						p.WT = mCursor1
								.getString(mCursor1.getColumnIndex("WT")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("WT"));
						p.HT = mCursor1
								.getString(mCursor1.getColumnIndex("HT")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("HT"));
						p.MUAC = mCursor1.getString(mCursor1
								.getColumnIndex("MUAC")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("MUAC"));

						p.DS17 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS17")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS17"))));

						p.DS18D = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS18D")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS18D")));
						p.DS18H = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS18H")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS18H")));
						p.DS19 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS19")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS19")));
						p.DS20 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS20")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS20")));
						p.DS21 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS21")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS21")));
						p.DS22 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS22")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS22")));
						p.DS23 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS23")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS23")));
						p.DS24 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS24")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS24")));
						p.DS25 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS25")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS25")));

						p.DS26 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS26")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS26")));

						p.DS26a = (mCursor1.getString(mCursor1
								.getColumnIndex("DS26a")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("DS26a")));
						p.DS26b = (mCursor1.getString(mCursor1
								.getColumnIndex("DS26b")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("DS26b")));

						if (p.DS26a == null) {
							p.DS26a = "";
						}
						if (p.DS26b == null) {
							p.DS26b = "";
						}

						// vomiting
						p.DS27 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS27")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS27"))));

						p.DS28D = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS28D")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS28D"))));

						p.DS28H = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS28H")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS28H"))));

						p.DS29 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS29")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS29"))));

						p.DS30 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS30")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS30"))));

						p.DS31 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS31")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS31"))));

						p.DS32D = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS32D")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS32D"))));

						p.DS32H = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS32H")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS32H"))));

						p.DS33 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS33")) == null ? "0.0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS33")));

						/*
						 * if(p.DS33.equalsIgnoreCase("null")) { p.DS33=""; }
						 */

						p.DS34 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS34")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS34"))));

						p.DS35D = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS35D")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS35D"))));

						p.DS35H = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS35H")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS35H"))));

						// ORS

						p.DS36 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS36")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS36"))));

						p.DS37 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS37")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS37"))));

						p.DS38 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS38")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS38"))));

						p.DS39 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS39")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS39"))));

						p.DS40 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS40")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS40"))));

						p.DS41 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS41")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS41"))));

						p.DS42 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS42")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS42")));

						p.DS43 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS43")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS43")));

						p.DS44 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS44")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS44"))));

						p.DS45 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS45")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS45"))));

						p.DS46 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS46")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS46"))));

						p.DS47 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS47")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS47"))));

						p.DS48 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS48")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS48"))));

						p.DS49 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS49")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS49"))));

						p.DS50 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS50")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS50"))));

						p.DS51 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS51")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS51"))));

						p.DS52 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS52")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS52"))));

						p.DS53 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS53")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS53"))));
						p.DS54 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS54")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS54"))));
						p.DS55 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS55")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS55")));
						p.DS56 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS56")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS56"))));

						p.DS57 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS57")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS57")));

						p.DS58 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS58")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS58")));

						p.DS59C = (mCursor1.getString(mCursor1
								.getColumnIndex("DS59C")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS59C")));

						p.DS59P = (mCursor1.getString(mCursor1
								.getColumnIndex("DS59P")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS59P")));

						// children
						p.DS60 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS60")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS60"))));

						p.DS61 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS61")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS61")));

						p.DS62 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS62")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS62"))));

						p.DS63 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS63")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS63"))));

						p.DS64 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS64")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS64"))));

						p.DS65 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS65")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS65"))));

						p.DS66 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS66")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS66"))));

						p.DS67 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS67")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS67"))));

						p.DS68 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS68")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS68"))));

						p.DS69 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS69")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS69"))));

						p.GISID = (mCursor1.getString(mCursor1
								.getColumnIndex("GISID")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("GISID")));

					} while (mCursor1.moveToNext());

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return p;
	}

	public PatientDetail GetPatientdetail(DatabaseHelper dhelper, String dataid) {
		PatientDetail p = new PatientDetail();
		String sql = "";
		sql = String.format("Select * from  SCVBDS where dataid='%s'", dataid);

		Cursor mCursor1 = null;
		try {

			mCursor1 = dhelper.getQueryCursor(sql);

			if (mCursor1.getCount() > 0) {

				if (mCursor1.moveToFirst()) {
					do {

						p.PatientID = mCursor1.getString(mCursor1
								.getColumnIndex("HOSPID")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("HOSPID"));
						p.PatientTY = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("PatientTY")) == -1 ? ""
								: mCursor1.getInt(mCursor1
										.getColumnIndex("PatientTY")));

						p.RegDate = mCursor1.getString(mCursor1
								.getColumnIndex("RegDate")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("RegDate"));
						p.RegTime = mCursor1.getString(mCursor1
								.getColumnIndex("RegTime")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("RegTime"));
						p.Name = mCursor1.getString(mCursor1
								.getColumnIndex("Name")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Name"));
						p.BDate = mCursor1.getString(mCursor1
								.getColumnIndex("BDate")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("BDate"));

						p.Sex = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("Sex")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("Sex")));
						p.AgeYr = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("AgeYr")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("AgeYr")));
						p.AgeMo = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("AgeMo")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("AgeMo")));
						p.AgeDa = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("AgeDa")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("AgeDa")));
						p.BDType = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("BDType")) == -1 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("BDType")));

						p.FaSpName = mCursor1.getString(mCursor1
								.getColumnIndex("FaSpName")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("FaSpName"));

						p.Ward = mCursor1.getString(mCursor1
								.getColumnIndex("Ward")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Ward"));
						p.Area = mCursor1.getString(mCursor1
								.getColumnIndex("Area")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Area"));
						p.SecBlock = mCursor1.getString(mCursor1
								.getColumnIndex("SecBlock")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("SecBlock"));
						p.Road = mCursor1.getString(mCursor1
								.getColumnIndex("Road")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Road"));
						p.House = mCursor1.getString(mCursor1
								.getColumnIndex("House")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("House"));
						p.HH = mCursor1
								.getString(mCursor1.getColumnIndex("HH")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("HH"));
						p.SL = mCursor1
								.getString(mCursor1.getColumnIndex("SL")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("SL"));
						p.PID = mCursor1.getString(mCursor1
								.getColumnIndex("PID")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("PID"));

						p.Phone = mCursor1.getString(mCursor1
								.getColumnIndex("Phone")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Phone"));

						if (p.Phone != null) {
							if (p.Phone.contains(",")) {
								if (p.Phone.length() >= 23) {
									p.Phone2 = p.Phone.substring(12, 23);
									p.Phone = p.Phone.substring(0, 11);
								}

							}
						}

						p.Vill = mCursor1.getString(mCursor1
								.getColumnIndex("Vill")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Vill"));
						p.UPZ = mCursor1.getString(mCursor1
								.getColumnIndex("UPZ")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("UPZ"));
						p.Dist = mCursor1.getString(mCursor1
								.getColumnIndex("Dist")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("Dist"));

						p.WT = mCursor1
								.getString(mCursor1.getColumnIndex("WT")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("WT"));
						p.HT = mCursor1
								.getString(mCursor1.getColumnIndex("HT")) == "" ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("HT"));
						p.MUAC = mCursor1.getString(mCursor1
								.getColumnIndex("MUAC")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("MUAC"));

						p.DS17 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS17")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS17"))));

						p.DS18D = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS18D")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS18D")));
						p.DS18H = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS18H")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS18H")));
						p.DS19 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS19")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS19")));
						p.DS20 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS20")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS20")));
						p.DS21 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS21")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS21")));
						p.DS22 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS22")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS22")));
						p.DS23 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS23")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS23")));
						p.DS24 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS24")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS24")));
						p.DS25 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS25")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS25")));

						p.DS26 = (Integer) (mCursor1.getInt(mCursor1
								.getColumnIndex("DS26")) == 0 ? 0 : mCursor1
								.getInt(mCursor1.getColumnIndex("DS26")));

						p.DS26a = (mCursor1.getString(mCursor1
								.getColumnIndex("DS26a")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("DS26a")));
						p.DS26b = (mCursor1.getString(mCursor1
								.getColumnIndex("DS26b")) == "" ? "" : mCursor1
								.getString(mCursor1.getColumnIndex("DS26b")));

						if (p.DS26a == null) {
							p.DS26a = "";
						}
						if (p.DS26b == null) {
							p.DS26b = "";
						}

						// vomiting
						p.DS27 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS27")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS27"))));

						p.DS28D = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS28D")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS28D"))));

						p.DS28H = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS28H")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS28H"))));

						p.DS29 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS29")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS29"))));

						p.DS30 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS30")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS30"))));

						p.DS31 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS31")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS31"))));

						p.DS32D = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS32D")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS32D"))));

						p.DS32H = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS32H")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS32H"))));

						p.DS33 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS33")) == null ? "0.0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS33")));

						/*
						 * if(p.DS33.equalsIgnoreCase("null")) { p.DS33=""; }
						 */

						p.DS34 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS34")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS34"))));

						p.DS35D = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS35D")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS35D"))));

						p.DS35H = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS35H")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS35H"))));

						// ORS

						p.DS36 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS36")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS36"))));

						p.DS37 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS37")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS37"))));

						p.DS38 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS38")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS38"))));

						p.DS39 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS39")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS39"))));

						p.DS40 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS40")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS40"))));

						p.DS41 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS41")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS41"))));

						p.DS42 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS42")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS42")));

						p.DS43 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS43")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS43")));

						p.DS44 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS44")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS44"))));

						p.DS45 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS45")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS45"))));

						p.DS46 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS46")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS46"))));

						p.DS47 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS47")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS47"))));

						p.DS48 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS48")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS48"))));

						p.DS49 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS49")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS49"))));

						p.DS50 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS50")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS50"))));

						p.DS51 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS51")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS51"))));

						p.DS52 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS52")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS52"))));

						p.DS53 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS53")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS53"))));
						p.DS54 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS54")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS54"))));
						p.DS55 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS55")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS55")));
						p.DS56 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS56")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS56"))));

						p.DS57 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS57")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS57")));

						p.DS58 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS58")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS58")));

						p.DS59C = (mCursor1.getString(mCursor1
								.getColumnIndex("DS59C")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS59C")));

						p.DS59P = (mCursor1.getString(mCursor1
								.getColumnIndex("DS59P")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS59P")));

						// children
						p.DS60 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS60")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS60"))));

						p.DS61 = (mCursor1.getString(mCursor1
								.getColumnIndex("DS61")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS61")));

						p.DS62 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS62")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS62"))));

						p.DS63 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS63")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS63"))));

						p.DS64 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS64")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS64"))));

						p.DS65 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS65")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS65"))));

						p.DS66 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS66")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS66"))));

						p.DS67 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS67")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS67"))));

						p.DS68 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS68")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS68"))));

						p.DS69 = Integer.parseInt((mCursor1.getString(mCursor1
								.getColumnIndex("DS69")) == null ? "0"
								: mCursor1.getString(mCursor1
										.getColumnIndex("DS69"))));

						p.GISID = (mCursor1.getString(mCursor1
								.getColumnIndex("GISID")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("GISID")));

						p.entryTime = (mCursor1.getString(mCursor1
								.getColumnIndex("EntryTime")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("EntryTime")));

						p.editTime = (mCursor1.getString(mCursor1
								.getColumnIndex("EditTime")) == null ? ""
								: mCursor1.getString(mCursor1
										.getColumnIndex("EditTime")));

					} while (mCursor1.moveToNext());

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return p;
	}

	private boolean IsValidPatientDetail(ViewGroup v) {

		ViewGroup viewGroup = (LinearLayout) v
				.findViewById(R.id.checkBoxHolder);
		int nrOfChildren = viewGroup.getChildCount();
		boolean alltrue = true;
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					if (view.getVisibility() == View.GONE) {
						continue;
					}

					else {
						if (ExcludeFromvalidation(view)) {
							continue;
						} else {
							view.setBackgroundResource(R.drawable.border);
							view.requestFocus();
							return false;
						}

					}

				}
			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE) {
					continue;
				} else if (ExcludeFromvalidation(view)) {
					continue;

				} else {
					if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {

						((RadioGroup) view)
								.setBackgroundResource(R.drawable.border);
						return false;

					}
				}

			}

		}
		return true;

	}

	private boolean IsValidPatientAge(ViewGroup v) {

		ViewGroup viewGroup = (LinearLayout) v
				.findViewById(R.id.checkBoxHolder2);
		int nrOfChildren = viewGroup.getChildCount();
		boolean alltrue = true;
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					if (view.getVisibility() == View.GONE) {
						continue;
					}

					else {
						if (ExcludeFromvalidation(view)) {
							continue;
						} else {
							view.setBackgroundResource(R.drawable.border);
							view.requestFocus();
							return false;
						}

					}

				}
			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE) {
					continue;
				} else if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					((RadioGroup) view)
							.setBackgroundResource(R.drawable.border);
					return false;

				}

			}

		}
		return true;

	}

	private boolean ExcludeFromvalidation(View v) {
		boolean excluded = false;

		if (v instanceof EditText) {
			switch (v.getId()) {
			case R.id.txtPhone:
				excluded = true;
				break;

			case R.id.txtPhone2:
				excluded = true;
				break;

			case R.id.txtHH:
				excluded = true;
				break;

			case R.id.txtSL:
				excluded = true;
				break;

			case R.id.txtPID:
				excluded = true;
				break;

			case R.id.txtArea:
				excluded = true;
				break;

			case R.id.txtSecBlock:
				excluded = true;
				break;

			case R.id.txtRoad:
				excluded = true;
				break;

			case R.id.txtHouse:
				excluded = true;
				break;

			case R.id.txtweight:
				excluded = true;
				break;

			case R.id.txtheight:
				excluded = true;
				break;

			case R.id.txtmuac:
				excluded = true;
				break;

			case R.id.DS26:
				excluded = true;
				break;

			case R.id.DS33:
				excluded = true;
				break;

			/*
			 * case R.id.DS36: excluded = true; break;
			 */

			case R.id.DS37:
				excluded = true;
				break;

			case R.id.DS39:
				excluded = true;
				break;

			case R.id.DS40:
				excluded = true;
				break;

			case R.id.DS43:
				excluded = true;
				break;

			case R.id.DS44:
				excluded = true;
				break;

			case R.id.DS45:
				excluded = true;
				break;

			case R.id.DS46:
				excluded = true;
				break;

			case R.id.DS47:
				excluded = true;
				break;

			case R.id.DS48:
				excluded = true;
				break;

			case R.id.DS49:
				excluded = true;
				break;

			case R.id.DS50:
				excluded = true;
				break;

			case R.id.DS52:
				excluded = true;
				break;

			case R.id.DS53:
				excluded = true;
				break;

			case R.id.DS54:
				excluded = true;
				break;

			case R.id.DS55:
				excluded = true;
				break;

			case R.id.DS56:
				excluded = true;
				break;

			case R.id.DS57:
				excluded = true;
				break;

			case R.id.DS58:
				excluded = true;
				break;
			case R.id.DS59C:
				excluded = true;
				break;

			case R.id.DS59P:
				excluded = true;
				break;

			default:
				break;
			}

			return excluded;
		}

		return true;
	}

	public String getWT() {
		return WT;
	}

	public void setWT(String wT) {
		WT = wT;
	}

	public String getHT() {
		return HT;
	}

	public void setHT(String hT) {
		HT = hT;
	}

	public String getMUAC() {
		return MUAC;
	}

	public void setMUAC(String mUAC) {
		MUAC = mUAC;
	}

	public Boolean SavePatientWeight(
			com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper dbHelper, ViewGroup v) {

		if (!IsValidPatientDetail(v))
			return false;
		else {

			PatientDetail pd = new PatientDetail();
			pd.setWT(((EditText) v.findViewById(R.id.txtweight)).getText()
					.toString());
			pd.setHT(((EditText) v.findViewById(R.id.txtheight)).getText()
					.toString());
			pd.setMUAC(((EditText) v.findViewById(R.id.txtmuac)).getText()
					.toString());

			if (pd.WT.length() > 0) {
				pd.WT = String.format("%.2f", Float.parseFloat(pd.WT));
			}
			if (pd.HT.length() > 0) {
				pd.HT = String.format("%.2f", Float.parseFloat(pd.HT));
			}
			if (pd.MUAC.length() > 0) {
				pd.MUAC = String.format("%.2f", Float.parseFloat(pd.MUAC));
			}

			Date d = new Date(System.currentTimeMillis());
			String entryDate = "dd-mmm-yy";

			entryDate = d.toLocaleString();

			String s1 = String
					.format("UPDATE SCVBDS SET WT ='%s', HT = '%s', MUAC = '%s', EditBy='%s', EditDate='%s' , EditTime = '%s' WHERE dataid = '%s'",
							pd.WT, pd.HT, pd.MUAC,
							CommonStaticClass.userSpecificId,
							CommonStaticClass.GetDate(),
							CommonStaticClass.GetTime(),
							CommonStaticClass.dataId);

			if (pd.WT.length() > 0) {
				if (Float.valueOf(pd.WT) > 200) {
					return false;
				}
			}

			if (pd.HT.length() > 0) {
				if (Float.valueOf(pd.HT) > 200) {
					return false;

				}
			}
			if (pd.MUAC.length() > 0) {
				if (Float.valueOf(pd.MUAC) > 50.99) {
					return false;

				}
			}
			if (dbHelper.executeDMLQuery(s1)) {
				return true;
			}

			return false;
		}
	}

	public int getDS17() {
		return DS17;
	}

	public void setDS17(int dS17) {
		DS17 = dS17;
	}

	public int getDS18D() {
		return DS18D;
	}

	public void setDS18D(int dS18D) {
		DS18D = dS18D;
	}

	public int getDS18H() {
		return DS18H;
	}

	public void setDS18H(int dS18H) {
		DS18H = dS18H;
	}

	public int getDS19() {
		return DS19;
	}

	public void setDS19(int dS19) {
		DS19 = dS19;
	}

	public int getDS20() {
		return DS20;
	}

	public void setDS20(int dS20) {
		DS20 = dS20;
	}

	public int getDS21() {
		return DS21;
	}

	public void setDS21(int dS21) {
		DS21 = dS21;
	}

	public int getDS22() {
		return DS22;
	}

	public void setDS22(int dS22) {
		DS22 = dS22;
	}

	public int getDS23() {
		return DS23;
	}

	public void setDS23(int dS23) {
		DS23 = dS23;
	}

	public int getDS24() {
		return DS24;
	}

	public void setDS24(int dS24) {
		DS24 = dS24;
	}

	public int getDS25() {
		return DS25;
	}

	public void setDS25(int dS25) {
		DS25 = dS25;
	}

	public int getDS26() {
		return DS26;
	}

	public void setDS26(int dS26) {
		DS26 = dS26;
	}

	public Boolean SavePatientDiarrhea(
			com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper dbHelper, ViewGroup v) {

		if (!IsValidPatientDetail(v))
			return false;
		else {

			PatientDetail pd = new PatientDetail();

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS17))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS17(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS17))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS17(2);

			pd.setDS18D(Integer.parseInt(((EditText) v.findViewById(R.id.DS18D))
					.getText().toString()));

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS18H))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS18H(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS18H))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS18H(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS19))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS19(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS19))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS19(2);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS19))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS19(3);

			pd.setDS20(Integer.parseInt(((EditText) v.findViewById(R.id.DS20))
					.getText().toString()));
			pd.setDS21(Integer.parseInt(((EditText) v.findViewById(R.id.DS21))
					.getText().toString()));

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS22))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS22(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS22))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS22(2);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS22))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS22(3);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS23))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS23(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS23))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS23(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS24))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS24(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS24))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS24(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS25))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS25(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS25))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS25(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS26))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS26(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS26))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS26(2);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS26))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS26(3);

			if (pd.DS26 == 2|| pd.DS26 == 3) {
				pd.DS26a = ((EditText) v.findViewById(R.id.DS26a)).getText()
						.toString();
				pd.DS26b = ((EditText) v.findViewById(R.id.DS26b)).getText()
						.toString();
			}

			Date d = new Date(System.currentTimeMillis());
			String entryDate = "dd-mmm-yy";

			entryDate = d.toLocaleString();

			String s1 = String
					.format("UPDATE SCVBDS SET DS17='%s',DS18D='%s',DS18H='%s',DS19='%s',DS20='%s',DS21='%s',DS22='%s',DS23='%s',DS24='%s',DS25='%s',DS26='%s', EditBy='%s', EditDate='%s', DS26a='%s', DS26b='%s'"
							+ " , EditTime = '%s' WHERE dataid = '%s'",
							pd.DS17, pd.DS18D, pd.DS18H, pd.DS19, pd.DS20,
							pd.DS21, pd.DS22, pd.DS23, pd.DS24, pd.DS25,
							pd.DS26, CommonStaticClass.userSpecificId,
							CommonStaticClass.GetDate(), pd.DS26a, pd.DS26b,
							CommonStaticClass.GetTime(),
							CommonStaticClass.dataId);

			dbHelper.executeDMLQuery(s1);

			ArrayList<String> list = new ArrayList<String>();

			list = new ArrayList<String>();
			list.add("DS26a");
			list.add("DS26b");
			if (pd.DS26 == 1) {
				if (this.SetNull(dbHelper, list)) {

				}
			}

			list = new ArrayList<String>();

			list.add("DS18D");
			list.add("DS18H");
			list.add("DS19");
			list.add("DS20");
			list.add("DS21");
			list.add("DS22");
			list.add("DS23");
			list.add("DS24");
			list.add("DS25");

			if (pd.DS17 == 1) {
				if (this.SetNull(dbHelper, list)) {
					return true;
				}
			} else {
				return true;
			}
			return false;

		}
	}

	public int getDS27() {
		return DS27;
	}

	public void setDS27(int dS27) {
		DS27 = dS27;
	}

	public int getDS28D() {
		return DS28D;
	}

	public void setDS28D(int dS28D) {
		DS28D = dS28D;
	}

	public int getDS28H() {
		return DS28H;
	}

	public void setDS28H(int dS28H) {
		DS28H = dS28H;
	}

	public int getDS29() {
		return DS29;
	}

	public void setDS29(int dS29) {
		DS29 = dS29;
	}

	public int getDS30() {
		return DS30;
	}

	public void setDS30(int dS30) {
		DS30 = dS30;
	}

	public int getDS31() {
		return DS31;
	}

	private void setDS31(int dS31) {
		DS31 = dS31;
	}

	public int getDS32D() {
		return DS32D;
	}

	public void setDS32D(int dS32D) {
		DS32D = dS32D;
	}

	public int getDS32H() {
		return DS32H;
	}

	public void setDS32H(int dS32H) {
		DS32H = dS32H;
	}

	public String getDS33() {
		return DS33;
	}

	public void setDS33(String dS33) {
		DS33 = dS33;
	}

	public int getDS34() {
		return DS34;
	}

	public void setDS34(int dS34) {
		DS34 = dS34;
	}

	public int getDS35D() {
		return DS35D;
	}

	public void setDS35D(int dS35D) {
		DS35D = dS35D;
	}

	public int getDS35H() {
		return DS35H;
	}

	public void setDS35H(int dS35H) {
		DS35H = dS35H;
	}

	// vomiting
	public Boolean SavePatientVomiting(
			com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper dbHelper, ViewGroup v) {

		if (!IsValidPatientDetail(v))
			return false;
		else {

			PatientDetail pd = new PatientDetail();

			pd.setDS27(Integer.parseInt(((EditText) v.findViewById(R.id.DS27))
					.getText().toString()));

			pd.setDS28D(Integer.parseInt(((EditText) v.findViewById(R.id.DS28D))
					.getText().toString()));

			if (pd.DS27 > 0) {
				if (pd.DS28D <= 0) {
					DisplayToast(ParentActivity.thisactivity,
							"Invalid Vomiting Duration", 1);
					return false;
				}
			}
			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS28H))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS28H(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS28H))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS28H(2);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS28H))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS28H(3);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS29))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS29(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS29))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS29(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS30))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS30(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS30))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS30(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS31))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS31(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS31))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS31(2);

			pd.setDS32D(Integer.parseInt(((EditText) v.findViewById(R.id.DS32D))
					.getText().toString()));

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS32H))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS32H(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS32H))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS32H(2);

			pd.setDS33(((EditText) v.findViewById(R.id.DS33)).getText()
					.toString());

		/*	if (String.valueOf(((Double.parseDouble(pd.DS33) - 32) * 5) / 9)
					.length() >= 5) {

				pd.DS33 = String.format("%.2f", Float.parseFloat(pd.DS33));

				pd.setFahrenheit(String.valueOf(
						((Double.parseDouble(pd.DS33) - 32) * 5) / 9)
						.substring(0, 5));
			} else {
				pd.DS33 = String.format("%.2f", Float.parseFloat(pd.DS33));
				pd.setFahrenheit(String.valueOf(((Double.parseDouble(pd.DS33) - 32) * 5) / 9));
			}*/

			
			
			if (String.valueOf(((Double.parseDouble(pd.DS33) - 32) * 5) / 9)
					.length() >= 5) {

				pd.DS33 = String.format("%.2f", Float.parseFloat(pd.DS33));

				pd.setFahrenheit(String.valueOf(
						((Double.parseDouble(pd.DS33) - 32) * 5) / 9)
						.substring(0, 5));
			} else {
				pd.DS33 = String.format("%.2f", Float.parseFloat(pd.DS33));
				pd.setFahrenheit(String.valueOf(((Double.parseDouble(pd.DS33) - 32) * 5) / 9));
			}
			
			
			
			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS34))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS34(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS34))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS34(2);

			pd.setDS35D(Integer.parseInt(((EditText) v.findViewById(R.id.DS35D))
					.getText().toString()));

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS35H))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS35H(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS35H))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS35H(2);

			Date d = new Date(System.currentTimeMillis());
			String entryDate = "dd-mmm-yy";

			entryDate = d.toLocaleString();

			String s1 = String
					.format("UPDATE SCVBDS SET DS27='%s', DS28D='%s',DS28H='%s',DS29='%s',DS30='%s',DS31='%s',DS32D='%s',DS32H='%s',DS33='%s',DS34='%s',DS35D='%s',DS35H='%s', Fahrenheit = '%s', EditBy='%s', EditDate='%s' , EditTime = '%s' WHERE dataid = '%s'",
							pd.DS27, pd.DS28D, pd.DS28H, pd.DS29, pd.DS30,
							pd.DS31, pd.DS32D, pd.DS32H, pd.DS33, pd.DS34,
							pd.DS35D, pd.DS35H, pd.Fahrenheit,
							CommonStaticClass.userSpecificId,
							CommonStaticClass.GetDate(),
							CommonStaticClass.GetTime(),
							CommonStaticClass.dataId);

			if (Float.valueOf(pd.DS33) < 90 || Float.valueOf(pd.DS33) > 110) {
				DisplayToast(ParentActivity.thisactivity,
						"Invalid temperature", 1);
				return false;
			}

			dbHelper.executeDMLQuery(s1);

			ArrayList<String> list = new ArrayList<String>();

			list.add("DS28D");
			list.add("DS28H");
			if (pd.DS27 == 0) {
				if (this.SetNull(dbHelper, list)) {
					// return true;
				}
			}
			if (pd.DS31 == 1) {
				list = new ArrayList<String>();

				list.add("DS32D");
				list.add("DS32H");

				if (this.SetNull(dbHelper, list)) {
					// return true;
				}
			}
			if (pd.DS34 == 1) {
				list = new ArrayList<String>();

				list.add("DS35D");
				list.add("DS35H");

				if (this.SetNull(dbHelper, list)) {
					// return true;
				}
			}

			return true;

		}

	}

	public int getDS36() {
		return DS36;
	}

	public void setDS36(int dS36) {
		DS36 = dS36;
	}

	public int getDS37() {
		return DS37;
	}

	public void setDS37(int dS37) {
		DS37 = dS37;
	}

	public int getDS38() {
		return DS38;
	}

	public void setDS38(int dS38) {
		DS38 = dS38;
	}

	public int getDS39() {
		return DS39;
	}

	public void setDS39(int dS39) {
		DS39 = dS39;
	}

	public int getDS40() {
		return DS40;
	}

	public void setDS40(int dS40) {
		DS40 = dS40;
	}

	public int getDS41() {
		return DS41;
	}

	public void setDS41(int dS41) {
		DS41 = dS41;
	}

	public String getDS42() {
		return DS42;
	}

	public void setDS42(String dS42) {
		DS42 = dS42;
	}

	public String getDS43() {
		return DS43;
	}

	public void setDS43(String dS43) {
		DS43 = dS43;
	}

	public int getDS44() {
		return DS44;
	}

	public void setDS44(int dS44) {
		DS44 = dS44;
	}

	public int getDS45() {
		return DS45;
	}

	public void setDS45(int dS45) {
		DS45 = dS45;
	}

	public int getDS46() {
		return DS46;
	}

	public void setDS46(int dS46) {
		DS46 = dS46;
	}

	public int getDS47() {
		return DS47;
	}

	public void setDS47(int dS47) {
		DS47 = dS47;
	}

	public int getDS48() {
		return DS48;
	}

	public void setDS48(int dS48) {
		DS48 = dS48;
	}

	public int getDS49() {
		return DS49;
	}

	public void setDS49(int dS49) {
		DS49 = dS49;
	}

	public int getDS50() {
		return DS50;
	}

	public void setDS50(int dS50) {
		DS50 = dS50;
	}

	public int getDS51() {
		return DS51;
	}

	public void setDS51(int dS51) {
		DS51 = dS51;
	}

	public int getDS52() {
		return DS52;
	}

	public void setDS52(int dS52) {
		DS52 = dS52;
	}

	public int getDS53() {
		return DS53;
	}

	public void setDS53(int dS53) {
		DS53 = dS53;
	}

	public int getDS54() {
		return DS54;
	}

	public void setDS54(int dS54) {
		DS54 = dS54;
	}

	public String getDS55() {
		return DS55;
	}

	public void setDS55(String dS55) {
		DS55 = dS55;
	}

	public int getDS56() {
		return DS56;
	}

	public void setDS56(int dS56) {
		DS56 = dS56;
	}

	public String getDS57() {
		return DS57;
	}

	public void setDS57(String dS57) {
		DS57 = dS57;
	}

	public String getDS58() {
		return DS58;
	}

	public void setDS58(String dS58) {
		DS58 = dS58;
	}

	public String getDS59C() {
		return DS59C;
	}

	public void setDS59C(String dS59C) {
		DS59C = dS59C;
	}

	public String getDS59P() {
		return DS59P;
	}

	public void setDS59P(String dS59P) {
		DS59P = dS59P;
	}

	// ORS
	private boolean IsValidDischargeDate(ViewGroup vg,
			com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper dHelper) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = CommonStaticClass.getSkip("RegDate", "SCVBDS",
				dHelper);

		Date startdate = null;
		Date enddate = null;
		try {
			startdate = formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dateInString = ((EditText) vg.findViewById(R.id.DS57)).getText()
				.toString();
		try {
			enddate = formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Context con = ParentActivity.thisactivity.getApplicationContext();

		if (futureDateValidator(enddate)) {
			DisplayToast(con, "Future date violation in Discharge date", 1);
			return false;
		}

		/*if (!CommonStaticClass.IsValidAdmissionDate(startdate, enddate))

		{
			return false;
		}*/

		return true;

	}

	public Boolean SavePatientORS(
			com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper dbHelper, ViewGroup v) {

		Context con = ParentActivity.thisactivity.getApplicationContext();

		if (!IsValidPatientDetail(v)) {
			return false;
		}

		else {

			PatientDetail pd = new PatientDetail();

			pd.setDS36(Integer.parseInt(((EditText) v.findViewById(R.id.DS36))
					.getText().toString()));

			pd.setDS37(Integer.parseInt(((EditText) v.findViewById(R.id.DS37))
					.getText().toString()));

			pd.setDS38(Integer.parseInt(((EditText) v.findViewById(R.id.DS38))
					.getText().toString()));

			pd.setDS39(Integer.parseInt(((EditText) v.findViewById(R.id.DS39))
					.getText().toString()));

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS40))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS40(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS40))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS40(2);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS40))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS40(3);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS40))
					.findViewById(R.id.radio3)).isChecked() == true)
				pd.setDS40(4);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS40))
					.findViewById(R.id.radio4)).isChecked() == true)
				pd.setDS40(5);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS41))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS41(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS41))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS41(2);

			pd.setDS42(((EditText) v.findViewById(R.id.DS42)).getText()
					.toString());

			pd.setDS43(((EditText) v.findViewById(R.id.DS43)).getText()
					.toString());

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS44))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS44(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS44))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS44(2);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS44))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS44(3);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS44))
					.findViewById(R.id.radio3)).isChecked() == true)
				pd.setDS44(4);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS45))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS45(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS45))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS45(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS46))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS46(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS46))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS46(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS47))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS47(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS47))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS47(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS48))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS48(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS48))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS48(2);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS48))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS48(3);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS48))
					.findViewById(R.id.radio3)).isChecked() == true)
				pd.setDS48(4);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS48))
					.findViewById(R.id.radio4)).isChecked() == true)
				pd.setDS48(5);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS49))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS49(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS49))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS49(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS50))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS50(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS50))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS50(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS51))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS51(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS51))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS51(2);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS51))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS51(3);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS52))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS52(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS52))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS52(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS53))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS53(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS53))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS53(2);

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS54))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS54(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS54))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS54(2);

			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS54))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS54(3);

			pd.setDS55(((EditText) v.findViewById(R.id.DS55)).getText()
					.toString());

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS56))
					.findViewById(R.id.radio0)).isChecked() == true)
				pd.setDS56(1);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS56))
					.findViewById(R.id.radio1)).isChecked() == true)
				pd.setDS56(2);

			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS56))
					.findViewById(R.id.radio2)).isChecked() == true)
				pd.setDS56(3);
			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS56))
					.findViewById(R.id.radio3)).isChecked() == true)
				pd.setDS56(4);

			else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDS56))
					.findViewById(R.id.radio4)).isChecked() == true)
				pd.setDS56(5);

			pd.setDS57(((EditText) v.findViewById(R.id.DS57)).getText()
					.toString());

			if (pd.DS57.length() > 0) {
				if (IsValidDischargeDate(v, dbHelper) == false) {
					DisplayToast(con,
							"Registration date and discharge date conflict", 1);
					return false;
				}
			}

			// Calendar c = Calendar.getInstance();

			// CommonStaticClass.IsValidAdmissionDate(startdate,enddate))

			pd.setDS58(((EditText) v.findViewById(R.id.DS58)).getText()
					.toString());

			pd.setDS59C(((EditText) v.findViewById(R.id.DS59C)).getText()
					.toString());

			pd.setDS59P(((EditText) v.findViewById(R.id.DS59P)).getText()
					.toString());

			/*if (CommonStaticClass.getSkip("RegDate", "SCVBDS", dbHelper)
					.equalsIgnoreCase(pd.DS57)) {
				if (!CommonStaticClass.IsValidDischargeTime(CommonStaticClass
						.getSkip("RegTime", "SCVBDS", dbHelper), pd.DS58)) {
					return false;
				}
			}*/

			Date d = new Date(System.currentTimeMillis());
			String entryDate = "dd-mmm-yy";

			entryDate = d.toLocaleString();

			String s1 = String
					.format("UPDATE SCVBDS SET DS36='%s',DS37='%s',DS38='%s',DS39='%s',DS40='%s',DS41='%s', DS42='%s', DS43='%s',DS44='%s',DS45='%s',DS46='%s',DS47='%s',DS48='%s',DS49='%s',DS50='%s',DS51='%s',DS52='%s',DS53='%s',DS54='%s',"
							+ " DS55='%s',DS56='%s', DS57='%s', DS58='%s', DS59C='%s', DS59P='%s', EditBy='%s', EditDate='%s' , EditTime = '%s' WHERE dataid = '%s'",
							pd.DS36, pd.DS37, pd.DS38, pd.DS39, pd.DS40,
							pd.DS41, pd.DS42, pd.DS43, pd.DS44, pd.DS45,
							pd.DS46, pd.DS47, pd.DS48, pd.DS49, pd.DS50,
							pd.DS51, pd.DS52, pd.DS53, pd.DS54, pd.DS55,
							pd.DS56, pd.DS57, pd.DS58, pd.DS59C, pd.DS59P,
							CommonStaticClass.userSpecificId,
							CommonStaticClass.GetDate(),
							CommonStaticClass.GetTime(),
							CommonStaticClass.dataId);

			dbHelper.executeDMLQuery(s1);

			if (pd.DS41 == 1) {
				ArrayList<String> list = new ArrayList<String>();

				list.add("DS42");

				if (this.SetNull(dbHelper, list)) {
					return true;
				}
			}

			return true;
		}
	}

	public int getDS60() {
		return DS60;
	}

	public void setDS60(int dS60) {
		DS60 = dS60;
	}

	public String getDS61() {
		return DS61;
	}

	public void setDS61(String dS61) {
		DS61 = dS61;
	}

	public int getDS62() {
		return DS62;
	}

	public void setDS62(int dS62) {
		DS62 = dS62;
	}

	public int getDS63() {
		return DS63;
	}

	public void setDS63(int dS63) {
		DS63 = dS63;
	}

	public int getDS64() {
		return DS64;
	}

	public void setDS64(int dS64) {
		DS64 = dS64;
	}

	public int getDS65() {
		return DS65;
	}

	public void setDS65(int dS65) {
		DS65 = dS65;
	}

	public int getDS66() {
		return DS66;
	}

	public void setDS66(int dS66) {
		DS66 = dS66;
	}

	public int getDS67() {
		return DS67;
	}

	public void setDS67(int dS67) {
		DS67 = dS67;
	}

	public int getDS68() {
		return DS68;
	}

	public void setDS68(int dS68) {
		DS68 = dS68;
	}

	public int getDS69() {
		return DS69;
	}

	public void setDS69(int dS69) {
		DS69 = dS69;
	}

	public String getGISID() {
		return GISID;
	}

	public void setGISID(String gISID) {
		GISID = gISID;
	}

	
	public Boolean SetNull(DatabaseHelper dbHelper, ArrayList<String> questions) {

		try {
			String sql = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " SET ";
			for (int i = 0; i < questions.size(); i++) {

				sql += questions.get(i) + " = " + null + ",";
			}
			sql = (String) sql.subSequence(0, sql.length() - 1);
			sql += " where dataid='" + CommonStaticClass.dataId + "'";

			if (dbHelper.executeDMLQuery(sql)) {
				return true;
			}

		} catch (Exception e) {
			Log.e("Error from nullify", e.toString());
			e.printStackTrace();
		}
		return false;
	}

	public String getPhone2() {
		return Phone2;
	}

	public void setPhone2(String phone2) {
		Phone2 = phone2;
	}

	public String getFahrenheit() {
		return Fahrenheit;
	}

	public void setFahrenheit(String fahrenheit) {
		Fahrenheit = fahrenheit;
	}

	public ArrayList<String> GetDistrict(
			com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper helper) {
		ArrayList<String> list = new ArrayList<String>();
		/*
		 * String sql = String
		 * .format("SELECT DISTINCT District FROM tblDistrict ORDER BY District"
		 * );
		 */

		String sql = String
				.format("SELECT DISTINCT District FROM tblDistrict UNION SELECT '   ' AS District  ORDER BY District");

		Cursor c = helper.getQueryCursor(sql);

		c = helper.getQueryCursor(sql);

		if (c.getCount() > 0) {

			if (c.moveToFirst()) {
				do {

					if (!c.getString(c.getColumnIndex("District")).toString()
							.equalsIgnoreCase("null")) {
						if (String.valueOf(
								c.getString(c.getColumnIndex("District")))
								.length() > 0) {

							list.add(c.getString(c.getColumnIndex("District"))
									.trim());

						}
					}

				} while (c.moveToNext());

			}
		}

		return list;

	}

	public ArrayList<String> GetUpazilla(
			com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper helper, String District) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(" ");
		String sql = "";
		if (District.trim().length() > 0)
			sql = String
					.format("SELECT Upazilla FROM tblDistrict WHERE District like  '%s' ORDER BY Upazilla",
							"%" + District + "%");
		else
			sql = String
					.format("SELECT Upazilla FROM tblDistrict ORDER BY Upazilla");

		Cursor c = helper.getQueryCursor(sql);

		c = helper.getQueryCursor(sql);

		if (c.getCount() > 0) {

			if (c.moveToFirst()) {
				do {

					if (!c.getString(c.getColumnIndex("Upazilla")).toString()
							.equalsIgnoreCase("null")) {
						if (String.valueOf(
								c.getString(c.getColumnIndex("Upazilla")))
								.length() > 0) {

							list.add(c.getString(c.getColumnIndex("Upazilla"))
									.trim());

						}
					}

				} while (c.moveToNext());

			}
		}

		return list;

	}

	public boolean IsValidMEMSCVB(com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper helper,
			String PID, String HH, String SL) {
		ArrayList<String> list = new ArrayList<String>();
		String sql = "";
		if (PID.length() > 0 && HH.length() > 0 && SL.length() > 0) {
			sql = String
					.format("SELECT PID, Hh, Slno FROM MemSCVB WHERE PID = '%s' AND Hh = '%s' AND Slno = '%s' GROUP BY PID, Hh, Slno",
							PID, HH, SL);
			Cursor c = helper.getQueryCursor(sql);

			c = helper.getQueryCursor(sql);

			if (c.getCount() > 0) {

				if (c.moveToFirst()) {
					do {

						return true;

					} while (c.moveToNext());

				}
			}

		}

		else if (PID.length() > 0 && SL.length() > 0) {
			sql = String
					.format("SELECT PID, Slno FROM MemSCVB WHERE PID = '%s' AND Slno = '%s' GROUP BY PID, Slno",
							PID, SL);

			Cursor c = helper.getQueryCursor(sql);

			c = helper.getQueryCursor(sql);

			if (c.getCount() > 0) {

				if (c.moveToFirst()) {
					do {

						return true;

					} while (c.moveToNext());

				}

			}
		}

		else if (HH.length() > 0) {
			sql = String.format(
					"SELECT Hh FROM MemSCVB WHERE Hh = '%s' GROUP BY Hh", HH);

			Cursor c = helper.getQueryCursor(sql);

			c = helper.getQueryCursor(sql);

			if (c.getCount() > 0) {

				if (c.moveToFirst()) {
					do {

						return true;

					} while (c.moveToNext());

				}

			}
		}

		return false;

	}

	public String getDS26a() {
		return DS26a;
	}

	public void setDS26a(String dS26a) {
		DS26a = dS26a;
	}

	public String getDS26b() {
		return DS26b;
	}

	public void setDS26b(String dS26b) {
		DS26b = dS26b;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
}
