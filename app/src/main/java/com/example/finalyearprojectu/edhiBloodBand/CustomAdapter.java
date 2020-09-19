package com.example.finalyearprojectu.edhiBloodBand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalyearprojectu.R;

import java.util.List;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<ModelClassForBloodPosts> list;
    private Context mCtx;

    public CustomAdapter(List<ModelClassForBloodPosts> list, Context mCtx) {
        this.list = list;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items_for_blood_post_notification, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CustomAdapter.ViewHolder holder, int position) {
        ModelClassForBloodPosts myList = list.get(position);
        holder.textViewHead.setText(myList.getRequest());
        holder.textViewDesc.setText(myList.getDesc());

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //will show popup menu here
                PopupMenu popup = new PopupMenu(mCtx, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.option_menu_for_blood_post_notification);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                                break;
                            case R.id.menu2:
                                //handle menu2 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public TextView buttonViewOption;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead =  itemView.findViewById(R.id.textViewHead);
            textViewDesc =  itemView.findViewById(R.id.textViewDesc);
            buttonViewOption = itemView.findViewById(R.id.textViewOptions);
        }
    }
}
