package com.ndv.eqa.earthquakeapplication;

import android.app.Fragment;
import android.app.ListFragment;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by admin on 11/16/2017.
 */

public class EarthquakeListFragment extends ListFragment {
//    ArrayAdapter<Quake> quakeArrayAdapter;
//    ArrayList<Quake> earthquakes = new ArrayList<Quake>();
//    private static final String TAG = "EARTHQUAKE";
//    private Handler handler = new Handler();

    ArrayList<Quake> quakeArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, container, false);

        String quakeFeed = getString(R.string.quake_feed);
        getJsonEarthquakes(quakeFeed);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, quakeArrayList);
        setListAdapter(adapter);

        return view;
    }

    private void getJsonEarthquakes(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonItems = response.getJSONArray("features");
                    String details, link;
                    Date date;
                    Location location = null;
                    double magnitude;

                    for (int i=0 ; i < jsonItems.length();i++) {
                        JSONObject jsonItem = jsonItems.getJSONObject(i);
                        JSONObject jsonSnippet = jsonItem.getJSONObject("properties");
                        details = jsonSnippet.getString("detail");
                        link = jsonSnippet.getString("url");
                        magnitude = jsonSnippet.getDouble("mag");
                        int dt = jsonSnippet.getInt("time");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd’T’hh:mm:ss’Z’");
                        date = new GregorianCalendar(0,0,0).getTime();
                        try {
                            date = sdf.parse(String.valueOf(dt));
                        } catch (ParseException e) {
                            Log.d(TAG, "Date parsing exception.", e);
                        }
                        JSONObject jsonGeometry = jsonItem.getJSONObject("geometry");
                        JSONArray jsonLocations = jsonGeometry.getJSONArray("coordinates");
                        location.setLatitude(jsonLocations.getDouble(0));
                        location.setLongitude(jsonLocations.getDouble(1));

                        quakeArrayList.add(new Quake(date, details, location, magnitude, link));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error !", Toast.LENGTH_SHORT).show();
                    }
                });
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        int layoutID = android.R.layout.simple_expandable_list_item_1;
//        quakeArrayAdapter = new ArrayAdapter<Quake>(getActivity(), layoutID, earthquakes);
//        setListAdapter(quakeArrayAdapter);
//
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                refreshEarthquakes();
//            }
//        });
//        t.start();
//    }

//    public void refreshEarthquakes() {
//        // Get the XML
//        URL url;
//        try {
//            String quakeFeed = getString(R.string.quake_feed);
//            url = new URL(quakeFeed);
//            URLConnection connection;
//            connection = url.openConnection();
//            HttpURLConnection httpConnection = (HttpURLConnection)connection;
//            int responseCode = httpConnection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                InputStream in = httpConnection.getInputStream();
//                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//                DocumentBuilder db = dbf.newDocumentBuilder();
//                // Parse the earthquake feed.
//                Document dom = db.parse(in);
//                Element docEle = dom.getDocumentElement();
//                // Clear the old earthquakes
//                earthquakes.clear();
//                // Get a list of each earthquake entry.
//                NodeList nl = docEle.getElementsByTagName("entry");
//                if (nl != null && nl.getLength() > 0) {
//                    for (int i = 0 ; i < nl.getLength(); i++) {
//                        Element entry = (Element)nl.item(i);
//                        Element title = (Element)entry.getElementsByTagName("title").item(0);
//                        Element g = (Element)entry.getElementsByTagName("georss:point").item(0);
//                        Element when = (Element)entry.getElementsByTagName("updated").item(0);
//                        Element link = (Element)entry.getElementsByTagName("link").item(0);
//                        String details = title.getFirstChild().getNodeValue();
//                        String hostname = "http://earthquake.usgs.gov";
//                        String linkString = hostname + link.getAttribute("href");
//                        String point = g.getFirstChild().getNodeValue();
//                        String dt = when.getFirstChild().getNodeValue();
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd’T’hh:mm:ss’Z’");
//                        Date qdate = new GregorianCalendar(0,0,0).getTime();
//                        try {
//                            qdate = sdf.parse(dt);
//                        } catch (ParseException e) {
//                            Log.d(TAG, "Date parsing exception.", e);
//                        }
//                        String[] location = point.split(" ");
//                        Location l = new Location("dummyGPS");
//                        l.setLatitude(Double.parseDouble(location[0]));
//                        l.setLongitude(Double.parseDouble(location[1]));
//                        String magnitudeString = details.split(" ")[1];
//                        int end = magnitudeString.length()-1;
//                        double magnitude = Double.parseDouble(magnitudeString.substring(0, end));
//                        details = details.split(",")[1].trim();
//                        final Quake quake = new Quake(qdate, details, l, magnitude, linkString);
//                        // Process a newly found earthquake
//                        handler.post(new Runnable() {
//                            public void run() {addNewQuake(quake);
//                            }
//                        });
//                    }
//                }
//            }
//        } catch (MalformedURLException e) {
//            Log.d(TAG, "MalformedURLException");
//        } catch (IOException e) {
//            Log.d(TAG, "IOException");
//        } catch (ParserConfigurationException e) {
//            Log.d(TAG, "Parser Configuration Exception");
//        } catch (SAXException e) {
//            Log.d(TAG, "SAX Exception");
//        }
//        finally {
//        }
//    }
//    private void addNewQuake(Quake quake) {
//        earthquakes.add(quake);
//
//        quakeArrayAdapter.notifyDataSetChanged();
//    }
}
