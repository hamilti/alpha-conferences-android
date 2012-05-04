package uk.co.brightec.alphaconferences.programme;

import java.util.ArrayList;
import java.util.List;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.Row.OnClickListener;
import uk.co.brightec.alphaconferences.Section;
import uk.co.brightec.alphaconferences.more.TwitterActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;

public class ProgrammeFragment extends SherlockListFragment {
	private AlphaAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// data
		List<Row> rows = new ArrayList<Row>();
		for (int i = 0; i < 10; i++) {
			ProgrammeRow row = new ProgrammeRow("Main Session " + i, "The Royal Albert Hall", "Cameron Cooke", "10:00 - 11:00", ((i % 2) == 1 ? Color.RED : Color.BLUE), this.getActivity());
			row.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onRowClicked() {
					Intent intent = new Intent(getActivity(), TwitterActivity.class);
					startActivity(intent);						
				}				
			});
			rows.add(row);
		}
		
		List<Section> sections = new ArrayList<Section>();
		for (int i = 0; i < 4; i++) {
			Section section = new Section("Section " + i, rows, this.getActivity());
			sections.add(section);
		}		
		
        adapter = new AlphaAdapter();
        adapter.setSections(sections);
        setListAdapter(adapter);		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, container, false);
	}
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
        getListView().setOnItemClickListener(adapter);		
	}	
	
	
	@Override
	public void onPause() {
		super.onPause();
	}
}
