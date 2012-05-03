package uk.co.brightec.alphaconferences.programme;

import uk.co.brightec.alphaconferences.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProgrammeListAdapter extends BaseAdapter {
	
	private final Activity mActivity;
	private static LayoutInflater mInflater = null;
	
	
	public ProgrammeListAdapter(Activity a) {
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
		View row = convertView;
		if (row == null) {
			row = mInflater.inflate(R.layout.programme_list_item, null);
			ViewHolder holder = new ViewHolder();
			holder.titleTextView = (TextView)row.findViewById(R.id.title);
			holder.subTitleTextView = (TextView)row.findViewById(R.id.subTitle);
			holder.speakerTextView = (TextView)row.findViewById(R.id.speakerName);
			holder.timeTextView = (TextView)row.findViewById(R.id.time);
			holder.barColourView = row.findViewById(R.id.barColour);
			row.setTag(holder);
		}
		
		ViewHolder holder = (ViewHolder)row.getTag();
		holder.titleTextView.setText("Main Session" + (position+1));
		holder.subTitleTextView.setText("The Royal Albert Hall");
		holder.speakerTextView.setText("Cameron Cooke");
		holder.timeTextView.setText("10:00 - 11:00");
		
		if ((position % 2) == 1) {
			holder.barColourView.setBackgroundColor(Color.RED);	
		}
		else {
			holder.barColourView.setBackgroundColor(Color.BLUE);
		}		
		
		return row;
	}
	
	
	static class ViewHolder {
		TextView titleTextView;
		TextView subTitleTextView;
		TextView speakerTextView;
		TextView timeTextView;
		View barColourView;				
	}
}