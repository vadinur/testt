package com.vadinursezer.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
@BindView(R.id.numberAge) EditText numberAge;
@BindView(R.id.resultAge) TextView resultAge;
SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.vadinursezer.myapplication", Context.MODE_PRIVATE);
        int storeAge = sharedPreferences.getInt("storedAge",0);
        if (storeAge== 0){
            String resultString = getResources().getString(R.string.yourAge, storeAge);
            resultAge.setText(resultString);
        }else {
            String resultString = getResources().getString(R.string.yourAge, storeAge);
            resultAge.setText(resultString + storeAge);
        }
    }

    public void save (View view){
        if (numberAge.getText().toString().matches("")) {
            String resultString = "Enter Age!";
            resultAge.setText(resultString);
        }else {

            int age = Integer.parseInt(numberAge.getText().toString());
            String resultString = getResources().getString(R.string.result, age);
            resultAge.setText(resultString);
            numberAge.setText("");
            sharedPreferences.edit().putInt("storedAge",age).apply();
        }

    }

}
