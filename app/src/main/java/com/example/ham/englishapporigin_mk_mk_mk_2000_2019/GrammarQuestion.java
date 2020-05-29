package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.animation.ObjectAnimator;
import android.app.Activity;
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
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class GrammarQuestion extends AppCompatActivity implements TextWatcher {

    private Button Q3Selection1, Q3Selection2, Q3Selection3;
    private RelativeLayout QuestionOneLayout, QuestionTwoLayout;
    private LinearLayout QuestionThreeLayout;
    private ProgressBar progressBar;
    private TextView theQuestionTextView1, theQuestionTextView2;
    private EditText QuestionOneEditText, QuestionTwoEditText;
    private Activity activity;
    private ImageView Q1Done, Q2Done;

    DataBase dataBase = new DataBase(this);

    String QuestionOne, QuestionTwo;
    String QuestionOneCorrectAnswer, QuestionTwoCorrectAnswer, QuestionThreeCorrectAnswer;

    int rating = 0, levelNum;

    String backPressed = "back"; //هذا المتغير نحتاجه لتجنب حدوث المشاكل عندما تنتهي المرحله اثناء المعالجه و اضافه النقاط


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_question);
        activity = this;

        Q3Selection1 = (Button) findViewById(R.id.Q3_selection_1);
        Q3Selection2 = (Button) findViewById(R.id.Q3_selection_2);
        Q3Selection3 = (Button) findViewById(R.id.Q3_selection_3);

        theQuestionTextView1 = (TextView) findViewById(R.id.the_question_text_view_1);
        theQuestionTextView2 = (TextView) findViewById(R.id.the_question_text_view_2);
        Q1Done = (ImageView) findViewById(R.id.Q1_done);
        Q2Done = (ImageView) findViewById(R.id.Q2_done);

        QuestionOneLayout = (RelativeLayout) findViewById(R.id.question_one_layout);
        QuestionTwoLayout = (RelativeLayout) findViewById(R.id.question_two_layout);
        QuestionThreeLayout = (LinearLayout) findViewById(R.id.question_three_layout);

        QuestionOneEditText = (EditText) findViewById(R.id.question_one_edit_text);
        QuestionTwoEditText = (EditText) findViewById(R.id.question_two_edit_text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        Intent data = getIntent();
        levelNum = data.getExtras().getInt("levelNum");

        String[] questionThreeSelections = data.getExtras().getString("QuestionThreeSelections").split("/");

        //question one
        QuestionOne = data.getExtras().getString("QuestionOne");
        QuestionOneCorrectAnswer = data.getExtras().getString("QuestionOneCorrectAnswer");

        //question two
        QuestionTwo = data.getExtras().getString("QuestionTwo");
        QuestionTwoCorrectAnswer = data.getExtras().getString("QuestionTwoCorrectAnswer");

        //question three
        Q3Selection1.setText(questionThreeSelections[0]);
        Q3Selection2.setText(questionThreeSelections[1]);
        Q3Selection3.setText(questionThreeSelections[2]);
        QuestionThreeCorrectAnswer = data.getExtras().getString("QuestionThreeCorrectAnswer");

        theQuestionTextView2.setText(QuestionOne);

        QuestionOneEditText.addTextChangedListener(this);
        QuestionTwoEditText.addTextChangedListener(this);
    }

    public void Q1_done(View view) {
        if (QuestionOneEditText.getText().toString().trim().equalsIgnoreCase(QuestionOneCorrectAnswer)) {
            QuestionOneEditText.setBackgroundResource(R.drawable.background_right_answer);
            QuestionOneEditText.setTextColor(Color.parseColor("#FFFFFFFF"));

            QuestionOneEditText.setEnabled(false);
            Q1Done.setEnabled(false);
            rating += 1;
            thread th = new thread(1);
            th.start();
        } else if (QuestionOneEditText.getText().toString().trim().equalsIgnoreCase("")) {
            //nothing
        } else if (!QuestionOneEditText.getText().toString().trim().equalsIgnoreCase(QuestionOneCorrectAnswer)) {
            QuestionOneEditText.setBackgroundResource(R.drawable.background_wrong_answer);
            QuestionOneEditText.setTextColor(Color.parseColor("#FFFFFFFF"));

            QuestionOneEditText.setEnabled(false);
            Q1Done.setEnabled(false);
            shakeItBaby();
            thread th = new thread(1);
            th.start();
        }


    }

    public void Q2_done(View view) {

        if (QuestionTwoEditText.getText().toString().trim().equalsIgnoreCase(QuestionTwoCorrectAnswer)) {
            QuestionTwoEditText.setBackgroundResource(R.drawable.background_right_answer);
            QuestionTwoEditText.setTextColor(Color.parseColor("#FFFFFFFF"));

            QuestionTwoEditText.setEnabled(false);
            Q2Done.setEnabled(false);
            rating += 1;
            thread th = new thread(2);
            th.start();
        } else if (QuestionTwoEditText.getText().toString().trim().equalsIgnoreCase("")) {
            //nothing
        } else if (!QuestionTwoEditText.getText().toString().trim().equalsIgnoreCase(QuestionTwoCorrectAnswer)) {
            QuestionTwoEditText.setBackgroundResource(R.drawable.background_wrong_answer);
            QuestionTwoEditText.setTextColor(Color.parseColor("#FFFFFFFF"));

            QuestionTwoEditText.setEnabled(false);
            Q2Done.setEnabled(false);
            shakeItBaby();
            thread th = new thread(2);
            th.start();
        }

    }

    public void Q3_selections(View view) {
        switch (view.getId()) {

            case R.id.Q3_selection_1:
                if (Q3Selection1.getText().toString().equals(QuestionThreeCorrectAnswer)) {
                    Q3Selection1.setBackgroundResource(R.drawable.background_right_answer);
                    rating += 1;
                } else {
                    Q3Selection1.setBackgroundResource(R.drawable.background_wrong_answer);
                    shakeItBaby();
                }
                break;
            case R.id.Q3_selection_2:
                if (Q3Selection2.getText().toString().equals(QuestionThreeCorrectAnswer)) {
                    Q3Selection2.setBackgroundResource(R.drawable.background_right_answer);
                    rating += 1;
                } else {
                    Q3Selection2.setBackgroundResource(R.drawable.background_wrong_answer);
                    shakeItBaby();
                }
                break;
            case R.id.Q3_selection_3:
                if (Q3Selection3.getText().toString().equals(QuestionThreeCorrectAnswer)) {
                    Q3Selection3.setBackgroundResource(R.drawable.background_right_answer);
                    rating += 1;
                } else {
                    Q3Selection3.setBackgroundResource(R.drawable.background_wrong_answer);
                    shakeItBaby();
                }
                break;
        }

        //لتجنب الاخطاء
        Q3Selection1.setEnabled(false);  Q3Selection2.setEnabled(false);  Q3Selection3.setEnabled(false);

        backPressed = "";

        thread th = new thread(3);
        th.start();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String theAnswerQ1 = QuestionOneEditText.getText().toString();

        String theAnswerQ2 = QuestionTwoEditText.getText().toString();

        //theAnswerQ1
        if (theAnswerQ1.trim().length() > 0) {

            Q1Done.setAlpha(0.9f);

        }  else {

            Q1Done.setAlpha(0.4f);
        }

        //theAnswerQ2
        if (theAnswerQ2.trim().length() > 0) {

            Q2Done.setAlpha(0.9f);

        }  else {

            Q2Done.setAlpha(0.4f);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

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
                            theQuestionTextView1.setText("اكتب الاجابة الصحيحة :");
                            theQuestionTextView2.setText(QuestionTwo);
                            setAnimation(theQuestionTextView1, theQuestionTextView2, QuestionTwoLayout);
                            progressAnimator(0, 25);
                            QuestionOneLayout.setVisibility(View.GONE);
                            QuestionTwoLayout.setVisibility(View.VISIBLE);
                        } else if (x == 0 && QuestionNumber == 2) {
                            theQuestionTextView2.setVisibility(View.GONE);
                            theQuestionTextView1.setText("أختار الجملة الصحيحة :");
                            theQuestionTextView1.setAnimation(AnimationUtils.loadAnimation(GrammarQuestion.this, R.anim.alpha));
                            QuestionThreeLayout.setAnimation(AnimationUtils.loadAnimation(GrammarQuestion.this, R.anim.alpha));
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

        freeScoreTextView.setBackgroundResource(R.drawable.background_indigo);
        showLevels.setImageResource(R.drawable.next_indigo_ic);
        alertDialogLayout.setBackgroundResource(R.drawable.frame_indigo);

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

                startActivity(new Intent(getApplicationContext(), GrammarsLevels.class));
                finish();
            }
        });
    }

    public void setAnimation(View view1, View view2, ViewGroup viewGroup) {

        view1.setAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));

        view2.setAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));

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
            startActivity(new Intent(getApplicationContext(), GrammarsLevels.class));
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
