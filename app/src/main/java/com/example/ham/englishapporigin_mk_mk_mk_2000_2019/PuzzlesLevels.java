package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PuzzlesLevels extends AppCompatActivity {

    DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_list);

        ListView listView = (ListView) findViewById(R.id.list_view);


        //The Levels
        final ArrayList<Level> levels = new ArrayList<Level>();
        levels.add(new Level(51, dataBase.getRating(51)));
        levels.add(new Level(52, dataBase.getRating(52)));
        levels.add(new Level(53, dataBase.getRating(53)));
        levels.add(new Level(54, dataBase.getRating(54)));
        levels.add(new Level(55, dataBase.getRating(55)));
        levels.add(new Level(56, dataBase.getRating(56)));
        levels.add(new Level(57, dataBase.getRating(57)));
        levels.add(new Level(58, dataBase.getRating(58)));
        levels.add(new Level(59, dataBase.getRating(59)));
        levels.add(new Level(60, dataBase.getRating(60)));
        levels.add(new Level(61, dataBase.getRating(61)));
        levels.add(new Level(62, dataBase.getRating(62)));
        levels.add(new Level(63, dataBase.getRating(63)));
        levels.add(new Level(64, dataBase.getRating(64)));
        levels.add(new Level(65, dataBase.getRating(65)));
        levels.add(new Level(66, dataBase.getRating(66)));
        levels.add(new Level(67, dataBase.getRating(67)));
        levels.add(new Level(68, dataBase.getRating(68)));
        levels.add(new Level(69, dataBase.getRating(69)));
        levels.add(new Level(70, dataBase.getRating(70)));
        levels.add(new Level(71, dataBase.getRating(71)));
        levels.add(new Level(72, dataBase.getRating(72)));
        levels.add(new Level(73, dataBase.getRating(73)));
        levels.add(new Level(74, dataBase.getRating(74)));
        levels.add(new Level(75, dataBase.getRating(75)));
        levels.add(new Level(76, dataBase.getRating(76)));
        levels.add(new Level(77, dataBase.getRating(77)));
        levels.add(new Level(78, dataBase.getRating(78)));
        levels.add(new Level(79, dataBase.getRating(79)));
        levels.add(new Level(80, dataBase.getRating(80)));

        LevelAdapter adapter = new LevelAdapter(this, levels, "purple");
        listView.setAdapter(adapter);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("LastLevelThirdStage", MODE_PRIVATE);
        listView.setSelection(sharedPreferences.getInt("lastLevel", 0));


        //51-60
        final ArrayList<TheData> theData = new ArrayList<TheData>();
        theData.add(new TheData("بيت", "start  بداية", "yes  نعم", "homee/home/houme", "neat/begin/old", "", "home", "begin", "no"));
        theData.add(new TheData("دجاجه", "beautiful  جميلة", "after  بعد", "chicken/checken/chcken", "pretty/deceive/brilliant", "", "chicken", "pretty", "before"));
        theData.add(new TheData("كبير", "select  اختار", "hot  ساخن", "larg/large/largee", "mistake/choose/dull", "", "large", "choose", "cold"));
        theData.add(new TheData("بناء", "false  خطأ", "night  ليل", "build/bulid/builde", "fresh/wrong/right", "", "build", "wrong", "day"));
        theData.add(new TheData("صّورة", "new  جديد", "fast  سريع", "pictur/picture/picutre", "extinct/modern/obsolete", "", "picture", "modern", "slow"));
        theData.add(new TheData("ما بين", "صغير  small", "hard  صعب", "between/betwean/betwene", "quick/vast/little", "", "between", "little", "easy"));
        theData.add(new TheData("معاً", "near  قريب", "boy  فتى", "togther/togethr/together", "far/close/launch", "", "together", "close", "girl"));
        theData.add(new TheData("رسالة", "come  يأتي", "happy  سعيد", "leter/letter/letterr", "fade/arrive/receive", "", "letter", "arrive", "sad"));
        theData.add(new TheData("جبل", "run  يركض", "long  طويل", "mountain/mounitain/mountaine", "hurry/achieve/hold", "", "mountain", "hurry", "short"));
        theData.add(new TheData("مشكلة", "intelligent  ذكي", "man  رجل", "problem/probelm/probleme", "droll/smart/fantastic", "", "problem", "smart", "woman"));
        //61-70

        theData.add(new TheData("علوم", "cut  قص", "open  فتح", "sceinces/scienes/sciences", "drop/crop/resolve", "", "sciences", "crop", "close"));
        theData.add(new TheData("اثناء", "answer  جواب", "quiet  هادئ", "duringe/durng/during", "reply/request/shout", "", "during", "reply", "noisy"));
        theData.add(new TheData("أفضل", "pain  ألم", "thin  نحيف", "beter/beteer/better", "rude/hurt/spot", "", "better", "hurt", "fat"));
        theData.add(new TheData("ممكن", "help  مساعدة", "future  مستقبل", "possible/posible/possble", "settle/assist/smash", "", "possible", "assist", "past"));
        theData.add(new TheData("سجل", "dangerous  خطير", "near  قريب", "record/recorde/recore", "risky/tiring/rude", "", "record", "risky", "far"));
        theData.add(new TheData("آلة", "cry  صرخة", "cloudy  غائم", "machne/machine/machinne", "expect/shout/advise", "", "machine", "shout", "sunny"));
        theData.add(new TheData("عدّة", "show  عرض", "useful  مفيد", "severale/several/sevral", "display/prick/benefit", "", "several", "display", "useless"));
        theData.add(new TheData("سؤال", "mark  علامة", "rise  ترتفع", "qustion/question/qeustion", "huge/prove/sign", "", "question", "sign", "set"));
        theData.add(new TheData("مباشر", "quiet  هادئ", "few  قليل", "direct/direact/driect", "furious/calm/smooth", "", "direct", "calm", "many"));
        theData.add(new TheData("جملة", "angry  غاضب", "pretty  جميلة", "sentenece/sentences/sentence", "furious/pleased/smooth", "", "sentence", "furious", "ugly"));
        //71-80

        theData.add(new TheData("تزامن", "department  قسم", "prince  أمير", "coinecide/concide/coincide", "region/situation/section", "", "coincide", "section", "princess"));
        theData.add(new TheData("مأساة", "bright  مُشرق", "backward للوراء", "trageedy/tragedy/trageddy", "fearless/shining/laughable", "", "tragedy", "shining", "forward"));
        theData.add(new TheData("روح", "scared  خائف", "below  أدناه", "spirit/spirt/spirite", "afraid/miserable/pleased", "", "spirit", "afraid", "above"));
        theData.add(new TheData("ربط", "crazy  مجنون", "best  الأفضل", "faten/faste/fasten", "crude/insane/gloomy", "", "fasten", "insane", "worst"));
        theData.add(new TheData("تزويد", "benefit  فائده", "bitter  مرّ", "suply/supply/suplly", "advantage/perilous/trouble", "", "supply", "advantage", "sweet"));
        theData.add(new TheData("تحليل", "dark  داكن", "wide  عريض", "analysis/analysise/analyis", "tiring/dusky/light", "", "analysis", "dusky", "narrow"));
        theData.add(new TheData("تدفق", "shame  عار", "careful  شديد الحرص", "fluxy/flux/fluxe", "fright/disgrace/enrage", "", "flux", "disgrace", "careless"));
        theData.add(new TheData("كنيسة", "rich  ثري", "clever  ذكي", "church/churchs/churche", "overjoyed/wealthy/admire", "", "church", "wealthy", "stupid"));
        theData.add(new TheData("صراع", "mood  مزاج", "common  شائع", "conflictes/conflicte/conflict", "astonishing/temper/gleaming", "", "conflict", "temper", "rare"));
        theData.add(new TheData("آلة حاسبة", "tornado  إعصار", "cruel  قاسي", "calculator/calclator/calcultor", "hazardous/twister/catastrophe", "", "calculator", "twister", "kind"));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                sharedPreferences.edit().putInt("lastLevel", position).apply();

                Intent intent = new Intent(getApplicationContext(), PuzzleQuestion.class);

                intent.putExtra("QuestionOne", theData.get(position).getmTheQuestionOne());
                intent.putExtra("QuestionTwo", theData.get(position).getmTheQuestionTwo());
                intent.putExtra("QuestionThree", theData.get(position).getmTheQuestionThree());

                intent.putExtra("QuestionOneSelections", theData.get(position).getmQuestionOneSelections());
                intent.putExtra("QuestionTwoSelections", theData.get(position).getmQuestionTwoSelections());

                intent.putExtra("QuestionOneCorrectAnswer", theData.get(position).getmQuestionOneCorrectAnswer());
                intent.putExtra("QuestionTwoCorrectAnswer", theData.get(position).getmQuestionTwoCorrectAnswer());
                intent.putExtra("QuestionThreeCorrectAnswer", theData.get(position).getmQuestionThreeCorrectAnswer());

                intent.putExtra("levelNum", levels.get(position).getmLevelNumber());

                startActivity(intent);

                finish();
            }
        });


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
