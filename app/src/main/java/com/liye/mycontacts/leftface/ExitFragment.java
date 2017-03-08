package com.liye.mycontacts.leftface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liye.mycontacts.R;

public class ExitFragment extends Fragment implements View.OnClickListener {
    View view;
    TextView mBye;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exit, container, false);
        mBye = (TextView) view.findViewById(R.id.bye);
        mBye.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bye:
                getActivity().finish();
                break;
        }
    }
}

