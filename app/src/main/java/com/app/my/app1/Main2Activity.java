package com.app.my.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ArrayList dane;
    ListView listaOcen;
    int liczbaOcen;
    float srednia=0;
    Button oblicz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dane = new ArrayList<ModelOceny>();
        liczbaOcen  = getIntent().getExtras().getInt("liczbaOcen");
        for(int i=1;i<=liczbaOcen;i++){
            dane.add(new ModelOceny("ocena "+i));
        }
        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this,dane);
        listaOcen = (ListView) findViewById(R.id.listaOcen);
        listaOcen.setAdapter(adapter);

        oblicz = (Button) findViewById(R.id.obliczSrednia);
        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent zamiar = new Intent();
                for(int i=0;i<liczbaOcen;i++){
                    srednia+=((ModelOceny)dane.get(i)).getOcena();
                    //System.out.println(((ModelOceny)dane.get(i)).getOcena());
                }
                srednia/=liczbaOcen;
                zamiar.putExtra("srednia",srednia+"");
                System.out.println(srednia);
                setResult(998,zamiar);
                finish();
            }
        });

    }
}
