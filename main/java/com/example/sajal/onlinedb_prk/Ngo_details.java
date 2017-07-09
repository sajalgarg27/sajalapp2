package com.example.sajal.onlinedb_prk;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.sajal.onlinedb_prk.R.id.ngo_contact;
import static com.example.sajal.onlinedb_prk.R.id.ngo_name;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ngo_details extends Fragment {
    TextView ngo_email_tv;
TextView ngo_name_tv;
    TextView ngo_phone_tv;
    String ngo_name_disp,ngo_email_disp,ngo_phone_disp;
    public Ngo_details() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ngo_details, container, false);
        ngo_email_tv=(TextView) view.findViewById(R.id.ngo_email);
        ngo_name_tv=(TextView) view.findViewById(ngo_name);
        ngo_phone_tv=(TextView) view.findViewById(ngo_contact);
        Bundle bundle = new Bundle();
        ngo_name_disp = getArguments().getString("Your_Key");
        ngo_email_disp = getArguments().getString("Your_Key1");
        ngo_phone_disp = getArguments().getString("Your_Key2");
        //Toast.makeText(view.getContext(), Item, Toast.LENGTH_LONG).show();
        ngo_name_tv.setText(ngo_name_disp);
        ngo_email_tv.setText(ngo_email_disp);
        ngo_phone_tv.setText(ngo_phone_disp);
        return view;
    }

}
