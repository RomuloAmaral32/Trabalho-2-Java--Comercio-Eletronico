/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.ufjf.dcc.dcc025;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CupomValorMinimoTest {

    private CupomValorMinimo instance; // Define a instância aqui

    @BeforeEach
    public void setUp() {
        // Inicializa a instância com valores adequados
        instance = new CupomValorMinimo("DESCONTO20",20,true,100.0); // Define os valores para teste
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testGetValorMinimo() {
        System.out.println("getValorMinimo");
        double expResult = 100.0; // Define o valor esperado
        double result = instance.getValorMinimo();
        assertEquals(expResult, result, 0.001); // Utiliza uma margem de erro
    }


    @Test
    public void testAplicarCupom() {
        System.out.println("aplicarCupom");
        double valorCompra = 150.0; // Define o valor da compra para testar
        boolean expResult = true; // Supoe que o cupom seja aplicado para valor >= 100.0
        boolean result = instance.aplicarCupom(valorCompra);
        assertEquals(expResult, result);
    }


    @Test
    public void testCalcularDesconto() {
        System.out.println("calcularDesconto");
        double valorCompra = 150.0; // Defina o valor da compra
        double expResult = 30.0; // Suponha que o desconto seja de 10.0
        double result = instance.calcularDesconto(valorCompra);
        assertEquals(expResult, result, 0.001); // Utilize uma margem de erro
    }
}

