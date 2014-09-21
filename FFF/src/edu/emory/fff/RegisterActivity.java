package edu.emory.fff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		Button findBut = (Button) findViewById(R.id.findButton);
		findBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent= getIntent();
				intent.getExtras();
				EditText server = (EditText) findViewById(R.id.server_name);
				String server_name = server.getText().toString();
				
				EditText username_txt = (EditText) findViewById(R.id.username);
				String username = server.getText().toString();
				
				EditText password_txt = (EditText) findViewById(R.id.password);
				String password = server.getText().toString();
				
				
				
			}
		});
	}
}
