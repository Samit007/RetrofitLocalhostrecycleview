package com.example.retrofitlocalhost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import APIclient.EmployeeAPI;
import model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployee extends AppCompatActivity {
    private EditText empid;
    private Button btnSearch;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_byid);

        empid=findViewById(R.id.empid);
        btnSearch=findViewById(R.id.btnSearch);
        tvData=findViewById(R.id.tvData);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();

            }
        });
    }


    private void loadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4000/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Employee> listCall = employeeAPI.getEmployeeByID(
                Integer.parseInt(empid.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchEmployee.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                String content = "";

                content += "ID : " + response.body().getId() + "\n";
                content += "FName : " + response.body().getFirstname() + "\n";
                content += "LName : " + response.body().getLastname() + "\n";
                content += "Salary : " + response.body().getSalary() + "\n";
                content += "Age : " + response.body().getAge() + "\n";

                tvData.setText(content);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchEmployee.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
