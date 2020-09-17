package com.example.finalyearprojectuser.home.bloodPostRecycleV;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.edhiBloodBand.bloodDetail.BloodDetail;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BloodPostsAdapter extends RecyclerView.Adapter<BloodPostsAdapter.ViewHolder> {
    List<BloodPostR> bloodListHome;
    Context context;

    public BloodPostsAdapter(List<BloodPostR> bloodPostRHome, Context context) {
        this.bloodListHome = bloodPostRHome;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_home_blood_posts,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BloodPostR bloodPostR = bloodListHome.get(position);
        holder.bloodGroupTxtView.setText(bloodPostR.getBlood_group());
        holder.bloodReceiverName.setText(bloodPostR.getBlood_for());
        holder.bloodpostsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BloodDetail.class);
                intent.putExtra("bloodFor",bloodPostR.getBlood_for());
                intent.putExtra("blood",bloodPostR.getBlood_group());
                intent.putExtra("location",bloodPostR.getRefer_city());
                intent.putExtra("request",bloodPostR.getRquest_type());
                intent.putExtra("age",bloodPostR.getAge());
                intent.putExtra("gender",bloodPostR.getGender());
                intent.putExtra("fullAddress",bloodPostR.getFull_address());
                intent.putExtra("phoneNumber",bloodPostR.getPhone_number());
                context.startActivity(intent);
                Toast.makeText(context, "blood post clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return bloodListHome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bloodGroupTxtView;
        TextView bloodReceiverName;
        CardView bloodpostsCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bloodGroupTxtView = itemView.findViewById(R.id.list_item_blood_group);
            bloodReceiverName = itemView.findViewById(R.id.list_item_blood_for_name);
            bloodpostsCardView = itemView.findViewById(R.id.list_item_blood_post_cardView);
        }
    }
}
