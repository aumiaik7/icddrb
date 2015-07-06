package app.stoolsampleendline;

import android.content.Context;
import android.widget.TextView;
import app.stoolsampleendline.db.DatabaseHelper;

public class AlliquotsAndRandomID {

	Context con;
	public void GetAlliquoteAndRandomID(String sampleid1,String sampleid2, String sampleid3, 
			String sampleid4, String sampleid5, TextView v, TextView v1, 
			TextView v2, TextView v3, TextView v4, TextView v5)
	{
		String sql = String.format("SELECT randomid from samplesinfo where sampleid = '%s'", sampleid1);
		//Curson
		//DatabaseHelper.getInstance(con).getQueryCursor(sql);
		
	}
}
