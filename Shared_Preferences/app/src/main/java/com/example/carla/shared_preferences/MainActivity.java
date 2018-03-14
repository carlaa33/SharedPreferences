package com.example.carla.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner opciones;
    Button btnGuardar,btnMostar;
    EditText edit1;
    RadioButton btnMale, btnFemale;
    CheckBox check1,check2,check3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;
        SharedPreferences sharprefs = getSharedPreferences("ArchivoSP",context.MODE_PRIVATE);

        opciones = (Spinner) findViewById(R.id.spinner);
        //array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.opciones,android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        //instanciar
        btnGuardar = (Button)findViewById(R.id.button);
        btnMostar = (Button)findViewById(R.id.button2);
        edit1 = (EditText)findViewById(R.id.editText);
        btnMale = (RadioButton)findViewById(R.id.radioButton_male);
        btnFemale = (RadioButton)findViewById(R.id.radioButton_female);
        check1 = (CheckBox)findViewById(R.id.checkBox);
        check2 = (CheckBox)findViewById(R.id.checkBox2);
        check3 = (CheckBox)findViewById(R.id.checkBox3);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharprefs = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharprefs.edit();
                editor.putString("MiDato",edit1.getText().toString());
                editor.commit();

            }
        });

        btnMostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharprefs = getPreferences(context.MODE_PRIVATE);
                String valor = sharprefs.getString("MiDato","No hay dato");
                Toast.makeText(getApplicationContext(),"Datos guardados:"+valor,Toast.LENGTH_LONG).show();



            }
        });



    }
}
