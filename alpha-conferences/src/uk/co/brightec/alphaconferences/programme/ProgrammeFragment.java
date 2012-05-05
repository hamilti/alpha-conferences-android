package uk.co.brightec.alphaconferences.programme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import uk.co.brightec.alphaconferences.AlphaAdapter;
import uk.co.brightec.alphaconferences.Page;
import uk.co.brightec.alphaconferences.R;
import uk.co.brightec.alphaconferences.Row;
import uk.co.brightec.alphaconferences.Row.OnClickListener;
import uk.co.brightec.alphaconferences.Section;
import uk.co.brightec.alphaconferences.data.DataStore;
import uk.co.brightec.alphaconferences.data.Day;
import uk.co.brightec.alphaconferences.data.Session;
import uk.co.brightec.alphaconferences.more.TwitterActivity;
import uk.co.brightec.alphaconferences.rows.ProgrammeRow;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;

public class ProgrammeFragment extends SherlockListFragment {
    private AlphaAdapter adapter;
    private List<Page> mPages;
    private int mPageIndex;

    ImageButton prevButton;
    ImageButton nextButton;
    TextView pagerTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPageIndex = 0;

        // data
        List<Row> rows = new ArrayList<Row>();
        for (int i = 0; i < 2; i++) {
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
            Section section = new Section(i + "0:00 - " + (i+1) + "0:00", rows, this.getActivity());
            sections.add(section);
        }

        mPages = new ArrayList<Page>();
        for (int i = 0; i < 5; i++) {
            Page page = new Page("Page " + i, sections);
            mPages.add(page);
        }
    }

    private void setPage(int pageIndex) {

        Log.v("CAKE", "pageIndex: " + pageIndex);
        Log.v("CAKE", "mPageIndex: " + mPageIndex);

        prevButton.setEnabled(pageIndex > 0);
        nextButton.setEnabled(pageIndex < mPages.size()-1);

        Page page = mPages.get(pageIndex);
        List<Section> sections = page.getSections();
        adapter.setSections(sections);

        pagerTitle.setText(page.getTitle());

        mPageIndex = pageIndex;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.list, container, false);
        View pager = view.findViewById(R.id.pagerView);

        prevButton = (ImageButton)pager.findViewById(R.id.prevButton);
        nextButton = (ImageButton)pager.findViewById(R.id.nextButton);
        pagerTitle = (TextView)pager.findViewById(R.id.title);

        prevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPage(mPageIndex-1);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPage(mPageIndex+1);
            }
        });

        pager.setVisibility(View.VISIBLE);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new AlphaAdapter();
        setPage(mPageIndex);
        setListAdapter(adapter);

        getListView().setOnItemClickListener(adapter);
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
        populate();
    }


    private void populate() {
        Context context = getActivity();
        
        List<Day> days = DataStore.days(context);
        List<Session> sessions = DataStore.sessions(context);
        if (days.isEmpty()) {
            return;
        }
        
        mPages = new ArrayList<Page>();
        for (Day day : days) {
            
            // group sessions into hours
            Map<LocalDateTime,List<Session>> sessionsKeyedByHour = new HashMap<LocalDateTime,List<Session>>();
            for (Session session : sessions) {
                if (session.dayId == day.dayId) {
                    LocalDateTime key = session.startHour();
                    List<Session> l = sessionsKeyedByHour.get(key);
                    if (l == null) {
                        l = new ArrayList<Session>();
                        sessionsKeyedByHour.put(key, l);
                    }
                    l.add(session);
                }
            }

            List<Section> sections = new ArrayList<Section>();
            List<LocalDateTime> hours = new ArrayList<LocalDateTime>(sessionsKeyedByHour.keySet());
            Collections.sort(hours);
            for (LocalDateTime hour : hours) {
                
                List<Row> rows = new ArrayList<Row>();
                List<Session> sessionsInThisHour = sessionsKeyedByHour.get(hour);
                
                // sessions are sorted first by type (seminar slot first), then by start time
                Collections.sort(sessionsInThisHour, new Comparator<Session>() {
                    public int compare(Session a, Session b) {
                        if (a.type == Session.Type.SEMINAR_SLOT && b.type != Session.Type.SEMINAR_SLOT) {
                            return -1;
                        } else if (a.type != Session.Type.SEMINAR_SLOT && b.type == Session.Type.SEMINAR_SLOT) {
                            return 1;
                        } else {
                            return a.startDateTime.compareTo(b.endDateTime);
                        }
                    }
                });
                
                for (Session session : sessionsInThisHour) {
                    
                    if (session.type == Session.Type.SEMINAR_OPTION) {
                        // don't show seminars here
                    }
                    else if (session.type == Session.Type.SEMINAR_SLOT) {
                        // seminar slot
                        String title = "Seminar options";
                        String time = session.startDateTime.toString("HH:mm") + " - " + session.endDateTime.toString("HH:mm");
                        int color = session.type.color;
                        rows.add(new ProgrammeRow(title, null, null, time, color, context));
                    }
                    else {
                        // all other sessions
                        String title = session.name;
                        String time = session.startDateTime.toString("HH:mm") + " - " + session.endDateTime.toString("HH:mm");
                        int color = session.type.color;
                        rows.add(new ProgrammeRow(title, "Venue", "Speaker", time, color, context));
                    }
                }

                String sectionTitle = hour.toString("HH:mm");
                sections.add(new Section(sectionTitle, rows, context));
            }
            
            String title = day.date.toString("EEEE d MMMM yyyy");
            mPages.add(new Page(title, sections));
        }

        setPage(0);
    }

}
