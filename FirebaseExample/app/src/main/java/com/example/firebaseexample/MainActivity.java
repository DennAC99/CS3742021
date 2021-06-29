package com.example.firebaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListenerProxy;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("employees");
    private TextView EmpView;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmpView = findViewById(R.id.employeeView);
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        scrollView = findViewById(R.id.scrollView);


        myRef.addValueEventListener(new ValueEventListener() {
            Employee emp;
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                StringBuilder employeeList = new StringBuilder();
                int counter = 1;
                for (DataSnapshot s : snapshot.getChildren())
                {
                    emp = (Employee)s.getValue(Employee.class);
                    employeeList.append("Employee #" + counter + ":\n"+"First name: " + emp.getFirstName()
                            + "\nLast name: " + emp.getLastName() + "\n\n");
                    counter += 1;
                }
                EmpView.setText(employeeList);
                //Scroll screen to bottom by default, and to show new added employee
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("MAINACTIVITY", "Database read failed!", error.toException());
            }
        });
    }

    public void addEmployee_toDatabase(View view) {
        //hide keyboard when button is pressed
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        String firstName, lastName;
        firstName = firstNameInput.getText().toString();
        lastName = lastNameInput.getText().toString();

        if (firstName.length() == 0 || lastName.length() == 0) {
            //either first name or last name is empty.
            Toast toast = Toast.makeText(this, "Employee's full name required",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            //if First and Last name blanks are filled, add employee to database.
            Employee emp = new Employee(lastName, firstName);
            myRef.push().setValue(emp);
            Toast toast = Toast.makeText(this, "Employee added!",
                    Toast.LENGTH_SHORT);
            toast.show();
            firstNameInput.setCursorVisible(false);
            firstNameInput.setText("");
            lastNameInput.setCursorVisible(false);
            lastNameInput.setText("");
        }
    }
}
