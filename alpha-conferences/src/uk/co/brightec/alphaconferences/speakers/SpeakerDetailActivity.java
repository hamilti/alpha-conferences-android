package uk.co.brightec.alphaconferences.speakers;

import java.util.ArrayList;
import java.util.List;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Speaker;
import uk.co.brightec.alphaconferences.resources.Resource;
import uk.co.brightec.alphaconferences.rows.ButtonBarRow;
import uk.co.brightec.alphaconferences.rows.DetailRow;
import uk.co.brightec.alphaconferences.rows.HTMLRow;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;


public class SpeakerDetailActivity extends SherlockListActivity {
    
    static final String EXTRA_SPEAKER_ID = "EXTRA_SPEAKER_ID";
    
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
        //getListView().setAdapter(adapter);
        
        setListAdapter(adapter);
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

        ButtonBarRow buttons = new ButtonBarRow(this);
        buttons.setButton1("Website", websiteOnClick);
        buttons.setButton2("Twitter", twitterOnClick);
        rows.add(buttons);

        ((AlphaAdapter) getListAdapter()).setRows(rows, this);
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
