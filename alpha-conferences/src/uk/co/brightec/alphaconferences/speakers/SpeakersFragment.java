package uk.co.brightec.alphaconferences.speakers;

import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.R.layout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;

public class SpeakersFragment extends SherlockListFragment {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        SpeakersListAdapter listAdapter = new SpeakersListAdapter(getActivity());
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