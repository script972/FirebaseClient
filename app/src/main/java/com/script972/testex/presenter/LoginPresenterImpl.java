package com.script972.testex.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.script972.testex.R;
import com.script972.testex.presenter.iterafaces.LoginPresenter;

/**
 * Created by script972 on 16.12.2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private Activity activity;
    private Context context;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public LoginPresenterImpl(Activity loginActivity) {
        this.activity=loginActivity;
        this.context=loginActivity;
        initFirebase();
    }

    @Override
    public void login(String email, String pass) {
        Log.d("login","loginPresenter IN");
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("login", "login Success");
                    Toast.makeText(context, context.getResources().getString(R.string.t_authorization_success), Toast.LENGTH_LONG).show();
                }else {
                    Log.d("login", "login Fail");
                    Toast.makeText(context, context.getResources().getString(R.string.t_authorization_fail), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void registration(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("login", "registration Success");
                    Toast.makeText(context, context.getResources().getString(R.string.t_registration_success), Toast.LENGTH_LONG).show();
                }else{
                    Log.d("login", "registration fail");
                    Toast.makeText(context, context.getResources().getString(R.string.t_registration_fail), Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    @Override
    public void onStart() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("firebasecutcom", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("firebasecutcom", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

}
