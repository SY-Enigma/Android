package com.example.homework2application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.homework2application.databinding.FragmentVideoChildBinding;


public class VideoChildFragment extends Fragment {
    private  static  final  String TAG = "VideoChildFragment";
    private static final String ARGUMENT_POSITION = "argument_position";
    private FragmentVideoChildBinding binding;

    public VideoChildFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static VideoChildFragment newInstance(int position) {
        VideoChildFragment fragment = new VideoChildFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_POSITION,position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVideoChildBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null){
            int position = getArguments().getInt(ARGUMENT_POSITION);

            Log.e(TAG, "onViewCreated: "+position);
            binding.tvDashboard.setText(position == 0?R.string.do_school : R.string.do_school2);
        }

    }
}