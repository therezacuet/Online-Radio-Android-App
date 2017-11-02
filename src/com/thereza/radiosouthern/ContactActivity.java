package com.thereza.radiosouthern;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ContactActivity extends Activity {

	View call,email,website,facebook;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		call=findViewById(R.id.call);
		email=findViewById(R.id.email);
		website=findViewById(R.id.website);
		facebook=findViewById(R.id.facebook);
		call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent callIntent = new Intent(Intent.ACTION_CALL);
			    callIntent.setData(Uri.parse("tel:01824770501"));
			    startActivity(callIntent);
			    call.setBackgroundColor(color.holo_green_light);
			}
		});
		
		facebook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://www.facebook.com"); // missing 'http://' will cause crashed
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				facebook.setBackgroundColor(color.holo_green_light);
			}
		});
		
		email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("plain/text");
				intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "radiosouthern@gmail.address" });
				intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
				intent.putExtra(Intent.EXTRA_TEXT, "mail body");
				startActivity(Intent.createChooser(intent, ""));
				email.setBackgroundColor(color.holo_green_light);
			}
		});
		
		website.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://www.radiosouthern.com"); // missing 'http://' will cause crashed
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				website.setBackgroundColor(color.holo_green_light);
			}
		});
	}


}
