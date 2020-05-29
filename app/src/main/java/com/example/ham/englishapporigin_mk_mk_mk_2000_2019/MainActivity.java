package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView score;
    ImageView secondStageImageLock, thirdStageImageLock, fourthStageImageLock;
    DataBase dataBase = new DataBase(this);
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = (TextView) findViewById(R.id.score);
        secondStageImageLock = (ImageView) findViewById(R.id.second_stage_image_lock);
        thirdStageImageLock = (ImageView) findViewById(R.id.third_stage_image_lock);
        fourthStageImageLock = (ImageView) findViewById(R.id.fourth_stage_image_lock);

        score.setText("" + dataBase.getScore() + "");


        if (dataBase.getScore() >= 300) {

            secondStageImageLock.setVisibility(View.GONE);
        }

        if (dataBase.getScore() >= 510) {

            thirdStageImageLock.setVisibility(View.GONE);
        }

        if (dataBase.getScore() >= 810) {

            fourthStageImageLock.setVisibility(View.GONE);
        }

    }

    public void firstStage(View view) {
        startActivity(new Intent(MainActivity.this, WordsLevels.class));
        finish();

    }

    public void secondStage(View view) {
        if (dataBase.getScore() >= 300) {
            startActivity(new Intent(MainActivity.this, ImagesLevels.class));
            finish();
        } else {

            showAlertDialog("<font color='#FBC02D'> 300 $ </font>", R.drawable.two_ic);
        }
    }

    public void thirdStage(View view) {
        if (dataBase.getScore() >= 510) {
            startActivity(new Intent(MainActivity.this, PuzzlesLevels.class));
            finish();
        } else {

            showAlertDialog("<font color='#FBC02D'> 510 $ </font>", R.drawable.three_ic);

        }
    }

    public void fourthStage(View view) {
        if (dataBase.getScore() >= 810) {
            startActivity(new Intent(MainActivity.this, GrammarsLevels.class));
            finish();
        } else {

            showAlertDialog("<font color='#FBC02D'> 810 $ </font>", R.drawable.four_ic);

        }
    }

    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.example.ham.englishapporigin_mk_mk_mk_2000_2019");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "مشاركة التطبيق"));
    }

    public void email(View view) {
        String[] to = {"mkcompany445@gmail.com"};

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_EMAIL, to);

        startActivity(Intent.createChooser(intent, "ارسال الملاحظات والاقتراحات"));
    }

    public void rateApp(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.rate_ic_2);
        builder.setTitle("قيم التطبيق");
        builder.setCancelable(false);
        builder.setPositiveButton("حسناً", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.example.ham.englishapporigin_mk_mk_mk_2000_2019"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("لاحقاً", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        }).show();
    }


    public void showAlertDialog(String message, int icon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("مغلق");
        builder.setIcon(icon);

        TextView textView = new TextView(this);
        textView.setText(Html.fromHtml("يجب ان تحصل على " + message), TextView.BufferType.SPANNABLE);
        textView.setPadding(32, 32, 32, 32);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.parseColor("#FF171D3D"));

        builder.setView(textView);

        builder.setPositiveButton(
                "حسناً",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        if (time + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(MainActivity.this, "أضغط مرة اخرى للخروج", Toast.LENGTH_LONG).show();
        }
        time = System.currentTimeMillis();
    }
}


