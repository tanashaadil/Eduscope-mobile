package com.learn.eduscope.eduscope;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pw;
    private Spinner dropdown;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email   = (EditText)findViewById(R.id.input_email);
        pw = (EditText)findViewById(R.id.input_password);
        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

//create a list of items for the spinner.
        String[] items = new String[]{"Lecturer", "Student"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }

    public void goToDashboard(View view) {
        if(email.toString().matches("") || pw.toString().matches(""))
        {
            System.out.println("invalid");
        }
        else
        {
            System.out.println("valid");

            String spinnerSelectedItem = (String) dropdown.getSelectedItem();

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();

            if(spinnerSelectedItem.equals("Lecturer"))
            {
                editor.putString("role", "Lecturer");
                editor.commit();

                /*String role = sharedpreferences.getString("role", "");

                System.out.println("role");
                System.out.println(role);*/


                Intent intent = new Intent(this, StudentDashboard.class);
                startActivity(intent);
            }
            else
            {
                editor.putString("role", "Student");
                editor.commit();

                /*String role = sharedpreferences.getString("role", "");

                System.out.println("role");
                System.out.println(role);*/

                Intent intent = new Intent(this, StudentDashboard.class);
                startActivity(intent);
            }
        }

    }


}
