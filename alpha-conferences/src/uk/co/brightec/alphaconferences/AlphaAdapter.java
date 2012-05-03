package uk.co.brightec.alphaconferences;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AlphaAdapter extends BaseAdapter {
	private List<Cell> mRows;
	
	@Override
	public int getCount() {
		return mRows.size();
	}

	@Override
	public Object getItem(int position) {
		return mRows.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Cell row = mRows.get(position);
		return row.getView(null);
	}
	
	public void setSections(List<Section> sections) {
		mRows = new ArrayList<Cell>(); 
		
		for (Section section : sections) {
			if (section.getTitle() !=  null && section.getTitle().length() > 0) {
				mRows.add(section);
			}
			for (Row row : section.getRows()) {
				mRows.add(row);
			}
		}
		
		notifyDataSetChanged();
	}
	
//	public void setRows(Row[] rows) {	
//	
//	mAdapterType = AdapterType.ROWS;
//}	
	
//	public void setPages(Page[] pages) {
//		mAdapterType = AdapterType.PAGES;
//	}

}
