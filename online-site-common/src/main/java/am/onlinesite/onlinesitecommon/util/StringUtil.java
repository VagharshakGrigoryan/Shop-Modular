package am.onlinesite.onlinesitecommon.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    public String trim(String value) {
        if (value == null) {
            return null;
        }
        return value.trim();
    }

    public boolean endsLy(String value) {
        if (value == null || value.equals("")) {
            return false;
        }
        return value.endsWith("ly");
    }

}
