package com.covidapp.notasmartapp.Interfaces;

import com.covidapp.notasmartapp.POJO.Article;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface MainContract {

    interface RegistrationView{
        void onSuccess(FirebaseUser user);
        void onFailed(String error);
        void showLoading();
        void hideLoading();
    }

    interface RegistrationPresenter{
        void register(String email,String password);
    }

    interface LoginView{
        void onSuccess(FirebaseUser user);
        void onFailed(String error);
        void showLoading();
        void hideLoading();
    }

    interface LoginPresenter{
        void login(String email,String password);
    }

    interface HealthNewsPresenter{
        void loadNews();
    }

    interface HealthNewsView{
        void onSuccess(List<Article> list);
        void onFailed(String error);
        void showLoading();
        void hideLoading();
    }


}
