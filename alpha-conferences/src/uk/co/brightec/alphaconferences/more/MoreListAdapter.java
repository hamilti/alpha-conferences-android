package uk.co.brightec.alphaconferences.more;

import uk.co.brightec.alphaconferences.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MoreListAdapter extends BaseAdapter {
	
	private final Activity mActivity;
	private static LayoutInflater mInflater = null;
	private static MoreRow[] mListItems = null;
	
	
	public MoreListAdapter(Activity a, MoreRow[] listItems) {
		mActivity = a;
		mInflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mListItems = listItems;
	}

	
	@Override
	public int getCount() {
		return mListItems.length;
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
		
		MoreRow moreRow = mListItems[position];
		
		View row = mInflater.inflate(R.layout.more_list_item, null);				
		TextView titleTextView = (TextView)row.findViewById(R.id.title);
		ImageView imageView = (ImageView)row.findViewById(R.id.image);
		
		titleTextView.setText(moreRow.title);
		imageView.setImageResource(moreRow.iconImageResource);
		
		
		return row;
	}
	
	
}