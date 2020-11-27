package com.covidapp.notasmartapp.Presenters;

import android.content.Context;

import com.covidapp.notasmartapp.Clients.RetrofitClient;
import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.POJO.Article;
import com.covidapp.notasmartapp.POJO.HealthNewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthNewsPresenter implements MainContract.HealthNewsPresenter {

    private Context context;
    private MainContract.HealthNewsView view;

    public HealthNewsPresenter(Context context, MainContract.HealthNewsView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadNews() {
        view.showLoading();
        RetrofitClient.getInstance().getApi().getNews().enqueue(new Callback<HealthNewsResponse>() {
            @Override
            public void onResponse(Call<HealthNewsResponse> call, Response<HealthNewsResponse> response) {
                List<Article> list = response.body().getArticles();
                view.onSuccess(list);
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<HealthNewsResponse> call, Throwable t) {
                view.hideLoading();
                view.onFailed(t.getLocalizedMessage());
            }
        });

    }
}
