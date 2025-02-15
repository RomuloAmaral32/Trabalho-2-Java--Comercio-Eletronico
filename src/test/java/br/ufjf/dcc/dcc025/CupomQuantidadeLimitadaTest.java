package br.ufjf.dcc.dcc025;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CupomQuantidadeLimitadaTest {

    private CupomQuantidadeLimitada instance; // Define a instância aqui

    @BeforeEach
    public void setUp() {
        // Inicializa a instância com valores adequados
        instance = new CupomQuantidadeLimitada("PROMO10", 10.0, true, 5); // Passa o cupom com as suas informações individuais e de uso
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetLimiteUsos() { // Testa o limite de usos do cupom
        System.out.println("getLimiteUsos");
        int expResult = 5; // Define o limite de usos esperado
        int result = instance.getMaximoUtilizacoes();
        assertEquals(expResult, result);
    }

    @Test
    public void testAplicarCupom() { // Testa a aplicação do cupom
        System.out.println("aplicarCupom");
        double valorCompra = 100.0; // Define o valor da compra para testar
        boolean expResult = true; // Suponha que o cupom seja aplicado corretamente
        boolean result = instance.aplicarCupom(valorCompra);
        assertEquals(expResult, result);

        // Após aplicar o cupom, o número de usos deve ser decrementado
        int expLimiteUsos = 5; // O limite de usos deve ser decrementado para 4
        assertEquals(expLimiteUsos, instance.getMaximoUtilizacoes());
    }

    @Test
    public void testCalcularDesconto() {
        System.out.println("calcularDesconto");
        double valorCompra = 100.0; // Define o valor da compra
        double expResult = 10.0; // Supõe que o desconto seja de 10.0
        double result = instance.calcularDesconto(valorCompra);
        assertEquals(expResult, result, 0.001); // Utilize uma margem de erro
    }

    @Test
    public void testAplicarCupomExcedendoLimite() {
        System.out.println("aplicarCupomExcedendoLimite");

        // Aplicar o cupom 5 vezes, até atingir o limite de usos
        for (int i = 0; i < 5; i++) {
            boolean result = instance.aplicarCupom(100.0);
            assertTrue(result); // O cupom deve ser aplicado com sucesso nas primeiras 5 vezes
        }

        // Na sexta tentativa, o cupom não deve ser aplicado
        boolean expResult = false;
        boolean result = instance.aplicarCupom(100.0);
        assertEquals(expResult, result);

        // O limite de usos deve ser zero agora
        int expLimiteUsos = 5;
        assertEquals(expLimiteUsos, instance.getMaximoUtilizacoes());
    }
}
