package com.example.retrofitlocalhost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import APIclient.EmployeeAPI;
import model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEmployee extends AppCompatActivity {
    private EditText etFName,etLName, etSalary, etEmpAge;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        etFName = findViewById(R.id.etfirstName);
        etLName = findViewById(R.id.etlastName);
        etSalary = findViewById(R.id.etSalary);
        etEmpAge = findViewById(R.id.etEmpAge);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register() {
        String fname=etFName.getText().toString();
        String lname=etLName.getText().toString();
        String salary = etSalary.getText().toString();
        int age= Integer.parseInt(etEmpAge.getText().toString());

        Employee employee = new Employee(fname, lname, salary, age);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Void> VoidCall = employeeAPI.registerEmployee(employee);

        VoidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEmployee.this, "You have been Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEmployee.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
