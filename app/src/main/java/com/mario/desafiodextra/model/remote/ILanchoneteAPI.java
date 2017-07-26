package com.mario.desafiodextra.model.remote;

import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;
import com.mario.desafiodextra.model.remote.entityAPI.LancheAPI;
import com.mario.desafiodextra.model.remote.entityAPI.PedidoAPI;
import com.mario.desafiodextra.model.remote.entityAPI.PromocaoAPI;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mario on 23/07/17.
 */

public interface ILanchoneteAPI {
    @GET("lanche")
    Observable<List<LancheAPI>> lunchFindAll();

    @GET("lanche/{id}")
    Observable<LancheAPI> lunchFindById(@Path("id")String id);

    @GET("ingrediente")
    Observable<List<IngredienteAPI>> ingredientFindAll();

    @GET("ingrediente/de/{id}")
    Observable<List<IngredienteAPI>> ingredientFindById(@Path("id")Long id);

    @GET("promocao")
    Observable<List<PromocaoAPI>> promotionFindAll();

    @GET("pedido")
    Observable<List<PedidoAPI>> orderFindAll();

    @PUT("pedido/{id_lanche}")
    Observable<PedidoAPI> sendOrder(@Path("id_lanche") Long id);

    @FormUrlEncoded
    @PUT("pedido/{id_lanche}")
    Observable<PedidoAPI> sendOrderWithExtras(@Path("id_lanche") Long id, @Field("extras") String extras);
}
