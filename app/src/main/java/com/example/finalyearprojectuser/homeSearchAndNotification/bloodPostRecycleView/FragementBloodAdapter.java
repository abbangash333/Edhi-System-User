package com.example.finalyearprojectuser.homeSearchAndNotification.bloodPostRecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.edhiBloodBand.bloodDetail.BloodDetail;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FragementBloodAdapter extends RecyclerView.Adapter<FragementBloodAdapter.ViewHolder> {
    Context context;
    List<FragmentBloodModel> fBModelList;

    public FragementBloodAdapter(Context context, List<FragmentBloodModel> fBModelList) {
        this.context = context;
        this.fBModelList = fBModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_bottom_navigation_blood_home,parent,
                false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FragmentBloodModel fragementBloodModel = fBModelList.get(position);
        holder.mainTxtBloodGroup.setText(fragementBloodModel.getBlood_group());
        holder.bloodG.setText(fragementBloodModel.getRequest_type());
        holder.nameP.setText(fragementBloodModel.getBlood_for());
        holder.locationP.setText(fragementBloodModel.getRefer_city());
        holder.bloodDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BloodDetail.class);
                intent.putExtra("bloodFor",fragementBloodModel.getBlood_for());
                intent.putExtra("blood",fragementBloodModel.getBlood_group());
                intent.putExtra("location",fragementBloodModel.getRefer_city());
                intent.putExtra("request",fragementBloodModel.getRequest_type());
                intent.putExtra("age",fragementBloodModel.getAge());
                intent.putExtra("gender",fragementBloodModel.getGender());
                intent.putExtra("fullAddress",fragementBloodModel.getFull_address());
                intent.putExtra("phoneNumber",fragementBloodModel.getPhone_number());
                context.startActivity(intent);
                Toast.makeText(context, "blood post clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return fBModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mainTxtBloodGroup;
        TextView nameP;
        TextView bloodG;
        TextView locationP;
        Button bloodDetailBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             mainTxtBloodGroup = itemView.findViewById(R.id.bottom_navigation_blood_view);
            nameP = itemView.findViewById(R.id.bottom_blood_post_name_txt);
            bloodG = itemView.findViewById(R.id.bottom_blood_group_txt);
            locationP = itemView.findViewById(R.id.bottom_blood_location_date_txt);
            bloodDetailBtn = itemView.findViewById(R.id.bottom_blood_detail_btn);

        }
    }
}
