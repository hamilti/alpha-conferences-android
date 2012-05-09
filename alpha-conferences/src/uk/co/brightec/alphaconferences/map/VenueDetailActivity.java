package uk.co.brightec.alphaconferences.map;

import java.util.ArrayList;
import java.util.List;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.FloorplanActivity;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Venue;
import uk.co.brightec.alphaconferences.resources.Resource;
import uk.co.brightec.alphaconferences.rows.ButtonBarRow;
import uk.co.brightec.alphaconferences.rows.DetailRow;
import uk.co.brightec.alphaconferences.rows.HTMLRow;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;


public class VenueDetailActivity extends SherlockListActivity {
	
	public static final String VENUE_ID = "VENUE_ID";
	
	private ActionBar mActionBar;
	private Venue mVenue;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        int venueId = getIntent().getIntExtra(VENUE_ID, 0);
        mVenue = DataStore.venue(this, venueId);
        
        mActionBar = getSupportActionBar(); 
        mActionBar.setTitle(mVenue.name);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        
        AlphaAdapter adapter = new AlphaAdapter();
        setListAdapter(adapter);						
	}
	
	
    @Override
    protected void onResume() {
        super.onResume();
        populate();
    }	
	
    
    private void populate() {
    	final Context context = this;
    	
    	 List<Row> rows = new ArrayList<Row>();    	 
    	 rows.add(new DetailRow(mVenue.name, mVenue.address(), new Resource(mVenue.imageKey, Resource.Type.VenueImageSmall), this));
    	 
    	 OnClickListener viewMapOnClick = new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
                String url = "http://maps.google.co.uk?q=" + mVenue.name + "@" + mVenue.latitude + "," + mVenue.longitude;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));				
			}
		};
		
		OnClickListener viewFloorplanOnClick = new View.OnClickListener() {			
			@Override
			public void onClick(View v) {				
                Intent intent = new Intent(context, FloorplanActivity.class);
                intent.putExtra(FloorplanActivity.FLOORPLAN_KEY, mVenue.floorplanKey);
                context.startActivity(intent);
			}
		};
		
        ButtonBarRow buttons = new ButtonBarRow(this);
        buttons.setButton1(getString(R.string.map_button_title), viewMapOnClick);
        buttons.setButton2(getString(R.string.floorplan_button_title), viewFloorplanOnClick);
        rows.add(buttons);	
        
        rows.add(new HTMLRow(mVenue.details, this));
        
        ((AlphaAdapter) getListAdapter()).setRows(rows, this);
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
