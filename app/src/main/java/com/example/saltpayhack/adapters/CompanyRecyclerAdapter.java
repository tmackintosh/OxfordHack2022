package com.example.saltpayhack.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saltpayhack.R;
import com.example.saltpayhack.models.CompanyModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CompanyRecyclerAdapter extends RecyclerView.Adapter<CompanyRecyclerAdapter.ViewHolder>
    implements Filterable {

    // Constants
    private static final String TAG = "CompanyRecyclerAdapter";

    // Variables
    private Context mContext;
    private OnCompanyClickListener mOnCompanyClickListener;
    private ArrayList<CompanyModel> mCompaniesList;
    private ArrayList<CompanyModel> mCompaniesListFull;

    public CompanyRecyclerAdapter(Context context, ArrayList<CompanyModel> companiesList, OnCompanyClickListener onCompanyClickListener) {
        mContext = context;
        mCompaniesList = companiesList;
        mOnCompanyClickListener = onCompanyClickListener;
        mCompaniesListFull = new ArrayList<>(mCompaniesList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_card, parent, false);
        return new ViewHolder(view, mOnCompanyClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompanyModel company = mCompaniesList.get(position);
        holder.tv_companyName.setText(company.getCompanyName());
        //TODO change to calculated rating
        holder.tv_calculatedRating.setText("0.0/10");
    }

    @Override
    public int getItemCount() {
        return mCompaniesList.size();
    }

    @Override
    public Filter getFilter() {
        return companyFilter;
    }

    private final Filter companyFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CompanyModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mCompaniesListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CompanyModel exercise : mCompaniesListFull) {
                    if (exercise.getCompanyName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(exercise);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            System.out.println(results.values);
            System.out.println(results.count);
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mCompaniesList.clear();
            mCompaniesList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public interface OnCompanyClickListener {
        void onCompanyClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnCompanyClickListener onCompanyClickListener;

        TextView tv_calculatedRating, tv_companyName;
        FloatingActionButton btn_like, btn_dislike;

        public ViewHolder(View itemView, OnCompanyClickListener onCompanyClickListener) {
            super(itemView);
            tv_companyName = itemView.findViewById(R.id.row_card_tv_companyName);
            tv_calculatedRating = itemView.findViewById(R.id.row_card_tv_overallRating);
            btn_like = itemView.findViewById(R.id.card_btn_like);
            btn_dislike = itemView.findViewById(R.id.card_btn_dislike);

            this.onCompanyClickListener = onCompanyClickListener;
            btn_dislike.setOnClickListener(this);
            btn_like.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCompanyClickListener.onCompanyClick(getBindingAdapterPosition(), view);
        }
    }

}
