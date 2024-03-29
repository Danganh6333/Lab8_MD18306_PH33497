package com.dangchph33497.fpoly.lab8_md18306_ph33497;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dangchph33497.fpoly.lab8_md18306_ph33497.Adapter.Adapter_Item_District_Select_GHN;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Adapter.Adapter_Item_Province_Select_GHN;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Adapter.Adapter_Item_Ward_Select_GHN;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.District;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.DistrictRequest;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.Province;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.ResponseGHN;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.Ward;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Service.GHNRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends AppCompatActivity {
    GHNRequest request;
    Spinner sp_province,sp_district,sp_ward;
    EditText edt_location;
    Button btn_next;
    //Lưu giữ liệu chọn
    private String WardCode;
    private int DistrictID;
    private int ProvinceID;
    //Adapter Spinner
    private Adapter_Item_Ward_Select_GHN adapter_item_ward_select_ghn;
    private Adapter_Item_District_Select_GHN adapter_item_district_select_ghn;
    private Adapter_Item_Province_Select_GHN adapter_item_province_select_ghn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        request = new GHNRequest();
        sp_province = findViewById(R.id.sp_province);
        sp_district = findViewById(R.id.sp_district);
        sp_ward = findViewById(R.id.sp_ward);
        edt_location = findViewById(R.id.edt_location);
        btn_next = findViewById(R.id.btn_next);
        //Goij API lấy danh sách TP/Tỉnh đầu tiên
        request.callAPI().getListProvince().enqueue(responseProvince);
        //Lắng nghe sự kiện chọn
        sp_province.setOnItemSelectedListener(onItemSelectedListener);
        sp_district.setOnItemSelectedListener(onItemSelectedListener);
        sp_ward.setOnItemSelectedListener(onItemSelectedListener);
        sp_province.setSelection(0);
    }
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()){

                case R.id.sp_province:
                    ProvinceID = ((Province) parent.getAdapter().getItem(position)).getProvinceID();
                    DistrictRequest districtRequest = new DistrictRequest(ProvinceID);
                    //Sau khi có TP/Tỉnh lấy gọi API lấy danh sách Quận/Huyện
                    request.callAPI().getListDistrict(districtRequest).enqueue(responseDistrict);
                    break;
                case R.id.sp_district:
                    DistrictID = ((District) parent.getAdapter().getItem(position)).getDistrictID();
                    //Sau khi có TP/Tỉnh lấy gọi API lấy danh sách Phường/Xã
                    request.callAPI().getListWard(DistrictID).enqueue(responseWard);
                    break;
                case R.id.sp_ward:
                    WardCode = ((Ward)parent.getAdapter().getItem(position)).getWardCode();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    //Set view lên giao diện Spinner
    private void SetDataSpinProvince(ArrayList<Province> ds){
        adapter_item_province_select_ghn = new Adapter_Item_Province_Select_GHN(this,ds);
        sp_province.setAdapter(adapter_item_province_select_ghn);
    }
    private void SetDataSpinDistrict(ArrayList<District>ds){
        adapter_item_district_select_ghn = new Adapter_Item_District_Select_GHN(ds,this);
        sp_district.setAdapter(adapter_item_district_select_ghn);
    }
    private void SetDataSpinWard(ArrayList<Ward> ds){
        adapter_item_ward_select_ghn = new Adapter_Item_Ward_Select_GHN(this,ds);
        sp_ward.setAdapter(adapter_item_ward_select_ghn);
    }
    Callback<ResponseGHN<ArrayList<Province>>> responseProvince = new Callback<ResponseGHN<ArrayList<Province>>>() {
        @Override
        public void onResponse(Call<ResponseGHN<ArrayList<Province>>> call, Response<ResponseGHN<ArrayList<Province>>> response) {
            if(response.isSuccessful()){
                if (response.body().getCode() == 200) {
                    ArrayList<Province> ds = new ArrayList<>(response.body().getData());
                    SetDataSpinProvince(ds);
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseGHN<ArrayList<Province>>> call, Throwable t) {

        }
    };
    Callback<ResponseGHN<ArrayList<District>>> responseDistrict = new Callback<ResponseGHN<ArrayList<District>>>() {
        @Override
        public void onResponse(Call<ResponseGHN<ArrayList<District>>> call, Response<ResponseGHN<ArrayList<District>>> response) {
            if(response.isSuccessful()){
                if (response.body().getCode() == 200) {
                    ArrayList<District> ds = new ArrayList<>(response.body().getData());
                    SetDataSpinDistrict(ds);
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseGHN<ArrayList<District>>> call, Throwable t) {

        }
    };
    Callback<ResponseGHN<ArrayList<Ward>>> responseWard = new Callback<ResponseGHN<ArrayList<Ward>>>() {
        @Override
        public void onResponse(Call<ResponseGHN<ArrayList<Ward>>> call, Response<ResponseGHN<ArrayList<Ward>>> response) {
            if(response.isSuccessful()){
                if (response.body().getCode() == 200) {
                    ArrayList<Ward> ds = new ArrayList<>(response.body().getData());
                    if(response.body().getData() == null) return;
                    SetDataSpinWard(ds);
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseGHN<ArrayList<Ward>>> call, Throwable t) {

        }
    };
}