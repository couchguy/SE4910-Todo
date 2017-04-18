package com.dankass.todo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DetailedActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed);
	}
	
	 public void sendNotification(View view) {
		    // Prepare intent which is triggered if the
		    // notification is selected
		    Intent intent = new Intent(this, MainActivity.class);
		    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

		    // Build notification
		    // Actions are just fake
		    Notification noti = new Notification.Builder(this)
		        .setContentTitle("TodoList Item")
		        .setContentText("A New Item was Added").setSmallIcon(R.drawable.ic_launcher)
		        .setContentIntent(pIntent).build();
		        //.addAction(R.drawable.ic_launcher, "Call", pIntent);
		        //.addAction(R.drawable.ic_launcher, "More", pIntent)
		        //.addAction(R.drawable.ic_launcher, "And more", pIntent).build();
		    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		    // hide the notification after its selected
		    noti.flags |= Notification.FLAG_AUTO_CANCEL;

		    notificationManager.notify(0, noti);

		  }
}
