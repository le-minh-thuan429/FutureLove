package com.example.futurelove.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.futurelove.databinding.FragmentHomeBinding;
import com.example.futurelove.model.EventHomeDto;
import com.example.futurelove.service.api.ApiService;
import com.example.futurelove.service.api.RetrofitClient;
import com.example.futurelove.service.api.Server;

import com.example.futurelove.util.PaginationScrollListener;
import com.example.futurelove.view.activity.MainActivity;
import com.example.futurelove.view.adapter.EventHomeAdapter;

import java.util.ArrayList;
import java.util.List;

import io.github.rupinderjeet.kprogresshud.KProgressHUD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private KProgressHUD kProgressHUD;
    private FragmentHomeBinding fragmentHomeBinding;
    private MainActivity mainActivity;
    private EventHomeAdapter eventHomeAdapter;
    private List<List<EventHomeDto>> eventList;
    private LinearLayoutManager linearLayoutManager;

    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage =1;
    List<List<EventHomeDto>> eventHomeDtoList;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        mainActivity = (MainActivity) getActivity();
        kProgressHUD = mainActivity.createHud();
        eventHomeDtoList = new ArrayList<>();


//        checkClickSetImageMale =  true;
        try {
            initUi();
            getData(currentPage);
            initListener();

        }catch (Exception e) {
            Log.e("ExceptionRuntime", e.toString());
        }


        return fragmentHomeBinding.getRoot();
    }

    private void initListener() {
        fragmentHomeBinding.rcvHome.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading = true;
                currentPage++;
                loadNextPage();

            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLagePage() {
                return isLastPage;
            }

            @Override
            public void ReloadItem() {
                currentPage=1;
                getData(currentPage);
            }
        });
    }

    private void loadNextPage() {
        getData(currentPage);
    }

    private void initUi() {
        eventList = new ArrayList<>();
         linearLayoutManager = new LinearLayoutManager(getActivity(), GridLayoutManager.VERTICAL, false);
        fragmentHomeBinding.rcvHome.setLayoutManager(linearLayoutManager);
        eventHomeAdapter = new EventHomeAdapter(eventList ,this::goToEventDetail);
        fragmentHomeBinding.rcvHome.setAdapter(eventHomeAdapter);
    }

    private void goToEventDetail(long id) {
            mainActivity.eventSummaryCurrentId = id;
            mainActivity.setCurrentPage(4);

    }

    private void getData(int currentPage) {
        if (!kProgressHUD.isShowing()) {
            kProgressHUD.show();
        }
        if (currentPage==1){
            eventHomeDtoList.clear();
        }
        ApiService apiService = RetrofitClient.getInstance(Server.DOMAIN2).getRetrofit().create(ApiService.class);
        Call<List<List<EventHomeDto>>> call = apiService.getListAllEventHome(currentPage);
        call.enqueue(new Callback<List<List<EventHomeDto>>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<List<EventHomeDto>>> call, Response<List<List<EventHomeDto>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<List<EventHomeDto>> eventHomeDtos = response.body();
                        if (!eventHomeDtos.isEmpty()){
                            eventHomeDtoList.addAll(eventHomeDtos);
                            eventHomeAdapter.setData(eventHomeDtoList);
                            eventHomeAdapter.notifyDataSetChanged();
                        }

                }
                if (kProgressHUD.isShowing()) {
                    kProgressHUD.dismiss();
                }
                isLoading=false;
            }

            @Override
            public void onFailure(Call<List<List<EventHomeDto>>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() + "", Toast.LENGTH_SHORT).show();
                if (kProgressHUD.isShowing()) {
                    kProgressHUD.dismiss();
                    Log.e("MainActivityLog", t.getMessage().toString());
                }
                isLoading=false;
            }
        });
    }
}
