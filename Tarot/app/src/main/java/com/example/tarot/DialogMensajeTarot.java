package com.example.tarot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogMensajeTarot extends AppCompatDialogFragment {

    private TextView mensaje;
    private  Context context;
    private String msg = "Tu numero de Tarot es: ";
    private String noTarot;

    public void getContent(Context context, String noTarot)
    {
        this.context = context;
        this.noTarot = noTarot;
        msg = msg + noTarot;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater =  getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_mensaje_tarot, null);
        builder.setView(view)
                .setTitle("Mensaje Tarot 15140924")
                .setPositiveButton("Aceptar",null);
        mensaje =view.findViewById(R.id.Jbl_Mensaje);
        mensaje.setText(msg);
        return builder.create();
    }
}
