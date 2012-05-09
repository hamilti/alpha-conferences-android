package uk.co.brightec.alphaconferences.programme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.Section;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Session;
import uk.co.brightec.alphaconferences.data.Stream;
import uk.co.brightec.alphaconferences.rows.ProgrammeRow;
import uk.co.brightec.util.MultiValueMap;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;


public class SeminarOptionsActivity extends SherlockListActivity {

    public static final String EXTRA_SESSION_GROUP_ID = "EXTRA_SESSION_GROUP_ID";

    private ActionBar mActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBar = getSupportActionBar(); 
        mActionBar.setTitle("Seminar Options");
        mActionBar.setDisplayHomeAsUpEnabled(true);

        AlphaAdapter adapter = new AlphaAdapter();
        setListAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        populate();
    }


    private void populate() {
        
        int sessionGroupId = getIntent().getIntExtra(EXTRA_SESSION_GROUP_ID, 0);
        List<Session> sessions = DataStore.sessionsForGroup(this, sessionGroupId);

        Map<Integer,Stream> streams = new HashMap<Integer,Stream>();
        for (Stream s : DataStore.streams(this)) {
            streams.put(s.streamId, s);
        }
        
        MultiValueMap<Integer, Session> sessionsKeyedByStreamId = new MultiValueMap<Integer, Session>();
        for (Session session : sessions) {
            sessionsKeyedByStreamId.put(session.streamId, session);
        }
        
        List<Stream> streamsSortedAlphabetically = new ArrayList<Stream>(streams.values());
        Collections.sort(streamsSortedAlphabetically, new Comparator<Stream>() {
            public int compare(Stream a, Stream b) {
                return a.name.compareTo(b.name);
            }
        });
        
        List<Section> sections = new ArrayList<Section>();
        
        for (Stream stream : streamsSortedAlphabetically) {
            if (sessionsKeyedByStreamId.containsKey(stream.streamId)) {
                List<Row> rows = new ArrayList<Row>();
                for (Session session : sessionsKeyedByStreamId.get(stream.streamId)) {
                    ProgrammeRow row = ProgrammeRow.createForSession(session, this);
                    rows.add(row);
                }
                sections.add(new Section(stream.name, rows, this));
            }
        }
        
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
