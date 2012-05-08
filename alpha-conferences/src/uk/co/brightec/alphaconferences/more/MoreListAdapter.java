package uk.co.brightec.alphaconferences.more;

import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.rows.MoreRow;
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
	public MoreRow getItem(int position) {		
		return mListItems[position];
	}
	

	@Override
	public long getItemId(int position) {
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			row = mInflater.inflate(R.layout.more_list_item, null);	
			ViewHolder holder = new ViewHolder();
			holder.titleTextView = (TextView)row.findViewById(R.id.title);
			holder.imageView = (ImageView)row.findViewById(R.id.image);
			row.setTag(holder);
		}	
		
		// data source
		MoreRow moreRow = getItem(position);
		
		ViewHolder holder = (ViewHolder)row.getTag();
		holder.titleTextView.setText(moreRow.title);
		holder.imageView.setImageResource(moreRow.iconImageResource);
		
		return row;
	}
	
	static class ViewHolder {
		TextView titleTextView;
		ImageView imageView;
	}	
}
