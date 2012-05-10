package uk.co.brightec.alphaconferences.speakers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.Section;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Day;
import uk.co.brightec.alphaconferences.data.Session;
import uk.co.brightec.alphaconferences.data.Speaker;
import uk.co.brightec.alphaconferences.programme.SessionDetailActivity;
import uk.co.brightec.alphaconferences.resources.Resource;
import uk.co.brightec.alphaconferences.rows.ButtonBarRow;
import uk.co.brightec.alphaconferences.rows.DetailRow;
import uk.co.brightec.alphaconferences.rows.HTMLRow;
import uk.co.brightec.alphaconferences.rows.ProgrammeRow;
import uk.co.brightec.util.MultiValueMap;
import uk.co.brightec.util.ReadablePartialComparator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;


public class SpeakerDetailActivity extends SherlockListActivity {
    
    public static final String EXTRA_SPEAKER_ID = "EXTRA_SPEAKER_ID";
    
    private ActionBar mActionBar;
    private Speaker speaker;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int speakerId = getIntent().getIntExtra(EXTRA_SPEAKER_ID, 0);
        speaker = DataStore.speaker(this, speakerId);
        
        mActionBar = getSupportActionBar(); 
        mActionBar.setTitle(speaker.displayName());
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
        List<Row> rows = new ArrayList<Row>();
        rows.add(new DetailRow(speaker.displayName(), speaker.position, new Resource(speaker.imageKey, Resource.Type.SpeakerImageSmall), this));
        rows.add(new HTMLRow(speaker.biography, this));

        OnClickListener websiteOnClick = null;
        if (speaker.websiteUrl != null) {
            websiteOnClick = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(speaker.websiteUrl)));
                }
            };
        }

        OnClickListener twitterOnClick = null;
        if (speaker.twitterUsername != null) {
            twitterOnClick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "http://twitter.com/"+speaker.twitterUsername;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }
            };
        }

        if (websiteOnClick != null || twitterOnClick != null) {
            ButtonBarRow buttons = new ButtonBarRow(this);
            buttons.setButton1(getString(R.string.website_button), websiteOnClick);
            buttons.setButton2(getString(R.string.twiter_button), twitterOnClick);
            rows.add(buttons);			
		}

//        DetailRow sessionsRow = new DetailRow("View their sessions", null, null, this);
//        sessionsRow.setOnClickListener(new Row.OnClickListener() {
//            public void onRowClicked() {
//                Intent intent = new Intent(SpeakerDetailActivity.this, SessionsBySpeakerActivity.class);
//                intent.putExtra(SessionsBySpeakerActivity.EXTRA_SPEAKER_ID, speaker.speakerId);
//                startActivity(intent);
//            }
//        });
//        rows.add(sessionsRow);
        
        // speakers
        List<Section> sections = new ArrayList<Section>();
        sections.add(new Section(null, rows, this));
        Section sessionsHeaderSection = new Section(getString(R.string.speaker_sessions_title), new ArrayList<Row>(), this);
        sessionsHeaderSection.mSectionBackgroundColourResource = R.color.fixed_section_header;
        sections.add(sessionsHeaderSection);
        sections.addAll(getSessionsForSpeaker(speaker));
        ((AlphaAdapter) getListAdapter()).setSections(sections);
    }
    
    private List<Section> getSessionsForSpeaker(Speaker speaker) {
    	
        List<Day> days = DataStore.days(this);
        
        MultiValueMap<Integer,Session> sessionsKeyedByDayId = new MultiValueMap<Integer,Session>();
        for (int sessionId : speaker.sessionIds) {
            Session session = DataStore.session(this, sessionId);
            sessionsKeyedByDayId.put(session.dayId, session);
        }

        List<Section> sections = new ArrayList<Section>();
        
        for (Day day : days) {
            Collection<Session> sessionsForDay = sessionsKeyedByDayId.get(day.dayId);
            if (sessionsForDay == null) {
                continue;
            }
            
            List<Session> sortedSessionsForDay = new ArrayList<Session>(sessionsForDay);
            Collections.sort(sortedSessionsForDay, new Comparator<Session>() {
                @Override
				public int compare(Session a, Session b) {
                    return ReadablePartialComparator.NULLS_FIRST.compare(a.startDateTime, b.startDateTime);
                }
            });
            
            List<Row> rows = new ArrayList<Row>();
            for (final Session session : sessionsForDay) {
                ProgrammeRow row = ProgrammeRow.createForSession(session, this);
                row.setOnClickListener(new Row.OnClickListener() {
                    @Override
					public void onRowClicked() {
                        Intent intent = new Intent(SpeakerDetailActivity.this, SessionDetailActivity.class);
                        intent.putExtra(SessionDetailActivity.EXTRA_SESSION_ID, session.sessionId);
                        startActivity(intent);
                    }
                });
                rows.add(row);
            }
            
            if (!rows.isEmpty()) {
                sections.add(new Section(day.date.toString("d MMMM yyyy"), rows, this));
            }
        }
        
        return sections;
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
