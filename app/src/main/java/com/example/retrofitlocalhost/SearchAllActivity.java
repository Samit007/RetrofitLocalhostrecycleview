package com.example.retrofitlocalhost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import APIclient.EmployeeAPI;
import model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchAllActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Employee> listEmployee;
    private TextView tvdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all);
        recyclerView=findViewById(R.id.recyclerView);
        tvdata=findViewById(R.id.tvdata);


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> call = employeeAPI.getAllEmployee();

        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> employeeList = response.body();
                for (Employee employee:employeeList){
                    String content = "";
                    content += "ID : " +employee.getId() + "\n";
                    content += "FName : " +employee.getFirstname() + "\n";
                    content += "LName : " +employee.getLastname() + "\n";
                    content += "Salary : " +employee.getSalary() + "\n";
                    content += "Age : " +employee.getAge() + "\n";
                    content += "Image : " +employee.getProfile_image() + "\n";
                    content += "-------------------------------"+"\n";
                    tvdata.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(SearchAllActivity.this, "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
