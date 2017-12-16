package com.script972.testex.presenter.iterafaces;

/**
 * Created by script972 on 16.12.2017.
 */

public interface LoginPresenter {

    void login(String email, String pass);

    void registration(String email, String pass);


    void onStart();

    void onStop();
}
