package com.wix.traitsoft.tpo_mnnit;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.BaseKeyListener;
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


public class Fragment_Academics extends Fragment {

    ArrayList<EditText>etList;
    ArrayList<TextView>tvList;
    String json;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__academics, container, false);


        JsonFetch jsonFetch = new JsonFetch(getActivity(), "https://one-eyed-rewards.000webhostapp.com/fetch_academic.php");
        JsonFetch.json1="";
        jsonFetch.execute();


        Thread th = new Thread(new Runnable()
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

        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        json = JsonFetch.json1;
        JsonFetch.json1="";
        Log.i("json",json);
        Button abuttton=(Button)view.findViewById(R.id.acadbuttom);
        abuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String>data=new ArrayList<String>();
                for(EditText et:etList)
                {
                    data.add(et.getText().toString());
                }
                Session session = new Session(getContext());
                BackgroundTask backgroundTask=new BackgroundTask(getContext());
                backgroundTask.execute("academicupload",session.getUsername(),data.get(0),data.get(1),data.get(2),data.get(3),data.get(4),
                        data.get(5),data.get(6),data.get(7),data.get(8),data.get(9),data.get(10),data.get(11),data.get(12),
                        data.get(13),data.get(14),data.get(15));


            }
        });

        init(view);

        return view;
    }

    void init(View view)
    {
        etList=new ArrayList<EditText>();
        tvList=new ArrayList<TextView>();
        TableLayout ll = (TableLayout) view.findViewById(R.id.acadtable);
        ViewAdapter va=new ViewAdapter(getContext(),json,"academic");

        ArrayList<Information> academicdata=va.arrayList;
        for (int i = 0; i <academicdata.size(); i++) {

            TableRow row= new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView textView;
            textView = new TextView(getContext());
            EditText editText = new EditText(getContext());
            //   Log.i("label","hell");
            //  Log.i("label",personalldata.get(i).getLabel().toString());
            //  Log.i("name",personalldata.get(i).getName().toString());
            textView.setText(academicdata.get(i).getLabel().toString());
            editText.setText(academicdata.get(i).getName().toString());
            row.addView(textView);
            row.addView(editText);
            ll.addView(row,i);
            etList.add(editText);
            tvList.add(textView);
        }

    }

}