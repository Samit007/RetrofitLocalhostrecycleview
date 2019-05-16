package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofitlocalhost.R;

import java.util.List;

import model.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    Context mContext;
    List<Employee> employeeList;

    public EmployeeAdapter(Context mContext, List<Employee> employeeList){
        this.mContext=mContext;
        this.employeeList=employeeList;
    }



    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee,viewGroup,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, final int i) {
        final Employee employee = employeeList.get(i);
        employeeViewHolder.tvfName.setText(employee.getFirstname());
        employeeViewHolder.tvlName.setText(employee.getLastname());
        employeeViewHolder.tvSalary.setText(employee.getSalary());
        employeeViewHolder.tvAge.setText(employee.getAge());
        employeeViewHolder.tvImage.setText(employee.getProfile_image());
    }
    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder{
        TextView tvfName,tvlName, tvSalary, tvAge, tvImage;
        public EmployeeViewHolder(View employeeView){
            super(employeeView);
            tvfName=employeeView.findViewById(R.id.efname);
            tvlName=employeeView.findViewById(R.id.elname);
            tvSalary=employeeView.findViewById(R.id.esal);
            tvAge=employeeView.findViewById(R.id.eage);
            tvImage=employeeView.findViewById(R.id.eimg);

        }
    }
}

