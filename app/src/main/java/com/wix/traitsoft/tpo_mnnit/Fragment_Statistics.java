package com.wix.traitsoft.tpo_mnnit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment_Statistics extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__statistics, container, false);


        JsonFetch jsonFetch = new JsonFetch(getContext(),"https://one-eyed-rewards.000webhostapp.com/fetch_graph.php");
        JsonFetch.json1 = "";
        jsonFetch.execute();

        Thread th = new Thread(new Runnable()
        {
            String json;
            int count = 0;
            public void run()
            {

                while(true)
                {
                    json = JsonFetch.json1;
                    if(!json.equals("")) {
                        break;
                    }

                    count++;

                }
            }
        });
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String json  = JsonFetch.json1;
        Log.d("Graph",json);

        JSONObject mainObject = null;
        int cse = 0;
        int ece = 0;
        int mech = 0;
        int civil = 0;
        int chem = 0;
        int it = 0;
        int ee = 0;
        int bio = 0;

        try {
            JSONArray jsonArray = new JSONArray(json);
            //for (int j = 0; j < jsonArray.length(); j++) { // there can be more than one array in JSON Response
                JSONObject obj = jsonArray.getJSONObject(0);
            Log.d("jsonobj",obj.toString());
                cse = obj.getInt("cse");
            obj = jsonArray.getJSONObject(1);
                ece = obj.getInt("ece");
            obj = jsonArray.getJSONObject(2);
                mech = obj.getInt("mech");
            obj = jsonArray.getJSONObject(3);
                civil = obj.getInt("civil");
            obj = jsonArray.getJSONObject(6);
                chem = obj.getInt("chem");
            obj = jsonArray.getJSONObject(7);
                it = obj.getInt("it");
            obj = jsonArray.getJSONObject(4);
                ee = obj.getInt("ee");
            obj = jsonArray.getJSONObject(5);
                bio = obj.getInt("bio");
          //  }
        } catch (JSONException e) {
            e.printStackTrace();
           // Toast.makeText(c,"ABCD",Toast.LENGTH_LONG).show();
        }

        Log.d("cse",Integer.toString(cse));
        Log.d("cse",Integer.toString(ece));
        Log.d("cse",Integer.toString(civil));

        GraphView graphView = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]
                {
                        new DataPoint(0,cse),
                        new DataPoint(1,ece),
                        new DataPoint(2,mech),
                        new DataPoint(3,civil),
                        new DataPoint(4,chem),
                        new DataPoint(5,it),
                        new DataPoint(6,ee),
                        new DataPoint(7,bio)
                });

        StaticLabelsFormatter staticlabel = new StaticLabelsFormatter(graphView);
        staticlabel.setHorizontalLabels(new String[]{"cse","ece","mech","civil","chem","it","ee","bio"});
        graphView.getGridLabelRenderer().setLabelFormatter(staticlabel);
        graphView.addSeries(series);

        // Inflate the layout for this fragment
        return view;
    }


    private int getString(JSONObject obj,String str)
    {
        try {
            str =  obj.getString(str);
            Log.d("String",str);
            int a = Integer.parseInt(str);
            return a;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
