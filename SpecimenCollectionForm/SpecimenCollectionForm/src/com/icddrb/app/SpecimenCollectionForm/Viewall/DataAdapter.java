package com.icddrb.app.SpecimenCollectionForm.Viewall;

import java.util.ArrayList;

import com.icddrb.app.SpecimenCollectionForm.R;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import android.view.LayoutInflater;

import android.widget.BaseAdapter;
import android.widget.TextView;

public class DataAdapter extends BaseAdapter

{

	
	Context mContext;

	/*
	 * private String[] id = { "ID", "S001", "S002", "S003", "S004", "S005",
	 * "S006", "S007" };
	 * 
	 * private String[] name = { "Person Name", "Rohit", "Rahul", "Ravi",
	 * "Amit", "Arun", "Anil", "Kashif" };
	 * 
	 * private String[] address = { "Address", "Rohit", "Rahul", "Ravi", "Amit",
	 * "Arun", "Anil", "Kashif" };
	 */

	ArrayList<String> _PID;
	ArrayList<String> _Hh;
	ArrayList<String> _Slno;
	ArrayList<String> _Name;
	ArrayList<String> _Sex;
	ArrayList<String> _Age;
	ArrayList<String> _BDate;
	ArrayList<String> _Rth;
	ArrayList<String> _MS;
	ArrayList<String> _Area;
	ArrayList<String> _SecBlock;
	ArrayList<String> _Road;
	ArrayList<String> _House;
	ArrayList<String> _Dist;
	ArrayList<String> _UPZ;
	ArrayList<String> _Vill;
	ArrayList<String> _Phone1;
	ArrayList<String> _Phone2;
	ArrayList<String> _Phone3;
	ArrayList<String> _Phone4;
	ArrayList<String> _GISID;
	ArrayList<String> _Ward;
	ArrayList<String> _Clust;
	ArrayList<String> _Block;
	ArrayList<String> _FaSize;
	ArrayList<String> _EnDate;
	ArrayList<String> _MotherName;
	ArrayList<String> _FatherName;
	ArrayList<String> _HeadName;
	ArrayList<String> _SpouseName;
	ArrayList<String> _Ucode;
	ArrayList<String> _UnionName;
	ArrayList<String> _VillK;
	ArrayList<String> _Vpart;
	ArrayList<String> _VillName;
	ArrayList<String> _Mohalla;
	ArrayList<String> _Bari;
	ArrayList<String> _Khana;
	ArrayList<String> _Project;
	ArrayList<String> _Location;
	private LayoutInflater mInflater;

	public DataAdapter(Context c, ArrayList<String> PID, ArrayList<String> Hh,
			ArrayList<String> Slno, ArrayList<String> Name,
			ArrayList<String> Sex, ArrayList<String> Age,
			ArrayList<String> BDate, ArrayList<String> Rth,
			ArrayList<String> MS, ArrayList<String> Area,
			ArrayList<String> SecBlock, ArrayList<String> Road,
			ArrayList<String> House, ArrayList<String> Dist,
			ArrayList<String> UPZ, ArrayList<String> Vill,
			ArrayList<String> Phone1, ArrayList<String> Phone2,
			ArrayList<String> Phone3, ArrayList<String> Phone4,
			ArrayList<String> GISID, ArrayList<String> Ward,
			ArrayList<String> Clust, ArrayList<String> Block,
			ArrayList<String> FaSize, ArrayList<String> EnDate,
			ArrayList<String> MotherName, ArrayList<String> FatherName,
			ArrayList<String> HeadName, ArrayList<String> SpouseName,
			ArrayList<String> Ucode, ArrayList<String> UnionName,
			ArrayList<String> VillK, ArrayList<String> Vpart,
			ArrayList<String> VillName, ArrayList<String> Mohalla,
			ArrayList<String> Bari, ArrayList<String> Khana,
			ArrayList<String> Project, ArrayList<String> Location)

	{

		mContext = c;

		_PID = PID;
		_Hh = Hh;
		_Slno = Slno;
		_Name = Name;
		_Sex = Sex;
		_Age = Age;
		_BDate = BDate;
		_Rth = Rth;
		_MS = MS;
		_Area = Area;
		_SecBlock = SecBlock;
		_Road = Road;
		_House = House;
		_Dist = Dist;
		_UPZ = UPZ;
		_Vill = Vill;
		_Phone1 = Phone1;
		_Phone2 = Phone2;
		_Phone3 = Phone3;
		_Phone4 = Phone4;
		_GISID = GISID;
		_Ward = Ward;
		_Clust = Clust;
		_Block = Block;
		_FaSize = FaSize;
		_EnDate = EnDate;
		_MotherName = MotherName;
		_FatherName = FatherName;
		_HeadName = HeadName;
		_SpouseName = SpouseName;
		_Ucode = Ucode;
		_UnionName = UnionName;
		_VillK = VillK;
		_Vpart = Vpart;
		_VillName = VillName;
		_Mohalla = Mohalla;
		_Bari = Bari;
		_Khana = Khana;
		_Project = Project;
		_Location = Location;

		mInflater = LayoutInflater.from(c);

	}

	public int getCount()

	{

		return _PID.size();

	}

	public Object getItem(int position)

	{

		return position;

	}

	public long getItemId(int position)

	{

		return position;

	}

	public View getView(int position, View convertView, ViewGroup parent)

	{

		ViewHolder holder = null;

		if (convertView == null)

		{

			convertView = mInflater.inflate(R.layout.customgrid, parent, false);

			holder = new ViewHolder();

			/*
			 * holder.txtId = (TextView) convertView.findViewById(R.id.txtId);
			 * 
			 * holder.txtId.setPadding(100, 10, 10, 10);
			 * 
			 * holder.txtName = (TextView)
			 * convertView.findViewById(R.id.txtName);
			 * 
			 * holder.txtName.setPadding(10, 10, 10, 10);
			 * 
			 * holder.txtAddress = (TextView) convertView
			 * .findViewById(R.id.txtAddress);
			 * 
			 * holder.txtAddress.setPadding(10, 10, 10, 10);
			 */

			/*
			 * holder.PID= (TextView) convertView.findViewById(R.id.PID);
			 * holder.PID.setPadding(100, 10, 10, 10); holder.Hh= (TextView)
			 * convertView.findViewById(R.id.Hh); holder.Hh.setPadding(100, 10,
			 * 10, 10); holder.Slno= (TextView)
			 * convertView.findViewById(R.id.Slno); holder.Slno.setPadding(100,
			 * 10, 10, 10); holder.Name= (TextView)
			 * convertView.findViewById(R.id.Name); holder.Name.setPadding(100,
			 * 10, 10, 10); holder.Sex= (TextView)
			 * convertView.findViewById(R.id.Sex); holder.Sex.setPadding(100,
			 * 10, 10, 10); holder.Age= (TextView)
			 * convertView.findViewById(R.id.Age); holder.Age.setPadding(100,
			 * 10, 10, 10); holder.BDate= (TextView)
			 * convertView.findViewById(R.id.BDate);
			 * holder.BDate.setPadding(100, 10, 10, 10); holder.Rth= (TextView)
			 * convertView.findViewById(R.id.Rth); holder.Rth.setPadding(100,
			 * 10, 10, 10); holder.MS= (TextView)
			 * convertView.findViewById(R.id.MS); holder.MS.setPadding(100, 10,
			 * 10, 10); holder.Area= (TextView)
			 * convertView.findViewById(R.id.Area); holder.Area.setPadding(100,
			 * 10, 10, 10); holder.SecBlock= (TextView)
			 * convertView.findViewById(R.id.SecBlock);
			 * holder.SecBlock.setPadding(100, 10, 10, 10); holder.Road=
			 * (TextView) convertView.findViewById(R.id.Road);
			 * holder.Road.setPadding(100, 10, 10, 10); holder.House= (TextView)
			 * convertView.findViewById(R.id.House);
			 * holder.House.setPadding(100, 10, 10, 10); holder.Dist= (TextView)
			 * convertView.findViewById(R.id.Dist); holder.Dist.setPadding(100,
			 * 10, 10, 10); holder.UPZ= (TextView)
			 * convertView.findViewById(R.id.UPZ); holder.UPZ.setPadding(100,
			 * 10, 10, 10); holder.Vill= (TextView)
			 * convertView.findViewById(R.id.Vill); holder.Vill.setPadding(100,
			 * 10, 10, 10); holder.Phone1= (TextView)
			 * convertView.findViewById(R.id.Phone1);
			 * holder.Phone1.setPadding(100, 10, 10, 10); holder.Phone2=
			 * (TextView) convertView.findViewById(R.id.Phone2);
			 * holder.Phone2.setPadding(100, 10, 10, 10); holder.Phone3=
			 * (TextView) convertView.findViewById(R.id.Phone3);
			 * holder.Phone3.setPadding(100, 10, 10, 10); holder.Phone4=
			 * (TextView) convertView.findViewById(R.id.Phone4);
			 * holder.Phone4.setPadding(100, 10, 10, 10); holder.GISID=
			 * (TextView) convertView.findViewById(R.id.GISID);
			 * holder.GISID.setPadding(100, 10, 10, 10); holder.Ward= (TextView)
			 * convertView.findViewById(R.id.Ward); holder.Ward.setPadding(100,
			 * 10, 10, 10); holder.Clust= (TextView)
			 * convertView.findViewById(R.id.Clust);
			 * holder.Clust.setPadding(100, 10, 10, 10); holder.Block=
			 * (TextView) convertView.findViewById(R.id.Block);
			 * holder.Block.setPadding(100, 10, 10, 10); holder.FaSize=
			 * (TextView) convertView.findViewById(R.id.FaSize);
			 * holder.FaSize.setPadding(100, 10, 10, 10); holder.EnDate=
			 * (TextView) convertView.findViewById(R.id.EnDate);
			 * holder.EnDate.setPadding(100, 10, 10, 10); holder.MotherName=
			 * (TextView) convertView.findViewById(R.id.MotherName);
			 * holder.MotherName.setPadding(100, 10, 10, 10); holder.FatherName=
			 * (TextView) convertView.findViewById(R.id.FatherName);
			 * holder.FatherName.setPadding(100, 10, 10, 10); holder.HeadName=
			 * (TextView) convertView.findViewById(R.id.HeadName);
			 * holder.HeadName.setPadding(100, 10, 10, 10); holder.SpouseName=
			 * (TextView) convertView.findViewById(R.id.SpouseName);
			 * holder.SpouseName.setPadding(100, 10, 10, 10); holder.Ucode=
			 * (TextView) convertView.findViewById(R.id.Ucode);
			 * holder.Ucode.setPadding(100, 10, 10, 10); holder.UnionName=
			 * (TextView) convertView.findViewById(R.id.UnionName);
			 * holder.UnionName.setPadding(100, 10, 10, 10); holder.VillK=
			 * (TextView) convertView.findViewById(R.id.VillK);
			 * holder.VillK.setPadding(100, 10, 10, 10); holder.Vpart=
			 * (TextView) convertView.findViewById(R.id.Vpart);
			 * holder.Vpart.setPadding(100, 10, 10, 10); holder.VillName=
			 * (TextView) convertView.findViewById(R.id.VillName);
			 * holder.VillName.setPadding(100, 10, 10, 10); holder.Mohalla=
			 * (TextView) convertView.findViewById(R.id.Mohalla);
			 * holder.Mohalla.setPadding(100, 10, 10, 10); holder.Bari=
			 * (TextView) convertView.findViewById(R.id.Bari);
			 * holder.Bari.setPadding(100, 10, 10, 10); holder.Khana= (TextView)
			 * convertView.findViewById(R.id.Khana);
			 * holder.Khana.setPadding(100, 10, 10, 10); holder.Project=
			 * (TextView) convertView.findViewById(R.id.Project);
			 * holder.Project.setPadding(100, 10, 10, 10); holder.Location=
			 * (TextView) convertView.findViewById(R.id.Location);
			 * holder.Location.setPadding(100, 10, 10, 10);
			 */

			if (position == 0)

			{

				convertView.setTag(holder);

			}

		}

		else

		{

			holder = (ViewHolder) convertView.getTag();

		}

		/*
		 * holder.txtId.setText(id[position]);
		 * 
		 * holder.txtName.setText(name[position]);
		 * 
		 * holder.txtAddress.setText(address[position].toString());
		 */

		/*
		 * holder.Hh.setText(_Hh.get(position));
		 * holder.Slno.setText(_Slno.get(position));
		 * holder.Name.setText(_Name.get(position));
		 * holder.Sex.setText(_Sex.get(position));
		 * holder.Age.setText(_Age.get(position));
		 * holder.BDate.setText(_BDate.get(position));
		 * holder.Rth.setText(_Rth.get(position));
		 * holder.MS.setText(_MS.get(position));
		 * holder.Area.setText(_Area.get(position));
		 * holder.SecBlock.setText(_SecBlock.get(position));
		 * holder.Road.setText(_Road.get(position));
		 * holder.House.setText(_House.get(position));
		 * holder.Dist.setText(_Dist.get(position));
		 * holder.UPZ.setText(_UPZ.get(position));
		 * holder.Vill.setText(_Vill.get(position));
		 * holder.Phone1.setText(_Phone1.get(position));
		 * holder.Phone2.setText(_Phone2.get(position));
		 * holder.Phone3.setText(_Phone3.get(position));
		 * holder.Phone4.setText(_Phone4.get(position));
		 * holder.GISID.setText(_GISID.get(position));
		 * holder.Ward.setText(_Ward.get(position));
		 * holder.Clust.setText(_Clust.get(position));
		 * holder.Block.setText(_Block.get(position));
		 * holder.FaSize.setText(_FaSize.get(position));
		 * holder.EnDate.setText(_EnDate.get(position));
		 * holder.MotherName.setText(_MotherName.get(position));
		 * holder.FatherName.setText(_FatherName.get(position));
		 * holder.HeadName.setText(_HeadName.get(position));
		 * holder.SpouseName.setText(_SpouseName.get(position));
		 * holder.Ucode.setText(_Ucode.get(position));
		 * holder.UnionName.setText(_UnionName.get(position));
		 * holder.VillK.setText(_VillK.get(position));
		 * holder.Vpart.setText(_Vpart.get(position));
		 * holder.VillName.setText(_VillName.get(position));
		 * holder.Mohalla.setText(_Mohalla.get(position));
		 * holder.Bari.setText(_Bari.get(position));
		 * holder.Khana.setText(_Khana.get(position));
		 * holder.Project.setText(_Project.get(position));
		 * holder.Location.setText(_Location.get(position));
		 */
		/*((TextView) convertView.findViewById(R.id.PID)).setText(_PID
				.get(position));
		((TextView) convertView.findViewById(R.id.Hh)).setText(_Hh
				.get(position));
		((TextView) convertView.findViewById(R.id.Slno)).setText(_Slno
				.get(position));
		((TextView) convertView.findViewById(R.id.Name)).setText(_Name
				.get(position));
		((TextView) convertView.findViewById(R.id.Sex)).setText(_Sex
				.get(position));
		((TextView) convertView.findViewById(R.id.Age)).setText(_Age
				.get(position));
		((TextView) convertView.findViewById(R.id.BDate)).setText(_BDate
				.get(position));
		((TextView) convertView.findViewById(R.id.Rth)).setText(_Rth
				.get(position));
		((TextView) convertView.findViewById(R.id.MS)).setText(_MS
				.get(position));
		((TextView) convertView.findViewById(R.id.Area)).setText(_Area
				.get(position));
		((TextView) convertView.findViewById(R.id.SecBlock)).setText(_SecBlock
				.get(position));
		((TextView) convertView.findViewById(R.id.Road)).setText(_Road
				.get(position));
		((TextView) convertView.findViewById(R.id.House)).setText(_House
				.get(position));
		((TextView) convertView.findViewById(R.id.Dist)).setText(_Dist
				.get(position));
		((TextView) convertView.findViewById(R.id.UPZ)).setText(_UPZ
				.get(position));
		((TextView) convertView.findViewById(R.id.Vill)).setText(_Vill
				.get(position));
		((TextView) convertView.findViewById(R.id.Phone1)).setText(_Phone1
				.get(position));
		((TextView) convertView.findViewById(R.id.Phone2)).setText(_Phone2
				.get(position));
		((TextView) convertView.findViewById(R.id.Phone3)).setText(_Phone3
				.get(position));
		((TextView) convertView.findViewById(R.id.Phone4)).setText(_Phone4
				.get(position));
		((TextView) convertView.findViewById(R.id.GISID)).setText(_GISID
				.get(position));
		((TextView) convertView.findViewById(R.id.Ward)).setText(_Ward
				.get(position));
		((TextView) convertView.findViewById(R.id.Clust)).setText(_Clust
				.get(position));
		((TextView) convertView.findViewById(R.id.Block)).setText(_Block
				.get(position));
		((TextView) convertView.findViewById(R.id.FaSize)).setText(_FaSize
				.get(position));
		((TextView) convertView.findViewById(R.id.EnDate)).setText(_EnDate
				.get(position));
		((TextView) convertView.findViewById(R.id.MotherName))
				.setText(_MotherName.get(position));
		((TextView) convertView.findViewById(R.id.FatherName))
				.setText(_FatherName.get(position));
		((TextView) convertView.findViewById(R.id.HeadName)).setText(_HeadName
				.get(position));
		((TextView) convertView.findViewById(R.id.SpouseName))
				.setText(_SpouseName.get(position));
		((TextView) convertView.findViewById(R.id.Ucode)).setText(_Ucode
				.get(position));
		((TextView) convertView.findViewById(R.id.UnionName))
				.setText(_UnionName.get(position));
		((TextView) convertView.findViewById(R.id.VillK)).setText(_VillK
				.get(position));
		((TextView) convertView.findViewById(R.id.Vpart)).setText(_Vpart
				.get(position));
		((TextView) convertView.findViewById(R.id.VillName)).setText(_VillName
				.get(position));
		((TextView) convertView.findViewById(R.id.Mohalla)).setText(_Mohalla
				.get(position));
		((TextView) convertView.findViewById(R.id.Bari)).setText(_Bari
				.get(position));
		((TextView) convertView.findViewById(R.id.Khana)).setText(_Khana
				.get(position));
		((TextView) convertView.findViewById(R.id.Project)).setText(_Project
				.get(position));
		((TextView) convertView.findViewById(R.id.Location)).setText(_Location
				.get(position));*/

		
		
		//ViewGroup vg = (TableLayout) convertView.findViewById(R.id.TableLayout01);
		
		
		
		
		((TextView) convertView.findViewById(R.id.PID)).setText(_PID
				.get(position));
		((TextView) convertView.findViewById(R.id.PID)).setPadding(100, 10, 10,
				10);

		((TextView) convertView.findViewById(R.id.Hh)).setText(_Hh
				.get(position));
		((TextView) convertView.findViewById(R.id.Hh)).setPadding(150, 10, 10,
				10);

		((TextView) convertView.findViewById(R.id.Slno)).setText(_Slno
				.get(position));
		((TextView) convertView.findViewById(R.id.Slno)).setPadding(200, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Name)).setText(_Name
				.get(position));
		((TextView) convertView.findViewById(R.id.Name)).setPadding(100, 10, 10,
				10);

		((TextView) convertView.findViewById(R.id.Sex)).setText(_Sex
				.get(position));
		((TextView) convertView.findViewById(R.id.Sex)).setPadding(100, 10, 10,
				10);

		((TextView) convertView.findViewById(R.id.Age)).setText(_Age
				.get(position));
		((TextView) convertView.findViewById(R.id.Age)).setPadding(100, 10, 10,
				10);

		((TextView) convertView.findViewById(R.id.BDate)).setText(_BDate
				.get(position));
		((TextView) convertView.findViewById(R.id.BDate)).setPadding(100, 10, 10,
				10);

		((TextView) convertView.findViewById(R.id.Rth)).setText(_Rth
				.get(position));
		((TextView) convertView.findViewById(R.id.Rth)).setPadding(100, 10, 10,
				10);

		((TextView) convertView.findViewById(R.id.MS)).setText(_MS
				.get(position));
		((TextView) convertView.findViewById(R.id.MS)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Area)).setText(_Area
				.get(position));
		((TextView) convertView.findViewById(R.id.Area)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.SecBlock)).setText(_SecBlock
				.get(position));
		((TextView) convertView.findViewById(R.id.SecBlock)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Road)).setText(_Road
				.get(position));
		((TextView) convertView.findViewById(R.id.Road)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.House)).setText(_House
				.get(position));
		((TextView) convertView.findViewById(R.id.House)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Dist)).setText(_Dist
				.get(position));
		((TextView) convertView.findViewById(R.id.Dist)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.UPZ)).setText(_UPZ
				.get(position));
		((TextView) convertView.findViewById(R.id.UPZ)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Vill)).setText(_Vill
				.get(position));
		((TextView) convertView.findViewById(R.id.Vill)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Phone1)).setText(_Phone1
				.get(position));
		((TextView) convertView.findViewById(R.id.Phone1)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Phone2)).setText(_Phone2
				.get(position));
		((TextView) convertView.findViewById(R.id.Phone2)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Phone3)).setText(_Phone3
				.get(position));
		((TextView) convertView.findViewById(R.id.Phone3)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Phone4)).setText(_Phone4
				.get(position));
		((TextView) convertView.findViewById(R.id.Phone4)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.GISID)).setText(_GISID
				.get(position));
		((TextView) convertView.findViewById(R.id.GISID)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Ward)).setText(_Ward
				.get(position));
		((TextView) convertView.findViewById(R.id.Ward)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Clust)).setText(_Clust
				.get(position));
		((TextView) convertView.findViewById(R.id.Clust)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Block)).setText(_Block
				.get(position));
		((TextView) convertView.findViewById(R.id.Block)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.FaSize)).setText(_FaSize
				.get(position));
		((TextView) convertView.findViewById(R.id.FaSize)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.EnDate)).setText(_EnDate
				.get(position));
		((TextView) convertView.findViewById(R.id.EnDate)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.MotherName))
				.setText(_MotherName.get(position));
		((TextView) convertView.findViewById(R.id.MotherName)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.FatherName))
				.setText(_FatherName.get(position));
		((TextView) convertView.findViewById(R.id.FatherName)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.HeadName)).setText(_HeadName
				.get(position));
		((TextView) convertView.findViewById(R.id.HeadName)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.SpouseName))
				.setText(_SpouseName.get(position));
		((TextView) convertView.findViewById(R.id.SpouseName)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Ucode)).setText(_Ucode
				.get(position));
		((TextView) convertView.findViewById(R.id.Ucode)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.UnionName))
				.setText(_UnionName.get(position));
		((TextView) convertView.findViewById(R.id.UnionName)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.VillK)).setText(_VillK
				.get(position));
		((TextView) convertView.findViewById(R.id.VillK)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Vpart)).setText(_Vpart
				.get(position));
		((TextView) convertView.findViewById(R.id.Vpart)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.VillName)).setText(_VillName
				.get(position));
		((TextView) convertView.findViewById(R.id.VillName)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Mohalla)).setText(_Mohalla
				.get(position));
		((TextView) convertView.findViewById(R.id.Mohalla)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Bari)).setText(_Bari
				.get(position));
		((TextView) convertView.findViewById(R.id.Bari)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Khana)).setText(_Khana
				.get(position));
		((TextView) convertView.findViewById(R.id.Khana)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Project)).setText(_Project
				.get(position));
		((TextView) convertView.findViewById(R.id.Project)).setPadding(100, 10, 10,
				10);
		((TextView) convertView.findViewById(R.id.Location)).setText(_Location
				.get(position));
		((TextView) convertView.findViewById(R.id.Location)).setPadding(100, 10, 10,
				10);

		return convertView;

	}

	static class ViewHolder

	{

		TextView PID;
		TextView Hh;
		TextView Slno;
		TextView Name;
		TextView Sex;
		TextView Age;
		TextView BDate;
		TextView Rth;
		TextView MS;
		TextView Area;
		TextView SecBlock;
		TextView Road;
		TextView House;
		TextView Dist;
		TextView UPZ;
		TextView Vill;
		TextView Phone1;
		TextView Phone2;
		TextView Phone3;
		TextView Phone4;
		TextView GISID;
		TextView Ward;
		TextView Clust;
		TextView Block;
		TextView FaSize;
		TextView EnDate;
		TextView MotherName;
		TextView FatherName;
		TextView HeadName;
		TextView SpouseName;
		TextView Ucode;
		TextView UnionName;
		TextView VillK;
		TextView Vpart;
		TextView VillName;
		TextView Mohalla;
		TextView Bari;
		TextView Khana;
		TextView Project;
		TextView Location;

		/*
		 * TextView txtId;
		 * 
		 * TextView txtName; TextView txtAddress;
		 */

	}

}