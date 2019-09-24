package com.example.pr02_login;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import java.util.ArrayList;

import modelos.Cuentas;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity {
    EditText txUser, txPass;
    Button btnIn, btnReg;
    boolean valido=false;
    //crear lista
    ArrayList<Cuentas> lCuentas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarControles();
        cargarEventos();
    }

    private void cargarControles() {
        txUser = findViewById(R.id.tx_user);
        txPass = findViewById(R.id.tx_pass);
        btnIn = findViewById(R.id.btn_iniciar);
        btnReg = findViewById(R.id.btn_registra);
    }

    private void cargarEventos() {

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                inputMethodManager.hideSoftInputFromWindow(txUser.getWindowToken(), 0);
                registra();
            }
        });
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                inputMethodManager.hideSoftInputFromWindow(txUser.getWindowToken(), 0);
                inicio();
            }
        });
    }

    private void registra() {
        if(!txUser.getText().toString().isEmpty() && !txPass.getText().toString().isEmpty()) {
            Cuentas miCuenta = new Cuentas();
            miCuenta.user = txUser.getText().toString();
            miCuenta.pwd = txPass.getText().toString();
            lCuentas.add(miCuenta);
            miToast("Registro exitoso");
        }else{
            Toast.makeText(this, "Porfavor llene todos los campos", Toast.LENGTH_SHORT).show();
        }
        txUser.setText("");
        txPass.setText("");

    }
    public void miToast(String msg){
        SuperActivityToast.create(MainActivity.this, new Style(), Style.TYPE_PROGRESS_BAR)
                // .setButtonText("Deshacer")
                //.setButtonIconResource(R.drawable.ic_launcher_foreground)
                //.setOnButtonClickListener("Texto", null, null)
                .setProgressBarColor(WHITE)
                .setText(msg)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.GREY))
                .setAnimations(Style.ANIMATIONS_FLY).show();

    }

    private void inicio() {
        if(!txUser.getText().toString().isEmpty() && !txPass.getText().toString().isEmpty()){
            if(lCuentas.size()>0){
                valido=false;
                String us=txUser.getText().toString();
                String pw=txPass.getText().toString();

                for (int i = 0; i < lCuentas.size(); i++) {
                     if(lCuentas.get(i).user.equals(us) && lCuentas.get(i).pwd.equals(pw)){
                         valido=true;
                         break;
                     }

                }
                if(valido){
                    miToast("Bienvenido "+us);
                }else{
                    Toast.makeText(this, "ERROR, Usuario/Pass incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "Porfavor llene todos los campos", Toast.LENGTH_SHORT).show();
        }
        txUser.setText("");
        txPass.setText("");
    }

}
