package uk.co.brightec.alphaconferences;

import android.app.ListActivity;
import android.os.Bundle;

public class HomeActivity extends ListActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MyListAdapter listAdapter = new MyListAdapter(this);
        setListAdapter(listAdapter);
    }
}

