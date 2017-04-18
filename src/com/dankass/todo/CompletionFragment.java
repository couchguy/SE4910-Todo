package com.dankass.todo;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;




public class CompletionFragment extends DialogFragment {

	public CompletionFragment() {
		// Empty
	}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.completion_fragment, container);
    
    ListView listView = (ListView) view.findViewById(R.id.myListView);
    listView.setOnItemClickListener(new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {

            switch(position){
            case 0:
            	Log.i("yay", "0");
            	break;
            case 1:
            	Log.i("yay", "1");
            	break;
            case 2:
            	Log.i("yay", "2");
            	break;
            default:
            	break;
            }
            CompletionFragment.this.dismiss();
        }

    });
 
    return view;
}
}