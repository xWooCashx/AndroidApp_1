package com.app.my.app1;

import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    boolean czyImie = false, czyNazwisko = false, czyLiczby = false;
    EditText imie, nazwisko, liczbaOcen;
    TextView srednia;
    Button oblicz;

    protected void pokazOblicz() {
        if (czyImie && czyNazwisko && czyLiczby)
            oblicz.setVisibility(Button.VISIBLE);

        else
            oblicz.setVisibility(Button.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srednia = findViewById(R.id.srednia);
        oblicz = (Button) findViewById(R.id.oblicz);
        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zamiar = new Intent(MainActivity.this, Main2Activity.class);
                zamiar.putExtra("liczbaOcen", Integer.parseInt(liczbaOcen.getText().toString()));
                startActivityForResult(zamiar, 999);
            }
        });
        imie = (EditText) findViewById(R.id.imieInput);
        imie.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                          @Override
                                          public void onFocusChange(View v, boolean hasFocus) {
                                              if (imie.getText().toString().isEmpty() && !hasFocus) {
                                                  Toast.makeText(getBaseContext(), "imie nie moze byc puste", Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }
        );
        imie.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    czyImie = true;
                } else
                    czyImie = false;
                pokazOblicz();
            }

        });
        nazwisko = (EditText) findViewById(R.id.nazwiskoInput);
        nazwisko.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                              @Override
                                              public void onFocusChange(View v, boolean hasFocus) {
                                                  if (nazwisko.getText().toString().isEmpty() && !hasFocus) {
                                                      Toast.makeText(getBaseContext(), "nazwisko nie moze byc puste", Toast.LENGTH_SHORT).show();
                                                  }
                                              }
                                          }
        );
        nazwisko.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //s.toString();
                if (!s.toString().isEmpty()) {
                    czyNazwisko = true;
                } else
                    czyNazwisko = false;
                pokazOblicz();
            }

        });
        liczbaOcen = (EditText) findViewById(R.id.liczbaOcenInput);
        liczbaOcen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                                @Override
                                                public void onFocusChange(View v, boolean hasFocus) {
                                                    if (!czyLiczby && !hasFocus) {
                                                        Toast.makeText(getBaseContext(), "liczba ocen 5-15", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
        );
        liczbaOcen.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int liczba = 0;
                try {
                    liczba = Integer.parseInt(liczbaOcen.getText().toString());
                } catch (Exception e) {
                    czyLiczby = false;
                }
                if (liczba >= 5 && liczba <= 15)
                    czyLiczby = true;
                else
                    czyLiczby = false;
                pokazOblicz();

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 998) {
            // Bundle tobolek=data.getExtras();
            //String liczba=tobolek.getString("srednia");  //można przekazać więcej niż
            // 1 element
            //System.out.println("powrót");
            srednia.setText("Twoja srednia to: " + data.getExtras().getString("srednia"));
            if (Float.parseFloat(data.getExtras().getString("srednia")) >= 2) {
                srednia.setTextColor(Color.GREEN);
                Toast.makeText(getBaseContext(), "Pozytywna średnia", Toast.LENGTH_SHORT).show();
            } else {
                srednia.setTextColor(Color.RED);
                Toast.makeText(getBaseContext(), "Nie zaliczono", Toast.LENGTH_SHORT).show();
            }
            srednia.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("srednia1", srednia.getText().toString());
        outState.putInt("colorOfSrednia", srednia.getTextColors().getDefaultColor());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        srednia.setText(savedInstanceState.getString("srednia1"));
        srednia.setTextColor(savedInstanceState.getInt("colorOfSrednia"));
        if (!srednia.getText().equals("Srednia")) {
            if (srednia.getVisibility() == View.INVISIBLE)
                srednia.setVisibility(View.VISIBLE);

        }
    }

}
