package com.example.saltpayhack;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saltpayhack.adapters.CompanyRecyclerAdapter;
import com.example.saltpayhack.models.CompanyModel;

import java.util.ArrayList;

public class MainFragment extends Fragment implements
        View.OnClickListener,
        CompanyRecyclerAdapter.OnCompanyClickListener {

    // UI Components
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    // Variables
    private Context mContext;
    private CompanyRecyclerAdapter mCompanyRecyclerAdapter;
    public ArrayList<CompanyModel> mCompaniesList = new ArrayList<>();

    private static MainFragment instance;

    public MainFragment() {
        instance = this;
    }

    public static MainFragment getInstance() {
        if (instance == null) instance = new MainFragment();
        return instance;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        setListeners();
        initRecyclerView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mContext = container.getContext();
        mCompaniesList.add(new CompanyModel(1, "Yelp", 0.2f));
        mCompaniesList.add(new CompanyModel(2, "Yahoo", 0.6f));
        mCompaniesList.add(new CompanyModel(3, "Google", 0.2f));
        mCompaniesList.add(new CompanyModel(4, "Nandos", 0.6f));
        mCompaniesList.add(new CompanyModel(5, "KFC", 0.2f));
        mCompaniesList.add(new CompanyModel(6, "Maccies", 0.6f));

        return view;
    }

    @Override
    public void onClick(View view) {
    }

    private void initUI(View view) {
        mRecyclerView = view.findViewById(R.id.fragment_main_rv);
        mToolbar = view.findViewById(R.id.activity_main_tb);
        setHasOptionsMenu(true);

    }

    private void setListeners() {

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mCompanyRecyclerAdapter = new CompanyRecyclerAdapter(mContext, mCompaniesList, this);
        mRecyclerView.setAdapter(mCompanyRecyclerAdapter);
    }


    @Override
    public void onCompanyClick(int position, View view) {
        switch (view.getId()) {
            case R.id.card_btn_dislike:
                Toast.makeText(mContext, "Disliked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_btn_like:
                Toast.makeText(mContext, "Liked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_itm_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        System.out.println(searchView);
        System.out.println(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("Submitted");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("Test");
                mCompanyRecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

}
