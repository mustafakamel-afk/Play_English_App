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

public class WordsLevels extends AppCompatActivity {

    DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_list);

        ListView listView = (ListView) findViewById(R.id.list_view);

        //The Levels
        final ArrayList<Level> levels = new ArrayList<Level>();
        levels.add(new Level(1, dataBase.getRating(1)));
        levels.add(new Level(2, dataBase.getRating(2)));
        levels.add(new Level(3, dataBase.getRating(3)));
        levels.add(new Level(4, dataBase.getRating(4)));
        levels.add(new Level(5, dataBase.getRating(5)));
        levels.add(new Level(6, dataBase.getRating(6)));
        levels.add(new Level(7, dataBase.getRating(7)));
        levels.add(new Level(8, dataBase.getRating(8)));
        levels.add(new Level(9, dataBase.getRating(9)));
        levels.add(new Level(10, dataBase.getRating(10)));
        levels.add(new Level(11, dataBase.getRating(11)));
        levels.add(new Level(12, dataBase.getRating(12)));
        levels.add(new Level(13, dataBase.getRating(13)));
        levels.add(new Level(14, dataBase.getRating(14)));
        levels.add(new Level(15, dataBase.getRating(15)));
        levels.add(new Level(16, dataBase.getRating(16)));
        levels.add(new Level(17, dataBase.getRating(17)));
        levels.add(new Level(18, dataBase.getRating(18)));
        levels.add(new Level(19, dataBase.getRating(19)));
        levels.add(new Level(20, dataBase.getRating(20)));
        levels.add(new Level(21, dataBase.getRating(21)));
        levels.add(new Level(22, dataBase.getRating(22)));
        levels.add(new Level(23, dataBase.getRating(23)));
        levels.add(new Level(24, dataBase.getRating(24)));
        levels.add(new Level(25, dataBase.getRating(25)));
        levels.add(new Level(26, dataBase.getRating(26)));
        levels.add(new Level(27, dataBase.getRating(27)));
        levels.add(new Level(28, dataBase.getRating(28)));
        levels.add(new Level(29, dataBase.getRating(29)));
        levels.add(new Level(30, dataBase.getRating(30)));


        LevelAdapter adapter = new LevelAdapter(this, levels, "blue");
        listView.setAdapter(adapter);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("LastLevelFirstStage", MODE_PRIVATE);
        listView.setSelection(sharedPreferences.getInt("lastLevel", 0));


        //theData
        final ArrayList<TheData> theData = new ArrayList<TheData>();
        //1-10
        theData.add(new TheData("Five", "اثني عشر", "مائة", "خمسة/اربعة/ثمانية", "twelve/twenty/eleven", "hundred/thousand", "خمسة", "twelve", "hundred"));
        theData.add(new TheData("Red", "برتقالي", "اسود", "ازرق/اصفر/احمر", "green/orange/pink", "white/black", "احمر", "orange", "black"));
        theData.add(new TheData("School", "يتعلم", "طالب", "جامعة/مدرسة/مستشفى", "teach/learn/write", "student/teacher", "مدرسة", "learn", "student"));
        theData.add(new TheData("Sunday", "الجمعه", "الاربعاء", "الخميس/الاحد/السبت", "monday/tuesday/friday", "wednesday/saturday", "الاحد", "friday", "wednesday"));
        theData.add(new TheData("July", "غرب", "أمس", "يوليو/يونيو/مارس", "north/east/west", "tomorrow/yesterday", "يوليو", "west", "yesterday"));
        theData.add(new TheData("Century", "شهر", "الظهر", "قرن/سنه/عهد", "month/year/day", "noon/evening", "قرن", "month", "noon"));
        theData.add(new TheData("Midday", "خريف", "شروق الشمس", "منتصف اليوم/منتصف الليل/الفجر", "winter/spring/autumn", "sunrise/sunset", "منتصف اليوم", "autumn", "sunrise"));
        theData.add(new TheData("Son", "حفيد", "ذكر", "حفيد/بنت/ابن", "grandson/granddaughter/grandmother", "male/female", "ابن", "grandson", "male"));
        theData.add(new TheData("Aunt", "ابنة الأخ", "زوجه", "عم/عمة/ابن العم", "niece/nephew/cousin", "wife/husband", "عمة", "niece", "wife"));
        theData.add(new TheData("Dinner", "مخبوز", "ساخن", "فطور/غداء/عشاء", "baked/roasted/boiled", "hot/cold", "عشاء", "baked", "hot"));
        //11-20
        theData.add(new TheData("Bride", "زفاف", "اعزب", "عروس/عريس/زواج", "wedding/ceremony/competition", "single/married", "عروس", "wedding", "single"));
        theData.add(new TheData("The taste", "الشم", "الحواس", "السمع/الشم/التذوق", "the smell/the hearing/the sight", "the senses/the scenes", "التذوق", "the smell", "the senses"));
        theData.add(new TheData("Muscles", "معدة", "إصبع قدم", "اعصاب/عظام/عضلات", "stomach/brain/spleen", "toe/foot", "عضلات", "stomach", "toe"));
        theData.add(new TheData("Coward", "تقي", "خجول", "جبان/خائف/امين", "pious/deceiver/bashful", "bashful/jealous", "جبان", "pious", "bashful"));
        theData.add(new TheData("Stupid", "حكيم", "شجاع", "ذكي/غير مهذب/غبي", "wise/liar/cruel", "brave/Weak", "غبي", "wise", "brave"));
        theData.add(new TheData("Volcano", "بحيرة", "صحراء", "غابة/جبل/بركان", "sea/river/lake", "desert/sand", "بركان", "lake", "desert"));
        theData.add(new TheData("valley", "مزرعة", "جزيرة", "تل/ضفة/وادي", "field/farm/oasis", "island/mountain", "وادي", "farm", "island"));
        theData.add(new TheData("the climate", "ضباب", "برق", "المناخ/الطقس/التضاريس", "fog/wind/dew", "thunder/lightning", "المناخ", "fog", "lightning"));
        theData.add(new TheData("Poet", "صائغ", "ممثل", "رسام/مؤلف/شاعر", "goldsmith/jeweller/porter", "actor/actress", "شاعر", "goldsmith", "actor"));
        theData.add(new TheData("Perfume", "شال", "حریر", "قلادة/جوهرة/عطر", "shawl/coat/fez", "silk/wool", "عطر", "shawl", "silk"));
        //21-30
        theData.add(new TheData("Cursor", "متصفح الانترنت", "القرص الصلب", "بصمة/مزود/مؤشر", "browser/application/server", "hard drive/hardware", "مؤشر", "browser", "hard drive"));
        theData.add(new TheData("Fever", "حقنة", "جرح", "صداع/سعال/حُمى", "suppository/injection/vaccination", "wound/burn", "حُمى", "injection", "wound"));
        theData.add(new TheData("Peanut", "زبيب", "بندق", "جوز/لوز/الفول السوداني", "pistachio/raisin/almond", "hazelnut/walnut", "الفول السوداني", "raisin", "hazelnut"));
        theData.add(new TheData("Ruins", "حضارة", "قبر", "حضارات/معابد/آثار", "civilization/cradle/holy", "tomb/Cemetery", "آثار", "civilization", "tomb"));
        theData.add(new TheData("Investment", "ربح", "قرض", "استثمار/ادخار/استهلاك", "profit/cost/stocks", "loan/interest", "استثمار", "profit", "loan"));
        theData.add(new TheData("Investigation", "ضابط", "سجن", "عقوبة/اعتقال/تحقيق", "soldier/officer/investigator", "prison/cage", "تحقيق", "officer", "prison"));
        theData.add(new TheData("Tailored", "تجول", "فرصة", "تناسب/تناغم/مرونه", "wander/observe/practice", "situation/opportunity", "تناسب", "wander", "opportunity"));
        theData.add(new TheData("Resource", "زراعة", "قابلة للتجديد", "معادن/موارد/نفايات", "agriculture/industry/politics", "renewable/efficient", "موارد", "agriculture", "renewable"));
        theData.add(new TheData("Qualifications", "شاغر", "متنوع", "عادات/مؤهلات/صفات", "vacant/preoccupied/unemployed", "various/similar", "مؤهلات", "vacant", "various"));
        theData.add(new TheData("Suggestions", "متطلبات", "المعرفة", "اقتراحات/احتياجات/مسؤوليات", "needs/requirements/aspirations", "knowledgeable/lack of knowledge", "اقتراحات", "requirements", "knowledgeable"));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                sharedPreferences.edit().putInt("lastLevel", position).apply();

                Intent intent = new Intent(getApplicationContext(), WordsQuestions.class);

                intent.putExtra("QuestionOne", theData.get(position).getmTheQuestionOne());
                intent.putExtra("QuestionTwo", theData.get(position).getmTheQuestionTwo());
                intent.putExtra("QuestionThree", theData.get(position).getmTheQuestionThree());

                intent.putExtra("QuestionOneSelections", theData.get(position).getmQuestionOneSelections());
                intent.putExtra("QuestionTwoSelections", theData.get(position).getmQuestionTwoSelections());
                intent.putExtra("QuestionThreeSelections", theData.get(position).getmQuestionThreeSelections());

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
