package com.mario.desafiodextra.view.impl;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.mario.desafiodextra.R;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;
import com.mario.desafiodextra.presenter.impl.IngredientsPresenter;
import com.mario.desafiodextra.view.ExtrasSelectListener;
import com.mario.desafiodextra.view.IExtrasDialog;
import com.mario.desafiodextra.view.adapter.IngredientsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mario on 25/07/17.
 */

public class ExtrasDialog extends Dialog implements IExtrasDialog {

    @BindView(R.id.rv_add_ingre)
    RecyclerView rvAddIngre;
    @BindView(R.id.bt_dialog_ingre_save)
    Button btDialogIngreSave;
    @BindView(R.id.bt_dialog_ingre_cancel)
    Button btDialogIngreCancel;

    private IngredientsPresenter presenter;
    private IngredientsAdapter adapter;
    private ExtrasSelectListener extrasSelectListener;

    public ExtrasDialog(Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        ButterKnife.bind(this);
        rvAddIngre.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        presenter = new IngredientsPresenter(this);
        presenter.loadIngredients();
    }

    @Override
    public void showIngredientsOnIU(List<IngredienteAPI> ingredienteAPIs) {
        updateIngredients(ingredienteAPIs);
    }

    private void updateIngredients(List<IngredienteAPI> ingredients) {
        adapter = new IngredientsAdapter(ingredients);
        rvAddIngre.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.bt_dialog_ingre_cancel)
    public void cancel(){
        dismiss();
    }

    @OnClick(R.id.bt_dialog_ingre_save)
    public void clickSave(){
        if (extrasSelectListener != null) {
            extrasSelectListener.finish(adapter.getExtras());
        }
        ExtrasDialog.this.dismiss();
    }

    public void setResultExtrasSelect(ExtrasSelectListener extrasSelectListener) {
        this.extrasSelectListener = extrasSelectListener;
    }
}