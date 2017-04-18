package com.dankass.todo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;




public class PriorityFragment extends DialogFragment {

	public PriorityFragment() {
		// Empty
	}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.priority_fragment, container);
    
    ListView listView = (ListView) view.findViewById(R.id.myListViewPriority);
    listView.setOnItemClickListener(new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
        	ImageButton btn = ((MainActivity)getActivity()).getBtn();
        	btn.setBackgroundResource(0);
        	int p = 1; //used for setting priority
        	Drawable d;
            switch(position){
            case 0:
            	
            	d = getResources().getDrawable(R.drawable.high);
            	btn.setImageDrawable(d);
            	p = 3;
            	break;
            case 1:
            	d = getResources().getDrawable(R.drawable.medium);
            	btn.setImageDrawable(d);
            	p = 2;
            	break;
            case 2:
            	d = getResources().getDrawable(R.drawable.low);
            	btn.setImageDrawable(d);
            	p = 1;            	
            	break;
            default:
            	break;
            }
            ((MainActivity)getActivity()).setPriority(p);
            PriorityFragment.this.dismiss();
        }

    });
 
    return view;
}
}