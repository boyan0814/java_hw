package com.example.phone_book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class Detail extends Fragment {
    TextView tvname,tvphone;
    Button dt_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail,container,false);

        tvname = view.findViewById(R.id.detail_tv_name);
        tvphone = view.findViewById(R.id.detail_tv_phone);
        dt_btn = view.findViewById(R.id.det_btn_return);

        tvname.setText(getArguments().getString("name"));
        tvphone.setText(getArguments().getString("phone"));
        dt_btn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.nav_scan);
        });
        return view;
    }

}
