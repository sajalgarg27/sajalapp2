package com.example.sajal.onlinedb_prk;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Sajal on 22-06-2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    String JSON_STRING,user_email,user_pwd;

    String add_info_url;
    String json_url;
    Context ctx;
    JSONObject JO;
    JSONArray jsonArray;
    BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {

        add_info_url = "http://robofunda.tk/add_info.php";
        json_url = "http://robofunda.tk/json_get_data.php";
    }

    @Override
    protected String doInBackground(String... args) {

        String name,email,mobile,password,place;
        String method = args[0];
        if (method.equals("register")) {
            name = args[1];
            email = args[2];
            mobile = args[3];
           place = args[5];
            password = args[4];
            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data_string = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8")+ "&" +
                        URLEncoder.encode("place", "UTF-8") + "=" + URLEncoder.encode(place, "UTF-8")+ "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Registration Success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("login")) {
            user_email = args[1];
            user_pwd = args[2];
            try {
                URL url = new URL(json_url);
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
        }

        return null;
    }




    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("Registration Success..."))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else {
            try {
            JO = new JSONObject(result);
            jsonArray =JO.getJSONArray("server_response");
            int count=0;
            boolean flag=false;

            while(count< jsonArray.length()) {
                String email, password;

                JSONObject jsonObject = jsonArray.getJSONObject(count);
                email = jsonObject.getString("email");
                password = jsonObject.getString("password");
                if (user_email.equals(email) && user_pwd.equals(password)) {
                    flag=true;
                    break;
                }

                count++;

            }
            if(flag==true) {

                ctx.startActivity(new Intent(ctx,Tab_Activity.class));
                Toast.makeText(ctx, "Login", Toast.LENGTH_LONG).show();


            }
            else
            {
                Toast.makeText(ctx, "Login Failed", Toast.LENGTH_LONG).show();
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


    }


    }


