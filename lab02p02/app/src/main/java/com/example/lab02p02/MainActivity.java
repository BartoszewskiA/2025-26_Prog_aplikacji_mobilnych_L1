package com.example.lab02p02;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();
    TextView opis;
    TextView odpowiedz;
    int a,b,wynik;
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
        opis = findViewById(R.id.tv_dzialanie);
        odpowiedz = findViewById(R.id.tv_odpowiedz);
        dzialanie();
    }

    private void dzialanie() {
        a = random.nextInt(100);
        b = random.nextInt(100);

       int los = random.nextInt(2);
       if(los==1)
           wynik = a*b;
       else
           wynik = random.nextInt(10000);

        String s= a + " * " + b + " = " + wynik;
        opis.setText(s);
    }

    public void czy_prawda(View view) {
        int prawidlowy_wynik = a*b;
        if(wynik == prawidlowy_wynik) {
            odpowiedz.setText("Dobrze");
        }
        else {
            odpowiedz.setText("Źle, Powinno być "+ prawidlowy_wynik);
        }
    }

    public void czy_falsz(View view) {
        int prawidlowy_wynik = a*b;
        if(wynik != prawidlowy_wynik) {
            odpowiedz.setText("Dobrze, prawidłowy wynik to "+ prawidlowy_wynik);
        }
        else {
            odpowiedz.setText("Źle, to jest prawidłowy wynik");
        }
    }

    public void resetuj(View view) {
        dzialanie();
        odpowiedz.setText("");
    }
}