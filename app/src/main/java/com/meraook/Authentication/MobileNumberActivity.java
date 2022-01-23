package com.meraook.Authentication;

import androidx.annotation.NonNull;
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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.meraook.MainActivity;
import com.meraook.R;

import java.util.concurrent.TimeUnit;

public class MobileNumberActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button getOtpBtn;
    private EditText mobileNumber;
    private LottieAnimationView progressLottieAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number);

        getOtpBtn = findViewById(R.id.get_otp_button);
        mobileNumber = findViewById(R.id.mobile_number);
        progressLottieAnim=findViewById(R.id.progress_bar);


        getOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressLottieAnim.setVisibility(View.VISIBLE);
                getOtpBtn.setVisibility(View.INVISIBLE);
                String number = mobileNumber.getText().toString().trim();

                if (!(number.isEmpty())) {

                    if (number.length() == 10) {
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + number,
                                60,
                                TimeUnit.SECONDS,
                                MobileNumberActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(MobileNumberActivity.this, "Failed to send OTP "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressLottieAnim.setVisibility(View.INVISIBLE);
                                        getOtpBtn.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        Intent getOtpIntent = new Intent(MobileNumberActivity.this, GenerateOtpActivity.class);
                                        getOtpIntent.putExtra("number",number);
                                        getOtpIntent.putExtra("otp",s);
                                        startActivity(getOtpIntent);
                                        progressLottieAnim.setVisibility(View.INVISIBLE);
                                        getOtpBtn.setVisibility(View.VISIBLE);

                                    }
                                }
                        );
                    } else {
                        Toast.makeText(MobileNumberActivity.this, "Enter 10 digit Mobile Number", Toast.LENGTH_SHORT).show();
                        progressLottieAnim.setVisibility(View.INVISIBLE);
                        getOtpBtn.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(MobileNumberActivity.this, "Mobile Number is Required", Toast.LENGTH_SHORT).show();
                    progressLottieAnim.setVisibility(View.INVISIBLE);
                    getOtpBtn.setVisibility(View.VISIBLE);
                }

            }
        });

    }

}