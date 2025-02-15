package br.ufjf.dcc.dcc025;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private List<Produto> produtos; // Lista de produtos comprados
    private Cupons cupom; // Cupom aplicado (opcional)

    // Construtor que inicializa a venda com um id e uma lista de produtos
    public Venda(int id) {
        this.id = id;
        this.produtos = new ArrayList<>(); // Inicializa a lista de produtos vazia
    }

    // funcao para adicionar um produto a venda
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    // funcao para aplicar um cupom a venda
    public void aplicarCupom(Cupons cupom) {
        if (cupom != null && cupom.aplicarCupom(calcularValorTotalSemDesconto())) {
            this.cupom = cupom; // Aplica o cupom se ele for válido
        } else {
            System.out.println("Cupom inválido ou não aplicável.");
        }
    }

    // funcao para calcular o valor total dos produtos sem aplicar descontos
    public double calcularValorTotalSemDesconto() {
        double total = 0.0;
        for (Produto produto : produtos) {
            total += produto.getPreco();            //a cada adicao de produto, soma o seu valor ao valor total da compra
        }
        return total;                               //retorna o total das compras
    }

    // funcao para calcular o valor total da venda, aplicando o desconto do cupom, se houver
    public double calcularValorTotalComDesconto() {
        double totalSemDesconto = calcularValorTotalSemDesconto();
        if (cupom != null) {                    //verifica se existe um cupom aplicado
            double desconto = totalSemDesconto * (cupom.getPercentualDesconto() / 100);     //calcula desconto
            return totalSemDesconto - desconto;                         //retorna valor total da compra com desconto
        }
        return totalSemDesconto; // Se não houver cupom, retorna o valor total sem desconto
    }

    // funcao para obter os produtos da venda-auxiliar para testes
    public List<Produto> getProdutos() {
        return produtos;
    }

    // funcao para obter o cupom aplicado-auxiliar para testes
    public Cupons getCupom() {
        return cupom;
    }

    // funcao para exibir o resumo da venda
    public void exibirResumo() {
        StringBuilder resumo = new StringBuilder();

        resumo.append("\n===== Resumo da Venda =====\n");
        resumo.append("ID da Venda: ").append(id).append("\n");
        resumo.append("Produtos Vendidos:\n");

        for (Produto produto : produtos) {
            resumo.append("- ").append(produto.getNome()).append(" (R$ ").append(produto.getPreco()).append(")\n");
        }

        resumo.append("Valor total sem desconto: R$ ").append(calcularValorTotalSemDesconto()).append("\n");

        if (cupom != null) {
            resumo.append("Cupom aplicado: ").append(cupom.getCodigo()).append("\n");
            resumo.append("Desconto: ").append(cupom.getPercentualDesconto()).append("%\n");
            resumo.append("Valor total com desconto: R$ ").append(calcularValorTotalComDesconto()).append("\n");
        } else {
            resumo.append("Nenhum cupom aplicado.\n");
        }

        System.out.println(resumo.toString());
    }

}
