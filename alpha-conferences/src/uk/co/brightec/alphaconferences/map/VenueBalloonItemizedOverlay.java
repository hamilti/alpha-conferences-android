package uk.co.brightec.alphaconferences.map;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

public class VenueBalloonItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {
	private List<OverlayItem> mOverlayItems = new ArrayList<OverlayItem>();

	public VenueBalloonItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
	}


	@Override
	protected OverlayItem createItem(int i) {
		return mOverlayItems.get(i);
	}


	@Override
	public int size() {
		return mOverlayItems.size();
	}

	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
		return true;
	}
	
	
	public void addOverlayItems(List<OverlayItem> overlayItems) {
		mOverlayItems = overlayItems;
		populate();
	}
}