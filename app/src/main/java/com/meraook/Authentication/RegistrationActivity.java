package com.meraook.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.meraook.MainActivity;
import com.meraook.R;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name,email,password;
    private Button nextBtn;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;
    private LottieAnimationView progressLottieAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.name);
        nextBtn = findViewById(R.id.next_btn);
        progressLottieAnim = findViewById(R.id.progress_bar);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressLottieAnim.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.INVISIBLE);
                String username = name.getText().toString();
                String emailId=email.getText().toString().trim();
                String pass=password.getText().toString().trim();

                String number=getIntent().getStringExtra("number");

                Map<String, String> hashmap = new HashMap<String, String>();
                hashmap.put("Name", username);
                hashmap.put("Email Id",emailId);
                hashmap.put("Password",pass);
                hashmap.put("User Id",uid);

                documentReference = FirebaseFirestore.getInstance().collection("User").document(number);

                documentReference.set(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else{
                            Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}