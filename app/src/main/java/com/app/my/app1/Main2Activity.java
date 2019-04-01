package com.app.my.app1;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ArrayList dane;
    ListView listaOcen;
    int liczbaOcen;
    float srednia = 0;
    Button oblicz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dane = new ArrayList<ModelOceny>();
        liczbaOcen = getIntent().getExtras().getInt("liczbaOcen");
        for (int i = 1; i <= liczbaOcen; i++) {
            dane.add(new ModelOceny("ocena " + i));
        }
        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, dane);
        listaOcen = (ListView) findViewById(R.id.listaOcen);

        View footerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer, null, false);
        listaOcen.addFooterView(footerView);
        listaOcen.setAdapter(adapter);

        oblicz = (Button) findViewById(R.id.obliczSrednia);
        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wypelnionoPola = true;
                for (Object ocena : dane) {
                    if (((ModelOceny) ocena).getOcena() == 0)
                        wypelnionoPola = false;

                }
                if (wypelnionoPola) {
                    Intent zamiar = new Intent();
                    for (int i = 0; i < liczbaOcen; i++) {
                        srednia += ((ModelOceny) dane.get(i)).getOcena();
                        //System.out.println(((ModelOceny)dane.get(i)).getOcena());
                    }
                    srednia /= liczbaOcen;
                    zamiar.putExtra("srednia", srednia + "");
                    System.out.println(srednia);
                    setResult(998, zamiar);
                    finish();
                } else
                    Toast.makeText(getBaseContext(), "Wypelnij oceny", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        int[] oceny = new int[dane.size()];
        for (int i = 0; i < liczbaOcen; i++) {
            oceny[i] = ((ModelOceny) dane.get(i)).getOcena();
        }
//        for (Object ocena : dane) {
//            oceny.add(((ModelOceny)ocena).getOcena());
//        }
        outState.putIntArray("oceny", oceny);
        super.onSaveInstanceState(outState);
        System.out.println("zapisano oceny");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int[] oceny = savedInstanceState.getIntArray("oceny");
        int i = 0;
        for (Object ocena : dane) {
            ((ModelOceny) ocena).setOcena(oceny[i]);
            i++;
        }
        System.out.println("wczytano oceny");
    }

}
