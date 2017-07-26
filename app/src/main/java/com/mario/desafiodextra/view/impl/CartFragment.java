package com.mario.desafiodextra.view.impl;

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
import com.mario.desafiodextra.model.entity.Pedido;
import com.mario.desafiodextra.presenter.impl.OrderPresenter;
import com.mario.desafiodextra.view.ICartFragment;
import com.mario.desafiodextra.view.adapter.OrderAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Mario Feles dos Santos Junior
 * @email mario_feles@live.com
 */

public class CartFragment extends Fragment implements ICartFragment {
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.rvOrders)
    RecyclerView rvOrders;

    Unbinder unbinder;
    private OrderPresenter presenter;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        unbinder = ButterKnife.bind(this, view);

        rvOrders.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvOrders.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        presenter = new OrderPresenter(this);
        presenter.loadOrders();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onStop();
    }

    private void updateTableOrders(List<Pedido> orders) {
        OrderAdapter adapter = new OrderAdapter(orders);
        rvOrders.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showOrderOnIU(List<Pedido> orders) {
        updateTableOrders(orders);
        if (progress.isShown()) {
            hideProgress();
        }
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