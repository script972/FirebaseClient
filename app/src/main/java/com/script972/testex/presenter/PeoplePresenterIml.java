package com.script972.testex.presenter;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.script972.testex.presenter.iterafaces.db.PeoplePresenter;
import com.script972.testex.view.PeopleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by script972 on 17.12.2017.
 */

public class PeoplePresenterIml implements PeoplePresenter {

    private PeopleView view;
    private Context context;

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private List<String> persons = new ArrayList<>();

    public PeoplePresenterIml(Context context) {
        this.view = (PeopleView) context;
        this.context = context;
    }

    @Override
    public void onCreate() {
        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<String>> t =new GenericTypeIndicator<List<String>>() {};
                persons=dataSnapshot.child("person").getValue(t);
                if(!persons.isEmpty())
                    Log.i("persons", "Person 0="+persons.get(0));

                view.showListUsers(persons);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void loadData() {


    }
}
