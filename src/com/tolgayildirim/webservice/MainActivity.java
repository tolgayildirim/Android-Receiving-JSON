package com.tolgayildirim.webservice;

import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.tolgayildirim.globalurl.GlobalURL;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	GlobalURL gu = new GlobalURL();
	WebService wb = new WebService();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Ana thread içinde network iþlemleri yapýldýðý için oluþmaktadýr.(android.os.NetworkOnMainThreadException Hatasý çözümü)
		if (android.os.Build.VERSION.SDK_INT > 11) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			}
//		 Httppost iþlemleri için NameValuePair listesi gereklidir. Post edilirken header a nelerin Post edileceði liste olarak gönderilir
//		 ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//		 nameValuePairs.add(new BasicNameValuePair("email","ty@ty.com")); 
//		 nameValuePairs.add(new BasicNameValuePair("password", "123123"));
//		 wb.webService(gu.url+"get_tag_index","POST",nameValuePairs,MainActivity.class);
		
		//aþaðýda sadece veri çekme iþlemi gerçekleþtirilmiþtir.
	    wb.webService(gu.url+"get_tag_index","GET",null,MainActivity.class);
	    aDialog();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void aDialog() {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();

		// Setting Dialog Title
		alertDialog.setTitle("Mesaj");
		alertDialog.setMessage(wb.mesajlar);

		
		// Setting Icon to Dialog

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Write your code here to execute after dialog closed
				Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
			}
		});

		// Showing Alert Message
		alertDialog.show();
		
	}

}
