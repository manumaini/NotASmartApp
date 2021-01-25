package com.covidapp.notasmartapp.Presenters;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.covidapp.notasmartapp.Data.Models.Hospital;
import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HospitalPresenter implements MainContract.HospitalPresenter {
    private Context context;
    private MainContract.HospitalView view;
    private static final String TAG = "HospitalPresenter";
    private ArrayList<Hospital> list;

    public HospitalPresenter(Context context, MainContract.HospitalView view) {
        this.context = context;
        this.view = view;
        list = new ArrayList<>();
    }

    @Override
    public void loadHospitals() {
        view.showLoading();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("Hospitals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "onComplete: "+task.getResult().size());
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Hospital hospital = document.toObject(Hospital.class);
                        Log.d(TAG, "onComplete: "+hospital.getName());
                        list.add(hospital);
                    }
                    view.hideLoading();
                    view.onSuccess(list);
                } else {
                    view.hideLoading();
                    view.onFailed(task.getException().getLocalizedMessage());
                }
            }
        });

    }
}
