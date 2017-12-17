package com.script972.testex.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.script972.testex.R;
import com.script972.testex.presenter.LoginPresenterImpl;
import com.script972.testex.presenter.iterafaces.login.SaveData;
import com.script972.testex.view.LoginView;


public class PersonalInfoActivity extends AppCompatActivity implements LoginView {



    // UI references.
    private EditText etfirstname;
    private EditText etlastname;
    private EditText etcountry;
    private EditText etcity;
    private EditText etphone;
    private EditText etbirthday;


    private View mProgressView;
    private View mLoginFormView;

    private SaveData presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initView();
        presenter=new LoginPresenterImpl(this);
        presenter.initAuthorization();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    private void initView() {
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        etfirstname = (EditText) findViewById(R.id.firstname);
        etlastname= (EditText) findViewById(R.id.lastname);
        etcountry= (EditText) findViewById(R.id.country);
        etcity= (EditText) findViewById(R.id.city);
        etphone= (EditText) findViewById(R.id.phone);
        etbirthday= (EditText) findViewById(R.id.birthday);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void savedateclick(View view) {
        Log.d("login", "saveData Button");
        attemptLogin();
    }


    @Override
    public void loginSuccess() {
        Log.d("login", "saveData Success");
        showProgress(false);
    }

    @Override
    public void loginFail() {
        Log.d("login", "saveData Fail");
        showProgress(false);

    }

    private View focusView=null;
    private void attemptLogin() {
        viewNullError();

        String firstName = etfirstname.getText().toString();
        String lastName = etlastname.getText().toString();
        String phone = etphone.getText().toString();
        String country = etcountry.getText().toString();
        String city = etcity.getText().toString();
        String birthday = etbirthday.getText().toString();

        boolean cancel = validateAll(firstName, lastName, phone, country, city, birthday);


        if (cancel) {
            if(focusView!=null)
                focusView.requestFocus();
        } else {
            showProgress(true);
            presenter.saveData(firstName, lastName, phone, country, city, birthday);
        }
    }

    private boolean validateAll(String firstName, String lastName, String phone, String country, String city, String birthday) {
        if(validateGeneralForm(firstName, etfirstname) != null)
            return true;

        if(validateGeneralForm(lastName, etlastname) != null)
            return true;

        if(validateGeneralForm(country, etcountry) != null)
            return true;

        if(validateGeneralForm(city, etcity) != null)
            return true;

        if(validateGeneralForm(phone, etphone) != null)
            return true;

        if(validateGeneralForm(birthday, etbirthday) != null)
            return true;
        return false;
    }

    private View validateGeneralForm(String str, EditText view) {
        if (TextUtils.isEmpty(str)) {
            view.setError(getString(R.string.error_invalid_field));
            this.focusView=view;
            return view;
        } else
            return null;
    }

    private void viewNullError() {
        etfirstname.setError(null);
        etlastname.setError(null);
        etcountry.setError(null);
        etcity.setError(null);
        etphone.setError(null);
        etbirthday.setError(null);
    }


}

