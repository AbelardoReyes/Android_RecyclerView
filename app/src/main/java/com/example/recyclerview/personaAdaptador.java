package com.example.recyclerview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest.permission;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.RecyclerView;
import java.security.Permission;
import java.util.ArrayList;

public class personaAdaptador extends RecyclerView.Adapter<personaAdaptador.viewHolder> {
    final personaAdaptador.OnItemClickListener listener;
private ArrayList<Persona> PersonList;
public personaAdaptador(ArrayList<Persona> PersonList,personaAdaptador.OnItemClickListener listener){
    this.PersonList = PersonList;
    this.listener = listener;
}

    public interface OnItemClickListener {
    void OnItemClick();
    }

    public class  viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView nombretx;
    private TextView telefonotx;

    public  viewHolder(final View view){
        super(view);
        nombretx = view.findViewById(R.id.nombre);
        telefonotx = view.findViewById(R.id.telefono);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick();
            }
        });
    }
        void binData(final personaAdaptador item){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick();
                }
            });
        }

    @Override
    public void onClick(View view) {
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:8714733996"));
            view.getContext().startActivity(i);
    }
}

    @NonNull
    @Override
    public personaAdaptador.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista,parent,false);
        return  new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull personaAdaptador.viewHolder holder, int position) {
    String name = PersonList.get(position).getNombre();
        String tel = PersonList.get(position).getTelefono();
        holder.nombretx.setText(name);
        holder.telefonotx.setText(tel);

    }


    @Override
    public int getItemCount() {
        return PersonList.size();
    }
}