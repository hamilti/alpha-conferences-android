package uk.co.brightec.alphaconferences.programme;

import uk.co.brightec.alphaconferences.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProgrammeListAdapter extends BaseAdapter {
	
	private final Activity mActivity;
	private static LayoutInflater mInflater = null;
	private ProgrammeSection[] mSections;
	
	private final static int TYPE_SECTION_HEADER = 0;
	private final static int TYPE_ROW = 1;
	
	public ProgrammeListAdapter(Activity a) {
		mActivity = a;
		mInflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	
	@Override
	public int getCount() {
		Integer i = mSections.length;
		for (int j = 0; j < mSections.length; j++) {
			i += mSections[j].rows.length;
		}
		
		return i;
	}

	
	@Override
	public Object getItem(int position) {		
		// flatten all the objects
		Object[] rows = new Object[getCount()];
		int x = 0;
		for (int i = 0; i < mSections.length; i++) {
			rows[x++] = mSections[i];
			for (int j = 0; j < mSections[i].rows.length; j++) {
				rows[x++] = mSections[i].rows[j];
			}
		}		
		return rows[position];
	}
	

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	
	@Override
	public int getItemViewType(int position) {
		Object row = getItem(position);
		if (row instanceof ProgrammeSection) {
			return TYPE_SECTION_HEADER;
		}
		else {
			return TYPE_ROW;
		}
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;			
		
		if (getItemViewType(position) == TYPE_SECTION_HEADER) {
			
			ProgrammeSection section = (ProgrammeSection)getItem(position);
			
			if (rowView == null) {
				rowView = mInflater.inflate(R.layout.section_header_item, null);
			}
			
			TextView title = (TextView)rowView.findViewById(R.id.headerTitle);
			title.setText(section.title);
			
		}
		else {
			
			ProgrammeRow row = (ProgrammeRow)getItem(position);
			
			if (rowView == null) {
				rowView = mInflater.inflate(R.layout.programme_list_item, null);
				ViewHolder holder = new ViewHolder();
				holder.titleTextView = (TextView)rowView.findViewById(R.id.title);
				holder.subTitleTextView = (TextView)rowView.findViewById(R.id.subTitle);
				holder.speakerTextView = (TextView)rowView.findViewById(R.id.speakerName);
				holder.timeTextView = (TextView)rowView.findViewById(R.id.time);
				holder.barColourView = rowView.findViewById(R.id.barColour);
				rowView.setTag(holder);
			}
			
			ViewHolder holder = (ViewHolder)rowView.getTag();
			holder.titleTextView.setText(row.title);
			holder.subTitleTextView.setText(row.subTitle);
			holder.speakerTextView.setText(row.speakerName);
			holder.timeTextView.setText(row.time);
			holder.barColourView.setBackgroundColor(row.barColourInt);			
		}
		
		return rowView;
	}
	
	
	public void setSections(ProgrammeSection[] sections) {
		mSections = sections;
		notifyDataSetChanged();
	}
	
	
	static class ViewHolder {
		TextView titleTextView;
		TextView subTitleTextView;
		TextView speakerTextView;
		TextView timeTextView;
		View barColourView;				
	}
}