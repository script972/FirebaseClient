package com.script972.testex.presenter.iterafaces.login;

/**
 * Created by script972 on 17.12.2017.
 */

public interface SaveData {
    void saveData(String name, String lName, String country, String city, String phone, String birthday);

    void onStart();

    void onStop();

    void initAuthorization();
}
