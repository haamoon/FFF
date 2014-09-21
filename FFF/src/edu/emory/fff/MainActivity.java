package edu.emory.fff;

import java.util.Calendar;


import edu.emory.fff.database.DataSource;
import edu.emory.fff.mail.imap.UpdateAlertsAsyncTask;
import edu.emory.fff.mail.imap.ImapSettings;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
		
		//Amirreza's part! You can delete me :)
		DataSource dataSource = new DataSource(this);
		dataSource.open();
		new UpdateAlertsAsyncTask(dataSource).execute(new ImapSettings("mail.gatech.edu", "mfarajtabar3", "mehrGT9sahar", 0));
		//
		
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
}
