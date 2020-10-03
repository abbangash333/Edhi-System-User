package com.example.finalyearprojectu.centersContactInformation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;
import com.example.finalyearprojectu.R;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CenterContactAdapter extends RecyclerView.Adapter<CenterContactAdapter.ViewHolder> implements Filterable {
    Context mContext;
    ArrayList<CenterDetailModel> mList;
    private List<CenterDetailModel> searchList;

    public CenterContactAdapter(Context mContext, ArrayList<CenterDetailModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
        searchList = new ArrayList<>(mList);
    }

    @NonNull
    @Override
    public CenterContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_contact_center,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CenterContactAdapter.ViewHolder holder, int position) {
        CenterDetailModel centerDetailModel = mList.get(position);
        holder.centerContactTextView.setText(centerDetailModel.getName_center());
        holder.centerContactTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"click on recycle View",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,CentersAddresses.class);
                intent.putExtra("phone",centerDetailModel.getCenter_number());
                intent.putExtra("address",centerDetailModel.getCenter_address());
                intent.putExtra("name",centerDetailModel.getName_center());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    @Override
    public Filter getFilter() {
        return recentFilter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView centerContactTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            centerContactTextView = itemView.findViewById(R.id.center_contact_tx_view);
        }
    }
    private Filter recentFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CenterDetailModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(searchList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CenterDetailModel item : searchList) {
                    if (item.getName_center().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList.clear();
            mList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
