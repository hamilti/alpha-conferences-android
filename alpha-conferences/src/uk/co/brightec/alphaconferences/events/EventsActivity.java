package uk.co.brightec.alphaconferences.events;

import java.util.ArrayList;
import java.util.List;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.Row.OnClickListener;
import uk.co.brightec.alphaconferences.data.Conference;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.rows.DetailRow;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockListActivity;

public class EventsActivity extends SherlockListActivity {
	private AlphaAdapter mAdapter;
	private static String DATE_FORMAT = "dd MMMM YYYY";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list);
		
		mAdapter = new AlphaAdapter();
        setListAdapter(mAdapter);
        
		getListView().setOnItemClickListener(mAdapter);        
	}
	
	
	private void populate() {
		List<Conference> conferences = DataStore.otherConferences(this);		
		conferences.add(DataStore.conference(this));
		
		List<Row> rows = new ArrayList<Row>();
		final Context context = this;
		
		
		for (final Conference conference : conferences) {
						
			String dateText = conference.startDate.toString(DATE_FORMAT);
			if (conference.endDate != null) {
				dateText += " - " + conference.endDate.toString(DATE_FORMAT);						
			}			
			
			Row row = new DetailRow(conference.name, dateText, null, this);
			row.setOnClickListener(new OnClickListener() {				
				@Override
				public void onRowClicked() {
					
					Intent intent = new Intent(context, EventDetailActivity.class);
					intent.putExtra(EventDetailActivity.CONFERENCE_ID, conference.conferenceId);
					context.startActivity(intent);
				}
			});
			rows.add(row);
		}
		
		mAdapter.setRows(rows, this);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		populate();		
	}
}