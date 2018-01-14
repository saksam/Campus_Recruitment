package com.wix.traitsoft.tpo_mnnit;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Fragment_Project extends Fragment {

    String json;
    ArrayList<EditText> etList;
    ArrayList<TextView> tvList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__project, container, false);


        JsonFetch jsonFetch = new JsonFetch(getActivity(), "https://one-eyed-rewards.000webhostapp.com/fetch_project.php");
        JsonFetch.json1="";
        jsonFetch.execute();

        Thread t = new Thread(new Runnable()
        {
            String json;
            public void run()
            {
                while(true)
                {
                    json = JsonFetch.json1;
                    if(!json.equals(""))
                        break;

                }
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        json = JsonFetch.json1;
        JsonFetch.json1="";

        Button bproject=(Button)view.findViewById(R.id.projectbuttom);
        bproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> data=new ArrayList<String>();
                for(EditText et:etList)
                {
                    data.add(et.getText().toString());
                }
                Session session = new Session(getContext());
                BackgroundTask backgroundTask=new BackgroundTask(getContext());
                backgroundTask.execute("projectupload",session.getUsername(),data.get(0),data.get(1),data.get(2),data.get(3),data.get(4));
            }
        });

        init(view);
        // Inflate the layout for this fragment
        return view;


    }

    void init(View view)
    {
        etList=new ArrayList<EditText>();
        tvList=new ArrayList<TextView>();
        TableLayout ll = (TableLayout) view.findViewById(R.id.projecttable);
        ViewAdapter va=new ViewAdapter(getContext(),json,"project");
        ArrayList<Information> projectdata=va.arrayList;
        for (int i = 0; i <projectdata.size(); i++) {

            TableRow row= new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView textView;
            textView = new TextView(getContext());
            EditText editText = new EditText(getContext());

            textView.setText(projectdata.get(i).getLabel().toString());
            editText.setText(projectdata.get(i).getName().toString());
            row.addView(textView);
            row.addView(editText);
            ll.addView(row,i);
            etList.add(editText);
            tvList.add(textView);
        }

    }

}
