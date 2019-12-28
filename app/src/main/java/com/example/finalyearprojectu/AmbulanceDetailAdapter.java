package com.example.finalyearprojectu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AmbulanceDetailAdapter extends RecyclerView.Adapter<AmbulanceDetailAdapter.MyViewHolder> {
    private Context mContext;
    String name[];
    int image[];

    public AmbulanceDetailAdapter(Context mContext, String[] name, int[] image) {
        this.mContext = mContext;
        this.name = name;
        this.image = image;
    }

    @NonNull
    @Override

    public  AmbulanceDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ambulance_detail_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder ambulanceViewHolder, int position) {
          ambulanceViewHolder.ambulanceLocationImageView.setImageResource(R.drawable.ambulance_icon);
          ambulanceViewHolder.ambulanceDriverPhoneImageView.setImageResource(R.drawable.mobile_icon);
          ambulanceViewHolder.ambulanceDriverDistanceImageView.setImageResource(R.drawable.distance_icon);
//        ambulaceViewHolder.tvName.setText(name[i]);
//        ambulaceViewHolder.ivProfile.setImageResource(image[i]);


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ambulanceLocationImageView;
        private ImageView ambulanceDriverPhoneImageView;
        private  ImageView ambulanceDriverDistanceImageView;
        private TextView  ambulanceLocationTextView;
        private TextView ambulanceDriverPhoneTextView;
        private  TextView ambulanceDriverDistanceTextView;
        private TextView  ambulanceDriverNameTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ambulanceLocationImageView = itemView.findViewById(R.id.ambulance_location_imageView);
            ambulanceDriverPhoneImageView = itemView.findViewById(R.id.ambulance_mobile_image_view);
            ambulanceDriverDistanceImageView =itemView.findViewById(R.id.ambulance_distance_image_view);
        }
    }
}
