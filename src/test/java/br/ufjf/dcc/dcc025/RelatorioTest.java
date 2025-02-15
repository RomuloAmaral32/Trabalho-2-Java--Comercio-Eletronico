package br.ufjf.dcc.dcc025;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class RelatorioTest {

    public RelatorioTest() {
    }

    @Test
    public void testRelatorioDeProdutos() {
        // Configura a venda e adiciona produtos
        Venda venda = new Venda(1);
        Produto roupa = new Roupa(1, "Camisa", 50, "azul", "M");
        Produto roupa2 = new Roupa(2, "Calça", 100, "azul", "G");

        venda.adicionarProduto(roupa);
        venda.adicionarProduto(roupa2);

        // Captura a saída do método exibirResumo()
        ByteArrayOutputStream saidaCapturada = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(saidaCapturada));

        venda.exibirResumo(); // Chama o método que imprime o resumo

        System.setOut(originalOut); // Restaura a saída padrão

        // Verifica se o relatório de produtos retorna corretamente os nomes e preços
        String relatorioEsperado = "===== Resumo da Venda =====\n" +
                "ID da Venda: 1\n" +
                "Produtos Vendidos:\n" +
                "- Camisa (R$ 50.0)\n" +
                "- Calça (R$ 100.0)\n" +
                "Valor total sem desconto: R$ 150.0\n" +
                "Nenhum cupom aplicado.\n";

        // Comparar as saídas ignorando espaços em branco ou quebras de linha extras
        assertEquals(relatorioEsperado.trim(), saidaCapturada.toString().trim());
    }

    @Test
    public void testRelatorioTotalVenda() {
        // Configura a venda e adiciona produtos
        Venda venda = new Venda(1);
        Produto roupa = new Roupa(1, "Camisa", 50, "azul", "M");
        Produto roupa2 = new Roupa(2, "Calça", 100, "azul", "G");

        venda.adicionarProduto(roupa);
        venda.adicionarProduto(roupa2);

        // Captura a saída do método exibirResumo()
        ByteArrayOutputStream saidaCapturada = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(saidaCapturada));

        venda.exibirResumo(); // Chama o método que imprime o resumo

        System.setOut(originalOut); // Restaura a saída padrão

        // Verifica se o relatório total da venda está correto
        String relatorioEsperado = "===== Resumo da Venda =====\n" +
                "ID da Venda: 1\n" +
                "Produtos Vendidos:\n" +
                "- Camisa (R$ 50.0)\n" +
                "- Calça (R$ 100.0)\n" +
                "Valor total sem desconto: R$ 150.0\n" +
                "Nenhum cupom aplicado.\n";

        // Comparar as saídas ignorando espaços em branco ou quebras de linha extras
        assertEquals(relatorioEsperado.trim(), saidaCapturada.toString().trim());
    }
}
