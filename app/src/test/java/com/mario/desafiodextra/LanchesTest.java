package com.mario.desafiodextra;

import com.mario.desafiodextra.model.entity.Lanche;
import com.mario.desafiodextra.model.entity.builder.IngredienteBuilder;
import com.mario.desafiodextra.model.entity.builder.LancheBuilder;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LanchesTest {
    @Test
    public void verifica_valor_do_lanche_pela_soma_dos_ingredientes() throws Exception {
        IngredienteAPI pao = new IngredienteBuilder()
                .withId(1L)
                .withImage("http://pao.png")
                .withName("Pao_gergilim")
                .withPrice(1.0)
                .builder();

        IngredienteAPI hamburguer = new IngredienteBuilder()
                .withId(2L)
                .withImage("http://hamburguer.png")
                .withName("Hamburguer de Carne")
                .withPrice(3.0)
                .builder();

        IngredienteAPI bacon = new IngredienteBuilder()
                .withId(3L)
                .withImage("http://bacon.png")
                .withName("Bacon")
                .withPrice(2.0)
                .builder();

        IngredienteAPI queijo = new IngredienteBuilder()
                .withId(4L)
                .withImage("http://queijo.png")
                .withName("Queijo")
                .withPrice(1.5)
                .builder();

        Lanche xBacon = new LancheBuilder()
                .withId(1L)
                .withImage("http://x_bacon.png")
                .withName("X-Bacon")
                .withIngredients(Arrays.asList(pao, bacon, hamburguer, queijo))
                .builder();

        assertEquals(7.5, xBacon.getPriceTotal(),0.00001);
    }

    //Promoção LIGHT
    //Se o lanche tem alface e não tem bacon, ganha 10% de desconto.
    @Test
    public void verifica_valor_do_lanche_promocao_light_10_por_cento_desconto() throws Exception {
        IngredienteAPI pao = new IngredienteBuilder()
                .withId(1L)
                .withImage("http://pao.png")
                .withName("Pao_gergilim")
                .withPrice(1.0)
                .builder();

        IngredienteAPI hamburguer = new IngredienteBuilder()
                .withId(2L)
                .withImage("http://hamburguer.png")
                .withName("Hamburguer de Carne")
                .withPrice(3.0)
                .builder();

        IngredienteAPI queijo = new IngredienteBuilder()
                .withId(3L)
                .withImage("http://queijo.png")
                .withName("Queijo")
                .withPrice(1.5)
                .builder();

        //Adicional de Alface
        IngredienteAPI alface = new IngredienteBuilder()
                .withId(4L)
                .withImage("http://alface.png")
                .withName("Alface")
                .withPrice(0.4)
                .builder();

        Lanche xBurguer = new LancheBuilder()
                .withId(1L)
                .withImage("http://x.png")
                .withName("X-Burguer")
                .withIngredients(Arrays.asList(pao, hamburguer, queijo))
                .withExtras(Collections.singletonList(alface))
                .builder();

        //Total da 5,9 com Desconto de 10% 5,31
        assertEquals(5.31, xBurguer.getPriceTotal(),0.00001);
    }

    //Promoção MUITA CARNE
    //A cada 3 porções de carne o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante
    @Test
    public void verifica_valor_do_lanche_promocao_muita_carne() throws Exception {
        IngredienteAPI pao = new IngredienteBuilder()
                .withId(1L)
                .withImage("http://pao.png")
                .withName("Pao_gergilim")
                .withPrice(1.0)
                .builder();

        IngredienteAPI hamburguer = new IngredienteBuilder()
                .withId(2L)
                .withImage("http://hamburguer.png")
                .withName("Hamburguer de Carne")
                .withPrice(3.0)
                .builder();

        IngredienteAPI queijo = new IngredienteBuilder()
                .withId(3L)
                .withImage("http://queijo.png")
                .withName("Queijo")
                .withPrice(1.5)
                .builder();

        //Adicional de hamburguer
        IngredienteAPI hamburguerExtra01 = new IngredienteBuilder()
                .withId(2L)
                .withImage("http://hamburguer.png")
                .withName("Hamburguer de Carne")
                .withPrice(3.0)
                .builder();

        //Adicional de hamburguer
        IngredienteAPI hamburguerExtra02 = new IngredienteBuilder()
                .withId(2L)
                .withImage("http://hamburguer.png")
                .withName("Hamburguer de Carne")
                .withPrice(3.0)
                .builder();

        Lanche xBurguerComMuitaCarne = new LancheBuilder()
                .withId(1L)
                .withImage("http://x.png")
                .withName("X-Burguer")
                .withIngredients(Arrays.asList(pao, hamburguer, queijo))
                .withExtras(Arrays.asList(hamburguerExtra01,hamburguerExtra02))
                .builder();

        //Adiocionei 2 Hamburguer Extras pois o lanche ja contem 1
        //O Valor do Lanche daria 11,50 com a promoção valor é 8,50
        assertEquals(8.5, xBurguerComMuitaCarne.getPriceTotal(),0.00001);
    }

    //Promoção MUITA QUEIJO
    //A cada 3 porções de queijo o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante
    @Test
    public void verifica_valor_do_lanche_promocao_muito_queijo() throws Exception {
        IngredienteAPI pao = new IngredienteBuilder()
                .withId(1L)
                .withImage("http://pao.png")
                .withName("Pao_gergilim")
                .withPrice(1.0)
                .builder();

        IngredienteAPI hamburguer = new IngredienteBuilder()
                .withId(2L)
                .withImage("http://hamburguer.png")
                .withName("Hamburguer de Carne")
                .withPrice(3.0)
                .builder();

        IngredienteAPI queijo = new IngredienteBuilder()
                .withId(3L)
                .withImage("http://queijo.png")
                .withName("Queijo")
                .withPrice(1.5)
                .builder();

        //Adicional de Queijo
        IngredienteAPI queijoExtra01 = new IngredienteBuilder()
                .withId(3L)
                .withImage("http://hamburguer.png")
                .withName("Queijo")
                .withPrice(1.5)
                .builder();

        //Adicional de Queijo
        IngredienteAPI queijoExtra02 = new IngredienteBuilder()
                .withId(3L)
                .withImage("http://hamburguer.png")
                .withName("Queijo")
                .withPrice(1.5)
                .builder();

        Lanche xBurguerComMuitoQueijo = new LancheBuilder()
                .withId(1L)
                .withImage("http://x.png")
                .withName("X-Burguer")
                .withIngredients(Arrays.asList(pao, hamburguer, queijo))
                .withExtras(Arrays.asList(queijoExtra01,queijoExtra02))
                .builder();

        //Adiocionei 2 Queijo Extras pois o lanche ja contem
        //O Valor do Lanche daria 8,50 com a promoção valor é 7,00
        assertEquals(7, xBurguerComMuitoQueijo.getPriceTotal(),0.00001);
    }
}