package com.example.sajal.onlinedb_prk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

Button B1,B2;
    TextView textView,log_Email,log_Password;
    String log_email,log_password;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        B1=(Button)findViewById(R.id.b1);
        B2=(Button)findViewById(R.id.b2);
        textView=(TextView)findViewById(R.id.textview);
        log_Email=(TextView)findViewById(R.id.login_email);
        log_Password=(TextView)findViewById(R.id.login_pwd);

        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            textView.setVisibility(View.INVISIBLE);
        }
        else
        {
            B1.setEnabled(false);
            B2.setEnabled(false);
        }
    }

    public void addcontact(View view)
    {
        startActivity(new Intent(this,Addinfo.class));
    }

    public void viewcontact(View view)
    {
        log_password=log_Password.getText().toString();
        log_email=log_Email.getText().toString();
        progressDialog=new ProgressDialog(this);
        progressDialog.show();
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,log_email,log_password);
        //startActivity(new Intent(this,Tab_Activity.class));


    }
}
