package com.covidapp.notasmartapp.Interfaces;

import com.google.firebase.auth.FirebaseUser;

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


}
