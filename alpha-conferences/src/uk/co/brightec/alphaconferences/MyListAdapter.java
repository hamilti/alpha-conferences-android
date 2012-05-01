package uk.co.brightec.alphaconferences;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {
	
	private final Activity activity;
	private static LayoutInflater inflater = null;
	
	
	public MyListAdapter(Activity a) {
		activity = a;
		inflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		
		View row = inflater.inflate(R.layout.left_image_list_item, null);
		
		TextView titleTextView = (TextView)row.findViewById(R.id.title);
		TextView subTitleTextView = (TextView)row.findViewById(R.id.subTitle);
//		ImageView imageView = (ImageView)row.findViewById(R.id.image);
		
		titleTextView.setText("Hello World");
		subTitleTextView.setText("The quick brown fox jumped over the lazy dog.");
		
		return row;
	}
	
	
}