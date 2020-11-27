package com.covidapp.notasmartapp.Interfaces;

public interface MainContract {

    interface RegistrationView{
        void onSuccess();
        void onFailed(String error);
        void showLoading();
        void hideLoading();
    }

    interface RegistrationPresenter{
        void register(String email,String password);
    }


}
