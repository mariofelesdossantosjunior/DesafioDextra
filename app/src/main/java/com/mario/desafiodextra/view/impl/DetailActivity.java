package com.mario.desafiodextra.view.impl;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mario.desafiodextra.R;
import com.mario.desafiodextra.model.entity.Lanche;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;
import com.mario.desafiodextra.model.remote.entityAPI.PedidoAPI;
import com.mario.desafiodextra.presenter.impl.CartPresenter;
import com.mario.desafiodextra.view.IDetailLunch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mario.desafiodextra.util.Generics.formatCurrency;
import static com.mario.desafiodextra.util.Generics.makeIngredients;

public class DetailActivity extends AppCompatActivity implements IDetailLunch {

    @BindView(R.id.bt_add_cart) Button btAddCart;
    @BindView(R.id.bt_customize) Button btCustomize;
    @BindView(R.id.imv_row_detail) SimpleDraweeView imvRowDetail;
    @BindView(R.id.tv_row_detail_name) TextView tvRowDetailName;
    @BindView(R.id.tv_row_detail_price) TextView tvRowDetailPrice;
    @BindView(R.id.tv_row_detail_ingredients) TextView tvRowDetailIngredients;

    private Lanche lanche;
    private CartPresenter cartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartPresenter = new CartPresenter(this);

        if (!getIntent().getExtras().isEmpty()) {
            lanche = getIntent().getExtras().getParcelable("lunch");
        }

        showDetailLunch(lanche);
    }

    @Override
    public void showDetailLunch(Lanche lunch) {
        Uri uri = Uri.parse(lunch.getImage());
        imvRowDetail.setImageURI(uri);
        tvRowDetailName.setText(lunch.getName());
        tvRowDetailPrice.setText(formatCurrency(lunch.getPriceTotal()));
        tvRowDetailIngredients.setText(makeIngredients(lunch.getIngredients()));
    }

    @Override
    public void sendSucess(PedidoAPI pedidoAPI) {
        Toast.makeText(this, "Lanche adicionado com sucesso.", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void sendError() {
        Toast.makeText(this, "Erro ao adionar item !", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bt_add_cart)
    public void sendOrder(){
        if(!lanche.getExtras().isEmpty()){
            cartPresenter.addItemCartWithExtras(lanche.getId(),Stream.of(lanche.getExtras())
                    .map(IngredienteAPI::getId)
                    .toList());
        }else{
            cartPresenter.addItemCart(lanche.getId());
        }
    }

    @OnClick(R.id.bt_customize)
    public void customizeOrder(){
        showDialogCustomize();
    }

    public void showDialogCustomize() {
        ExtrasDialog dialog = new ExtrasDialog(this);
        dialog.setResultExtrasSelect(this::makeExtras);
        dialog.show();
    }

    private void makeExtras(Map<Long, Integer> extras) {
        lanche.setName(lanche.getName().concat(" - do seu jeito"));
        lanche.setExtras(new ArrayList<>());

        Stream.of(extras).flatMap(this::makeExtrasEntry)
                .forEach(key -> lanche.getExtras().add(recuperIngrediente(key)));

        showDetailLunch(lanche);
    }

    private Stream<Long> makeExtrasEntry(Map.Entry<Long, Integer> extraEntry) {
        List<Long> extrasKey = new ArrayList<>();
        for(int x = 0; x < extraEntry.getValue(); x++){
            extrasKey.add(extraEntry.getKey());
        }
        return Stream.of(extrasKey);
    }

    private IngredienteAPI recuperIngrediente(Long key) {
        return cartPresenter.findIngredientByKey(key);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cartPresenter.onStop();
    }
}
