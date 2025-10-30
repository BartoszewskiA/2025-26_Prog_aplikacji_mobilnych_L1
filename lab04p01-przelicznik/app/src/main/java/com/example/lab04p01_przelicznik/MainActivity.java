package com.example.lab04p01_przelicznik;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et_01, et_02;
    TextView tv_01, tv_02;
    SeekBar suwak;
    RadioGroup rg_01, rg_02;
    RadioButton rb_01, rb_02, rb_03, rb_04, rb_05, rb_06;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        znajdz_kontrolki();
        obsluga_suwaka();
        obsluga_radio();
        przelicz();
    }

    private void obsluga_radio() {
        RadioGroup.OnCheckedChangeListener sluchacz = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                przelicz();
                if(rb_01.isChecked()) tv_01.setText("PLN");
                else if(rb_02.isChecked()) tv_01.setText("EURO");
                else if(rb_03.isChecked()) tv_01.setText("DOLAR");

                if(rb_04.isChecked()) tv_02.setText("PLN");
                else if(rb_05.isChecked()) tv_02.setText("EURO");
                else if(rb_06.isChecked()) tv_02.setText("DOLAR");

            }
        };
        rg_01.setOnCheckedChangeListener(sluchacz);
        rg_02.setOnCheckedChangeListener(sluchacz);
    }

    private void obsluga_suwaka() {
        suwak.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                et_01.setText(String.valueOf(progress));
                przelicz();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    private void znajdz_kontrolki() {
        et_01 = findViewById(R.id.et_01);
        et_02 = findViewById(R.id.et_02);
        tv_01 = findViewById(R.id.tv_01);
        tv_02 = findViewById(R.id.tv_02);
        suwak = findViewById(R.id.sb_01);
        rg_01 = findViewById(R.id.rg_01);
        rg_02 = findViewById(R.id.rg_02);
        rb_01 = findViewById(R.id.rb_01);
        rb_02 = findViewById(R.id.rb_02);
        rb_03 = findViewById(R.id.rb_03);
        rb_04 = findViewById(R.id.rb_04);
        rb_05 = findViewById(R.id.rb_05);
        rb_06 = findViewById(R.id.rb_06);
    }

    private void przelicz() {
        double kurs_EURO = 4.2;
        double kurs_DOLAR = 3.8;
        double wynik =0;
        double posrednia_PLN =0;

        if(rb_01.isChecked())
            posrednia_PLN = Double.valueOf(et_01.getText().toString());
        else if(rb_02.isChecked())
            posrednia_PLN = Double.valueOf(et_01.getText().toString()) * kurs_EURO;
        else if(rb_03.isChecked())
            posrednia_PLN = Double.valueOf(et_01.getText().toString()) * kurs_DOLAR;

        if(rb_04.isChecked())
            wynik = posrednia_PLN;
        else if(rb_05.isChecked())
            wynik = posrednia_PLN / kurs_EURO;
        else if(rb_06.isChecked())
            wynik = posrednia_PLN / kurs_DOLAR;
        wynik = Math.round(wynik * 100.0) / 100.0;
        et_02.setText(String.valueOf(wynik));


    }
}