package com.script972.testex.presenter.iterafaces.login;

/**
 * Created by script972 on 17.12.2017.
 */

public interface RegistrationPresenter {
    void registration(String email, String pass);

    void onStart();

    void onStop();

    void initAuthorization();
}
