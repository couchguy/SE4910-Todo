package com.dankass.todo;

import java.util.Calendar;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	public TextView dateText;
	public ImageButton btn;
	private TodoDataSource datasource;
	String priority = "low";
	List<TodoItem> todoItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get reference to UI Widgets
		ListView myListView = (ListView) findViewById(R.id.myListView);
		final EditText myEditText = (EditText) findViewById(R.id.myEditText);
		dateText = (TextView) findViewById(R.id.date);

		final Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1; // the month is comming as one
												// less than it is so im adding
												// one
		int day = c.get(Calendar.DAY_OF_MONTH);
		int year = c.get(Calendar.YEAR);
		dateText.setText(" " + month + "/" + day + "/" + year + " ");

		btn = (ImageButton) findViewById(R.id.prorityButton);
		btn.setFocusable(false);

		datasource = new TodoDataSource(this);
		datasource.open();

		todoItems = datasource.getAllItems();

		final ArrayAdapter<TodoItem> aa;
		aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
				todoItems);

		// Bind the Array Adapter to the ListView
		myListView.setAdapter(aa);
		// myListView.setOnItemClickListener(mOnListClick);
		
		myListView.setClickable(false);
	    myListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                //move to the detailed activity take position to find id number to sent to the activity to find the entry in the db
            	Intent i= new Intent("com.dankass.todo.DetailedActivity");
                startActivity(i);
            	//Log.i("it works", position+""); this was working
            }
        });
	    
	    myListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
					
					
					datasource.deleteItem(todoItems.get(arg2));
					todoItems.remove(arg2);
					aa.notifyDataSetChanged();
					
				return false;
			}
	    });
		
		
		myEditText.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
							|| (keyCode == KeyEvent.KEYCODE_ENTER)) {

						String item = myEditText.getText().toString();
						// checks to see if the text is empty
						if (!item.isEmpty()) {
							// if its not empty then add it to the list
							String date = dateText.getText().toString();						    
							TodoItem todoItem = new TodoItem(item, "", date, priority, "Not Started");
							todoItems.add(todoItem);
							
							aa.notifyDataSetChanged();

							// resets the date
							TextView dateText = getDateText();
							final Calendar c = Calendar.getInstance();
							int month = c.get(Calendar.MONTH) + 1; 
							// the month is comming as one less than
							// it is so im adding one to it
							int day = c.get(Calendar.DAY_OF_MONTH);
							int year = c.get(Calendar.YEAR);
							dateText.setText(" " + month + "/" + day + "/"
									+ year + " ");

							// resets the priority
							Drawable d = getResources().getDrawable(
									R.drawable.low);
							btn.setImageDrawable(d);
							setPriority(1);

							// reset the text
							myEditText.setText("");
						}
						return true;
					}
				}
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getSupportFragmentManager(), "timePicker");
	}

	public void showConpletionDialog(View v) {
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		CompletionFragment dialog = new CompletionFragment();
		dialog.show(fm, "fragment_help");
	}

	public void showPriorityDialog(View v) {
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		PriorityFragment dialog = new PriorityFragment();
		dialog.show(fm, "fragment_help");
	}

	public TextView getDateText() {
		return dateText;
	}

	public ImageButton getBtn() {
		return btn;
	}
	
	public void setPriority(int p) {
		switch(p) {
			case 1:
				priority = "low";
				break;
			case 2:
				priority = "medium";
				break;
			case 3:
				priority = "high";
				break;
			default:
				break;
		}
	}
	
	
	 
		

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}
