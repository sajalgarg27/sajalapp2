package com.example.sajal.onlinedb_prk;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sajal on 28-06-2017.
 */

public class DataFragment extends Fragment {

    String [] places={"delhi","mumbai","chennai"};
    String baseurl="http://robofunda.tk/searchdata_";
    int position;







public String ngo_name, ngo_email, ngo_mobile, ngo_city;

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;

ProgressDialog progressDialog;

    public DataFragment(int pos) {
        // Required empty public constructor
        position=pos;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_datafragment,container,false);


        BackTask backTask = new BackTask();
        backTask.execute();
        recyclerView=(RecyclerView)rootview.findViewById(R.id.recylcler_view);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.show();



        return rootview;
    }



    public class BackTask extends AsyncTask<String,String,String> {
        String JSON_STRING, user_email, user_pwd;
        String city_url;
        Context ctx;
        JSONObject JO;
        JSONArray jsonArray;

        @Override
        protected void onPreExecute() {
            city_url=baseurl+places[position]+".php";
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(city_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();

            try {
                JO = new JSONObject(result);
                jsonArray =JO.getJSONArray("server_response");
                recyclerViewAdapter=new RecyclerViewAdapter(ctx,jsonArray);
                recyclerView.setAdapter(recyclerViewAdapter);



            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }


        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

}
