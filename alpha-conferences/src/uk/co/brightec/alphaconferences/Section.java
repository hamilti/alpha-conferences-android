package uk.co.brightec.alphaconferences;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Section implements Cell {
	private static LayoutInflater mInflater;
	private final Context mContext;
	private final String mTitle;
	private final List<Row> mRows;
	
	public Section(String title, List<Row> rows, Context context) {
		mContext = context;
		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mTitle = title;
		mRows = rows;
	}
	
	@Override
	public View getView(View convertView) {
		View rowView = convertView;
		if (rowView == null) {
			rowView = mInflater.inflate(R.layout.section_header_item, null);
			TextView titleTextView = (TextView)rowView.findViewById(R.id.headerTitle);
			rowView.setTag(titleTextView);
		}
		
		TextView titleTextView = (TextView)rowView.getTag();			
		titleTextView.setText(mTitle);
		
		return rowView;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public List<Row> getRows() {
		return mRows;
	}
}