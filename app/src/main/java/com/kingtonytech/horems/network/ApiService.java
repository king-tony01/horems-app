package com.kingtonytech.horems.network;

import com.kingtonytech.horems.models.BaseResponse;
import com.kingtonytech.horems.models.Patient;
import com.kingtonytech.horems.models.PatientListResponse;
import com.kingtonytech.horems.models.Resource;
import com.kingtonytech.horems.models.ResourceListResponse;
import com.kingtonytech.horems.models.ResourceResponse;
import com.kingtonytech.horems.models.Staff;
import com.kingtonytech.horems.models.StaffListResponse;
import com.kingtonytech.horems.models.StaffResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    // 🔐 Authentication
    @POST("auth/login")
    @Headers("Content-Type: application/json")
    Call<BaseResponse> login(@Body Map<String, String> credentials);

    @POST("auth/logout")
    Call<BaseResponse> logout();

    // 👨‍⚕️ Patients
    @GET("patients")
    Call<PatientListResponse> getPatients();

    @POST("patients")
    @Headers("Content-Type: application/json")
    Call<Patient> createPatient(@Body Patient patient);

    // 🧑‍⚕️ Staff
    @GET("staff")
    Call<StaffListResponse> getStaff();

    @POST("staff")
    @Headers("Content-Type: application/json")
    Call<StaffResponse> createStaff(@Body Staff staff);

    // 🏥 Resources
    @GET("resources")
    Call<ResourceListResponse> getResources();

    @POST("resources")
    @Headers("Content-Type: application/json")
    Call<ResourceResponse> createResource(@Body Resource resource);
}