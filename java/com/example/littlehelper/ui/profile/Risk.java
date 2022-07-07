package com.example.littlehelper.ui.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.littlehelper.R;

public class Risk extends AppCompatActivity {
    private CheckBox checkBoxQuest1Ad, checkBoxQuest1IM,checkBoxQuest1Insult,checkBozQuest2HeadAche,
            checkBoxQuest2Dizzines,checkBoxQuest2ChestAche,checkBoxQest2Apnea, checkBoxQuest2Tachicardia,
            checkBoxQuest2LegAche,checkBoxQyest2Ad140,checkBoxStressWorry,checkBoxStressUnsleep,
            checkBoxStressLoudlySounds, checkBoxStresUnhappyToHappy, checkBoxStressUnworked,checkBoxStressApathy;

    private RadioButton radioButtonSmokeYes, radioButtonDrinkRarely,
            radioButtonDrinkOften, radioButtonSportRarely, radioButtonSportNormal, radioButtonEatNo, radioButtonEatRarely, radioButtonEatOften,
            radioButtonHolesterineNo,radioButtonHolesterineReallyBad, radioButtonHolesterineYesControle,radioButtonMale, radioButtonAge39, radioButtonAge49, radioButtonAge59, radioButtonAge69,
            radioButtonAge79;


    private TextView textResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_anketa);
        init();
    }
    private void init(){
        checkBoxQuest1Ad = findViewById(R.id.checkBoxQuest1Ad);
        checkBoxQuest1IM = findViewById(R.id.checkBoxQuest1IM);
        checkBoxQuest1Insult = findViewById(R.id.checkBoxQuest1Insult);
        checkBozQuest2HeadAche = findViewById(R.id.checkBozQuest2HeadAche);
        checkBoxQuest2Dizzines = findViewById(R.id.checkBoxQuest2Dizzines);
        checkBoxQuest2ChestAche = findViewById(R.id.checkBoxQuest2ChestAche);
        checkBoxQest2Apnea = findViewById(R.id.checkBoxQest2Apnea);
        checkBoxQuest2Tachicardia = findViewById(R.id.checkBoxQuest2Tachicardia);
        checkBoxQuest2LegAche = findViewById(R.id.checkBoxQuest2LegAche);
        checkBoxQyest2Ad140 = findViewById(R.id.checkBoxQyest2Ad140);
        checkBoxStressWorry = findViewById(R.id.checkBoxStressWorry);
        checkBoxStressUnsleep = findViewById(R.id.checkBoxStressUnsleep);
        checkBoxStressLoudlySounds = findViewById(R.id.checkBoxStressLoudlySounds);
        checkBoxStresUnhappyToHappy = findViewById(R.id.checkBoxStresUnhappyToHappy);
        checkBoxStressUnworked = findViewById(R.id.checkBoxStressUnworked);
        checkBoxStressApathy = findViewById(R.id.checkBoxStressApathy);

        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonAge39 = findViewById(R.id.radioButtonAge39);
        radioButtonAge49 = findViewById(R.id.radioButtonAge49);
        radioButtonAge59 = findViewById(R.id.radioButtonAge59);
        radioButtonAge69 = findViewById(R.id.radioButtonAge69);
        radioButtonAge79 = findViewById(R.id.radioButtonAge79);
        radioButtonSmokeYes = findViewById(R.id.radioButtonSmokeYes);
        radioButtonDrinkRarely = findViewById(R.id.radioButtonDrinkRarely);
        radioButtonDrinkOften = findViewById(R.id.radioButtonDrinkOften);
        radioButtonSportRarely = findViewById(R.id.radioButtonSportRarely);
        radioButtonSportNormal = findViewById(R.id.radioButtonSportNormal);
        radioButtonEatNo = findViewById(R.id.radioButtonEatNo);
        radioButtonEatRarely = findViewById(R.id.radioButtonEatRarely);
        radioButtonEatOften = findViewById(R.id.radioButtonEatOften);
        radioButtonHolesterineNo = findViewById(R.id.radioButtonHolesterineNo);
        radioButtonHolesterineReallyBad = findViewById(R.id.radioButtonHolesterineReallyBad);
        radioButtonHolesterineYesControle = findViewById(R.id.radioButtonHolesterineYesControle);

        textResult = findViewById(R.id.textViewResultRisk);

    }

    public void onClickResult(View view) {
        int result = 0;
        if (radioButtonMale.isChecked())
            result += 1;
        if(radioButtonAge39.isChecked())
            result += 1;
        if(radioButtonAge49.isChecked())
            result += 2;
        if(radioButtonAge59.isChecked())
            result += 3;
        if (radioButtonAge69.isChecked())
            result += 4;
        if(radioButtonAge79.isChecked())
            result += 5;
        if(checkBoxQuest1Ad.isChecked())
            result += 1;
        if(checkBoxQuest1IM.isChecked() || checkBoxQuest1Insult.isChecked())
            result += 2;
        if(checkBozQuest2HeadAche.isChecked())
            result += 1;
        if (checkBoxQuest2Dizzines.isChecked() || checkBoxQuest2LegAche.isChecked() || checkBoxQyest2Ad140.isChecked())
            result += 2;
        if (checkBoxQuest2ChestAche.isChecked() || checkBoxQuest2Tachicardia.isChecked())
            result += 4;
        if(checkBoxQest2Apnea.isChecked())
            result += 3;
        if (checkBoxStressWorry.isChecked())
            result += 1;
        if (checkBoxStressUnsleep.isChecked() || checkBoxStressLoudlySounds.isChecked() || checkBoxStresUnhappyToHappy.isChecked())
            result += 2;
        if (checkBoxStressUnworked.isChecked() || checkBoxStressApathy.isChecked())
            result += 3;
        if (radioButtonSmokeYes.isChecked())
            result += 2;
        if (radioButtonDrinkRarely.isChecked())
            result += 1;
        if(radioButtonDrinkOften.isChecked())
            result += 2;
        if (radioButtonSportRarely.isChecked())
            result += 2;
        if (radioButtonSportNormal.isChecked())
            result += 1;
        if(radioButtonEatNo.isChecked())
            result += 3;
        if(radioButtonEatRarely.isChecked())
            result += 2;
        if (radioButtonEatOften.isChecked())
            result += 1;
        if (radioButtonHolesterineNo.isChecked())
            result += 2;
        if(radioButtonHolesterineReallyBad.isChecked())
            result += 3;
        if(radioButtonHolesterineYesControle.isChecked())
            result += 1;
        if(result<6){
            textResult.setText("Риск развития сердечно-сосудистых заболеваний отсутствует.\n"+
                    "Вы – молодец! Делитесь опытом здорового образа жизни со всеми друзьями и знакомыми.");
        } else if (result <13 && result >6){
            textResult.setText("Умеренный риск.\n"+"Не забывайте о регулярной диспансеризации. " +" В Ваших силах" +
                    " сохранить и преумножить Ваше здоровье." +
                    " Откажитесь от вредных привычек, упорядочьте" +
                    " режим дня, займитесь физкультурой и спортом," +
                    " чаще бывайте на свежем воздухе.");
        } else if(result <27 && result >13){
            textResult.setText("Риск значительный.\n"+"Ваше здоровье нуждается в безотлагательной" +
                    " медицинской коррекции. Вам следует обратится к" +
                    " участковому врачу для дополнительных исследований и получении рекомендаций по сохранению вашего здоровья.");
        } else if(result>27){
            textResult.setText("Высокий риск.\n"+"Вам необходимо срочно обратится к участковому" +
                    " врачу для проведения комплексного" +
                    " обследования и назначения лечения. При" +
                    " необходимости Вас направят на консультацию к" +
                    " кардиологу или кардиохирургу");
        }
    }

}
