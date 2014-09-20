package edu.emory.fff;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Start service using AlarmManager

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 10);
       
        Intent intent = new Intent(this, NotificationService.class);

        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
       
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //for 30 mint 60*60*1000
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                     10*1000, pintent);

        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(new Intent(getBaseContext(), NotificationService.class));
				start_newAct();
				
			}
		});
        Button stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new OnClickListener() {

               @Override
               public void onClick(View v) {
                     // TODO Auto-generated method stub
                     stopService(new Intent(getBaseContext(), NotificationService.class));
               }
               
        });
		
	}
	
	public void start_newAct(){
 	   Intent intent = new Intent(this,EditEvent.class);
 	  intent.putExtra("date", "05-12-2014 12:23:34");
		intent.putExtra("location", "Lobby");
		String body="This is a service email from Bank of America. Please note that you may receive service emails in accordance with your Bank of America service agreements"+
				"whether or not you elect to receive promotional email."+
				"Read our Privacy Notice.Please dont reply directly to this automatically generated email message."+
				"Bank of America Email, 8th Floor-NC1-002-08-25, 101 South Tryon St., Charlotte, NC 28255-0001Bank of America";
		intent.putExtra("body", body);
		startActivity(intent);
 	   
    }
}
