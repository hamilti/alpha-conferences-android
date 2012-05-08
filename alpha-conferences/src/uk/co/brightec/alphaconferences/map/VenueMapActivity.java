package uk.co.brightec.alphaconferences.map;

import java.util.ArrayList;
import java.util.List;

import uk.co.brightec.alphaconferences.Constants;
import uk.co.brightec.alphaconferences.MainActivity;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Venue;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockMapActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class VenueMapActivity extends SherlockMapActivity {
	private ActionBar mActionBar;
	private MapView mMapView;	
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layout.setOrientation(LinearLayout.VERTICAL);
		
		mMapView = new MapView(this, Constants.mapsAPIKey);
		mMapView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		mMapView.setBuiltInZoomControls(true);
		mMapView.setEnabled(true);
		mMapView.setClickable(true);
		layout.addView(mMapView);
		
		setContentView(layout);
		
	    mActionBar = getSupportActionBar();	
	    mActionBar.setTitle(getString(R.string.all_venues_action_bar_title));
	    mActionBar.setDisplayHomeAsUpEnabled(true);		
	}
	
	
    @Override
    public void onResume() {
        super.onResume();
        populate();
    }	
    
    
    private void populate() {
		Context context = this;
		
		List<Venue> mVenues = DataStore.venues(context);
        if (mVenues.isEmpty()) {
            return;
        }		
        
        List<OverlayItem> overlayItems = new ArrayList<OverlayItem>();
        for (Venue venue : mVenues) {
        	GeoPoint point = new GeoPoint((int)(venue.latitude *1E6), (int)(venue.longitude *1E6));
        	OverlayItem overlayItem  = new OverlayItem(point, venue.name, venue.address());
        	overlayItems.add(overlayItem);			
		}    
        
        List<Overlay> mapOverlays = mMapView.getOverlays();
        
        Drawable defaultMarker = getResources().getDrawable(R.drawable.marker);
        VenueBalloonItemizedOverlay itemizedOverlay = new VenueBalloonItemizedOverlay(defaultMarker, mMapView);        
        itemizedOverlay.addOverlayItems(overlayItems);    
        
        mapOverlays.add(itemizedOverlay);
    }
	
    
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
        case android.R.id.home:
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
	    }
	}	

}