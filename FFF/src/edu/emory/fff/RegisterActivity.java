package edu.emory.fff;

import java.util.Calendar;

import edu.emory.fff.database.DataSource;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends Activity {
	public static String username_key="USERNAME";
	public static String password_key="PASSWORD";
	public static String server_key="SERVER";
	
	DataSource DS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		DS=new DataSource(this);
		DS.open();
//		if(DS.getSetting("USERNAME")!= null){
//			startActivity(new Intent(this,NotifListActivity.class));
//			return;
//		}
		Button findBut = (Button) findViewById(R.id.findButton);
		findBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent= getIntent();
				intent.getExtras();
				EditText server = (EditText) findViewById(R.id.server_name);
				String server_name = server.getText().toString();
				DS.updateSetting(server_key, server_name);
				
				EditText username_txt = (EditText) findViewById(R.id.username);
				String username = username_txt.getText().toString();
				DS.updateSetting(username_key, username);
				
				EditText password_txt = (EditText) findViewById(R.id.password);
				String password = password_txt.getText().toString();
				DS.updateSetting(password_key, password);
				
				DS.close();
				
				
				Calendar cal = Calendar.getInstance();
		        cal.add(Calendar.SECOND, 10);
		       
		        Intent intent_1 = new Intent(v.getContext(), NotificationService.class);

		        PendingIntent pintent = PendingIntent.getService(v.getContext(), 0, intent_1, 0);
		       
		        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		        //for 30 mint 60*60*1000
		        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
		                     10*1000, pintent);
//		        stopService(new Intent(getBaseContext(), NotificationService.class));
		        startService(new Intent(getBaseContext(), NotificationService.class));
				
		        Intent intent_2 = new Intent(v.getContext(),NotifListActivity.class);
				finish();
				startActivity(intent_2);
				
				
				
			}
		});
	}
}
