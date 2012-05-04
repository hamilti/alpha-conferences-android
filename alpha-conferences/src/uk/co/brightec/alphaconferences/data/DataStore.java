package uk.co.brightec.alphaconferences.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DataStore {
    
    
    public static List<Speaker> speakers(Context context) {
        List<Speaker> result = new ArrayList<Speaker>();
        DBHelper h = null;
        SQLiteDatabase db = null;
        try {
            h = new DBHelper(context);
            db = h.getWritableDatabase();

            List<JSONObject> entities = DBHelper.getEntities(db, "speakers");
            for (JSONObject j : entities) {
                result.add(new Speaker(j));
            }
            
        } finally {
            if (db != null) db.close();
            if (h != null) h.close();
        }

        Collections.sort(result);
        return result;
    }
    

    public static Speaker speaker(Context context, int speakerId) {
        DBHelper h = null;
        SQLiteDatabase db = null;
        try {
            h = new DBHelper(context);
            db = h.getWritableDatabase();

            JSONObject j = DBHelper.getEntity(db, "speakers", speakerId);
            return new Speaker(j);
            
        } finally {
            if (db != null) db.close();
            if (h != null) h.close();
        }
    }
    

}
