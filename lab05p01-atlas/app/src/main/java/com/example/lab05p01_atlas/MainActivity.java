package com.example.lab05p01_atlas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv_nazwa, tv_opis;
    ImageView iv_grafika;
    Button btn_poprzedni, btn_następny;


    String[] nazwy;
    String[] opisy;
    int[] grafiki = {   R.drawable.prawdziwek,
                        R.drawable.podgrzybek,
                        R.drawable.muchomor,
                        R.drawable.kania};
    int pozycja=0;
    Random random = new Random();

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
        pozycja = random.nextInt(grafiki.length);
        nazwy = getResources().getStringArray(R.array.nazwy);
        opisy = getResources().getStringArray(R.array.opisy);
        znajdzKontrolki();
        tv_nazwa.setText(nazwy[pozycja]);
        tv_opis.setText(opisy[pozycja]);
        iv_grafika.setImageResource(grafiki[pozycja]);
        sprawdz_przyciski();
        obslugaPrzyciskow();
    }

    private void obslugaPrzyciskow() {
        View.OnClickListener sluchacz = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int Id = v.getId();
               if(Id==R.id.btn_np)
                    pozycja++;
               else if(Id==R.id.btn_pp)
                    pozycja--;
               //------------------------
                sprawdz_przyciski();

                tv_nazwa.setText(nazwy[pozycja]);
                tv_opis.setText(opisy[pozycja]);
                iv_grafika.setImageResource(grafiki[pozycja]);
            }
        };
        btn_poprzedni.setOnClickListener(sluchacz);
        btn_następny.setOnClickListener(sluchacz);
    }

    private void sprawdz_przyciski() {
        if(pozycja==grafiki.length-1)
            btn_następny.setEnabled(false);
        else
            btn_następny.setEnabled(true);
        if(pozycja==0)
            btn_poprzedni.setEnabled(false);
        else
            btn_poprzedni.setEnabled(true);
    }

    private void znajdzKontrolki() {
        tv_nazwa = findViewById(R.id.tv_nazwa_grzyba);
        tv_opis = findViewById(R.id.tv_opis);
        iv_grafika = findViewById(R.id.iv_01);
        btn_poprzedni = findViewById(R.id.btn_pp);
        btn_następny = findViewById(R.id.btn_np);
    }
}