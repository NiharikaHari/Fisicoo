package com.example.myapplicationapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationapp.HomeActivity;
import com.example.myapplicationapp.R;
import com.example.myapplicationapp.data.model.LoggedInUser;

public class RegisterActivity extends AppCompatActivity {

    //declaring instance of firebase auth


    LoggedInUser user;

    String email, name1;
    float ht, wt;

    EditText username;
    EditText name;
    EditText height;
    EditText weight;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void clickLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void clickSignUp(View view){
        //Toast.makeText(this, "hahahah", Toast.LENGTH_LONG);

        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        height = findViewById(R.id.height);
        weight  = findViewById(R.id.weight);


        email = username.getText().toString();
        name1 = name.getText().toString();
        ht = Float.parseFloat(height.getText().toString());
        wt = Float.parseFloat(weight.getText().toString());
        
        user = new LoggedInUser(email, name1, ht, wt);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Welcome " + user.getDisplayName()+"!", Toast.LENGTH_SHORT);
    }


}
