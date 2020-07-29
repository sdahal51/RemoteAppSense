package com.example.remotesense;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

    View view;
    public Holder(@NonNull View itemView) {
        super(itemView);


        view= itemView;
    }

    public void setView(Context context, String Humidity, String Temp_C, String Temp_F, String Time ){

        TextView timedp= view.findViewById(R.id.IOT_time);
        TextView temperaturedp= view.findViewById(R.id.IOT_temp);
        TextView temperaturecdp= view.findViewById(R.id.IOT_tempC);
        TextView humiditydp= view.findViewById(R.id.IOT_humidity);

        timedp.setText(Time);
        temperaturedp.setText(Temp_F);
        temperaturecdp.setText(Temp_C);
        humiditydp.setText(Humidity);

    }
}
