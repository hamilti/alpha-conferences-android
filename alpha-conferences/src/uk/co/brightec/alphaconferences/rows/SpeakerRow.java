package uk.co.brightec.alphaconferences.rows;

import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SpeakerRow extends Row {
	public String mSpeaker;
	public String mDescription;
	public int mImageResource;
	
	public SpeakerRow(String speaker, String description, int imageResource, Context context) {
		super(context);
		mSpeaker = speaker;
		mDescription = description;
		mImageResource = imageResource;
	}
	
	
	@Override
	public String indexerAlphaString() {
		String alpha = mSpeaker.substring(0, 1);
		alpha.toUpperCase();
		return alpha;
	}
	
	
	@Override
	public View getView(View convertView) {
		View rowView = convertView;
		if (rowView == null) {
			rowView = mInflater.inflate(R.layout.speaker_list_item, null);
			SpeakersViewHolder holder = new SpeakersViewHolder();
			holder.speakerTextView = (TextView)rowView.findViewById(R.id.title);
			holder.descriptionTextView = (TextView)rowView.findViewById(R.id.subTitle);
			holder.imageView = (ImageView)rowView.findViewById(R.id.image);
			rowView.setTag(holder);
		}
		
		SpeakersViewHolder holder = (SpeakersViewHolder)rowView.getTag();			
		holder.speakerTextView.setText(mSpeaker);
		holder.descriptionTextView.setText(mDescription);
		holder.imageView.setImageResource(mImageResource);
		
		return rowView;
	}
	
	
	private static class SpeakersViewHolder {
		TextView speakerTextView;
		TextView descriptionTextView;
		ImageView imageView;		
	}	
}