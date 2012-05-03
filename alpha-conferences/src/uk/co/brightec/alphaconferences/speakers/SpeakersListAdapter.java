package uk.co.brightec.alphaconferences.speakers;

import uk.co.brightec.alphaconferences.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpeakersListAdapter extends BaseAdapter {
	
	private final Activity mActivity;
	private static LayoutInflater mInflater = null;
	
	
	public SpeakersListAdapter(Activity a) {
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
			row = mInflater.inflate(R.layout.speaker_list_item, null);
			ViewHolder holder = new ViewHolder();
			holder.titleTextView = (TextView)row.findViewById(R.id.title);
			holder.subTitleTextView = (TextView)row.findViewById(R.id.subTitle);
			holder.imageView = (ImageView)row.findViewById(R.id.image);
			row.setTag(holder);
		}
		
		ViewHolder holder = (ViewHolder)row.getTag();
				
		holder.titleTextView.setText("Cameron Cooke");
		holder.subTitleTextView.setText("Amazing world famous actor best known for lead role in Ape the Movie.");
		
		return row;
	}
	
	
	static class ViewHolder {
		TextView titleTextView;
		TextView subTitleTextView;
		ImageView imageView;		
	}
}