package com.example.tarot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText dia, mes, anio;
    private Button Enviar;
    private  String msg;
    private  int noTarot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        dia = findViewById(R.id.Txt_Dia);
        mes = findViewById(R.id.Txt_Mes);
        anio = findViewById(R.id.Txt_Anio);
        Enviar =  findViewById(R.id.Btn_Enviar);

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int diaAuz =  Integer.parseInt(dia.getText().toString());
                int mesAuz = Integer.parseInt(mes.getText().toString());
                int anioAuz = Integer.parseInt(anio.getText().toString());
                Ctarot ctarot =  new Ctarot();
                try
                {
                    ctarot.setMes(mesAuz);
                    ctarot.setAnio(anioAuz);
                    ctarot.setDia(diaAuz);
                    noTarot = ctarot.calcularTarot();
                    msg = "" + noTarot;
                    DialogMensajeTarot dialogMensajeTarot = new DialogMensajeTarot();
                    dialogMensajeTarot.getContent(MainActivity.this,msg);
                    dialogMensajeTarot.show(getSupportFragmentManager(),"Mensaje Tarot");
                }
                catch (FechaException kiwi)
                {
                    Toast.makeText(MainActivity.this, kiwi.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
