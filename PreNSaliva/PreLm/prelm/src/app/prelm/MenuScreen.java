package app.prelm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.app.prelm.R;

public class MenuScreen extends BaseActivity{
	protected static final int ALLQUESTIONLOADED = 1;
	protected static final int QUESTIONCANTLODED = 2;
	protected static final int DBSTOREDONE = 11;
	private Button addButton,editButton;
	private Context con;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menulayout);
		con = this;
		loadGui();
	}
	
	private void loadGui() {
		// TODO Auto-generated method stub
		addButton = (Button)findViewById(R.id.addButton);
		addButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommonStaticClass.mode = CommonStaticClass.ADDMODE;
				clearEveryThing();
				Intent i = new Intent(MenuScreen.this,MainActivity.class);
				startActivity(i);
			}

		});
		editButton = (Button)findViewById(R.id.editButton);
		editButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommonStaticClass.mode = CommonStaticClass.EDITMODE;
				clearEveryThing();
				Intent i = new Intent(MenuScreen.this,EditEntryList.class);
				startActivity(i);
			}
			
		});
	}

	private void clearEveryThing() {


		CommonStaticClass.dataId = "";

		CommonStaticClass.currentSLNo = 1;
		CommonStaticClass.participantType = "";
		CommonStaticClass.isChecked = false;
	}

	
}
