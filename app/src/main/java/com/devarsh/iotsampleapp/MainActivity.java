package com.devarsh.iotsampleapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity {
    public EditText enterData;
    public TextView displayData;
    public Button submitData;

    //database reference
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterData = findViewById(R.id.enter_value);
        displayData = findViewById(R.id.display_value);
        submitData = findViewById(R.id.submit_value);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
                Toast.makeText(MainActivity.this, "Data Submitted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        String data = enterData.getText().toString();
        mDatabase.child("data").setValue(data);
    }


    public void display(View view) {
        mDatabase.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue().toString();
                displayData.setText(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w( "getUser:onCancelled", databaseError.toException());
            }
        });
    }
}
