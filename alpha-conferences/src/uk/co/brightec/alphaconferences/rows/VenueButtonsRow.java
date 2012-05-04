package uk.co.brightec.alphaconferences.rows;

import uk.co.brightec.alphaconferences.Cell;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VenueButtonsRow extends Row implements Cell {
	private final String mMapUrl;
	private final String mFloorplanPdfUrl;
	
	
	public VenueButtonsRow(String websiteURL, String twitterUserName, Context context) {
		super(context);
		mMapUrl = websiteURL;
		mFloorplanPdfUrl = twitterUserName;
	}

	
	@Override
	public View getView(View convertView) {
		View rowView = convertView;
		if (rowView ==  null) {
			rowView = mInflater.inflate(R.layout.speaker_buttons_list_item, null);
			
			ButtonsViewHolder holder = new ButtonsViewHolder();
			holder.mapButton = (Button)rowView.findViewById(R.id.websiteButton);
			holder.floorplanButton = (Button)rowView.findViewById(R.id.twitterButton);
			rowView.setTag(holder);
		}
		
		ButtonsViewHolder holder = (ButtonsViewHolder)rowView.getTag();
		
		holder.mapButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast toast = Toast.makeText(mContext, mMapUrl, Toast.LENGTH_LONG);
				toast.show();
			}
		});
		
		holder.floorplanButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast toast = Toast.makeText(mContext, mFloorplanPdfUrl, Toast.LENGTH_LONG);
				toast.show();
			}
		});
		
		return null;
	}
	
	
	private static class ButtonsViewHolder {
		Button mapButton;
		Button floorplanButton;
	}		

}
