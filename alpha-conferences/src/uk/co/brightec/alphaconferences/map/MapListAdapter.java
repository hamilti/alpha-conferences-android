package uk.co.brightec.alphaconferences.map;

import uk.co.brightec.alphaconferences.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MapListAdapter extends BaseAdapter {
	
	private final Activity mActivity;
	private static LayoutInflater mInflater = null;
	
	
	public MapListAdapter(Activity a) {
		mActivity = a;
		mInflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	
	@Override
	public int getCount() {
		return 10;
	}

	
	@Override
	public Object getItem(int position) {		
		return null;
	}
	

	@Override
	public long getItemId(int position) {
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = mInflater.inflate(android.R.layout.simple_list_item_1, null);
		
		TextView titleTextView = (TextView)row.findViewById(R.id.title);
		titleTextView.setText("Cameron Cooke");
		
		return row;
	}
	
}