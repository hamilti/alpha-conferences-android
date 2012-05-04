package uk.co.brightec.alphaconferences.rows;

import uk.co.brightec.alphaconferences.Cell;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailRow extends Row implements Cell {
	private final String mTitle;
	private final String mSubTitle;
	public int mImageResource;
	
	protected DetailRow(String title, String subTitle, int imageResource, Context context) {
		super(context);
		mTitle = title;
		mSubTitle = subTitle;
		mImageResource = imageResource;
	}

	
	@Override
	public View getView(View convertView) {
		View rowView = convertView;
		if (rowView == null) {
			rowView = mInflater.inflate(R.layout.detail_list_item, null);
			
			DetailViewHolder holder = new DetailViewHolder();
			holder.titleTextView = (TextView)rowView.findViewById(R.id.titleTextView);
			holder.subTitleTextView = (TextView)rowView.findViewById(R.id.subTitleTextView);
			holder.imageView = (ImageView)rowView.findViewById(R.id.imageView);
			rowView.setTag(holder);
		}
		
		DetailViewHolder holder = (DetailViewHolder)rowView.getTag();			
		holder.titleTextView.setText(mTitle);
		holder.subTitleTextView.setText(mSubTitle);
		holder.imageView.setImageResource(mImageResource);
		
		return rowView;
	}
	
	private static class DetailViewHolder {
		TextView titleTextView;
		TextView subTitleTextView;
		ImageView imageView;		
	}	
}
