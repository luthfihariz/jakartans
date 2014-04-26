package com.luthfihariz.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Api {

	private static String DOMAIN = "http://172.16.6.15";
	private static String HOST_NAME = DOMAIN + "/hackjack";
	private static String HOST_NAME_API = HOST_NAME + "/api";
	private static String USERS_API = HOST_NAME_API + "/users";
	private static String REGISTER_API = USERS_API + "/register";
	private static String LOGIN_API = USERS_API + "/login";
	private static String SEARCH_API = HOST_NAME_API + "/search/rate";
	private static String STATUS_API = HOST_NAME_API + "/status/new";

	public static JSONObject searchTrayek(String from, String to) throws IOException {
		return Api.getHttp(SEARCH_API + "/" + from.replace(" ", "%20") + "/" + to.replace(" ", "%20"), null);
	}

	public static JSONObject updateStatus(int userId, String trayekId, String status, String trafficRate,
			String busRate, String busAvailRate, double latitude, double longitude) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("user_id", String.valueOf(userId));
		params.put("trayek_id", String.valueOf(trayekId));
		params.put("status", status);
		params.put("traffic_rate", String.valueOf(trafficRate));
		params.put("bus_rate", String.valueOf(busRate));
		params.put("bus_avail_rate", String.valueOf(busAvailRate));
		params.put("latitude", String.valueOf(latitude));
		params.put("longitude", String.valueOf(longitude));
		return Api.postHttp(STATUS_API, params);
	}

	public static JSONObject getUserProfile(int userId) throws IOException {
		return Api.getHttp(USERS_API + "/" + userId, null);
	}

	public static JSONObject loginWithFacebook(String username, String email, String birthdate, String city,
			String country, String gender, String android_os, String photo_uri) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("email", email);
		params.put("android_os", android_os);
		params.put("photo_uri", photo_uri);
		return Api.postHttp(REGISTER_API, params);
	}

	private static String getQueryString(Map<String, String> params) {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<Entry<String, String>> iterator = params.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<String, String> param = iterator.next();
			stringBuilder.append(param.getKey()).append('=').append(param.getValue());
			if (iterator.hasNext()) {
				stringBuilder.append('&');
			}
		}
		return stringBuilder.toString();
	}

	public static JSONObject getHttp(String urlString, String queryString) throws IOException {

		// always close connection
		System.setProperty("http.keepAlive", "false");// to fix EOFException

		URL url = null;
		HttpURLConnection conn = null;
		JSONObject jsonObject = null;

		try {

			if (queryString != null) {
				url = new URL(urlString + "?" + queryString);
			} else {
				url = new URL(urlString);
			}

			Helper.log("get url: " + url);

		} catch (MalformedURLException e) {
			Helper.log("invalid url " + e.getLocalizedMessage());
		}

		try {

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

			// handle the response
			int status = conn.getResponseCode();

			if (status == 200)// status OK
			{
				InputStream is = conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
				String line = null;
				StringBuilder sb = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();

				String json = sb.toString();

				Helper.log("json response: " + json);

				jsonObject = new JSONObject(json);

			} else {
				InputStream es = conn.getErrorStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(es));
				String line = null;

				while ((line = reader.readLine()) != null) {
					Helper.log("error: " + line);
				}
				es.close();

				throw new IOException("[Server Util] Request failed with error code: " + status);
			}

		} catch (JSONException e) {
			Helper.log("JSON Exception: " + e.getLocalizedMessage());
		} finally {
			conn.disconnect();
			Helper.log("connection disconnect");
		}

		return jsonObject;
	}

	public static JSONArray getHttpJsonArray(String urlString, String queryString) throws IOException {

		// always close connection
		System.setProperty("http.keepAlive", "false");// to fix EOFException

		URL url = null;
		HttpURLConnection conn = null;
		JSONArray jsonArr = null;

		try {

			if (queryString != null) {
				url = new URL(urlString + "?" + queryString);
			} else {
				url = new URL(urlString);
			}

			Helper.log("get url: " + url);

		} catch (MalformedURLException e) {
			Helper.log("invalid url " + e.getLocalizedMessage());
		}

		try {

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

			// handle the response
			int status = conn.getResponseCode();

			if (status == 200)// status OK
			{
				InputStream is = conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
				String line = null;
				StringBuilder sb = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();

				String json = sb.toString();

				Helper.log("json response: " + json);

				jsonArr = new JSONArray(json);

			} else {
				InputStream es = conn.getErrorStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(es));
				String line = null;

				while ((line = reader.readLine()) != null) {
					Helper.log("error: " + line);
				}
				es.close();

				throw new IOException("[Server Util] Request failed with error code: " + status);
			}

		} catch (JSONException e) {
			Helper.log("JSON Exception: " + e.getLocalizedMessage());
		} finally {
			conn.disconnect();
			Helper.log("connection disconnect");
		}

		return jsonArr;
	}

	public static JSONObject postHttp(String urlString, Map<String, String> params) throws IOException {

		// always close connection
		System.setProperty("http.keepAlive", "false");// to fix EOFException

		URL url = null;
		HttpURLConnection conn = null;
		JSONObject jsonObject = null;

		// data to be sent
		String paramBody = getQueryString(params);
		byte[] paramBytes = paramBody.getBytes();

		Helper.log("body to send: " + paramBody);

		try {
			// Get URL object
			url = new URL(urlString);

			Helper.log("post url: " + url);

		} catch (MalformedURLException e) {
			// TODO: handle exception
			Helper.log("invalid url " + e.getLocalizedMessage());
		}

		try {

			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setFixedLengthStreamingMode(paramBytes.length);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			conn.setRequestProperty("Connection", "close");// to prevent
															// EOFException

			// post the request
			OutputStream out = conn.getOutputStream();
			out.write(paramBytes);
			out.flush();
			out.close();

			// handle the response
			int status = conn.getResponseCode();

			if (status == 200)// status OK
			{
				InputStream is = conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
				String line = null;
				StringBuilder sb = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();

				String json = sb.toString();

				Helper.log("json response: " + json);

				// convert String to JSON object
				jsonObject = new JSONObject(json);
			} else {
				InputStream es = conn.getErrorStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(es));
				String line = null;

				while ((line = reader.readLine()) != null) {
					Helper.log("error: " + line);
				}
				es.close();

				throw new IOException("[Server Util] Request failed with error code: " + status);
			}

		} catch (JSONException e) {
			Helper.log("JSON Exception: " + e.getLocalizedMessage());
		} catch (Exception e) {
			Helper.log("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			conn.disconnect();
			Helper.log("connection disconnect");
		}

		return jsonObject;
	}

}
