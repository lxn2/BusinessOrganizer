package com.examples;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class DayViewFragment extends Fragment implements OnClickListener{
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
	private GridView timesView;
	private GridCellAdapter adapter;
	private int viewNumCols;
	private static final String tag = "DayViewFragment";
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        //if (savedInstanceState != null) {
          //  mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        //}
        View view = inflater.inflate(R.layout.dayview_frag, container, false);
        Button nextButton = (Button) view.findViewById(R.id.button_first);
        nextButton.setOnClickListener(this);
        /*timesView = (GridView) view.findViewById(R.id.day_view);
		// Initialised
		adapter = new GridCellAdapter(getActivity().getApplicationContext());
		adapter.notifyDataSetChanged();
		timesView.setAdapter(adapter);
		viewNumCols = 7;*/ //timesView.getNumColumns();
        
		return view;
		
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.activity_add_appointment_frag, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
    


	public class GridCellAdapter extends BaseAdapter implements OnClickListener
	{

		private final Context _context;
		private final List<Time> time_list;
		private final List<String> appointment_list;
		private int count;
		private Button gridcell;

		public GridCellAdapter(Context context)
		{
			super();
			this._context = context;
			this.time_list = new ArrayList<Time>();
			this.appointment_list = new ArrayList<String>();
			this.count = 0;
			printCal();
		}
		
		private void printCal()
		{
			for(int hr = 7; hr < 24; hr++)
				for(int mn = 00; mn < 60; mn += 15)
				{
					Time time = new Time();
					time.hour = hr;
					time.minute = mn;
					time_list.add(time);
					count++;
					for(int apptNum = 1; apptNum <= 7; apptNum++)
					{
						appointment_list.add("Null");
						count++;
					}
				}	
		}
		
		@Override
		public int getCount()
		{
			return count;
		}

		@Override
		public Time getItem(int position)
		{
			return time_list.get(position);
		}

		@Override
		public long getItemId(int position)
			{
				return position;
			}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View row = convertView;
			if (row == null)
				{
					LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					row = inflater.inflate(R.layout.dayview_time_gridcell, parent, false);
				} 


			// Time cell
			if (position % 7 == 0)
			{
				Time time = time_list.get(position/7);
				String hr_string = String.valueOf(time.hour);
				String mn_string = String.format("%02d", time.minute);
			
				// Reference to frame container
				//AddAppointmentFrag addApptFrag = (AddAppointmentFrag) getFragmentManager().findFragmentById(R.id.fragment_container);
				
				// Get a reference to the Day gridcell
				gridcell = (Button) row.findViewById(R.id.dayview_time_gridcell_straight);
				if (time.minute == 0)
					gridcell.setTextSize(12);
				else
					gridcell.setTextSize(8);
				gridcell.setOnClickListener(new View.OnClickListener() {
				    public void onClick(View v) {
				    	if (getFragmentManager().findFragmentById(R.id.fragment_container) != null) {
				    		Log.d(tag, "Get first frag");
				            // However, if we're being restored from a previous state,
				            // then we don't need to do anything and should return or else
				            // we could end up with overlapping fragments.
				            //if (savedInstanceState != null) {
				              //  return;
				            //}
				    		
				            // Create an instance of ExampleFragment
				            AddAppointmentFrag addApptFrag = (AddAppointmentFrag) getFragmentManager().findFragmentById(R.id.fragment_container); //new AddAppointmentFrag();
				            
				            // In case this activity was started with special instructions from an Intent,
				            // pass the Intent's extras to the fragment as arguments
				   
				            if (addApptFrag == null) {
				                // Make new fragment to show this selection.
				                addApptFrag = new AddAppointmentFrag();
				                addApptFrag.setArguments(getActivity().getIntent().getExtras());
				                
				                // Execute a transaction, replacing any existing fragment
				                // with this one inside the frame.
				                FragmentTransaction ft = getFragmentManager().beginTransaction();
				                ft.add(R.id.fragment_container, addApptFrag);
				                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				                ft.commit();
				            }
				            // Add the fragment to the 'fragment_container' FrameLayout
				           // getSupportFragmentManager().beginTransaction()
				             //       .add(R.id.fragment_container, addApptFrag).commit();
				        }
				    }
				}); 
			
				// Set the Day GridCell
				gridcell.setText(hr_string + ":" + mn_string);
				return row;
			}
			else
			{
//				String string = appointment_list.get((int) (position % viewNumCols - 1 + (viewNumCols - 1)*(Math.ceil(position / (viewNumCols - 1)) - 1)));
				gridcell = (Button) row.findViewById(R.id.dayview_time_gridcell_straight);
				gridcell.setBackgroundColor(3);
				gridcell.setTextSize(8);
				gridcell.setText("NULL");
				gridcell.setOnClickListener(this);
				return row;
			}
		}

		@Override
		public void onClick(View arg0) {
			Log.d(tag, "timclick");
		}
		
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
