package com.example.sajal.onlinedb_prk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Addinfo extends Activity {
 EditText Name,Email,Mobile,Password,City;

    String name,email,mobile,password,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinfo_layout);
        Name=(EditText)findViewById(R.id.et_name);
        Email=(EditText)findViewById(R.id.et_email);
        Mobile=(EditText)findViewById(R.id.et_mob);
        City=(EditText)findViewById(R.id.et_city);
        Password=(EditText)findViewById(R.id.et_pwd);
    }

    public  void saveInfo(View view){

        name=Name.getText().toString();
        email=Email.getText().toString();
        mobile=Mobile.getText().toString();
        city=City.getText().toString();
        password=Password.getText().toString();
        String method="register";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,name,email,mobile,password,city);

        startActivity(new Intent(this,MainActivity.class));
        //finish();


    }

}
