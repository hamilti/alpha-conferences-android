package uk.co.brightec.alphaconferences.programme;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.Section;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Room;
import uk.co.brightec.alphaconferences.data.Session;
import uk.co.brightec.alphaconferences.data.Speaker;
import uk.co.brightec.alphaconferences.data.Venue;
import uk.co.brightec.alphaconferences.rows.ButtonBarRow;
import uk.co.brightec.alphaconferences.rows.DetailRow;
import uk.co.brightec.alphaconferences.rows.HTMLRow;
import uk.co.brightec.alphaconferences.rows.SpeakerRow;
import uk.co.brightec.alphaconferences.speakers.SpeakerDetailActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;


public class SessionDetailActivity extends SherlockListActivity {
    
    public static final String EXTRA_SESSION_ID = "EXTRA_SESSION_ID";
    
    private ActionBar mActionBar;
    private Session session;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int sessionId = getIntent().getIntExtra(EXTRA_SESSION_ID, 0);
        session = DataStore.session(this, sessionId);
        
        mActionBar = getSupportActionBar(); 
        mActionBar.setTitle(session.name);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        
        AlphaAdapter adapter = new AlphaAdapter();
        setListAdapter(adapter);
        getListView().setOnItemClickListener(adapter);
    }
    

    @Override
    protected void onResume() {
        super.onResume();
        populate();
    }

    
    private void populate() {
        List<Row> detailRows = new ArrayList<Row>();
        List<Row> speakerRows = new ArrayList<Row>();
        
        Room room = DataStore.room(this, session.roomId);
        Venue venue = null;
        if (room != null) {
            venue = DataStore.venue(this, room.venueId);
        }
        
        // details
        
        StrBuilder subtitle = new StrBuilder();
        if (venue != null) {
            subtitle.append(venue.name + ", " + room.name);
        }
        subtitle.appendSeparator("\n");
        subtitle.append(session.startDateTime.toString("HH:mm") + " - "+session.endDateTime.toString("HH:mm"));
        subtitle.append(", ").append(session.startDateTime.toString("d MMMM yyyy"));
        detailRows.add(new DetailRow(session.name, subtitle.toString(), null, this));

        // buttons
        
        OnClickListener venueButtonHandler = null;
        if (venue != null) {
            venueButtonHandler = new OnClickListener() {
                public void onClick(View v) {
                    // TODO
                }
            };
        }
        
        ButtonBarRow buttons = new ButtonBarRow(this);
        buttons.setButton1("Venue Details", venueButtonHandler);
        detailRows.add(buttons);
        
        // description
        
        detailRows.add(new HTMLRow(session.text, this));

        // speakers
        
        for (int speakerId : session.speakerIds) {
            final Speaker speaker = DataStore.speaker(this, speakerId);
            if (speaker != null) {
                SpeakerRow row = new SpeakerRow(speaker, this);
                row.setOnClickListener(new Row.OnClickListener() {
                    public void onRowClicked() {
                        Intent intent = new Intent(SessionDetailActivity.this, SpeakerDetailActivity.class);
                        intent.putExtra(SpeakerDetailActivity.EXTRA_SPEAKER_ID, speaker.speakerId);
                        startActivity(intent);
                    }
                });
                speakerRows.add(row);
            }
        }

        List<Section> sections = new ArrayList<Section>();
        sections.add(new Section(null, detailRows, this));
        sections.add(new Section("Speakers", speakerRows, this));
        ((AlphaAdapter) getListAdapter()).setSections(sections);
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }


}
