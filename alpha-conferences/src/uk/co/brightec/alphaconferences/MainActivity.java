package uk.co.brightec.alphaconferences;

import uk.co.brightec.alphaconferences.map.MapFragment;
import uk.co.brightec.alphaconferences.more.MoreFragment;
import uk.co.brightec.alphaconferences.programme.ProgrammeFragment;
import uk.co.brightec.alphaconferences.speakers.SpeakersFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity {
	
	private ActionBar mActionBar;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
	    mActionBar = getSupportActionBar();
	    mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
	    mActionBar.setDisplayShowTitleEnabled(true);        
        
	    // programme
	    Tab programmeTab  = mActionBar.newTab()
	    		.setText(R.string.programme_tab)
	    		.setTabListener(new TabListener<ProgrammeFragment>(this, "programme", ProgrammeFragment.class));
	    mActionBar.addTab(programmeTab);         
	    
	    // speakers
	    Tab speakersTab  = mActionBar.newTab()
	    		.setText(R.string.speakers_tab)
	    		.setTabListener(new TabListener<SpeakersFragment>(this, "speakers", SpeakersFragment.class));
	    mActionBar.addTab(speakersTab); 
	    
	    // maps
	    Tab mapTab  = mActionBar.newTab()
	    		.setText(R.string.maps_tab)
	    		.setTabListener(new TabListener<MapFragment>(this, "map", MapFragment.class));
	    mActionBar.addTab(mapTab);
	    
	    // more
	    Tab moreTab  = mActionBar.newTab()
	    		.setText(R.string.more_tab)
				.setTabListener(new TabListener<MoreFragment>(this, "more", MoreFragment.class));        
	    mActionBar.addTab(moreTab);
	   
	    // restore previous selected tab
	    if (savedInstanceState != null) {
	    	Integer activeTabIndex = savedInstanceState.getInt("activeTabIndex", 0);
	    	mActionBar.selectTab(mActionBar.getTabAt(activeTabIndex));
	    	
		}
    }  
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Integer activeTabIndex = mActionBar.getSelectedNavigationIndex();
		outState.putInt("activeTabIndex", activeTabIndex);
		super.onSaveInstanceState(outState);
	}
	
	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
	    private Fragment mFragment;
	    private final SherlockFragmentActivity mActivity;
	    private final String mTag;
	    private final Class<T> mClass;

	    /** Constructor used each time a new tab is created.
	      * @param activity  The host Activity, used to instantiate the fragment
	      * @param tag  The identifier tag for the fragment
	      * @param clz  The fragment's Class, used to instantiate the fragment
	      */
	    public TabListener(SherlockFragmentActivity activity, String tag, Class<T> clz) {
	        mActivity = activity;
	        mTag = tag;
	        mClass = clz;
	    }

	    /* The following are each of the ActionBar.TabListener callbacks */

	    @Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
	        FragmentManager fragMgr = mActivity.getSupportFragmentManager();
	        ft = fragMgr.beginTransaction();
	        ft.commit();	    	
	        
	        // restore an existing fragment
	        mFragment = fragMgr.findFragmentByTag(mTag);
	    	
	        // Check if the fragment is already initialised
	        if (mFragment == null) {
	            // If not, instantiate and add it to the activity
	            mFragment = Fragment.instantiate(mActivity, mClass.getName());
	            ft.add(android.R.id.content, mFragment, mTag);
	        } else {
	            // If it exists, simply attach it in order to show it
	            ft.attach(mFragment);
	        }
	    }

	    @Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	        if (mFragment != null) {
	            // Detach the fragment, because another one is being attached
	            ft.detach(mFragment);
	        }
	    }

	    @Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
	        // User selected the already selected tab. Usually do nothing.
	    }
	}	
}