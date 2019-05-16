package com.example.retrofitlocalhost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {
    private Button searchall,regemp,delupdate, searchempid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        searchall = findViewById(R.id.searchall);
        regemp=findViewById(R.id.regemp);
        delupdate=findViewById(R.id.delupdate);
        searchempid=findViewById(R.id.searchempid);



        searchall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, SearchAllActivity.class);
                startActivity(intent);
            }

        });
        searchempid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, SearchEmployee.class);
                startActivity(intent);
            }
        });
        regemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, AddEmployee.class);
                startActivity(intent);
            }
        });
        delupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, UpdateEmployee.class);
                startActivity(intent);
            }
        });
    }
}
