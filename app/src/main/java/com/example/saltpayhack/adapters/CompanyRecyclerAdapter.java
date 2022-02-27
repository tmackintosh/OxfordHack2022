package com.example.saltpayhack.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saltpayhack.R;
import com.example.saltpayhack.models.CompanyModel;

import java.util.ArrayList;

public class CompanyRecyclerAdapter extends RecyclerView.Adapter<CompanyRecyclerAdapter.ViewHolder>{

    // Constants
    private static final String TAG = "CompanyRecyclerAdapter";

    // Variables
    private Context mContext;
    private OnCompanyClickListener mOnCompanyClickListener;
    private ArrayList<CompanyModel> mCompaniesList;

    public CompanyRecyclerAdapter(Context context, ArrayList<CompanyModel> companiesList, OnCompanyClickListener onCompanyClickListener) {
        mContext = context;
        mCompaniesList = companiesList;
        mOnCompanyClickListener = onCompanyClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.row_card, parent, false);
        return new ViewHolder(view, mOnCompanyClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompanyModel company = mCompaniesList.get(position);
    }

    @Override
    public int getItemCount() {
        return mCompaniesList.size();
    }

    public interface OnCompanyClickListener {
        void onCompanyClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnCompanyClickListener onCompanyClickListener;

        TextView tv_calculatedRating, tv_companyName;
        Button btn_like, btn_dislike;

        public ViewHolder(View itemView, OnCompanyClickListener onCompanyClickListener) {
            super(itemView);
            tv_companyName = itemView.findViewById(R.id.row_card_tv_companyName);
            tv_calculatedRating = itemView.findViewById(R.id.row_card_tv_calculatedRating);
            btn_like = itemView.findViewById(R.id.row_card_btn_like);
            btn_dislike = itemView.findViewById(R.id.row_card_btn_dislike);

            this.onCompanyClickListener = onCompanyClickListener;
        }

        @Override
        public void onClick(View view) {
            onCompanyClickListener.onCompanyClick(getBindingAdapterPosition(), view);
        }
    }
}
