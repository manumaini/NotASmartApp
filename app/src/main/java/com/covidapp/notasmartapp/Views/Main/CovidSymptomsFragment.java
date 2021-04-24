package com.covidapp.notasmartapp.Views.Main;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.covidapp.notasmartapp.Adapters.SymptomsAdapter;
import com.covidapp.notasmartapp.POJO.Symptoms;
import com.covidapp.notasmartapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CovidSymptomsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CovidSymptomsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private SymptomsAdapter adapter;
    private List<Symptoms> symptoms;
    private String[] sympName = {"Fever","Dry Cough","Fatigue","Shortness of Breath","Muscle Pain",
        "Sore Throat","Headache","Chills","Nausea or Vomiting","Nasal Congestion","Diarrhea","Conjunctival Congesion"};
    private String[] sympPerc ={"87.9","67.7","38.1","18.6","14.8","13.9","13.6","11.4","5.0","4.8","3.7","0.8"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CovidSymptomsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CovidSymptomsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CovidSymptomsFragment newInstance(String param1, String param2) {
        CovidSymptomsFragment fragment = new CovidSymptomsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_covid_symptoms, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.symptoms_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        symptoms=new ArrayList<>();
        for(int i=0; i<sympName.length; i++){
            Symptoms symptom = new Symptoms(sympName[i],sympPerc[i]);
            symptoms.add(symptom);
            adapter = new SymptomsAdapter(getContext(),symptoms);
        }
        recyclerView.setAdapter(adapter);
        return view;
    }
}