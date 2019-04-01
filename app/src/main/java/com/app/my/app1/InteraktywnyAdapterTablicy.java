package com.app.my.app1;

import android.app.Activity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class InteraktywnyAdapterTablicy extends ArrayAdapter<ModelOceny> {
    //przechowujemy referencję do listy ocen
    private List<ModelOceny> listaOcen;
    private Activity kontekst;

    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen) {
        super(kontekst, R.layout.ocena, listaOcen);
        //ustawienie wartości pól
        this.kontekst = kontekst;
        this.listaOcen = listaOcen;
    }

    //tworzenie nowego wiersza
    @Override
    public View getView(final int numerWiersza, View widokDoRecyklingu, ViewGroup parent) {
        View widok = null;
        //tworzenie nowego wiersza
        if (widokDoRecyklingu == null) {
            //utworzenie layout na podstawie pliku XML
            LayoutInflater pompka = kontekst.getLayoutInflater();
            widok = pompka.inflate(R.layout.ocena, null);
            //wybranie konkretnego przycisku radiowego musi zmieniać dane w modelu
            RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.grupaOceny);
            grupaOceny.setTag(listaOcen.get(numerWiersza));
            grupaOceny.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, //referencja do grupy
                                                     //przycisków
                                                     int checkedId) //identyfikator wybranego
                        //przycisku
                        {
                            // 1) odczytanie z etykiety, który obiekt modelu przechowuje dane o
                            //zmienionej ocenie
                            ModelOceny element = (ModelOceny) group.getTag();
                            // 2) zapisanie zmienionej oceny
                            //System.out.println(numerWiersza);
                            switch (checkedId) {
                                case R.id.ocena1:
                                    element.setOcena(1);
                                    break;
                                case R.id.ocena2:
                                    element.setOcena(2);
                                    break;
                                case R.id.ocena3:
                                    element.setOcena(3);
                                    break;
                                case R.id.ocena4:
                                    element.setOcena(4);
                                    break;
                                case R.id.ocena5:
                                    element.setOcena(5);
                                    break;
                            }
                            //element.setOcena();
                        }
                    }
            );
        }
        //aktualizacja istniejącego wiersza
        else {
            widok = widokDoRecyklingu;
            RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.grupaOceny);
            //powiązanie grupy przycisków z obiektem w modelu
            grupaOceny.setTag(listaOcen.get(numerWiersza));
        }

        TextView etykieta = (TextView) widok.findViewById(R.id.listaTextEtykieta);
        etykieta.setText(listaOcen.get(numerWiersza).getNazwa());
        //ustawienie tekstu etykiety na podstawie modelu
        RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.grupaOceny);
        //zaznaczenie odpowiedniego przycisku na podtawie modelu
        switch (listaOcen.get(numerWiersza).getOcena()) {
            case 5:
                grupaOceny.check(R.id.ocena1);
                break;
            case 4:
                grupaOceny.check(R.id.ocena2);
                break;
            case 3:
                grupaOceny.check(R.id.ocena3);
                break;
            case 2:
                grupaOceny.check(R.id.ocena4);
                break;
            case 1:
                grupaOceny.check(R.id.ocena5);
                break;
        }

        //grupaOceny.check(R.id.);
        //zwrócenie nowego lub zaktualizowanego wiersza listy
        return widok;
    }
}
