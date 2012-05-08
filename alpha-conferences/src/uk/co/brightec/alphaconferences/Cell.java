package uk.co.brightec.alphaconferences;

import android.view.View;

public interface Cell {
	
	public View getView(View convertView);	
	public Boolean isEnabled();
}