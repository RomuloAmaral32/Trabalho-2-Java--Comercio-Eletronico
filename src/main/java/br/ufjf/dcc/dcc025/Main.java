package br.ufjf.dcc.dcc025;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<ProdutoBase> produtos = new ArrayList<>();
    private static List<Cupons> cupons = new ArrayList<>();
    private static List<Venda> vendas = new ArrayList<>();

    public static void main(String[] args) {
        // Cadastrar Produtos manualmente
        produtos.add(new Eletronico(1, "Smartphone", 1500.0, 24)); //cria um produto com suas informaçoes gerais e com a especificacao de eletronico
        produtos.add(new Roupa(2, "Camisa", 50.0, "M", "Azul")); //cria um produto com suas informaçoes gerais e com a especificacao de roupa
        produtos.add(new Alimento(3, "Biscoito", 5.0, "2025-12-31")); //cria um produto com suas informaçoes gerais e com a especificacao de alimento

        // Cadasta os cupons manualmente com cada informacao individual
        cupons.add(new CupomQuantidadeLimitada("PROMO10", 10.0, true, 5));
        cupons.add(new CupomValorMinimo("DESCONTO20", 20.0, true, 100.0));
        cupons.add(new CupomQuantidadeLimitada("LIMITE", 15.0, true, 1));
        cupons.add(new CupomValorMinimo("VALORMIN", 5.0, true, 2000.0));
        cupons.add(new CupomQuantidadeLimitada("INATIVO", 10.0, false, 5));

        exibirRelatorioInicial(); // Exibe relatório inicial de cupons e produtos cadastrados
        gerarRelatorios(); // Gerar relatórios de cupons válidos e inválidos durante o processo
    }

    private static void exibirRelatorioInicial() {
        // Relatório de Produtos
        System.out.println("\n===== Relatório de Produtos =====");
        for (ProdutoBase produto : produtos) {
            System.out.println(produto.descricaoProduto()); //descricao de cada produto presente na loja
        }

        // Relatório de Cupons
        System.out.println("\n===== Relatório de Cupons Disponíveis =====");
        for (Cupons cupom : cupons) {
            if (cupom instanceof CupomQuantidadeLimitada) {  //verifica se o cupom é de quantidade limitada
                CupomQuantidadeLimitada cupomLimitado = (CupomQuantidadeLimitada) cupom;
                System.out.println("Código: " + cupomLimitado.getCodigo() +   //imprime as informacoes do cupom como codigo, desconto, validade e quantidade de utilizacoes
                        " | Desconto: " + cupomLimitado.getPercentualDesconto() + "%" +
                        " | Ativo: " + cupomLimitado.isAtivo() +
                        " | Máximo de Utilizações: " + cupomLimitado.getMaximoUtilizacoes() +
                        " | Utilizações Restantes: " + (cupomLimitado.getMaximoUtilizacoes() - cupomLimitado.getUtilizacoesAtuais()));
            } else if (cupom instanceof CupomValorMinimo) {     //verifica se o cupom é de valor minimo
                CupomValorMinimo cupomValorMinimo = (CupomValorMinimo) cupom;
                System.out.println("Código: " + cupomValorMinimo.getCodigo() +              //imprime as informacoes do cupom como codigo, desconto, validade e valor minimo
                        " | Desconto: " + cupomValorMinimo.getPercentualDesconto() + "%" +
                        " | Ativo: " + cupomValorMinimo.isAtivo() +
                        " | Valor Mínimo para Uso: R$ " + cupomValorMinimo.getValorMinimo());
            }
        }
    }

    private static void gerarRelatorios() {     //gera relatorios de vendas de cada cupom individualmente presente na loja, com todas suas informacoes
        System.out.println("\n===== Relatório de Vendas com Cupom PROMO10 =====");
        gerarRelatorioComCupom("PROMO10");

        System.out.println("\n===== Relatório de Vendas com Cupom DESCONTO20 =====");
        gerarRelatorioComCupom("DESCONTO20");

        System.out.println("\n===== Relatório de Vendas com Cupom LIMITE (utilização extra) =====");
        gerarRelatorioComCupom("LIMITE");
        gerarRelatorioComCupom("LIMITE"); // Tentativa de aplicar o cupom pela segunda vez

        System.out.println("\n===== Relatório de Vendas com Cupom VALORMIN (valor mínimo não atingido) =====");
        gerarRelatorioComCupom("VALORMIN");

        System.out.println("\n===== Relatório de Vendas com Cupom INATIVO =====");
        gerarRelatorioComCupom("INATIVO");
    }

    private static void gerarRelatorioComCupom(String codigoCupom) {
        Venda venda = new Venda(vendas.size() + 1); // Criar nova venda com ID automático

        // Adicionar todos os produtos na venda
        for (ProdutoBase produto : produtos) {
            venda.adicionarProduto(produto);
        }

        // Exibir valor total da venda de todos produtos adicionados antes do desconto
        double valorTotal = venda.calcularValorTotalSemDesconto();
        System.out.println("Valor total da compra sem desconto: R$ " + valorTotal);

        // Aplicar o cupom correspondente na venda
        Cupons cupomAplicado = cupons.stream()
                .filter(c -> c.getCodigo().equals(codigoCupom))
                .findFirst()
                .orElse(null);

        if (cupomAplicado != null) {
            venda.aplicarCupom(cupomAplicado);      //aplica o cupom na venda

            // Verificar se o cupom foi aplicado corretamente
            if (venda.calcularValorTotalComDesconto() < venda.calcularValorTotalSemDesconto()) {
                System.out.println("Cupom " + cupomAplicado.getCodigo() + " aplicado: " + cupomAplicado.getPercentualDesconto() + "% de desconto");     //informacoes do cupom aplicado
            } else {
                System.out.println("O cupom " + codigoCupom + " não foi aplicado. Verifique se as condições foram atendidas.");         //caso o cupom nao foi aplicado, envia uma mensagem informando que alguma das condicoes nao foram cumpridas
            }
        } else {
            System.out.println("Cupom " + codigoCupom + " não encontrado.");            //caso o codigo do cupom nao foi encontrado, envia uma mensagem informando que o cupom nao foi encontrado
        }

        vendas.add(venda);  // Adicionar venda ao histórico

        venda.exibirResumo(); // Exibir resumo da venda

        exibirRelatorioCuponsAtualizado(); // Exibir relatório atualizado de cupons
    }

    private static void exibirRelatorioCuponsAtualizado() {         //funcao que retorna o relatorio atualizado de cupons
        System.out.println("\n===== Relatório Atualizado de Cupons =====");
        for (Cupons cupom : cupons) {
            if (cupom instanceof CupomQuantidadeLimitada) {
                CupomQuantidadeLimitada cupomLimitado = (CupomQuantidadeLimitada) cupom;
                System.out.println("Código: " + cupomLimitado.getCodigo() +
                        " | Desconto: " + cupomLimitado.getPercentualDesconto() + "%" +
                        " | Ativo: " + cupomLimitado.isAtivo() +
                        " | Máximo de Utilizações: " + cupomLimitado.getMaximoUtilizacoes() +
                        " | Utilizações Restantes: " + (cupomLimitado.getMaximoUtilizacoes() - cupomLimitado.getUtilizacoesAtuais()));
            } else if (cupom instanceof CupomValorMinimo) {
                CupomValorMinimo cupomValorMinimo = (CupomValorMinimo) cupom;
                System.out.println("Código: " + cupomValorMinimo.getCodigo() +
                        " | Desconto: " + cupomValorMinimo.getPercentualDesconto() + "%" +
                        " | Ativo: " + cupomValorMinimo.isAtivo() +
                        " | Valor Mínimo para Uso: R$ " + cupomValorMinimo.getValorMinimo());
            }
        }
    }
}
