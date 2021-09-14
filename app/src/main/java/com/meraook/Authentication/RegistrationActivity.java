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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.meraook.MainActivity;
import com.meraook.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameActivity extends AppCompatActivity {

    private EditText name;
    private Button nextBtn;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;
    private LottieAnimationView progressLottieAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        name = findViewById(R.id.name);
        nextBtn = findViewById(R.id.next_btn);
        progressLottieAnim = findViewById(R.id.progress_bar);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();

        documentReference = FirebaseFirestore.getInstance().collection("User").document(uid);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressLottieAnim.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.INVISIBLE);
                String username = name.getText().toString();
                String number=getIntent().getStringExtra("number");

                Map<String, String> hashmap = new HashMap<String, String>();
                hashmap.put("Name", username);
                hashmap.put("Mobile Number",number);

                documentReference.set(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            SessionManager sessionManager=new SessionManager(NameActivity.this);
                            sessionManager.setName(username);

//                            SessionManager sessionManager=new SessionManager(NameActivity.this,SessionManager.IS_REMEMBER);
//                            sessionManager.createLoginSession(number,username);
//                            sessionManager.createRememberMeSession(username,number);
                            
                            finish();

                        } else{
                            Toast.makeText(NameActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}