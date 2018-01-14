package com.wix.traitsoft.tpo_mnnit;

/**
 * Created by Asus on 05-09-2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Tab_home extends Fragment {

    String name,branch,email;
    String jsonn;
    TextView homename,homeemail,homebranch;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home_home, container, false);
        homename=(TextView)view.findViewById(R.id.name);
        homeemail=(TextView)view.findViewById(R.id.email);
        homebranch=(TextView)view.findViewById(R.id.branch);
        getJsonn();
        return view;
    }
    public void getJsonn()
    {
        JsonFetch jsonFetch = new JsonFetch(getContext(),"https://one-eyed-rewards.000webhostapp.com/fetch_login.php");
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
        jsonn  = JsonFetch.json1;

        JSONObject mainObject = null;

        try {
            JSONArray jsonArray = new JSONArray(jsonn);
            for (int j = 0; j < jsonArray.length(); j++) { // there can be more than one array in JSON Response
                JSONObject obj = jsonArray.getJSONObject(j);
                name = obj.getString("name");
                branch = obj.getString("branch");
                email = obj.getString("email");
            }
            homename.setText(name);
            homebranch.setText(branch);
            homeemail.setText(email);
        } catch (JSONException e) {
            e.printStackTrace();
            // Toast.makeText(c,"ABCD",Toast.LENGTH_LONG).show();
        }
    }
    private int getString(JSONObject obj,String str)
    {
        try {
            str =  obj.getString(str);
            int a = Integer.parseInt(str);
            return a;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}