package ifi.librarian.login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

//Save session login onto COOKIE! Important
import ifi.librarian.login.model.LoginedUser;

public class AppUtils {
	private static int REDIRECT_ID = 0;

	private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
	private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();

	//Save infor to Session
	public void storeLoginedUser(HttpSession session, LoginedUser loginedUser) {
		session.setAttribute("loginedUser", loginedUser);//  ${loginedUser}
	}

	//Infor->>Session
	public LoginedUser getLoginedUser(HttpSession session) {
		LoginedUser loginedUser = (LoginedUser) session.getAttribute("loginedUser");
		return loginedUser;
	}

	//Change Value Redirect to Access
	public int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
		Integer id = uri_id_map.get(requestUri);
		if (id == null) {
			id = REDIRECT_ID++;
			uri_id_map.put(requestUri, id);
			id_uri_map.put(id, requestUri);
			return id;
		}
		return id;
	}

	//Take Account to MAP(System) to USE
	public String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
		String url = id_uri_map.get(redirectId);
		if (url != null) {
			return url;
		}
		return null;
	}
}
