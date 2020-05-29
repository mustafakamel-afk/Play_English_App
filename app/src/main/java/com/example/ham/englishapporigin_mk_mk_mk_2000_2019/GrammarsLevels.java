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

public class GrammarsLevels extends AppCompatActivity {

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
            noInternet.setBackgroundResource(R.drawable.background_indigo);
            Toast.makeText(GrammarsLevels.this, "لا يوجد اتصال بالانترنت ", Toast.LENGTH_LONG).show();
        }

        //The Levels
        final ArrayList<Level> levels = new ArrayList<Level>();
        levels.add(new Level(81, dataBase.getRating(81)));
        levels.add(new Level(82, dataBase.getRating(82)));
        levels.add(new Level(83, dataBase.getRating(83)));
        levels.add(new Level(84, dataBase.getRating(84)));
        levels.add(new Level(85, dataBase.getRating(85)));
        levels.add(new Level(86, dataBase.getRating(86)));
        levels.add(new Level(87, dataBase.getRating(87)));
        levels.add(new Level(88, dataBase.getRating(88)));
        levels.add(new Level(89, dataBase.getRating(89)));
        levels.add(new Level(90, dataBase.getRating(90)));
        levels.add(new Level(91, dataBase.getRating(91)));
        levels.add(new Level(92, dataBase.getRating(92)));
        levels.add(new Level(93, dataBase.getRating(93)));
        levels.add(new Level(94, dataBase.getRating(94)));
        levels.add(new Level(95, dataBase.getRating(95)));
        levels.add(new Level(96, dataBase.getRating(96)));
        levels.add(new Level(97, dataBase.getRating(97)));
        levels.add(new Level(98, dataBase.getRating(98)));
        levels.add(new Level(99, dataBase.getRating(99)));


        LevelAdapter adapter = new LevelAdapter(this, levels, "indigo");
        listView.setAdapter(adapter);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("LastLevelFourthStage", MODE_PRIVATE);
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
                DatabaseReference myRef = database.child("GrammarsLevel/");
                myRef.child(String.valueOf(levelNum)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        GrammarsLevelData grammarsLevelData = dataSnapshot.getValue(GrammarsLevelData.class);

                        Intent intent = new Intent(getApplicationContext(), GrammarQuestion.class);

                        intent.putExtra("QuestionOne", grammarsLevelData.getQuestionOne());
                        intent.putExtra("QuestionTwo", grammarsLevelData.getQuestionTwo());

                        intent.putExtra("QuestionThreeSelections", grammarsLevelData.getQuestionThreeSelections());

                        intent.putExtra("QuestionOneCorrectAnswer", grammarsLevelData.getQuestionOneCorrectAnswer());
                        intent.putExtra("QuestionTwoCorrectAnswer", grammarsLevelData.getQuestionTwoCorrectAnswer());
                        intent.putExtra("QuestionThreeCorrectAnswer", grammarsLevelData.getQuestionThreeCorrectAnswer());

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
