package br.ufjf.dcc.dcc025;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendaTest {

    @Test
    public void testAdicionarProduto() {
        Venda venda = new Venda(1);
        Produto roupa = new Roupa(1, "Camisa", 50, "azul", "M");

        venda.adicionarProduto(roupa);

        // Verifica se o produto foi adicionado corretamente
        assertEquals(1, venda.getProdutos().size());
        assertEquals(roupa, venda.getProdutos().get(0));
    }

    @Test
    public void testAplicarCupomValido() {
        Venda venda = new Venda(1);
        Produto roupa = new Roupa(1, "Camisa", 50, "azul", "M");
        CupomQuantidadeLimitada cupomQuantidadeLimitada = new CupomQuantidadeLimitada("PROMO10", 10.0, true, 5);

        venda.adicionarProduto(roupa);
        venda.aplicarCupom(cupomQuantidadeLimitada);

        // Verifica se o cupom foi aplicado corretamente
        assertEquals(cupomQuantidadeLimitada, venda.getCupom());
    }

    @Test
    public void testAplicarCupomInvalido() {
        Venda venda = new Venda(1);
        Produto roupa = new Roupa(1, "Camisa", 50, "azul", "M");

        // Cupom inativo (ativo = false) ou com quantidade esgotada (quantidadeUtilizada >= quantidadeMaxima)
        CupomQuantidadeLimitada cupomQuantidadeLimitada = new CupomQuantidadeLimitada("PROMO10", 10.0, false, 0);

        venda.adicionarProduto(roupa);
        venda.aplicarCupom(cupomQuantidadeLimitada);

        // Verifica se o cupom inválido não foi aplicado
        assertNull(venda.getCupom());
    }

    @Test
    public void testDescontoCupomQuantidadeLimitada() {
        Venda venda = new Venda(1);
        Produto roupa = new Roupa(1, "Camisa", 50, "azul", "M");
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("PROMO10", 10.0, true, 5);  // 10% de desconto, cupom válido

        venda.adicionarProduto(roupa);
        venda.aplicarCupom(cupom);

        // Verifica se o desconto foi aplicado corretamente
        assertEquals(45.0, venda.calcularValorTotalComDesconto(), 0.001); // 50 - 10% = 45.0
    }

    @Test
    public void testDescontoCupomValorMinimo() {
        Venda venda = new Venda(1);
        Produto roupa = new Roupa(1, "Camisa", 50, "azul", "M");
        CupomValorMinimo cupom = new CupomValorMinimo("PROMO15", 15.0, true, 40.0); // Cupom de 15% para compras acima de 40

        venda.adicionarProduto(roupa);
        venda.aplicarCupom(cupom);

        // Verifica se o cupom foi aplicado corretamente
        assertEquals(42.5, venda.calcularValorTotalComDesconto(), 0.001); // 50 - 15% = 42.5
    }

    @Test
    public void testCalcularTotalSemDesconto() {
        Venda venda = new Venda(1);
        Produto roupa = new Roupa(1, "Camisa", 50, "azul", "M");
        Produto roupa2 = new Roupa(2, "calça", 100, "azul", "G");

        venda.adicionarProduto(roupa);
        venda.adicionarProduto(roupa2);

        // Verifica se o valor total sem desconto foi calculado corretamente
        assertEquals(150.0, venda.calcularValorTotalSemDesconto(), 0.001);
    }

    @Test
    public void testCalcularTotalComDescontoCupomInvalido() {
        Venda venda = new Venda(1);
        Produto roupa = new Roupa(1, "Camisa", 50, "azul", "M");
        CupomValorMinimo cupom = new CupomValorMinimo("PROMO20", 20.0, false, 60.0); // Cupom inativo

        venda.adicionarProduto(roupa);
        venda.aplicarCupom(cupom);

        // Verifica que o valor total não tem desconto, pois o cupom é inativo
        assertEquals(50.0, venda.calcularValorTotalComDesconto(), 0.001);
    }

}
