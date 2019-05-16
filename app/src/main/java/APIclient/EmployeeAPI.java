package APIclient;

import java.util.List;

import model.Employee;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>>getAllEmployee();

    @GET("employeesid/{empID}")
    Call<Employee>getEmployeeByID(@Path("empID") int empId);

    @POST("employees")
    Call<Void> registerEmployee(@Body Employee emp);

    @PUT("employees/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empId,@Body Employee emp);

    @DELETE("employees/{empID}")
    Call<Void> deleteEmployee(@Path("empID")int empId);
}
