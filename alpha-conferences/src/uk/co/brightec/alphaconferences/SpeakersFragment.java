package uk.co.brightec.alphaconferences;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SpeakersFragment extends ListFragment {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        MyListAdapter listAdapter = new MyListAdapter(getActivity());
        setListAdapter(listAdapter);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.speakers, container, false);
	}

	
	@Override
	public void onPause() {
		super.onPause();
	}

	
}