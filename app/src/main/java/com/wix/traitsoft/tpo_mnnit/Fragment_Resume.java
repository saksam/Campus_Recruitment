package com.wix.traitsoft.tpo_mnnit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Fragment_Resume extends Fragment {


    Button b2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view= inflater.inflate(R.layout.fragment_fragment__resume, container, false);
        //b2=(Button)view.findViewById(R.id.button1);

        init(view);

/*        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                // Set your required file type
             */   //intent.setType("*/*");
               /* intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "DEMO"),1001);

            }


        });*/





        return view;
    }


    public void init(View view){
        TableLayout ll = (TableLayout) view.findViewById(R.id.resumetable);


        for (int i = 0; i <20; i++) {

            TableRow row= new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView textView;
            textView = new TextView(getContext());
            EditText editText = new EditText(getContext());

            textView.setText("hello"+i);
            editText.setText("10"+i);
            row.addView(textView);
            row.addView(editText);
            ll.addView(row,i);
        }
    }


    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        // super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            Uri currFileURI = data.getData();
            String path=currFileURI.getPath();
        }}


}