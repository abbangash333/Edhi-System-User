package com.example.finalyearprojectu.missingPersonManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.edhiBloodBand.ModelClassForBloodPosts;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<ModelClassForMissingPosts> list;
    private Context mCtx;

    public CustomAdapter(List<ModelClassForMissingPosts> list, Context mCtx) {
        this.list = list;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.missing_post_recycle_view_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.ViewHolder holder, int position) {
        ModelClassForMissingPosts myList = list.get(position);
        holder.missingPersonName.setText(myList.getNameOfPerson());
        holder.cityBelonginName.setText(myList.getBelongingCity());
        holder.dateOfBirth.setText(myList.getDOB());
        holder.ageOfPerson.setText(( String.valueOf(myList.getAge())));
        holder.cityNameD.setText(myList.getNameOfCityD());
        holder.missingDate.setText(myList.getPersonMissingDate());
        holder.optionMenuButtonForMissingPost.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mCtx, holder.optionMenuButtonForMissingPost);
                popup.inflate(R.menu.option_menu_for_missing_post_notification);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.menu1:
                            {
                                break;
                            }


                            case R.id.menu2:

                            {
                                break;
                            }


                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView missingPersonName;
        private TextView cityBelonginName;
        private TextView dateOfBirth;
        private TextView ageOfPerson;
        private TextView cityNameD;
        private TextView missingDate;
        private TextView optionMenuButtonForMissingPost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            missingPersonName = itemView.findViewById(R.id.nameOfPerson);
            cityBelonginName = itemView.findViewById(R.id.belonging_city_name);
            dateOfBirth = itemView.findViewById(R.id.date_of_birth);
            ageOfPerson = itemView.findViewById(R.id.age_of_missing_person);
            cityNameD = itemView.findViewById(R.id.city_name_disappeared);
            missingDate = itemView.findViewById(R.id.person_missing_date);
            optionMenuButtonForMissingPost = itemView.findViewById(R.id.textViewOptionsForMissing);

        }
    }
}
