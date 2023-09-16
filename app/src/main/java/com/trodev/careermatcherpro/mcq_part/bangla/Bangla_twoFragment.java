package com.trodev.careermatcherpro.mcq_part.bangla;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.trodev.careermatcherpro.R;
import com.trodev.careermatcherpro.mcq_part.McqAdapter;
import com.trodev.careermatcherpro.mcq_part.McqModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Bangla_twoFragment extends Fragment {

    private static final String json_url = "https://zobayer-dev-e12aa.web.app/bangla_mcq.json";
    RecyclerView recyclerView;
    List<McqModel> list;
    ProgressBar progressBar;

    public Bangla_twoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bangla_two, container, false);
        /*init views*/
        recyclerView = view.findViewById(R.id.dataRv);
        progressBar = view.findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);
        list = new ArrayList<>();

        loadData();

        return view;
    }

    private void loadData() {

        GetData getData = new  GetData();
        getData.execute();

    }


    public class GetData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(json_url);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data != -1) {
                    current += (char) data;
                    data = inputStreamReader.read();
                }

                return current;
            } catch (Exception e) {

            } finally {
                if (urlConnection != null) {

                    urlConnection.disconnect();
                }
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {

                progressBar.setVisibility(View.INVISIBLE);

                JSONObject jsonObject = new JSONObject(s);

                // database info
                JSONArray jsonArray = jsonObject.getJSONArray("bangla_second");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    /*create model class variable, object*/
                    McqModel modelClass = new McqModel();

                    /*set data on recyclerview*/
                    modelClass.setMcq_no(jsonObject1.getString("mcq_no"));

                    modelClass.setGrp_ques(jsonObject1.getString("grp_ques"));

                    modelClass.setFirst(jsonObject1.getString("first"));

                    modelClass.setSecond(jsonObject1.getString("second"));

                    modelClass.setThird(jsonObject1.getString("third"));

                    modelClass.setFourth(jsonObject1.getString("fourth"));

                    modelClass.setAns(jsonObject1.getString("ans"));


                    /*add model data on empty model class*/
                    list.add(modelClass);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            PutDataIntoRecyclerview(list);
        }
    }

    private void PutDataIntoRecyclerview(List<McqModel> list) {

        progressBar.setVisibility(View.INVISIBLE);
        McqAdapter customAdapter = new McqAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(customAdapter);

    }

}