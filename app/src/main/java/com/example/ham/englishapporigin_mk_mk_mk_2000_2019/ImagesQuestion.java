package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ImagesQuestion extends AppCompatActivity implements TextWatcher {

    private Button Q1Selection1, Q1Selection2, Q1Selection3;
    private Button Q2Selection1, Q2Selection2, Q2Selection3;
    private LinearLayout QuestionOneLayout, QuestionTwoLayout;
    private ImageView theQuestionImageView, Q3Done;
    private RelativeLayout QuestionThreeLayout;
    private EditText questionThreeEditText;
    private ProgressBar progressBar;
    private Activity activity;

    DataBase dataBase = new DataBase(this);

    String QuestionOne, QuestionTwo, QuestionThree;
    String QuestionOneCorrectAnswer, QuestionTwoCorrectAnswer, QuestionThreeCorrectAnswer;
    int rating = 0, levelNum;

    //نحتاجه لاكمال عمليه تكبير الصورة
    int questionNumberImageZoom = 1;

    String backPressed = "back"; //هذا المتغير نحتاجه لتجنب حدوث المشاكل عندما تنتهي المرحله اثناء المعالجه و اضافه النقاط


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_question);
        activity = this;

        Q1Selection1 = (Button) findViewById(R.id.Q1_selection_1);
        Q1Selection2 = (Button) findViewById(R.id.Q1_selection_2);
        Q1Selection3 = (Button) findViewById(R.id.Q1_selection_3);
        Q2Selection1 = (Button) findViewById(R.id.Q2_selection_1);
        Q2Selection2 = (Button) findViewById(R.id.Q2_selection_2);
        Q2Selection3 = (Button) findViewById(R.id.Q2_selection_3);

        theQuestionImageView = (ImageView) findViewById(R.id.the_question_image_view);
        Q3Done = (ImageView) findViewById(R.id.Q3_done);

        QuestionOneLayout = (LinearLayout) findViewById(R.id.question_one_layout);
        QuestionTwoLayout = (LinearLayout) findViewById(R.id.question_two_layout);
        QuestionThreeLayout = (RelativeLayout) findViewById(R.id.question_three_layout);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        questionThreeEditText = (EditText) findViewById(R.id.question_three_edit_text);


        Intent data = getIntent();

        levelNum = data.getExtras().getInt("levelNum");

        String[] questionOneSelections = data.getExtras().getString("QuestionOneSelections").split("/");
        String[] questionTwoSelections = data.getExtras().getString("QuestionTwoSelections").split("/");

        //question one
        QuestionOne = data.getExtras().getString("QuestionOne");
        Q1Selection1.setText(questionOneSelections[0]);
        Q1Selection2.setText(questionOneSelections[1]);
        Q1Selection3.setText(questionOneSelections[2]);
        QuestionOneCorrectAnswer = data.getExtras().getString("QuestionOneCorrectAnswer");
        //question two
        QuestionTwo = data.getExtras().getString("QuestionTwo");
        Q2Selection1.setText(questionTwoSelections[0]);
        Q2Selection2.setText(questionTwoSelections[1]);
        Q2Selection3.setText(questionTwoSelections[2]);
        QuestionTwoCorrectAnswer = data.getExtras().getString("QuestionTwoCorrectAnswer");
        //question three
        QuestionThree = data.getExtras().getString("QuestionThree");
        QuestionThreeCorrectAnswer = data.getExtras().getString("QuestionThreeCorrectAnswer");

        Picasso.with(getApplicationContext()).load(QuestionOne).into(theQuestionImageView);

        questionThreeEditText.addTextChangedListener(this);

    }

    public void Q1_selections(View view) {
        switch (view.getId()) {

            case R.id.Q1_selection_1:
                if (Q1Selection1.getText().toString().equals(QuestionOneCorrectAnswer)) {
                    Q1Selection1.setBackgroundResource(R.drawable.background_right_answer);
                    rating += 1;
                } else {
                    Q1Selection1.setBackgroundResource(R.drawable.background_wrong_answer);
                    shakeItBaby();
                }
                break;
            case R.id.Q1_selection_2:
                if (Q1Selection2.getText().toString().equals(QuestionOneCorrectAnswer)) {
                    Q1Selection2.setBackgroundResource(R.drawable.background_right_answer);
                    rating += 1;
                } else {
                    Q1Selection2.setBackgroundResource(R.drawable.background_wrong_answer);
                    shakeItBaby();
                }
                break;
            case R.id.Q1_selection_3:
                if (Q1Selection3.getText().toString().equals(QuestionOneCorrectAnswer)) {
                    Q1Selection3.setBackgroundResource(R.drawable.background_right_answer);
                    rating += 1;
                } else {
                    Q1Selection3.setBackgroundResource(R.drawable.background_wrong_answer);
                    shakeItBaby();
                }
                break;
        }

        //لتجنب الاخطاء
        Q1Selection1.setEnabled(false);
        Q1Selection2.setEnabled(false);
        Q1Selection3.setEnabled(false);

        thread th = new thread(1);
        th.start();

    }

    public void Q2_selections(View view) {
        switch (view.getId()) {

            case R.id.Q2_selection_1:
                if (Q2Selection1.getText().toString().equals(QuestionTwoCorrectAnswer)) {
                    Q2Selection1.setBackgroundResource(R.drawable.background_right_answer);
                    rating += 1;
                } else {
                    Q2Selection1.setBackgroundResource(R.drawable.background_wrong_answer);
                    shakeItBaby();
                }
                break;
            case R.id.Q2_selection_2:
                if (Q2Selection2.getText().toString().equals(QuestionTwoCorrectAnswer)) {
                    Q2Selection2.setBackgroundResource(R.drawable.background_right_answer);
                    rating += 1;
                } else {
                    Q2Selection2.setBackgroundResource(R.drawable.background_wrong_answer);
                    shakeItBaby();
                }
                break;
            case R.id.Q2_selection_3:
                if (Q2Selection3.getText().toString().equals(QuestionTwoCorrectAnswer)) {
                    Q2Selection3.setBackgroundResource(R.drawable.background_right_answer);
                    rating += 1;
                } else {
                    Q2Selection3.setBackgroundResource(R.drawable.background_wrong_answer);
                    shakeItBaby();
                }
                break;
        }

        //لتجنب الاخطاء
        Q2Selection1.setEnabled(false);
        Q2Selection2.setEnabled(false);
        Q2Selection3.setEnabled(false);

        thread th = new thread(2);
        th.start();
    }

    public void Q3_done(View view) {
        if (questionThreeEditText.getText().toString().trim().equalsIgnoreCase(QuestionThreeCorrectAnswer)) {
            questionThreeEditText.setBackgroundResource(R.drawable.background_right_answer);
            questionThreeEditText.setTextColor(Color.parseColor("#FFFFFFFF"));

            rating += 1;
            questionThreeEditText.setEnabled(false);
            backPressed = "";
            Q3Done.setEnabled(false);
            thread th = new thread(3);
            th.start();
        } else if (questionThreeEditText.getText().toString().trim().equalsIgnoreCase("")) {
            //nothing
        } else if (!questionThreeEditText.getText().toString().trim().equalsIgnoreCase(QuestionThreeCorrectAnswer)) {
            questionThreeEditText.setBackgroundResource(R.drawable.background_wrong_answer);
            questionThreeEditText.setTextColor(Color.parseColor("#FFFFFFFF"));

            questionThreeEditText.setEnabled(false);
            backPressed = "";
            Q3Done.setEnabled(false);
            shakeItBaby();
            thread th = new thread(3);
            th.start();

        }


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String theAnswer = questionThreeEditText.getText().toString();

        if (theAnswer.trim().length() > 0) {

            Q3Done.setAlpha(0.9f);

        } else {

            Q3Done.setAlpha(0.4f);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public void zoomIn(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(" ");

        ImageView imageView = new ImageView(this);
        imageView.setPadding(16, 16, 16, 16);
        imageView.setMinimumHeight(250);
        imageView.setMaxHeight(250);

        if (questionNumberImageZoom == 1) {
            Picasso.with(getApplicationContext()).load(QuestionOne).into(imageView);

        } else if (questionNumberImageZoom == 2) {
            Picasso.with(getApplicationContext()).load(QuestionTwo).into(imageView);

        } else if (questionNumberImageZoom == 3) {
            Picasso.with(getApplicationContext()).load(QuestionThree).into(imageView);

        }

        builder.setView(imageView);

        builder.setPositiveButton(
                "غلق",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    class thread extends Thread {

        int QuestionNumber;

        public thread(int QuestionNumber) {
            this.QuestionNumber = QuestionNumber;
        }

        int x = 4;

        public void run() {
            while (x >= 0) {

                activity.runOnUiThread(new Runnable() {//للوصول الى الاكتفتي
                    @Override
                    public void run() {
                        if (x == 0 && QuestionNumber == 1) {
                            questionNumberImageZoom = 2;
                            Picasso.with(getApplicationContext()).load(QuestionTwo).into(theQuestionImageView);
                            setAnimation(QuestionTwoLayout);
                            progressAnimator(0, 25);
                            QuestionOneLayout.setVisibility(View.GONE);
                            QuestionTwoLayout.setVisibility(View.VISIBLE);
                        } else if (x == 0 && QuestionNumber == 2) {
                            questionNumberImageZoom = 3;
                            Picasso.with(getApplicationContext()).load(QuestionThree).into(theQuestionImageView);
                            setAnimation(QuestionThreeLayout);
                            progressAnimator(25, 50);
                            QuestionTwoLayout.setVisibility(View.GONE);
                            QuestionThreeLayout.setVisibility(View.VISIBLE);

                        } else if (x == 0 && QuestionNumber == 3) {
                            progressAnimator(50, 75);
                            showAlertDialog();
                        }
                    }
                });
                x--;
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {

                }
            }
        }
    }

    public void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog, null);
        builder.setView(view);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
        ImageView showLevels = (ImageView) view.findViewById(R.id.show_levels);
        TextView addNewScore = (TextView) view.findViewById(R.id.add_new_score);
        TextView totalScore = (TextView) view.findViewById(R.id.total_score);
        TextView freeScoreTextView = (TextView) view.findViewById(R.id.free_score_text_view);
        LinearLayout alertDialogLayout = (LinearLayout) view.findViewById(R.id.alert_dialog_layout);

        freeScoreTextView.setBackgroundResource(R.drawable.background_orange);
        showLevels.setImageResource(R.drawable.next_orange_ic);
        alertDialogLayout.setBackgroundResource(R.drawable.frame_orange);

        ratingBar.setRating(rating);

        int totalRating = rating - dataBase.getRating(levelNum);

        if (totalRating == 1) {

            dataBase.updateScore(dataBase.getScore() + 5);

            dataBase.updateRating(rating, levelNum);

            addNewScore.setText("5+");
        } else if (totalRating == 2) {

            dataBase.updateScore(dataBase.getScore() + 10);

            dataBase.updateRating(rating, levelNum);

            addNewScore.setText("10+");
        } else if (totalRating == 3) {

            dataBase.updateScore(dataBase.getScore() + 15);

            dataBase.updateRating(rating, levelNum);

            addNewScore.setText("15+");
        } else {

            dataBase.updateScore(dataBase.getScore() + 1);

            addNewScore.setText("1+");

        }

        totalScore.setText("" + dataBase.getScore() + "");

        showLevels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ImagesLevels.class));
                finish();
            }
        });
    }

    public void setAnimation(ViewGroup viewGroup) {

        viewGroup.setAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
    }

    public void progressAnimator(int progressFrom, int progressTo) {
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", progressFrom, progressTo);
        progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }

    @Override
    public void onBackPressed() {

        if (backPressed.equals("back")){
            startActivity(new Intent(getApplicationContext(), ImagesLevels.class));
            finish();
        }
        else {
            //nothing to speak of
        }
    }

    private void shakeItBaby() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, 10));
        } else {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(150);
        }
    }

}

