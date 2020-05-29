package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ImagesLevels extends AppCompatActivity {

    DataBase dataBase = new DataBase(this);
    ListView listView;
    ImageView noInternet;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_list);

        listView = (ListView) findViewById(R.id.list_view);
        noInternet = (ImageView) findViewById(R.id.no_internet);

        progressDialog = new ProgressDialog(this);

        if (!isOnline(this)) {
            listView.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
            Toast.makeText(ImagesLevels.this, "لا يوجد اتصال بالانترنت ", Toast.LENGTH_LONG).show();
        }

        //The Levels
        final ArrayList<Level> levels = new ArrayList<Level>();
        levels.add(new Level(31, dataBase.getRating(31)));
        levels.add(new Level(32, dataBase.getRating(32)));
        levels.add(new Level(33, dataBase.getRating(33)));
        levels.add(new Level(34, dataBase.getRating(34)));
        levels.add(new Level(35, dataBase.getRating(35)));
        levels.add(new Level(36, dataBase.getRating(36)));
        levels.add(new Level(37, dataBase.getRating(37)));
        levels.add(new Level(38, dataBase.getRating(38)));
        levels.add(new Level(39, dataBase.getRating(39)));
        levels.add(new Level(40, dataBase.getRating(40)));
        levels.add(new Level(41, dataBase.getRating(41)));
        levels.add(new Level(42, dataBase.getRating(42)));
        levels.add(new Level(43, dataBase.getRating(43)));
        levels.add(new Level(44, dataBase.getRating(44)));
        levels.add(new Level(45, dataBase.getRating(45)));
        levels.add(new Level(46, dataBase.getRating(46)));
        levels.add(new Level(47, dataBase.getRating(47)));
        levels.add(new Level(48, dataBase.getRating(48)));
        levels.add(new Level(49, dataBase.getRating(49)));
        levels.add(new Level(50, dataBase.getRating(50)));

        LevelAdapter adapter = new LevelAdapter(this, levels, "orange");
        listView.setAdapter(adapter);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("LastLevelSecondStage", MODE_PRIVATE);
        listView.setSelection(sharedPreferences.getInt("lastLevel", 0));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                progressDialog.setMessage("يرجى الانتظار....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                sharedPreferences.edit().putInt("lastLevel", position).apply();

                final int levelNum = levels.get(position).getmLevelNumber();

                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                DatabaseReference myRef = database.child("ImagesLevel/");
                myRef.child(String.valueOf(levelNum)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        ImagesLevelData imagesLevelData = dataSnapshot.getValue(ImagesLevelData.class);

                        Intent intent = new Intent(getApplicationContext(), ImagesQuestion.class);

                        intent.putExtra("QuestionOne", imagesLevelData.getQuestionOne());
                        intent.putExtra("QuestionTwo", imagesLevelData.getQuestionTwo());
                        intent.putExtra("QuestionThree", imagesLevelData.getQuestionThree());

                        intent.putExtra("QuestionOneSelections", imagesLevelData.getQuestionOneSelections());
                        intent.putExtra("QuestionTwoSelections", imagesLevelData.getQuestionTwoSelections());

                        intent.putExtra("QuestionOneCorrectAnswer", imagesLevelData.getQuestionOneCorrectAnswer());
                        intent.putExtra("QuestionTwoCorrectAnswer", imagesLevelData.getQuestionTwoCorrectAnswer());
                        intent.putExtra("QuestionThreeCorrectAnswer", imagesLevelData.getQuestionThreeCorrectAnswer());

                        intent.putExtra("levelNum", levelNum);

                        startActivity(intent);

                        finish();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });

            }
        });

    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.back) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

}
