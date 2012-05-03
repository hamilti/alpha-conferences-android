package uk.co.brightec.alphaconferences.speakers;

import java.util.ArrayList;
import java.util.List;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.Section;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;

public class SpeakersFragment extends SherlockListFragment {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// data
		List<Row> rows = new ArrayList<Row>();
		for (int i = 0; i < 20; i++) {
			SpeakerRow row = new SpeakerRow("Cameron Cooke", "World famous actor best known for leading role in the blockbuster film Ape the Movie.", R.drawable.ic_launcher, this.getActivity());
			rows.add(row);
		}
		
		List<Section> sections = new ArrayList<Section>();
		Section section = new Section(null, rows, this.getActivity());
		sections.add(section);		
		
        AlphaAdapter adapter = new AlphaAdapter();
        adapter.setSections(sections);
        setListAdapter(adapter);
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