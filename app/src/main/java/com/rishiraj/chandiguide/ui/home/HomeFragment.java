package com.rishiraj.chandiguide.ui.home;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rishiraj.chandiguide.DatabaseConn;
import com.rishiraj.chandiguide.R;
import com.rishiraj.chandiguide.RecyclerRowAdapter;
import com.rishiraj.chandiguide.RowModel;
import com.rishiraj.chandiguide.model;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    RecyclerView recyclerView;

    ArrayList<model> arrQues = new ArrayList<>();

    ArrayList<RowModel> arr = new ArrayList<>();

    int i;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("City Guide");
        //Code to be written between this scope-----------------------------------------------------

        recyclerView = root.findViewById(R.id.recyclerView);

        DatabaseConn databaseConn = DatabaseConn.getDB(requireActivity().getApplicationContext());

        if (!databaseConn.checkDB()) {
            //for create
            databaseConn.createDB(requireActivity().getApplicationContext());
        }

        // for open
        databaseConn.openDB();

        //query execute
        arrQues = databaseConn.getQues();

        loadQues();

        //------------------------------------------------------------------------------------------
        return root;

    }

    private void loadQues() {


        for (i = 0; i < arrQues.size(); i++) {
            addData("" + arrQues.get(i).title);
        }


        //Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));

        RecyclerRowAdapter adapter = new RecyclerRowAdapter(requireActivity().getApplicationContext(), arr);
        recyclerView.setAdapter(adapter);

    }


    public void addData(String title) {

        RowModel rowModel = new RowModel();

        rowModel.title = title;

        arr.add(rowModel);

    }
}