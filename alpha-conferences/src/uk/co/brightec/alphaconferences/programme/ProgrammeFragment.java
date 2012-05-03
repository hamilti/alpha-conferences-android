package uk.co.brightec.alphaconferences.programme;

import uk.co.brightec.alphaconferences.R;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;

public class ProgrammeFragment extends SherlockListFragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// data
		ProgrammeRow[] rows = new ProgrammeRow[10];		
		for (int i = 0; i < 10; i++) {
			ProgrammeRow row = new ProgrammeRow();
			row.title = "Main Session" + i;
			row.subTitle = "The Royal Albert Hall";
			row.speakerName = "Cameron Cooke";
			row.time = "10:00 - 11:00";
			row.barColourInt = ((i % 2) == 1 ? Color.RED : Color.BLUE) ;
			rows[i] = row;
		}
		
		ProgrammeSection[] sections = new ProgrammeSection[4];
		for (int i = 0; i < 4; i++) {
			ProgrammeSection section = new ProgrammeSection();
			section.title = "Section " + i;
			section.rows = rows;
			sections[i] = section;
		}		
		
        ProgrammeListAdapter listAdapter = new ProgrammeListAdapter(getActivity());
        listAdapter.setSections(sections);
        setListAdapter(listAdapter);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, container, false);
	}

	
	@Override
	public void onPause() {
		super.onPause();
	}
	

}
