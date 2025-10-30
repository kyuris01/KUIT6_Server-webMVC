package jwp.util;

import jwp.model.User;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static boolean isLogined(HttpSession session) {
        System.out.println(session);
        Object value = session.getAttribute("user");
        User user = (User) value;
        if(user != null) return true;
        return false;
    }
}
