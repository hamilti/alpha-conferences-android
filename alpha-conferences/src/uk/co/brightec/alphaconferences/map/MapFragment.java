package uk.co.brightec.alphaconferences.map;

import java.util.ArrayList;
import java.util.List;

import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Venue;
import uk.co.brightec.alphaconferences.speakers.SpeakerDetailActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

public class MapFragment extends SherlockListFragment {
	private List<Venue> mVenues;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, container, false);
	}

	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	
    @Override
    public void onResume() {
        super.onResume();
        populate();
    }	
	

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	if (position == mVenues.size()) {
			Intent intent = new Intent(getActivity(), VenueMapActivity.class);
			startActivity(intent);		
		}
    	else {
    		Venue venue = mVenues.get(position);

    		Intent intent = new Intent(getActivity(), VenueDetailActivity.class);
            intent.putExtra(VenueDetailActivity.VENUE_ID, venue.venueId);
            startActivity(intent);	    		
    	}
    	
    	super.onListItemClick(l, v, position, id);
    }   
    
    
	private void populate() {
		Context context = getActivity();
		
		mVenues = DataStore.venues(context);
        if (mVenues.isEmpty()) {
            return;
        }		
        
        List<String> rows = new ArrayList<String>();
        for (Venue venue : mVenues) {
        	rows.add(venue.name);			
		}
        
        rows.add(getString(R.string.view_all_venues_row_title));
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, rows);
        setListAdapter(adapter);
	}

}