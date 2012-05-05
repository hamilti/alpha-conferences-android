package uk.co.brightec.util;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.json.JSONObject;


public final class JSON {
    
    public static enum DateIntepretation {
        SECONDS_SINCE_1970
    }


    private JSON() {}


    public static final String getString(JSONObject j, String name) {
        return j.isNull(name) ? null : j.optString(name);
    }

    public static final LocalDate getLocalDate(JSONObject j, String name, DateIntepretation di, DateTimeZone tz) {
        return getLocalDateTime(j, name, di, tz).toLocalDate();
    }
    
    public static final LocalDateTime getLocalDateTime(JSONObject j, String name, DateIntepretation di, DateTimeZone tz) {
        switch (di) {
        case SECONDS_SINCE_1970:
            return j.isNull(name) ? null : new LocalDateTime(j.optLong(name) * 1000L, tz);
        default:
            throw new RuntimeException("unknown DateIntepretation");
        }
    }

}
