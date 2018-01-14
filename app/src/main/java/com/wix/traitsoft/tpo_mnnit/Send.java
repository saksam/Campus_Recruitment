package com.wix.traitsoft.tpo_mnnit;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import static android.R.attr.data;

/**
 * Created by saksham_ on 30-Dec-17.
 */

public class Send extends AsyncTask<String, Void, String> {

    HttpURLConnection httpURLConnection;
    private String php_url;
    private Context context;
    private Dialog loadingDialog;
    private String status = "";
    private String username = "";
    private String password = "";

    Send(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loadingDialog = ProgressDialog.show(context, "Please wait", "Loading...");

    }

    @Override
    protected String doInBackground(String... args) {

        URL url;
        status = args[0];

        if (status.equals("signup")) {

            php_url=args[3];
            username = args[1];
            password = args[2];

            try {

                url = new URL(php_url);

                httpURLConnection = Httpurl.getConnection(url);

                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                String response = oneResponse(data, httpURLConnection);

                if (response.equals("success"))
                    return "Success";
                else
                    return "User Already Exists";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (status.equals("signin")) {

            php_url=args[3];
            username = args[1];
            password = args[2];

            try {

                url = new URL(php_url);

                httpURLConnection = Httpurl.getConnection(url);

                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                String response = oneResponse(data, httpURLConnection);

                if (response.equals("success"))
                    return "success";
                else
                    return "Invalid Credentials";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private String oneResponse(String one, HttpURLConnection httpURLConnection) {

        String response = null;
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(one);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            response = br.readLine();
            is.close();
            httpURLConnection.disconnect();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        loadingDialog.dismiss();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        if (status.equals("signin")) {
            if (result.equals("success")) {
                Session session = new Session(context);
                session.store(username, password);
                Intent i = new Intent(context, Home.class);

                i.putExtra("username", username);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
        }
    }
}
