package uk.co.brightec.alphaconferences;

import android.content.Context;
import android.view.LayoutInflater;

public abstract class Row implements Cell {	
	protected static LayoutInflater mInflater;
	protected Context mContext;
	
	protected Row(Context context) {
		mContext = context;
		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
	}
}