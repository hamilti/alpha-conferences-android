package uk.co.brightec.alphaconferences.rows;

import uk.co.brightec.alphaconferences.Cell;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SpeakerButtonsRow extends Row implements Cell {
	private final String mWebsiteURL;
	private final String mTwitterUserName;
	
	
	public SpeakerButtonsRow(String websiteURL, String twitterUserName, Context context) {
		super(context);
		mWebsiteURL = websiteURL;
		mTwitterUserName = twitterUserName;
	}

	
	@Override
	public View getView(View convertView) {
		View rowView = convertView;
		if (rowView ==  null) {
			rowView = mInflater.inflate(R.layout.speaker_buttons_list_item, null);
			
			ButtonsViewHolder holder = new ButtonsViewHolder();
			holder.websiteButton = (Button)rowView.findViewById(R.id.websiteButton);
			holder.twitterButton = (Button)rowView.findViewById(R.id.twitterButton);
			rowView.setTag(holder);
		}
		
		ButtonsViewHolder holder = (ButtonsViewHolder)rowView.getTag();
		
		holder.websiteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast toast = Toast.makeText(mContext, mWebsiteURL, Toast.LENGTH_LONG);
				toast.show();
			}
		});
		
		holder.twitterButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast toast = Toast.makeText(mContext, mTwitterUserName, Toast.LENGTH_LONG);
				toast.show();
			}
		});
		
		return null;
	}
	
	
	private static class ButtonsViewHolder {
		Button websiteButton;
		Button twitterButton;
	}		

}
