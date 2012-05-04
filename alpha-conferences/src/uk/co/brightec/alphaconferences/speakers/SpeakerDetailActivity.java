package uk.co.brightec.alphaconferences.speakers;

import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Speaker;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;


public class SpeakerDetailActivity extends SherlockActivity {
    
    static final String EXTRA_SPEAKER_ID = "EXTRA_SPEAKER_ID";
    
    private ActionBar mActionBar;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int speakerId = getIntent().getIntExtra(EXTRA_SPEAKER_ID, 0);
        Speaker s = DataStore.speaker(this, speakerId);
        
        mActionBar = getSupportActionBar(); 
        mActionBar.setTitle(s.displayName());
        mActionBar.setDisplayHomeAsUpEnabled(true);         
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    
    
    
}
