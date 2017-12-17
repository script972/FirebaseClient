package com.script972.testex.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.script972.testex.activity.MenuActivity;
import com.script972.testex.presenter.iterafaces.login.SignOut;
import com.script972.testex.presenter.iterafaces.login.LoginPresenter;
import com.script972.testex.presenter.iterafaces.login.RegistrationPresenter;
import com.script972.testex.presenter.iterafaces.login.SaveData;
import com.script972.testex.view.LoginView;

/**
 * Created by script972 on 16.12.2017.
 */

public class LoginPresenterImpl implements LoginPresenter, RegistrationPresenter, SaveData, SignOut {

    private Activity activity;
    private Context context;
    private LoginView view;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public LoginPresenterImpl(Activity loginActivity) {
        this.activity=loginActivity;
        this.view= (LoginView) loginActivity;
        this.context=loginActivity;
        initFirebase();
    }

    public LoginPresenterImpl(Activity loginActivity,int i) {
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
                    view.loginSuccess();
                }else {
                    Log.d("login", "login Fail");
                    view.loginFail();
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
                    view.loginSuccess();
                }else{
                    Log.d("login", "registration fail");
                    view.loginFail();

                }
            }
        });

    }

    @Override
    public void saveData(String name, String lName, String country, String city, String phone, String birthday) {
        Log.d("login", "saving data");




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

    @Override
    public void initAuthorization() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.i("firebasecutcom", "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    Log.i("firebasecutcom", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void checkAuth(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.i("firebasecutcom", "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent intent=new Intent(context, MenuActivity.class);
                    context.startActivity(intent);
                } else {
                    Log.i("firebasecutcom", "onAuthStateChanged:signed_out");
                }
            }
        };
    }



    private void initFirebase() {
        Log.i("firebasecutcom", "initOnCreateLoginAcitivty");
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void signOut() {
        Log.i("firebasecutcom", "SignOut firebase");
        mAuth.removeAuthStateListener(mAuthListener);
        FirebaseAuth.getInstance().signOut();

    }
}
