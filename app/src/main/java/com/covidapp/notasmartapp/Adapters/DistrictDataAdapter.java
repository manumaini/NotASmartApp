package com.covidapp.notasmartapp.Adapters;

//public class DistrictDataAdapter extends ArrayAdapter<CovidStateData.CovidDistrictData> {
//
//    public DistrictDataAdapter(@NonNull Context context, ArrayList<CovidStateData.CovidDistrictData> object) {
//        super(context,0,object);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View listView=convertView;
//        if(listView==null) {
//            listView = LayoutInflater.from(getContext()).inflate(R.layout.district_list_view, parent, false);
//        }
//        CovidStateData.CovidDistrictData dataPos=getItem(position);
//
//        TextView districtName=listView.findViewById(R.id.districtName);
//        districtName.setText(dataPos.name);
//
//        TextView confirmed=listView.findViewById(R.id.confirmedCase);
//        confirmed.setText(dataPos.confirmedDistrictCases+"");
//
//        return listView;
//    }
//    }
