package com.ndv.ra.retrofitapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StackOverflowAPI stackOverflowAPI;
    private String token;
    private Button authenticateButton;
    private Spinner questionsSpinner;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionsSpinner = findViewById(R.id.questions_spinner);
        questionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Question question = (Question) adapterView.getAdapter().getItem(i);
                stackOverflowAPI.getAnswersForQuestion(question.questionId).enqueue(answersCallback);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        List<Question> questions = FakeDataProvider.getQuestions();
//        ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<Question>(
//                MainActivity.this, android.R.layout.simple_spinner_dropdown_item, questions
//        );
//        questionsSpinner.setAdapter(arrayAdapter);

        authenticateButton = findViewById(R.id.authenticate_button);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

//        List<Answer> answers = FakeDataProvider.getAnswers();
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(answers);
//        recyclerView.setAdapter(adapter);

        createStackOverflowAPI();
        stackOverflowAPI.getQuestions().enqueue(questionsCallBack);
    }

    private void createStackOverflowAPI() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        String BASE_URL = getString(R.string.stack_base);
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
        stackOverflowAPI = retrofit.create(StackOverflowAPI.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (token != null){
            authenticateButton.setEnabled(false);
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case android.R.id.text1:
                if (token != null){

                }else {
                    Toast.makeText(MainActivity.this, "You need to authenticate first", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.authenticate_button:
//                Toast.makeText(this, String.valueOf(isNetworkOnline()), Toast.LENGTH_SHORT).show();
                if(isNetworkOnline()){
                    stackOverflowAPI.getQuestions().enqueue(questionsCallBack);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1){
            token = data.getStringExtra("token");
        }
    }

    Callback<ListWrapper<Question>> questionsCallBack = new Callback<ListWrapper<Question>>() {
        @Override
        public void onResponse(Call<ListWrapper<Question>> call, Response<ListWrapper<Question>> response) {
            if (response.isSuccessful()){
                ListWrapper<Question> questionListWrapper = response.body();
                ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, questionListWrapper.items);
                questionsSpinner.setAdapter(arrayAdapter);
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Question>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<ListWrapper<Answer>> answersCallback = new Callback<ListWrapper<Answer>>() {
        @Override
        public void onResponse(Call<ListWrapper<Answer>> call, Response<ListWrapper<Answer>> response) {
            if (response.isSuccessful()){
                List<Answer> data = new ArrayList<>();
                data.addAll(response.body().items);
                recyclerView.setAdapter(new RecyclerViewAdapter(data));
            } else {
                Log.d("AnswersCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Answer>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<ResponseBody> upvoteCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                Toast.makeText(MainActivity.this, "Upvote successful", Toast.LENGTH_LONG).show();
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
                Toast.makeText(MainActivity.this, "You already upvoted this answer", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            t.printStackTrace();
        }
    };

    public boolean isNetworkOnline() {
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;

    }
}
