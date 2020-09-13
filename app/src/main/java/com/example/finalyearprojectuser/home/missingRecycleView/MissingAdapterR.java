package com.example.finalyearprojectuser.home.missingRecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearprojectuser.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MissingAdapterR extends RecyclerView.Adapter<MissingAdapterR.ViewHolder> {
    ArrayList<MissingPersonR> missingPersonRArrayList;
    private Context context;

    public MissingAdapterR(Context context,ArrayList<MissingPersonR> missingPersonRArrayList) {
        this.context = context;
        this.missingPersonRArrayList = missingPersonRArrayList;
    }

    @NonNull
    @Override
    public MissingAdapterR.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_missing,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MissingAdapterR.ViewHolder holder, int position) {
       MissingPersonR missingPersonR = missingPersonRArrayList.get(position);
       holder.imgMissingR.setImageResource(missingPersonR.getImgPosition());
       holder.nameMissingR.setText(missingPersonR.getName());
       holder.cardViewMissinR.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show();
           }
       });

    }

    @Override
    public int getItemCount() {
        return missingPersonRArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgMissingR;
        TextView  nameMissingR;
        CardView cardViewMissinR;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMissingR = itemView.findViewById(R.id.list_item_missing_profile_circular);
            nameMissingR = itemView.findViewById(R.id.list_item_missing_name);
            cardViewMissinR = itemView.findViewById(R.id.list_item_missing_cardViewR);
        }
    }
}
