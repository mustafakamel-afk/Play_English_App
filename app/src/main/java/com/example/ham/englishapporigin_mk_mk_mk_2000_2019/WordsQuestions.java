package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class WordsQuestions extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private Button Q1Selection1, Q1Selection2, Q1Selection3;
    private Button Q2Selection1, Q2Selection2, Q2Selection3;
    private CardView Q3Selection1, Q3Selection2;
    private TextView TheQuestionTextView;
    private LinearLayout QuestionOneLayout, QuestionTwoLayout, QuestionThreeLayout;
    private ProgressBar progressBar;
    ImageView Q3Done;

    private Activity activity;

    DataBase dataBase = new DataBase(this);
    TextToSpeech textToSpeech;

    String QuestionOne, QuestionTwo, QuestionThree;
    String QuestionOneCorrectAnswer, QuestionTwoCorrectAnswer, QuestionThreeCorrectAnswer;
    String QuestionThreeSelection1, QuestionThreeSelection2, QuestionThreeSelection = null, QuestionThreeAnswer = null;

    int rating = 0, levelNum;

    String backPressed = "back"; //هذا المتغير نحتاجه لتجنب حدوث المشاكل عندما تنتهي المرحله اثناء المعالجه و اضافه النقاط

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_questions);
        activity = this;

        Q1Selection1 = (Button) findViewById(R.id.Q1_selection_1);
        Q1Selection2 = (Button) findViewById(R.id.Q1_selection_2);
        Q1Selection3 = (Button) findViewById(R.id.Q1_selection_3);
        Q2Selection1 = (Button) findViewById(R.id.Q2_selection_1);
        Q2Selection2 = (Button) findViewById(R.id.Q2_selection_2);
        Q2Selection3 = (Button) findViewById(R.id.Q2_selection_3);

        Q3Selection1 = (CardView) findViewById(R.id.Q3_selection_1);
        Q3Selection2 = (CardView) findViewById(R.id.Q3_selection_2);

        TheQuestionTextView = (TextView) findViewById(R.id.the_question_text_view);

        QuestionOneLayout = (LinearLayout) findViewById(R.id.question_one_layout);
        QuestionTwoLayout = (LinearLayout) findViewById(R.id.question_two_layout);
        QuestionThreeLayout = (LinearLayout) findViewById(R.id.question_three_layout);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        Q3Done = (ImageView) findViewById(R.id.Q3_done);

        textToSpeech = new TextToSpeech(this, this);

        Intent data = getIntent();

        levelNum = data.getExtras().getInt("levelNum");

        String[] questionOneSelections = data.getExtras().getString("QuestionOneSelections").split("/");
        String[] questionTwoSelections = data.getExtras().getString("QuestionTwoSelections").split("/");
        String[] questionThreeSelections = data.getExtras().getString("QuestionThreeSelections").split("/");

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
        QuestionThreeSelection1 = questionThreeSelections[0];
        QuestionThreeSelection2 = questionThreeSelections[1];
        QuestionThreeCorrectAnswer = data.getExtras().getString("QuestionThreeCorrectAnswer");

        TheQuestionTextView.setText(QuestionOne);

        //اول تشغيله لهذه المرحلة تظهر هذه الرسالة
        if (dataBase.getScore() == 0) {
            showHelpAlertDialog();
        }

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
        Q1Selection1.setEnabled(false);  Q1Selection2.setEnabled(false);  Q1Selection3.setEnabled(false);

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
        Q2Selection1.setEnabled(false);  Q2Selection2.setEnabled(false);  Q2Selection3.setEnabled(false);

        thread th = new thread(2);
        th.start();
    }

    public void Q3_selections(View view) {
        switch (view.getId()) {

            case R.id.Q3_selection_1:
                Q3Selection1.setCardBackgroundColor(Color.parseColor("#FF67D0FF"));
                Q3Selection2.setCardBackgroundColor(Color.WHITE);
                textToSpeech.speak(QuestionThreeSelection1, TextToSpeech.QUEUE_FLUSH, null);
                QuestionThreeAnswer = QuestionThreeSelection1;
                QuestionThreeSelection = "SelectionOne";
                break;
            case R.id.Q3_selection_2:
                Q3Selection2.setCardBackgroundColor(Color.parseColor("#FF67D0FF"));
                Q3Selection1.setCardBackgroundColor(Color.WHITE);
                textToSpeech.speak(QuestionThreeSelection2, TextToSpeech.QUEUE_FLUSH, null);
                QuestionThreeAnswer = QuestionThreeSelection2;
                QuestionThreeSelection = "SelectionTwo";
                break;
        }
    }

    public void Q3_done(View view) {
        if (QuestionThreeAnswer == null && QuestionThreeSelection == null) {
            Toast.makeText(getApplicationContext(), "يرجى اختيار احد الخيارين", Toast.LENGTH_LONG).show();
        } else if (QuestionThreeAnswer.equals(QuestionThreeCorrectAnswer) && QuestionThreeSelection.equals("SelectionOne")) {
            Q3Selection1.setCardBackgroundColor(Color.parseColor("#00e676"));
            rating += 1;
            Q3Done.setEnabled(false);
            backPressed = "";
            showToast();
            thread th = new thread(3);
            th.start();
        } else if (QuestionThreeAnswer.equals(QuestionThreeCorrectAnswer) && QuestionThreeSelection.equals("SelectionTwo")) {
            Q3Selection2.setCardBackgroundColor(Color.parseColor("#00e676"));
            rating += 1;
            Q3Done.setEnabled(false);
            backPressed = "";
            showToast();
            thread th = new thread(3);
            th.start();
        } else if (!QuestionThreeAnswer.equals(QuestionThreeCorrectAnswer) && QuestionThreeSelection.equals("SelectionOne")) {
            Q3Selection1.setCardBackgroundColor(Color.parseColor("#F44336"));
            shakeItBaby();
            Q3Done.setEnabled(false);
            backPressed = "";
            thread th = new thread(3);
            th.start();
        } else if (!QuestionThreeAnswer.equals(QuestionThreeCorrectAnswer) && QuestionThreeSelection.equals("SelectionTwo")) {
            Q3Selection2.setCardBackgroundColor(Color.parseColor("#F44336"));
            shakeItBaby();
            Q3Done.setEnabled(false);
            backPressed = "";
            thread th = new thread(3);
            th.start();
        }

    }

    @Override
    public void onInit(int i) {
        if (i != TextToSpeech.ERROR) {
            textToSpeech.setLanguage(Locale.ENGLISH);
            textToSpeech.setPitch(1.1f);
            textToSpeech.setSpeechRate(0.4f);
        }

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
                            TheQuestionTextView.setText(QuestionTwo);
                            setAnimation(TheQuestionTextView, QuestionTwoLayout);
                            progressAnimator(0,25);
                            QuestionOneLayout.setVisibility(View.GONE);
                            QuestionTwoLayout.setVisibility(View.VISIBLE);

                        } else if (x == 0 && QuestionNumber == 2) {
                            TheQuestionTextView.setText(QuestionThree);
                            setAnimation(TheQuestionTextView, QuestionThreeLayout);
                            progressAnimator(25,50);
                            QuestionTwoLayout.setVisibility(View.GONE);
                            QuestionThreeLayout.setVisibility(View.VISIBLE);
                        } else if (x == 0 && QuestionNumber == 3) {
                            progressAnimator(50,75);
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
                startActivity(new Intent(getApplicationContext(), WordsLevels.class));
                finish();
            }
        });
    }

    public void setAnimation(View view, ViewGroup viewGroup) {

        view.setAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));

        viewGroup.setAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
    }

    public void progressAnimator(int progressFrom, int progressTo){
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", progressFrom, progressTo);
        progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }

    @Override
    public void onBackPressed() {

        if (backPressed.equals("back")){
            startActivity(new Intent(getApplicationContext(), WordsLevels.class));
            finish();
        }
        else {
            //nothing to speak of
        }


    }

    public void showHelpAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" ");
        builder.setIcon(R.drawable.help_ic);
        builder.setCancelable(false);

        TextView textView = new TextView(this);
        textView.setText("اعطي معاني الكلمات");
        textView.setPadding(40, 40, 40, 40);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(24);
        textView.setTextColor(Color.parseColor("#2196F3"));
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

    public void shakeItBaby() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, 10));
        } else {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(150);
        }
    }

    public void showToast(){
        Toast toast = Toast.makeText(this,QuestionThreeCorrectAnswer, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);

        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.background_blue_1);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(32);
        textView.setPadding(24, 24, 24, 24);
        textView.setText(QuestionThreeCorrectAnswer);

        toast.setView(textView);
        toast.show();
    }

}
