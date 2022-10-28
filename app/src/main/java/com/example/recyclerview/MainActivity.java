package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Permission;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Persona> PersonList;
    private RecyclerView recyclerview;
    int requescode = 200;
    boolean b = false;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.telefono);
        Button llamada = findViewById(R.id.button);
        recyclerview = findViewById(R.id.recycler);
        PersonList = new ArrayList<>();
        setGetPerson();
        setAdapter();

        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setAdapter() {
        personaAdaptador adapter = new personaAdaptador(PersonList, new personaAdaptador.OnItemClickListener() {
            @Override
            public void OnItemClick() {
                pedirPermisos();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(adapter);
    }

    private void setGetPerson() {
        PersonList.add(new Persona("Abelardo", "8713733996"));
        PersonList.add(new Persona("Carlos Duron", "8713735647"));
        PersonList.add(new Persona("Cristian Avitia", "8713736754"));


    }


    private void ejecutarLlamada() {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8714733996"));
        startActivity(i);
    }

    private void pedirPermisos(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {
            ejecutarLlamada();
        }else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},255);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==255){
            if(permissions.length>=0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permisos Aceptados",Toast.LENGTH_SHORT).show();
                ejecutarLlamada();
            }else{
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CALL_PHONE)){
                    Toast.makeText(this,"Permisos Rechazado",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Tu necesitas habilitar los permisos de manera manual",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}