package com.example.finalyearprojectu.centerManagement;

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
import com.example.finalyearprojectu.centersContactInformation.CenterDetailModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CenterManagementAdapter extends RecyclerView.Adapter<CenterManagementAdapter.ViewHolder> implements Filterable {
    Context mContext;
    ArrayList<CenterManagementModelClass> mList;
    private List<CenterManagementModelClass> searchList;

    public CenterManagementAdapter(Context mContext, ArrayList<CenterManagementModelClass> mList) {
        this.mContext = mContext;
        this.mList = mList;
        searchList = new ArrayList<>(mList);
    }

    @NonNull
    @Override
    public CenterManagementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext  = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_center_management,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CenterManagementAdapter.ViewHolder holder, int position) {
        CenterManagementModelClass centerManagementModelClass = mList.get(position);
        holder.nameTextView.setText(centerManagementModelClass.getCenter_name());
        holder.nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CenterManagementDetailActivity.class);
                intent.putExtra("centerName",centerManagementModelClass.getCenter_name());
                intent.putExtra("children",centerManagementModelClass.getTotal_children());
                intent.putExtra("widow",centerManagementModelClass.getTotal_widows());
                intent.putExtra("orphan",centerManagementModelClass.getTotal_orphans());
                intent.putExtra("a",centerManagementModelClass.getA_group());
                intent.putExtra("a+",centerManagementModelClass.getA_positive_group());
                intent.putExtra("a-",centerManagementModelClass.getA_negative_group());
                intent.putExtra("ab",centerManagementModelClass.getAb_group());
                intent.putExtra("o+",centerManagementModelClass.getO_positive_group());
                intent.putExtra("o-",centerManagementModelClass.getO_negative_group());
                intent.putExtra("b+",centerManagementModelClass.getB_positive_group());
                intent.putExtra("b-",centerManagementModelClass.getB_negative_group());
                mContext.startActivity(intent);
                Toast.makeText(mContext,"You click on center name",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.center_management_txt_view);
        }
    }
    @Override
    public Filter getFilter() {
        return recentFilterSearch;
    }
    private Filter recentFilterSearch = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CenterManagementModelClass> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(searchList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CenterManagementModelClass item : searchList) {
                    if (item.getCenter_name().toLowerCase().contains(filterPattern)) {
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
