package edu.emory.fff;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class NotificationService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}
	@Override
    public void onCreate() {
           // TODO Auto-generated method stub
		   
           Toast.makeText(getApplicationContext(), "Service Created", 1).show();
           super.onCreate();
    }
	@Override
    public void onDestroy() {
           // TODO Auto-generated method stub
           Toast.makeText(getApplicationContext(), "Service Destroy", 1).show();
           System.out.println("Sahar: Service Destroy");
           super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
           // TODO Auto-generated method stub
           Toast.makeText(getApplicationContext(), "Service Running ", 1).show();
           System.out.println("Sahar: Service Running");
           generateNotification(getApplicationContext());
           return super.onStartCommand(intent, flags, startId);
    }
    
    public void generateNotification(Context context)
    {
        NotificationManager notifManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification note = new Notification(R.drawable.ic_launcher, "New E-mail", System.currentTimeMillis());

        PendingIntent bintent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        note.setLatestEventInfo(context, "New E-mail", "You have one unread message.", bintent);

        notifManager.notify(123, note);
    }


}
