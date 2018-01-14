package com.wix.traitsoft.tpo_mnnit;

import android.app.Dialog;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.R.layout.simple_list_item_1;
import static com.wix.traitsoft.tpo_mnnit.JsonFetch.json1;


public class Fragment_Personal extends Fragment {
    public Dialog loadingDialog;
    public String json;
    ArrayList<EditText> etList;
    ArrayList<TextView> tvList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment__personal, container, false);
        JsonFetch jsonFetch = new JsonFetch(getContext(),"https://one-eyed-rewards.000webhostapp.com/fetch_login.php");
        JsonFetch.json1="";
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

        Button pbutton=(Button)view.findViewById(R.id.personalbuttom);
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> data=new ArrayList<String>();
                for(EditText et:etList)
                {
                    data.add(et.getText().toString());
                }
               /* Log.i("data", data.get(0));
                Log.i("data", data.get(1));
                Log.i("data", data.get(2));
                Log.i("data", data.get(3));
                Log.i("data", data.get(4));*/

                Session session = new Session(getContext());
                BackgroundTask backgroundTask=new BackgroundTask(getContext());
                backgroundTask.execute("personalupload",session.getUsername(),data.get(0),data.get(1),data.get(2),data.get(3),data.get(4),
                        data.get(5),data.get(6),data.get(7),data.get(8),data.get(9),data.get(10),
                        data.get(11),data.get(12),data.get(13),data.get(14),data.get(15),data.get(16));

            }
        });

        json = jsonFetch.json1;
        JsonFetch.json1="";
        init(view);

        return view;
    }
    void init(View view)
    {
        etList=new ArrayList<EditText>();
        tvList=new ArrayList<TextView>();
        TableLayout ll = (TableLayout) view.findViewById(R.id.personaltable);
        ViewAdapter va=new ViewAdapter(getContext(),json,"personal");
        ArrayList<Information> personalldata=va.arrayList;
        for (int i = 0; i <personalldata.size(); i++) {

            TableRow row= new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView textView;
            textView = new TextView(getContext());
            EditText editText = new EditText(getContext());
            textView.setText(personalldata.get(i).getLabel().toString());
            editText.setText(personalldata.get(i).getName().toString());
            row.addView(textView);
            row.addView(editText);
            ll.addView(row,i);
            etList.add(editText);
            tvList.add(textView);
        }
    }
}
