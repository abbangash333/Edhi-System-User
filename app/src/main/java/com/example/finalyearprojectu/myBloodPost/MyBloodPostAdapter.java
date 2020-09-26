package com.example.finalyearprojectu.myBloodPost;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.bloodDetail.BloodDetail;
import com.example.finalyearprojectu.myMissingPost.MyMissingPost;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyBloodPostAdapter extends RecyclerView.Adapter<MyBloodPostAdapter.ViewHolder> {
    Context mContext;
    List<MyBloodPostModel> mList;
    List<String> keys;
    ViewGroup parentTop;
    MyBloodPostModel myBloodPostModel;
    String AKey;

    public MyBloodPostAdapter(Context mContext, List<MyBloodPostModel> mList, List<String> keys) {
        this.mContext = mContext;
        this.mList = mList;
        this.keys = keys;

    }

    public MyBloodPostAdapter() {

    }

    @NonNull
    @Override
    public MyBloodPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        parentTop = parent;
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_my_blood_posts,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyBloodPostAdapter.ViewHolder holder, int position) {
         myBloodPostModel = mList.get(position);
        holder.mybloodGroup.setText(myBloodPostModel.getBlood_group());
        holder.myBloodPostName.setText(myBloodPostModel.getBlood_for());
        holder.myBloodPostStatus.setText(myBloodPostModel.getRequest_type());
        holder.myBloodPostLocation.setText(myBloodPostModel.getFull_address());
        holder.myBloodPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BloodDetail.class);
                intent.putExtra("bloodFor",myBloodPostModel.getBlood_for());
                intent.putExtra("blood",myBloodPostModel.getBlood_group());
                intent.putExtra("location",myBloodPostModel.getRefer_city());
                intent.putExtra("request",myBloodPostModel.getRequest_type());
                intent.putExtra("age",myBloodPostModel.getAge());
                intent.putExtra("gender",myBloodPostModel.getGender());
                intent.putExtra("fullAddress",myBloodPostModel.getFull_address());
                intent.putExtra("phoneNumber",myBloodPostModel.getPhone_number());
                mContext.startActivity(intent);
                Toast.makeText(mContext, "blood post clicked", Toast.LENGTH_SHORT).show();
            }
        });
        holder.myBloodPostCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogBoxForEditAndDelete(position);
                return false;
            }
        });


    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mybloodGroup;
        TextView myBloodPostName;
        TextView myBloodPostStatus;
        TextView myBloodPostLocation;
        TextView myBloodPostBtn;
        CardView myBloodPostCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mybloodGroup = itemView.findViewById(R.id.my_blood_post_blood_group);
            myBloodPostName = itemView.findViewById(R.id.my_blood_post_name_txt);
            myBloodPostStatus = itemView.findViewById(R.id.my_blood_post_status_txt);
            myBloodPostLocation = itemView.findViewById(R.id.my_blood_location_txt);
            myBloodPostBtn = itemView.findViewById(R.id.my_blood_detail_btn);
            myBloodPostCardView = itemView.findViewById(R.id.my_blood_post_card_view);
        }
    }

    private void dialogBoxForEditAndDelete(int position) {
        final String[] Options = {"Delete","Edit","Cancel"};
        AlertDialog.Builder window;
        window = new AlertDialog.Builder(mContext, R.style.MyDialogTheme);
        window.setTitle("Select Option");
        window.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                deleteItem(position);
               Toast.makeText(mContext,"Delete clicked",Toast.LENGTH_SHORT).show();
                }
                else if(which == 1){
                    //second option clicked, do this...
                    editMethod(position);
                    Toast.makeText(mContext,"Edit clicked",Toast.LENGTH_SHORT).show();
                }
                else if(which == 2){
                    //second option clicked, do this.
                    dialog.dismiss();
                    Toast.makeText(mContext,"Cancel clicked",Toast.LENGTH_SHORT).show();
                }
            }

        });

        window.show();

    }
    public void deleteItem(int position){
        String key = keys.get(position);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("blood_requests");
        ref.child(key).removeValue();
    }

    private void editMethod(int position) {
        String key = keys.get(position);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("blood_requests");
        AKey = key;
        Intent intent = new Intent(mContext, MyBloodPostEdit.class);
        intent.putExtra("bloodFor",myBloodPostModel.getBlood_for());
        intent.putExtra("blood",myBloodPostModel.getBlood_group());
        intent.putExtra("location",myBloodPostModel.getRefer_city());
        intent.putExtra("request",myBloodPostModel.getRequest_type());
        intent.putExtra("age",myBloodPostModel.getAge());
        intent.putExtra("gender",myBloodPostModel.getGender());
        intent.putExtra("fullAddress",myBloodPostModel.getFull_address());
        intent.putExtra("phoneNumber",myBloodPostModel.getPhone_number());
        intent.putExtra("keyName",AKey);
        mContext.startActivity(intent);

    }

}
