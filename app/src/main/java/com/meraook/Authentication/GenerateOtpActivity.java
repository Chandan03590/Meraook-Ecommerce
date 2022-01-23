package com.meraook.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.meraook.R;

import java.util.concurrent.TimeUnit;

public class GenerateOtpActivity extends AppCompatActivity {

    private PinView pinView;
    private TextView number;
    private Button submitBtn;
    String otp;
    private LottieAnimationView progressLottieAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_otp);

        pinView=findViewById(R.id.pinview);
        number=findViewById(R.id.number);
        submitBtn=findViewById(R.id.submit_btn);
        progressLottieAnim=findViewById(R.id.progress_bar);

        number.setText(String.format("+91-%s",getIntent().getStringExtra("number")));

        otp=getIntent().getStringExtra("otp");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressLottieAnim.setVisibility(View.VISIBLE);
                submitBtn.setVisibility(View.INVISIBLE);

                String enterPinview=pinView.getText().toString();

                if (otp !=null){
                    PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                            otp, enterPinview
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent intent=new Intent(GenerateOtpActivity.this, RegistrationActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.putExtra("number",getIntent().getStringExtra("number"));
                                        startActivity(intent);

                                        progressLottieAnim.setVisibility(View.INVISIBLE);
                                        submitBtn.setVisibility(View.VISIBLE);
                                    }else {
                                        Toast.makeText(GenerateOtpActivity.this, "Enter Correct OTP", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(GenerateOtpActivity.this, "Blank OTP Cannot process", Toast.LENGTH_SHORT).show();
                    progressLottieAnim.setVisibility(View.INVISIBLE);
                    submitBtn.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    public void ResendOTP(View view) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + number,
                60,
                TimeUnit.SECONDS,
                GenerateOtpActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(GenerateOtpActivity.this, "Failed to send OTP", Toast.LENGTH_SHORT).show();
                        progressLottieAnim.setVisibility(View.INVISIBLE);
                        submitBtn.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCodeSent(@NonNull String newOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                      otp= newOtp;
                        Toast.makeText(GenerateOtpActivity.this, "Sent Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}