package com.wix.traitsoft.tpo_mnnit;

/**
 * Created by Asus on 05-09-2017.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class Tab_open extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_opening, container, false);

        init(view);

        return view;
    }

    public void init(View view) {
        TableLayout tl =(TableLayout) view.findViewById(R.id.table_view);

        //TableRow tableRow=(TableRow) view.findViewById(R.id.table_raw);
        TextView tv1= (TextView)view.findViewById(R.id.name_company);
        TextView tv2= (TextView) view.findViewById(R.id.cpi);
        TextView tv3= (TextView) view.findViewById(R.id.date);
        TextView tv4= (TextView) view.findViewById(R.id.branch);
        Button bt = (Button) view.findViewById(R.id.register_company);
        int i=0;
        for(i=0;i<10;i++ ){

            TableRow row= new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            tv1=new TextView(getContext());
            tv2=new TextView(getContext());
            tv3=new TextView(getContext());
            tv4=new TextView(getContext());
            bt=new Button(getContext());



            tv1.setText("Google");

            tv2.setText("7.5 cpi");
            tv3.setText("close date:15/10/2017");
            tv4.setText("Branch : ECE");
            bt.setText("Register");
            final Button finalBt = bt;
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TextView textView=(TextView)view.findViewById(R.id.name_company);
                    //String name=tv1.getText().toString();
                    // for registering;
                    // Toast.makeText(getContext(),"Registered"+name,Toast.LENGTH_LONG).show();
                    finalBt.setText("Registered");
                }
            });

            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);
            row.addView(tv4);
            row.addView(bt);

            tl.addView(row,i);

        }

    }
}
