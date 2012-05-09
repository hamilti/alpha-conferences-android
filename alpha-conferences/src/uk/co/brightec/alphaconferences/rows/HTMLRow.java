package uk.co.brightec.alphaconferences.rows;

import uk.co.brightec.alphaconferences.Cell;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

public class HTMLRow extends Row implements Cell {
	private final String mHtml;
	
	
	public HTMLRow(String html, Context context) {
		super(context);
		mHtml = html;
	}
	
	
    @Override
    public Boolean isEnabled() {
    	return false;
    }	

	
	@Override
	public View getView(View convertView) {
		View rowView = convertView;
		if (rowView == null) {
			rowView = mInflater.inflate(R.layout.html_list_item, null);
			
			TextView htmlTextView = (TextView)rowView.findViewById(R.id.htmlTextView);
			Linkify.addLinks(htmlTextView, Linkify.WEB_URLS);
			htmlTextView.setMovementMethod(LinkMovementMethod.getInstance());
			rowView.setTag(htmlTextView);
		}
		
		TextView htmlTextView = (TextView)rowView.getTag();			
		htmlTextView.setText(Html.fromHtml(mHtml));
		
		return rowView;		
	}

	
}
