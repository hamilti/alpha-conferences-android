package uk.co.brightec.alphaconferences.more;

import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.rows.MoreRow;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

public class MoreFragment extends SherlockListFragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		MoreRow twitterRow = new MoreRow();
		twitterRow.title = getString(R.string.twitter_menu_title);
		twitterRow.iconImageResource =  R.drawable.twitter;
		
		MoreRow donateRow = new MoreRow();
		donateRow.title = getString(R.string.donate_menu_title);
		donateRow.iconImageResource =  R.drawable.donate;
		donateRow.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DonateActivity.class);
                v.getContext().startActivity(intent);
            }
        };
		
		MoreRow alertRow = new MoreRow();
		alertRow.title = getString(R.string.alerts_menu_title);
		alertRow.iconImageResource =  R.drawable.alerts;
		
		MoreRow faqsRow = new MoreRow();
		faqsRow.title = getString(R.string.faqs_menu_title);
		faqsRow.iconImageResource =  R.drawable.faqs;
		
		MoreRow otherRow = new MoreRow();
		otherRow.title = getString(R.string.other_events_menu_title);
		otherRow.iconImageResource =  R.drawable.other_events;
		
		MoreRow specialOffersRow = new MoreRow();
		specialOffersRow.title = getString(R.string.special_offers_menu_title);
		specialOffersRow.iconImageResource =  R.drawable.special_offers;	
		
		
		MoreRow[] listItems  = {
				twitterRow,
				donateRow,
				alertRow,
				faqsRow,
				otherRow,
				specialOffersRow
		};
		
        MoreListAdapter listAdapter = new MoreListAdapter(getActivity(), listItems);
        setListAdapter(listAdapter);
	}

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        MoreRow row = ((MoreListAdapter) l.getAdapter()).getItem(position);
        if (row.onClickListener != null) {
            row.onClickListener.onClick(v);
        }
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