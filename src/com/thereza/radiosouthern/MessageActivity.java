package com.thereza.radiosouthern;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MessageActivity extends Activity {

	Button btnSend;
	EditText etMsg;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#113e64")));
		bar.setTitle(Html.fromHtml("<b>Radio Southern</b>"));
		etMsg = (EditText) findViewById(R.id.smstxt);
		btnSend = (Button) findViewById(R.id.send);
		tv = (TextView) this.findViewById(R.id.marquee);  
        tv.setSelected(true);
		
		btnSend.setOnClickListener(new OnClickListener() {			 
			@Override
			public void onClick(View v) { 
			  String phoneNo = "01824770501";
			  String msg = etMsg.getText().toString(); 
			  try {
				  
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNo, null, msg, null, null);				
				Toast.makeText(getApplicationContext(), "Message Sent",
							Toast.LENGTH_LONG).show();
			  } catch (Exception ex) {
				Toast.makeText(getApplicationContext(),
					ex.getMessage().toString(),
					Toast.LENGTH_LONG).show();
				ex.printStackTrace();
			  }
 
			}
		});
	}

}
