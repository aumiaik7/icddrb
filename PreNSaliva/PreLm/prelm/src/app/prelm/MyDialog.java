package app.prelm;

import android.app.Dialog;
import android.content.Context;

public class MyDialog extends Dialog{
	private SampleCollector.AliqoutSelcetionListener aliqoutSelcetionListener;

	public MyDialog(Context context, SampleCollector.AliqoutSelcetionListener aliqoutSelcetionListener) {
		super(context);
		// TODO Auto-generated constructor stub
		this.aliqoutSelcetionListener  = aliqoutSelcetionListener;

	}
	public SampleCollector.AliqoutSelcetionListener getAliqoutSelcetionListener(){
		return this.aliqoutSelcetionListener;
	}
}
