package uk.co.brightec.alphaconferences.speakers;

import java.util.ArrayList;
import java.util.List;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.Section;
import uk.co.brightec.alphaconferences.rows.SpeakerRow;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;

public class SpeakersFragment extends SherlockListFragment {
	private AlphaAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] alphabet = new String[] {
			"A",
			"B",
			"C",
			"D",
			"E",
			"F",
			"G",
			"H",
			"I",
			"J",
			"K",
			"L",
			"M",
			"N",
			"O",
			"P",
			"Q",
			"R",
			"S",
			"T",
			"U",
			"V",
			"W",
			"X",
			"Y",
			"Z"
		};
		
		// data
		List<Row> rows = new ArrayList<Row>();
		for (int i = 0; i < alphabet.length; i++) {
			SpeakerRow row = new SpeakerRow(alphabet[i] + "oon Bloggs ", "World famous actor best known for leading role in the blockbuster film Ape the Movie.", R.drawable.ic_launcher, this.getActivity());
			rows.add(row);
		}
		
		List<Section> sections = new ArrayList<Section>();
		Section section = new Section(null, rows, this.getActivity());
		sections.add(section);		
		
        adapter = new AlphaAdapter();
        adapter.showAlphaIndex(true);
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
		
        getListView().setFastScrollEnabled(true);
	}
	
	
	@Override
	public void onPause() {
		super.onPause();
	}

	
}