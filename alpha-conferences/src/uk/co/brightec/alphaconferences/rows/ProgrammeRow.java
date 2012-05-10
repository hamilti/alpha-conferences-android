package uk.co.brightec.alphaconferences.rows;

import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Room;
import uk.co.brightec.alphaconferences.data.Session;
import uk.co.brightec.alphaconferences.data.Speaker;
import uk.co.brightec.alphaconferences.data.Stream;
import uk.co.brightec.alphaconferences.data.Venue;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;


public class ProgrammeRow extends Row {

    public String mTitle;
    public String mVenue;
    public String mSpeakerName;
    public String mTime;
    public int mBarColour;


    private ProgrammeRow(Context context) {
        super(context);
    }


    public static ProgrammeRow createForSession(Session s, Context context) {
        ProgrammeRow r = new ProgrammeRow(context);
        r.mTitle = s.name;

        Room room = DataStore.room(context, s.roomId);
        if (room != null) {
            Venue venue = DataStore.venue(context, room.venueId);
            if (venue != null) {
                r.mVenue = venue.name;
            }
        }

        if (!s.speakerIds.isEmpty()) {
            Speaker speaker = DataStore.speaker(context, s.speakerIds.get(0));
            if (speaker != null) {
                r.mSpeakerName = speaker.displayName();
            }
        }

        r.mTime = s.startDateTime.toString("HH:mm") + " - " + s.endDateTime.toString("HH:mm");
        
        Stream stream = DataStore.stream(context, s.streamId);
        if (stream != null && stream.color != null) {
            r.mBarColour = stream.color;
        } else {
            r.mBarColour = context.getResources().getColor(s.type.color);
        }

        return r;
    }


    public static ProgrammeRow createForSeminarSlot(Session s, boolean existing, Context context) {
        ProgrammeRow r = new ProgrammeRow(context);
        r.mTitle = existing ? "Change seminar choice" : "View seminar options";
        r.mBarColour = s.type.color;
        return r;
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
