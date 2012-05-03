package uk.co.brightec.alphaconferences.speakers;

import uk.co.brightec.alphaconferences.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
		
		View row = mInflater.inflate(R.layout.speaker_list_item, null);
		
		TextView titleTextView = (TextView)row.findViewById(R.id.title);
		TextView subTitleTextView = (TextView)row.findViewById(R.id.subTitle);
//		ImageView imageView = (ImageView)row.findViewById(R.id.image);
		
		titleTextView.setText("Cameron Cooke");
		subTitleTextView.setText("Amazing world famous actor best known for lead role in Ape the Movie.");
		
		return row;
	}
	
	
}