package com.example.retrofitlocalhost;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class UpdateEmployee extends AppCompatActivity {
    private Button btnSearch, btnUpdate, btnDelete;
    private EditText etEmpNo, etEmpFName,etEmpLName, etEmpSalary, etEmpAge;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        btnSearch = findViewById(R.id.btnSearchEmp);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        etEmpFName = findViewById(R.id.etEmpFName);
        etEmpLName = findViewById(R.id.etEmpLName);
        etEmpSalary = findViewById(R.id.etEmpSalary);
        etEmpAge = findViewById(R.id.EmpAge);
        etEmpNo = findViewById(R.id.etEmpNo);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }

        });

    }



    private void CreateInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        employeeAPI = retrofit.create(EmployeeAPI.class);
    }

    private void loadData() {

        CreateInstance();
        Call<Employee> listCall = employeeAPI.getEmployeeByID(
                Integer.parseInt(etEmpNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etEmpFName.setText(response.body().getFirstname());
                etEmpLName.setText(response.body().getLastname());
                etEmpSalary.setText(response.body().getSalary());
                etEmpAge.setText(Integer.toString(response.body().getAge()));

            }
            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateEmployee.this, "Error"+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateEmployee() {
        CreateInstance();
        Employee employee = new Employee(
                etEmpFName.getText().toString(),
                etEmpLName.getText().toString(),
                etEmpSalary.getText().toString(),
                Integer.parseInt(etEmpAge.getText().toString()));
        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etEmpNo.getText().toString()), employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateEmployee.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateEmployee.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void deleteEmployee() {
        CreateInstance();
        final Call<Void> voidcall = employeeAPI.deleteEmployee(Integer.parseInt(etEmpNo.getText().toString()));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                voidcall.enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(UpdateEmployee.this, "Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateEmployee.this,UpdateEmployee.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(UpdateEmployee.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }

}

