package com.example.carla.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Spinner opciones;
    Button btnGuardar,btnMostar;
    EditText edit1;
    RadioButton btnMale, btnFemale;
    CheckBox check1,check2,check3;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout cl = findViewById(R.id.cl);

        final Context context = this;
        SharedPreferences sharprefs = getSharedPreferences("ArchivoSP",context.MODE_PRIVATE);
        //checkbox
        boolean fondo = sharprefs.getBoolean("checkBox",true);
        if (fondo ==true){


        }

        opciones = (Spinner) findViewById(R.id.spinner);
        //array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.opciones,android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        //instanciar
        btnGuardar = findViewById(R.id.button);
        btnMostar = findViewById(R.id.button2);
        edit1 = findViewById(R.id.editText);
        btnMale = findViewById(R.id.radioButton_male);
        btnFemale = findViewById(R.id.radioButton_female);
        check1 = findViewById(R.id.checkBox);
        check2 = findViewById(R.id.checkBox2);
        check3 = findViewById(R.id.checkBox3);
        radioGroup = findViewById(R.id.radioGroup);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getZodiac();
                

                SharedPreferences sharprefs = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharprefs.edit();
                String valores[]={getMail(),getGenero(),getHobbie(),getZodiac()};
                editor.putString("Mail",valores[0]);
                editor.putString("Genero",valores[1]);
                editor.putString("Hobbie",valores[2]);
                editor.putString("Zodiac",valores[3]);
                editor.commit();

                Toast.makeText(getApplicationContext(),
                        "Datos guardados:\n Email: "+valores[0]+
                        "\nGénero: "+valores[1]+
                                "\nHobbies: "+valores[2]+
                                "\nZodiaco: "+opciones.getItemAtPosition(Integer.parseInt(valores[3])).toString(),Toast.LENGTH_LONG).show();
            }
        });

        btnMostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sharprefs = getPreferences(context.MODE_PRIVATE);

                String valores[]={sharprefs.getString("Mail","No hay dato"),sharprefs.getString("Genero","No hay dato"),sharprefs.getString("Hobbie","No hay dato"),sharprefs.getString("Zodiac","No hay dato")};

                setMail(valores[0]);
                setGenero(valores[1]);
                setHobbie(valores[2]);
                setZodiac(valores[3]);

                Toast.makeText(getApplicationContext(),
                        "Datos recuperados:\n Email: "+valores[0]+
                                "\nGénero: "+valores[1]+
                                "\nHobbies: "+valores[2]+
                                "\nZodiaco: "+opciones.getItemAtPosition(Integer.parseInt(valores[3])).toString(),Toast.LENGTH_LONG).show();





            }
        });



    }

    private void setZodiac(String zodiac) {
        opciones.setSelection(Integer.parseInt(zodiac));
    }

    private void setHobbie(String hobbie) {
        if(hobbie.contains("Coding")){
            check1.setChecked(true);
        }else{
            check1.setChecked(false);
        }

        if(hobbie.contains("Writing")){
            check2.setChecked(true);
        }else{
            check2.setChecked(false);
        }

        if(hobbie.contains("Jogging")){
            check3.setChecked(true);
        }else{
            check3.setChecked(false);
        }
    }

    private void setGenero(String genero) {
        if(genero.equals("Male")){
            btnMale.setChecked(true);
            btnFemale.setChecked(false);
        }else{
            btnMale.setChecked(false);
            btnFemale.setChecked(true);
        }
    }

    private void setMail(String mail) {
        edit1.setText(mail);
    }

    private String getZodiac() {
        return opciones.getSelectedItemPosition()+"";
    }

    private String getHobbie() {
        String s="";

        if(check1.isChecked()){
            s+="Coding,";
        }
        if(check2.isChecked()){
            s+="Writing,";
        }
        if(check3.isChecked()){
            s+="Jogging";
        }

        return s;


    }

    private String getGenero() {
        if(btnFemale.isChecked()){
            return "Female";
        }else if(btnMale.isChecked()){
            return "Male";
        }else{
            return "";
        }

    }

    private String getMail() {
        return edit1.getText().toString();


    }
}
