package movil.rfc;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nombre, apellidoPaterno, apellidoMaterno, fecha;
    private Button RFC;
    private String linRfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        nombre = findViewById(R.id.Txt_Nombre);
        apellidoPaterno =  findViewById(R.id.Txt_ApellidoPaterno);
        apellidoMaterno =  findViewById(R.id.Txt_ApellidoMaterno);
        fecha = findViewById(R.id.Txt_Fecha);
        RFC =  findViewById(R.id.Btn_RFC);

        RFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String auxNombre =  nombre.getText().toString(),
                        auxApellidoPaterno = apellidoPaterno.getText().toString(),
                        auxApellidoMaterno = apellidoMaterno.getText().toString(),
                        auxFecha =  fecha.getText().toString();
                Crfc crfc = new Crfc();
                try
                {
                    crfc.setNombre(auxNombre);
                    crfc.setApellidoPaterno(auxApellidoPaterno);
                    crfc.setApellidoMaterno(auxApellidoMaterno);
                    crfc.setFecha(auxFecha);
                    linRfc = crfc.calcularRFC();
                    Intent intent =  new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("rfc",linRfc);
                    startActivity(intent);
                }
                catch (rfcException kiwi)
                {
                    Snackbar.make(findViewById(R.id.Ll_ActivityMain), kiwi.getMessage(), Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }
}
