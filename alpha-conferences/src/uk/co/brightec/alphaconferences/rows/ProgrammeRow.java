package uk.co.brightec.alphaconferences.rows;

import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class ProgrammeRow extends Row {
    public String mTitle;
    public String mVenue;
    public String mSpeakerName;
    public String mTime;
    public int mBarColour;


    public ProgrammeRow(String title, String venue, String speakerName, String time, int barColour, Context context) {
        super(context);
        mTitle = title;
        mVenue = venue;
        mSpeakerName = speakerName;
        mTime = time;
        mBarColour = barColour;
    }
    
    
    @Override
    public Boolean isEnabled() {
    	return true;
    }    


    @Override
    public View getView(View convertView) {
        View rowView = convertView;
        if (rowView == null) {
            rowView = mInflater.inflate(R.layout.programme_list_item, null);
            ProgrammeViewHolder holder = new ProgrammeViewHolder();
            holder.barColourView = rowView.findViewById(R.id.barColour);
            holder.titleTextView = (TextView)rowView.findViewById(R.id.title);
            holder.subTitleTextView = (TextView)rowView.findViewById(R.id.subTitle);
            holder.speakerNameTextView = (TextView)rowView.findViewById(R.id.speakerName);
            holder.timeTextView = (TextView)rowView.findViewById(R.id.time);
            rowView.setTag(holder);
        }

        ProgrammeViewHolder holder = (ProgrammeViewHolder)rowView.getTag();
        holder.barColourView.setBackgroundColor(mBarColour);
        
        holder.titleTextView.setText(mTitle);
        holder.titleTextView.setVisibility((mTitle == null ? View.GONE : View.VISIBLE));
        
        holder.subTitleTextView.setText(mVenue);
        holder.subTitleTextView.setVisibility((mVenue == null ? View.GONE : View.VISIBLE));
        
        holder.speakerNameTextView.setText(mSpeakerName);
        holder.speakerNameTextView.setVisibility((mSpeakerName == null ? View.GONE : View.VISIBLE));
        
        holder.timeTextView.setText(mTime);
        holder.timeTextView.setVisibility((mTime == null ? View.GONE : View.VISIBLE));

        return rowView;
    }


    private static class ProgrammeViewHolder {
        View barColourView;
        TextView titleTextView;
        TextView subTitleTextView;
        TextView speakerNameTextView;
        TextView timeTextView;
    }


}
