package br.ufjf.dcc.dcc025;

import java.util.ArrayList;
import java.util.List;

public class GestorVendas implements Relatorio {
    private List<Venda> vendas; // Lista de vendas realizadas
    private List<Cupons> cuponsDisponiveis; // Lista de cupons disponíveis

    public GestorVendas() {
        this.vendas = new ArrayList<>();                    // Inicializa a lista de vendas vazia
        this.cuponsDisponiveis = new ArrayList<>();         // Inicializa a lista de cupons vazia
    }

    public void registrarVenda(Venda venda) {
        vendas.add(venda);                               // Adiciona uma nova venda ao sistema
    }

    public void adicionarCupom(Cupons cupom) {
        cuponsDisponiveis.add(cupom);                   // Adiciona um novo cupom ao sistema
    }

    @Override
    public void gerarRelatorioVendas() {           // Implementação da funcao para gerar o relatório de vendas
        double totalArrecadado = 0.0;

        System.out.println("Relatório de Vendas:");
        for (Venda venda : vendas) {
            venda.exibirResumo(); // Exibe o resumo de cada venda
            totalArrecadado += venda.calcularValorTotalComDesconto(); // Soma o valor arrecadado
        }

        System.out.println("Total Arrecadado: R$ " + totalArrecadado);
        System.out.println("Número Total de Vendas: " + vendas.size());
    }

    // Implementação funcao para listar os cupons ativos
    @Override
    public void listarCuponsAtivos() {
        System.out.println("Cupons Disponíveis:");

        for (Cupons cupom : cuponsDisponiveis) {
            if (cupom.isAtivo()) {
                System.out.println("Código: " + cupom.getCodigo() + " - Desconto: " + cupom.getPercentualDesconto() + "%");
                if (cupom instanceof CupomQuantidadeLimitada) {                 //verifica se o cupom é de quantidade limitada
                    CupomQuantidadeLimitada cupomLimitado = (CupomQuantidadeLimitada) cupom;        //imprime as informacoes do cupom como codigo, desconto, validade e quantidade de utilizacoes
                    System.out.println("Tipo: Quantidade Limitada - Máximo de Utilizações: " + cupomLimitado.getMaximoUtilizacoes() +
                            " - Utilizações Atuais: " + cupomLimitado.getUtilizacoesAtuais());
                } else if (cupom instanceof CupomValorMinimo) {                     //verifica se o cupom é de valor minimo
                    CupomValorMinimo cupomMinimo = (CupomValorMinimo) cupom;        //imprime as informacoes do cupom como codigo, desconto, validade e valor minimo
                    System.out.println("Tipo: Valor Mínimo - Valor Mínimo: R$ " + cupomMinimo.getValorMinimo());
                }
            }
        }
    }
}
