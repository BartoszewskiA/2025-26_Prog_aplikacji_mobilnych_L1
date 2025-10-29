package com.example.lab03p01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText pole_a, pole_b, pole_c;
    TextView pole_wynik;
    Button btn_oblicz, btn_reset;

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
        uchwyty();
        btn_oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a=0,b=0,c=0;
                String temp;
                temp = pole_a.getText().toString();
                if(temp.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Wypełnji pole a", Toast.LENGTH_SHORT).show();
                    return;
                }
                a = Double.parseDouble(temp);
                temp = pole_b.getText().toString();
                if(temp.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Wypełnji pole b", Toast.LENGTH_SHORT).show();
                    return;
                }
                b = Double.parseDouble(temp);
                temp = pole_c.getText().toString();
                if(temp.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Wypełnji pole c", Toast.LENGTH_SHORT).show();
                    return;
                }
                c = Double.parseDouble(temp);

//                a = Double.parseDouble(pole_a.getText().toString());
//                b = Double.parseDouble(pole_b.getText().toString());
//                c = Double.parseDouble(pole_c.getText().toString());
                pole_wynik.setText(rownanie_kw(a,b,c));
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pole_a.setText("");
                pole_b.setText("");
                pole_c.setText("");
                pole_wynik.setText("");
            }
        });

    }

    private void uchwyty() {
        pole_a = findViewById(R.id.et_a);
        pole_b = findViewById(R.id.et_b);
        pole_c = findViewById(R.id.et_c);
        pole_wynik = findViewById(R.id.tv_wynik);
        btn_oblicz = findViewById(R.id.btn_oblicz);
        btn_reset = findViewById(R.id.btn_reset);
    }

//    private String rownanie_kw(double a, double b, double c) {
//        String wynik ="";
//        double delta = b*b-4*a*c;
//        if(delta<0)
//        {
//            wynik = "Brak rozwiązań";
//        }
//        else if(delta==0)
//        {
//            wynik = "Jedno rozwiązanie: "+(-b/(2*a));
//        }
//        else
//        {
//            wynik = "Dwa rozwiązania: "+(-b-Math.sqrt(delta))/(2*a)+" oraz "+(-b+Math.sqrt(delta))/(2*a);
//        }
//        return wynik;
//    }

    private String rownanie_kw(double a, double b, double c) {

        if(a==0) {
            return "To nie jest równanie kwadratowe";
        }
        double delta = b * b - 4 * a * c;
        if(delta < 0) {
            return "Brak rozwiązań";
        }
        if(delta == 0) {
            double x = Math.round(-b / (2 * a)*10000)/10000.0;
            return "Jedno rozwiązanie: " + x;
        }
        double x1 = Math.round((-b - Math.sqrt(delta)) / (2 * a)*10000)/10000.0;
        double x2 = Math.round((-b + Math.sqrt(delta)) / (2 * a)*10000)/10000.0;
        return "Dwa rozwiązania: "+ x1 +" oraz "+ x2;
    }
}