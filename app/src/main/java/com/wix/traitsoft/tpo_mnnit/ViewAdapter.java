package com.wix.traitsoft.tpo_mnnit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shiva on 29-09-2017.
 */

public class ViewAdapter extends BaseAdapter {

    String lab;
    Context c;
    String json;

    ArrayList<Profile>profiles;
    ArrayList<Project>projects;
    ArrayList<AcademicProfile> academicProfiles;
    ArrayList<Information>arrayList;

    ViewAdapter(Context c,String json,String label)
    {
        lab = label;
        this.c = c;
        this.json = json;
        arrayList = new ArrayList<Information>();
        Labels l =new Labels();
        String [] labels=l.getLabels(label);

        switch(label)
        {
            case "personal":
                profiles = new ArrayList<Profile>();


                break;
            case "academic":
                academicProfiles=new ArrayList<AcademicProfile>();

                break;


            case "project":
                projects=new ArrayList<Project>();

                break;
            default:
                break;

        }

        GetArray getArray= new GetArray();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int j = 0; j < jsonArray.length(); j++) { // there can be more than one array in JSON Response
                JSONObject obj = jsonArray.getJSONObject(j);

                if (label.equals("personal")) {
                    profiles.add(l.getpersonalProfile(obj));
                }
                if(label.equals("project")){
                    projects.add(l.getProject(obj));
                }
                if(label.equals("academic")){
                    academicProfiles.add(l.getacademicProfile(obj));
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(c,"ABCD",Toast.LENGTH_LONG).show();
        }


        if (label.equals("personal")){
            Profile pr = profiles.get(0);  //
            arrayList=getArray.getArray(labels,pr);
        }
        if (label.equals("project")){
            Project p = projects.get(0);
            arrayList=getArray.getArray(labels,p);
        }
        if (label.equals("academic")){
            AcademicProfile ap = academicProfiles.get(0);
            arrayList=getArray.getArray(labels,ap);
        }

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater)c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        View row = null;
        TextView label=null;
        EditText name=null;
        row = layoutInflater.inflate(R.layout.list_view, viewGroup, false);
        label =(TextView)row.findViewById(R.id.label);
        name = (EditText)row.findViewById(R.id.name);
        Information inf = arrayList.get(i);
        label.setText(inf.getLabel());
        name.setText(inf.getName());
        return row;
    }
}
