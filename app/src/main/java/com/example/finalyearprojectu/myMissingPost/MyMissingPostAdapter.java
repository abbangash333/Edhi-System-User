package com.example.finalyearprojectu.myMissingPost;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.missingPersonDeatail.MissingPersonDetail;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyMissingPostAdapter extends RecyclerView.Adapter<MyMissingPostAdapter.ViewHolder> {
    List<MyMissingPostModel> myMissingPostModelList;
    Context context;
    List<String> keys;
    MyMissingPostModel myMissingPostM;
    String AKey;
    ViewGroup parentTop;
    String DeletUrl;

    public MyMissingPostAdapter(List<MyMissingPostModel> myMissingPostModelList, Context context, List<String> keys) {
        this.myMissingPostModelList = myMissingPostModelList;
        this.context = context;
        this.keys = keys;

    }

    @NonNull
    @Override
    public MyMissingPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parentTop = parent;
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_my_missing_post,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMissingPostAdapter.ViewHolder holder, int position) {
        AKey =keys.get(position);
       MyMissingPostModel myMissingPostModel = myMissingPostModelList.get(position);
       myMissingPostM = myMissingPostModel;

        holder.nameMissing.setText(myMissingPostModel.getMissing_name());
        holder.statusMissing.setText(myMissingPostModel .getMissing_status());
        holder.dateMissing.setText(myMissingPostModel .getDisappeared_date());
        Picasso.get()
                .load(myMissingPostModel.getImage_url())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageViewMissing);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = keys.get(position);
                Intent missingIndividualDetailActivty = new Intent(context, MissingPersonDetail.class);
                missingIndividualDetailActivty.putExtra("name",myMissingPostModel.getMissing_name());
                missingIndividualDetailActivty.putExtra("FromCity",myMissingPostModel.getCity());
                missingIndividualDetailActivty.putExtra("MissingFrom",myMissingPostModel.getDissappeared_city());
                missingIndividualDetailActivty.putExtra("status",myMissingPostModel.getMissing_status());
                missingIndividualDetailActivty.putExtra("age",myMissingPostModel.getAge());
                missingIndividualDetailActivty.putExtra("gender",myMissingPostModel.getGender());
                missingIndividualDetailActivty.putExtra("date",myMissingPostModel.getDisappeared_date());
                missingIndividualDetailActivty.putExtra("fullAddress",myMissingPostModel.getAddress());
                missingIndividualDetailActivty.putExtra("phoneNumber",myMissingPostModel.getPhone_number());
                missingIndividualDetailActivty.putExtra("imageUrl",myMissingPostModel.getImage_url());
                missingIndividualDetailActivty.putExtra("postKey",myMissingPostModel.getMissing_key());
                missingIndividualDetailActivty.putExtra("keyName",key);
                context.startActivity(missingIndividualDetailActivty);
                Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show();
            }
        });
        holder.myMissingPostCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogBoxForEditAndDelete(myMissingPostModel,position);
                return false;
            }

        });

    }

    @Override
    public int getItemCount() {
        return myMissingPostModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMissing;
        TextView nameMissing;
        TextView statusMissing;
        TextView dateMissing;
        Button mButton;
        CardView myMissingPostCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMissing =itemView.findViewById(R.id.my_image_missing);
            nameMissing = itemView.findViewById(R.id.name_for_missing);
            statusMissing = itemView.findViewById(R.id.status_for_missing);
            dateMissing = itemView.findViewById(R.id.date_for_missing);
            mButton = itemView.findViewById(R.id.detail_btn_for_missing);
            myMissingPostCardView = itemView.findViewById(R.id.my_post_card_view_for_missing);
        }
    }
    private void dialogBoxForEditAndDelete(MyMissingPostModel myMissingPostModel, int position) {
        final String[] Options = {"Delete","Edit","Cancel"};
        AlertDialog.Builder window;
        window = new AlertDialog.Builder(context, R.style.MyDialogTheme);
        window.setTitle("Select Option");
        window.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    deleteItem(myMissingPostModel,position);
                }
                else if(which == 1){
                    //second option clicked, do this...
                    editMethod(myMissingPostModel,position);
                    Toast.makeText(context,"Edit clicked",Toast.LENGTH_SHORT).show();
                }
                else if(which == 2){
                    //second option clicked, do this.
                    dialog.dismiss();
                    Toast.makeText(context,"Cancel clicked",Toast.LENGTH_SHORT).show();
                }
            }

        });

        window.show();
    }
    public void deleteItem(MyMissingPostModel myMissingPostModel,int postion){
        String key =keys.get(postion);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("missing_requests");
        ref.child(key).removeValue();
        StorageReference stRef = FirebaseStorage.getInstance().getReferenceFromUrl(myMissingPostModel.getImage_url());
        stRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
           Toast.makeText(context,"Deleted successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void editMethod(MyMissingPostModel myMissingPostModel, int position) {
        String key = keys.get(position);
        Intent intent = new Intent(context, MyMissingPostEdit.class);
        intent.putExtra("name",myMissingPostModel.getMissing_name());
        intent.putExtra("FromCity",myMissingPostModel.getCity());
        intent.putExtra("MissingFrom",myMissingPostModel.getDissappeared_city());
        intent.putExtra("status",myMissingPostModel.getMissing_status());
        intent.putExtra("age",myMissingPostModel.getAge());
        intent.putExtra("gender",myMissingPostModel.getGender());
        intent.putExtra("date",myMissingPostModel.getDisappeared_date());
        intent.putExtra("fullAddress",myMissingPostModel.getAddress());
        intent.putExtra("phoneNumber",myMissingPostModel.getPhone_number());
        intent.putExtra("imageUrl",myMissingPostModel.getImage_url());
        intent.putExtra("keyName",key);
        context.startActivity(intent);

    }
}
