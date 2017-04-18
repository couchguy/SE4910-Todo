package com.dankass.todo;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class TodoDataSource {

	//Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_TODO, MySQLiteHelper.COLUMN_DESCRIPTION, 
			MySQLiteHelper.COLUMN_DATE, MySQLiteHelper.COLUMN_PRIORITY,
			MySQLiteHelper.COLUMN_STATUS};
	
	public TodoDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public TodoItem createTodoItem(String name, String date, String description, String priority, String status) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_TODO, name);
		values.put(MySQLiteHelper.COLUMN_DATE, date);
		values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description);
		values.put(MySQLiteHelper.COLUMN_PRIORITY, priority);
		values.put(MySQLiteHelper.COLUMN_STATUS, status);
		
		
		
		long insertId = database.insert(MySQLiteHelper.TABLE_TODO, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TODO,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		TodoItem newItem = cursorToItem(cursor);
		cursor.close();
		return newItem;
	}
	
	public void deleteItem(TodoItem item) {
		long id = item.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_TODO, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	public List<TodoItem> getAllItems() {
		List<TodoItem> items = new ArrayList<TodoItem>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TODO, 
				allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			TodoItem item = cursorToItem(cursor);
			items.add(item);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return items;
	}
	
	private TodoItem cursorToItem(Cursor cursor) {
		TodoItem item = new TodoItem();
		item.setId(cursor.getLong(0));
		item.setName(cursor.getString(1));
		item.setDescription(cursor.getString(2));
		item.setDate(cursor.getString(3));
		item.setPriority(cursor.getString(4));
		item.setStatus(cursor.getString(5));
		return item;
	}
}
