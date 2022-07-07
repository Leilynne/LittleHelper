package com.example.littlehelper;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;

import com.example.littlehelper.databinding.ActivityMainBinding;
import com.example.littlehelper.ui.ad.AdFragment;
import com.example.littlehelper.ui.ad.DiaryAd;
import com.example.littlehelper.ui.ad.NumAd;
import com.example.littlehelper.ui.ad.NumAdEvening;
import com.example.littlehelper.ui.login.LoginActivity;
import com.example.littlehelper.ui.move.Diagnostik;
import com.example.littlehelper.ui.move.DiaryMove;
import com.example.littlehelper.ui.profile.DiaryLs;
import com.example.littlehelper.ui.profile.Fragment_profile;
import com.example.littlehelper.ui.profile.NameLs;
import com.example.littlehelper.ui.profile.Risk;
import com.example.littlehelper.ui.sd.DiarySd;
import com.example.littlehelper.ui.sd.NumSd;
import com.example.littlehelper.ui.sd.NumSdEating;
import com.example.littlehelper.ui.sd.SdFragment;
import com.example.littlehelper.ui.weight.DiaryWeight;
import com.example.littlehelper.ui.weight.NumWeight;
import com.example.littlehelper.ui.weight.WeightFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        TimePickerDialog.OnTimeSetListener, SensorEventListener, StepListener{

    private ActivityMainBinding binding;
    public EditText num_systol_ad_morning, num_diastol_ad_morning, num_systol_ad_evening,
            num_diastol_ad_evening, num_text_sd_hungry, num_text_sd_eating, num_text_weight, namels, height;
    private TextView num_imt, resultRisk, mTextView, tv_steps, textViewActionMove, textViewSdResult, textViewHelp;
    private Button button_to_help;

    private DatabaseReference numAdMorning;
    private final String numsAdMorning = LoginActivity.getEmail() + " Утренние значения";

    private DatabaseReference numAdEvening;
    private final String numsAdEvening = LoginActivity.getEmail() + " Вечерние значения";

    private DatabaseReference numSdHungry;
    private final String numsSdHungry = LoginActivity.getEmail() + " До еды";

    private DatabaseReference numSdEating;
    private final String numsSsdEating = LoginActivity.getEmail() + " После еды";

    private DatabaseReference numDBWeight;
    private final String numStringweight = LoginActivity.getEmail() + " Вес";

    private DatabaseReference nameDBLs;
    private final String nameStringLs = LoginActivity.getEmail() + " Название препарата";

    private DatabaseReference db_move;
    private final String numStringMove = LoginActivity.getEmail() + "Количество шагов: ";
    public String text;

    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Количество шагов: ";

    private static final String APP_PREFERENCES = "mysettings";

    public static String APP_PREFERENCES_STEPS = "";
    public static String APP_PREFERENCES_ALARM = "Alarm";
    static SharedPreferences mSettings;
    public static  int numSteps;

    public static SharedPreferences getmSettings() {
        return mSettings;
    }

    public static int numSAdMorning;
    public static int numDAdMorning;
    public static int numSAdEvening;
    public static int numDAdEvening;
    public static String help;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        BottomNavigationView bottomNavigationView;
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.fragmentAd);
        init();

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        numSteps = Integer.parseInt(mSettings.getString(APP_PREFERENCES_STEPS, "0"));
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_GAME);


        if (mSettings.contains(APP_PREFERENCES_STEPS)) {
            tv_steps.setText("Количество шагов: " + mSettings.getString(APP_PREFERENCES_STEPS, "0"));
        }
    }

    public static String getHelp() {
        return help;
    }

    @Override
    public void onStart(){
        super.onStart();
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_GAME);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_STEPS, APP_PREFERENCES_STEPS + String.valueOf(numSteps));
        editor.apply();
        if(mSettings.contains(APP_PREFERENCES_STEPS)) {
            tv_steps.setText("Количество шагов: " + mSettings.getString(APP_PREFERENCES_STEPS, "0"));
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_GAME);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_STEPS, APP_PREFERENCES_STEPS + String.valueOf(numSteps));
        editor.apply();
        if(mSettings.contains(APP_PREFERENCES_STEPS)) {
            tv_steps.setText("Количество шагов: " + mSettings.getString(APP_PREFERENCES_STEPS, "0"));
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_GAME);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_STEPS, APP_PREFERENCES_STEPS + String.valueOf(numSteps));
        editor.apply();
        if(mSettings.contains(APP_PREFERENCES_STEPS)) {
            tv_steps.setText("Количество шагов: " + mSettings.getString(APP_PREFERENCES_STEPS, "0"));
        }

    }
    @Override
    public void onStop() {
        super.onStop();
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_GAME);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_STEPS, APP_PREFERENCES_STEPS + String.valueOf(numSteps));
        editor.apply();
        if(mSettings.contains(APP_PREFERENCES_STEPS)) {
            tv_steps.setText("Количество шагов: " + mSettings.getString(APP_PREFERENCES_STEPS, "0"));
        }
    }

   @Override
   public void onDestroy(){
       super.onDestroy();
       sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_GAME);
       SharedPreferences.Editor editor = mSettings.edit();
       editor.putString(APP_PREFERENCES_STEPS, APP_PREFERENCES_STEPS + String.valueOf(numSteps));
       editor.apply();
       if(mSettings.contains(APP_PREFERENCES_STEPS)) {
           tv_steps.setText("Количество шагов: " + mSettings.getString(APP_PREFERENCES_STEPS, "0"));
       }
   }

    AdFragment adFragment = new AdFragment();
    SdFragment sdFragment = new SdFragment();
    WeightFragment weightFragment = new WeightFragment();
    Fragment_profile fragmentProfile = new Fragment_profile();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_ad:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, adFragment).commit();
                return true;

            case R.id.navigation_sd:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, sdFragment).commit();
                return true;

            case R.id.navigation_weight:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, weightFragment).commit();
                return true;

            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentProfile).commit();
                return true;
        }
        return false;
    }
    public int getNumSteps() {
        return numSteps;
    }

    private void init(){

        num_systol_ad_morning = findViewById(R.id.num_systol_ad_morning);
        num_diastol_ad_morning = findViewById(R.id.num_diastol_ad_morning);
        num_systol_ad_evening = findViewById(R.id.num_systol_ad_evening);
        num_diastol_ad_evening = findViewById(R.id.num_diastol_ad_evening);

        numAdMorning = FirebaseDatabase.getInstance().getReference(numsAdMorning);
        numAdEvening = FirebaseDatabase.getInstance().getReference(numsAdEvening);
        numSdHungry = FirebaseDatabase.getInstance().getReference(numsSdHungry);
        numSdEating = FirebaseDatabase.getInstance().getReference(numsSsdEating);
        numDBWeight = FirebaseDatabase.getInstance().getReference(numStringweight);
        nameDBLs = FirebaseDatabase.getInstance().getReference(nameStringLs);
        db_move = FirebaseDatabase.getInstance().getReference(numStringMove);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        tv_steps = (TextView) findViewById(R.id.textad);
        textViewActionMove = findViewById(R.id.textViewActionMove);
        textViewHelp = findViewById(R.id.textViewHelp);
        button_to_help = findViewById(R.id.button_to_help);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void updateTimeText(Calendar c) {
        text = "Напоминание в: " + DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mTextView.setText(text);
        FinClass.setAlarm(text);
        APP_PREFERENCES_ALARM = text;
    }

    private void startAlarm(Calendar c) {
        text = FinClass.getAlarm();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        FinClass.setAlarm("Напоминание отключено");
        text = FinClass.getAlarm();
        mTextView.setText("Напоминание отключено");
    }

    public void onClickAddMorningAd(View view){
        String numAdSystolMorning = String.valueOf(num_systol_ad_morning.getText());
        String numAdDiastolMorning = String.valueOf(num_diastol_ad_morning.getText());

        NumAd numad = new NumAd(numAdSystolMorning, numAdDiastolMorning);
        if(!TextUtils.isEmpty(numAdSystolMorning) && !TextUtils.isEmpty(numAdDiastolMorning)){
            numAdMorning.push().setValue(numad);
            Toast.makeText(this, "Запись добавлена!", Toast.LENGTH_SHORT).show();
            numSAdMorning = Integer.parseInt(String.valueOf(num_systol_ad_morning.getText()));
            numDAdMorning = Integer.parseInt(String.valueOf(num_diastol_ad_morning.getText()));

            if(numSAdMorning > 170){
                textViewHelp.setVisibility(View.VISIBLE);
                button_to_help.setVisibility(View.VISIBLE);
                help = numSAdMorning + "/" + numDAdMorning;
            }
        } else {
            Toast.makeText(this, "Пустое поле!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickAddAdEvening(View view){
        String numAdSystolEvening = String.valueOf(num_systol_ad_evening.getText());
        String numAdDiastolEvening = String.valueOf(num_diastol_ad_evening.getText());
        NumAdEvening numadevening = new NumAdEvening(numAdSystolEvening, numAdDiastolEvening);

        if (!TextUtils.isEmpty(numAdSystolEvening) && (!TextUtils.isEmpty(numAdDiastolEvening))){
            numAdEvening.push().setValue(numadevening);
            numSAdEvening = Integer.parseInt(String.valueOf(num_systol_ad_evening.getText()));
            numDAdEvening = Integer.parseInt(String.valueOf(num_diastol_ad_evening.getText()));
            Toast.makeText(this, "Запись добавлена!", Toast.LENGTH_SHORT).show();
            if(numSAdEvening > 170){
                textViewHelp.setVisibility(View.VISIBLE);
                button_to_help.setVisibility(View.VISIBLE);
                help = numSAdEvening + "/" + numDAdEvening;
            }
        } else {
            Toast.makeText(this, "Пустое поле!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickAdDairy(View view){
        Intent i = new Intent(MainActivity.this, DiaryAd.class);
        startActivity(i);
    }
    public void onClickAddSdHungry(View view) {
        num_text_sd_hungry = findViewById(R.id.num_text_sd_hungr);
        textViewSdResult = findViewById(R.id.textViewSdResult);
        String numStringSdHungry = String.valueOf(num_text_sd_hungry.getText());
        NumSd numsd = new NumSd(numStringSdHungry);
        if(!TextUtils.isEmpty(numStringSdHungry)){
            numSdHungry.push().setValue(numsd);
            Toast.makeText(this, "Запись добавлена!", Toast.LENGTH_SHORT).show();
            Double numSd = Double.valueOf(numStringSdHungry);
            if(numSd > 5.6 && numSd < 6.3){
                textViewSdResult.setText("Уровень глюкозы натощак выше среднего. Вам следует соблюдать низкоуглеводную диету, для предотвращения развития сахарного диабета.");
            } else if(numSd >= 6.3 && numSd < 18.0){
                textViewSdResult.setText("Высокий уровень глюкозы. Вам следует обратиться к вашему лечащему врачу для проведения дополнительного исследования, либо для коррекции лечения. Также не забывайте придерживаться диеты и вовремя принимать ваши препараты.");
            } else if (numSd>18.0){
                textViewSdResult.setText("Крайне высокий уровень глюкозы. Следует принять сахароснижающие препараты или обратиться за скорой медицинской помощью.");
            } else if(numSd<5.6){
                textViewSdResult.setText("Нормальный уровень глюкозы. Так держать!");
            }
        } else {
            Toast.makeText(this, "Пустое поле!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickAddSdEating(View view) {
        textViewSdResult = findViewById(R.id.textViewSdResult);
        num_text_sd_eating = findViewById(R.id.num_text_sd_eating);
        String numStringSdEating = String.valueOf(num_text_sd_eating.getText());
        NumSdEating numSd = new NumSdEating(numStringSdEating);
        if (!TextUtils.isEmpty(numStringSdEating)){
            numSdEating.push().setValue(numSd);
            Toast.makeText(this, "Запись добавлена!", Toast.LENGTH_SHORT).show();
            Double numSdE = Double.valueOf(numStringSdEating);
            if(numSdE > 7.8 && numSdE < 11.0){
                textViewSdResult.setText("Уровень глюкозы выше среднего. Вам следует соблюдать низкоуглеводную диету, для предотвращения развития сахарного диабета.");
            } else if(numSdE >= 11.0 && numSdE < 18.0){
                textViewSdResult.setText("Высокий уровень глюкозы. Вам следует обратиться к вашему лечащему врачу для проведения дополнительного исследования, либо для коррекции лечения. Также не забывайте придерживаться диеты и вовремя принимать ваши препараты.");
            } else if (numSdE>18.0){
                textViewSdResult.setText("Крайне высокий уровень глюкозы. Следует принять сахароснижающие препараты или обратиться за скорой медицинской помощью.");
            } else if(numSdE<7.8){
                textViewSdResult.setText("Нормальный уровень глюкозы. Так держать!");
            }
        } else {
            Toast.makeText(this, "Пустое поле!", Toast.LENGTH_SHORT).show();
        }

    }
    public void onClickSdDiary(View view) {
        Intent n = new Intent(MainActivity.this, DiarySd.class);
        startActivity(n);
    }

    public void onClickAddWeight(View view) {
        num_text_weight = findViewById(R.id.num_text_weight);
        num_imt = findViewById(R.id.text_IMT);
        height = findViewById(R.id.editTextNumHeight);

        String numStringWeight = String.valueOf(num_text_weight.getText());
        String numHeight = String.valueOf(height.getText());
        NumWeight numWeight = new NumWeight(numStringWeight);
        if (!TextUtils.isEmpty(numStringWeight) && !TextUtils.isEmpty(numHeight)){
            numDBWeight.push().setValue(numWeight);
            Toast.makeText(this, "Запись добавлена!", Toast.LENGTH_SHORT).show();
            Double heighSm = Double.valueOf(numHeight)/100;
            Double imt = Double.valueOf(String.valueOf(num_text_weight.getText()))/(Math.pow(heighSm,2));

            if(imt<=16){
                num_imt.setText(Math.ceil(imt) + "\n" + "Выраженный дефицит массы тела");
            } else if (imt<18.5 && imt>16){
                num_imt.setText(Math.ceil(imt)+ "\n" + "Недостаточная масса тела");
            } else if (imt<25 && imt > 18.5){
                num_imt.setText(Math.ceil(imt) + "\n" + "Масса вашего тела в пределах нормы");
            } else if(imt<30 && imt>25){
                num_imt.setText(Math.ceil(imt)+ "\n" + "Избыточная масса тела");
            } else if (imt<35 && imt > 30){
                num_imt.setText(Math.ceil(imt) + "\n" + "Ожирение 1-й степени");
            } else if (imt < 40 && imt > 35){
                num_imt.setText(Math.ceil(imt) + "\n" + "Ожирение 2-й степени");
            } else {
                num_imt.setText(Math.ceil(imt)+ "\n" + "Ожирение 3-й степени");
            }
        } else {
            Toast.makeText(this, "Пустое поле!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickWeightDiary(View view) {
        Intent n = new Intent(MainActivity.this, DiaryWeight.class);
        startActivity(n);
    }

    public void onClickToRisk(View view) {
        Intent n = new Intent(MainActivity.this, Risk.class);
        startActivity(n);
    }

    public void onClickAddLs(View view) {
        namels= findViewById(R.id.name_ls);
        String nameStringLs = String.valueOf(namels.getText());
        NameLs nameLs = new NameLs(nameStringLs);
        if (!TextUtils.isEmpty(nameStringLs)){
            nameDBLs.push().setValue(nameLs);
            Toast.makeText(this, "Запись добавлена!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Пустое поле!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCliclToDiaryLs(View view) {
        Intent n = new Intent(MainActivity.this, DiaryLs.class);
        startActivity(n);
    }

    public void onClickLogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent( MainActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void onClickMoveDiary(View view) {
        Intent n = new Intent(MainActivity.this, DiaryMove.class);
        startActivity(n);
    }

    public void onClickAlarmOff(View view) {
                cancelAlarm();
    }

    public void onClicktoAlarmLs(View view) {
        mTextView = findViewById(R.id.textprofile);
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    @Override
    public void step(long timeNs) {
        numSteps++;
        tv_steps.setText(TEXT_NUM_STEPS + numSteps);
    }

    public void onClickListener(View view) {
        sensorManager.unregisterListener(MainActivity.this);
    }

    public void onClick(View view) {
        String numStringMove = String.valueOf(tv_steps.getText());
        NumMove numMove = new NumMove(numStringMove);
        if (!TextUtils.isEmpty(numStringMove)){
            db_move.push().setValue(numMove);
        }

        if (numSteps < 8000){
            textViewActionMove.setText("Небольшой индекс активности. Для поддержания здоровья рекомендуется " +
                    "проходить в день не менее 8000 шагов. Пешие прогулки благоприятно влияют на сердечно-сосудистую систему, " +
                    "а также снижают уровень стресса.");
        }  else if (numSteps <14999 && numSteps < 10000){
            textViewActionMove.setText("Нормальный уровень активности. Вы знаете толк в пеших прогулках, и ваш организм вам за это благодарен. Так держать!");
        } else if (numSteps > 15000){
            textViewActionMove.setText("Высокий уровень активности. Длительные пешие прогулки отлично справляются с задачей тренировки вашего сердца и сосудов, что благоприятно сказывается на самочувствии. Отличные показатели!");
        }


    }

    public void onClickToHelp(View view) {
        Intent n = new Intent(MainActivity.this, Diagnostik.class);
        startActivity(n);
    }
}