package edu.emory.fff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		Button startBtn = (Button) findViewById(R.id.register);
        startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				startService(new Intent(getBaseContext(), NotificationService.class));
				Intent intent_1 = new Intent(v.getContext(),RegisterActivity.class);
				finish();
				startActivity(intent_1);
			}
		});
	}
}
