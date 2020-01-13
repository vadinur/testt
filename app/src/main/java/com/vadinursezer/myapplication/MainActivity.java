package com.vadinursezer.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        sharedPreferences = this.getSharedPreferences("com.vadinursezer.myapplication",Context.MODE_PRIVATE);
        int storeAge = sharedPreferences.getInt("storeAge",0);


        if (storeAge==0){
            resultAge.setText("Your Age: ");
        }else {
            resultAge.setText("Your Age: " + storeAge);
        }
    }

    public void save (View view){
        if (!numberAge.getText().toString().matches("")){
            int userAge=Integer.parseInt(numberAge.getText().toString());
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Save");
            alert.setMessage("Are you sure?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resultAge.setText("Your Age: " + userAge);
                    sharedPreferences.edit().putInt("storeAge",userAge).apply();
                    Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_LONG).show();
                    numberAge.setText("");
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Not Saved",Toast.LENGTH_LONG).show();
                    numberAge.setText("");
                }
            });
            alert.show();
        }
    }
    public void clear(View view){
        int storeData = sharedPreferences.getInt("storeAge",0);
        if (storeData != 0){
            sharedPreferences.edit().remove("storeAge").apply();
            resultAge.setText("Your Age: ");
        }
    }
}
