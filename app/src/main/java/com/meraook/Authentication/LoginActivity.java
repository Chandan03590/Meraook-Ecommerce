package com.meraook.Authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.meraook.MainActivity;
import com.meraook.R;

public class LoginActivity extends AppCompatActivity {

    private EditText mobileNumber, password;
    private Button loginBtn;
    private DocumentReference documentReference;
    private FirebaseFirestore firebaseFirestore;
    private LottieAnimationView lottieProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobileNumber=findViewById(R.id.mobile_number);
        password=findViewById(R.id.password);
        loginBtn=findViewById(R.id.login_btn);
        lottieProgressBar=findViewById(R.id.progress_bar);

        firebaseFirestore=FirebaseFirestore.getInstance();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {

                lottieProgressBar.setVisibility(View.VISIBLE);
                loginBtn.setVisibility(View.INVISIBLE);

                String number=mobileNumber.getText().toString();
                String pass=password.getText().toString();

                if (number.isEmpty() && pass.isEmpty()){

                    Toast.makeText(LoginActivity.this, "All fields are Required", Toast.LENGTH_SHORT).show();
                    lottieProgressBar.setVisibility(View.INVISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);

                }
                else {
                    if (number.isEmpty()){
                        mobileNumber.setError("Email is Required");
                    }
                    else if (pass.isEmpty()){
                        password.setError("Passord is Required");
                    }
                    else {
                        loginMethod(number,pass);
                    }
                }


            }


        });
    }

    private void loginMethod(String number, String pass) {
        documentReference=firebaseFirestore.collection("User").document(number);

        documentReference.addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String password_original=value.getString("Password");

                if (pass.equals(password_original)){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    SessionManager sessionManager=new SessionManager(getApplicationContext());
                    sessionManager.setMobile_number(number);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed to login "+error, Toast.LENGTH_SHORT).show();
                    lottieProgressBar.setVisibility(View.INVISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    public void moveToRegistrationPage(View view) {
        startActivity(new Intent(getApplicationContext(),MobileNumberActivity.class));
    }
}