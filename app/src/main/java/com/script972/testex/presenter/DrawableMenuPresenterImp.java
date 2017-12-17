package com.script972.testex.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.script972.testex.R;
import com.script972.testex.activity.NewsActivity;
import com.script972.testex.activity.PeopleActivity;
import com.script972.testex.presenter.iterafaces.DrawableMenuPresenter;
import com.script972.testex.presenter.iterafaces.login.SignOut;

/**
 * Created by script972 on 17.12.2017.
 */

public class DrawableMenuPresenterImp implements DrawableMenuPresenter {

    private Context context;
    private SignOut firebasePresenter;


    public DrawableMenuPresenterImp(Context context) {
        this.context = context;
        firebasePresenter=new LoginPresenterImpl((Activity) context,1);
        firebasePresenter.initAuthorization();

    }

    @Override
    public void selectItem(int id) {
        if (id == R.id.nav_news)
            startActivityCustom(NewsActivity.class);
        else if (id == R.id.nav_sources) {

        } else if (id == R.id.nav_wather) {

        } else if (id == R.id.nav_peole)
            startActivityCustom(PeopleActivity.class);
        else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_exit)
            signOut();

    }

    private void signOut() {
        Log.i("firebasecutcom", "SignOut");
        firebasePresenter.signOut();
        Log.i("firebasecutcom", "SignOut2");
    }

    private void startActivityCustom(Class activity) {
        Intent intent=new Intent(context, activity);
        context.startActivity(intent);
    }
}
