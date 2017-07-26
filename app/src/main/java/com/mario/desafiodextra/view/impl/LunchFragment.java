package com.mario.desafiodextra.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.mario.desafiodextra.R;
import com.mario.desafiodextra.model.entity.Lanche;
import com.mario.desafiodextra.presenter.impl.LunchPresenter;
import com.mario.desafiodextra.view.ILunchFragment;
import com.mario.desafiodextra.view.adapter.ClickList;
import com.mario.desafiodextra.view.adapter.LunchAdapter;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Mario Feles dos Santos Junior
 * @email mario_feles@live.com
 * @date 02 21
 * @project GoodLaundry
 */

public class LunchFragment extends Fragment implements ILunchFragment, ClickList{
    @BindView(R.id.progress) ProgressBar progress;
    @BindView(R.id.rvLunch) RecyclerView rvLunch;

    Unbinder unbinder;
    private LunchPresenter presenter;

    public static LunchFragment newInstance() {
        return new LunchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lunch, container, false);

        unbinder = ButterKnife.bind(this, view);

        rvLunch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvLunch.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        presenter = new LunchPresenter(this);
        presenter.loadLunchs();
        return view;
    }

    @Override
    public void showLunchOnIU(List<Lanche> lunchs) {
        updateTableLunch(lunchs);
        if (progress.isShown()) {
            hideProgress();
        }
    }

    @Override
    public void onClickItem(Object object) {
        Lanche lunch = (Lanche) object;
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("lunch", lunch);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onStop();
    }

    private void updateTableLunch(List<Lanche> lunchs) {
        LunchAdapter adapter = new LunchAdapter(lunchs, this);
        rvLunch.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }
}