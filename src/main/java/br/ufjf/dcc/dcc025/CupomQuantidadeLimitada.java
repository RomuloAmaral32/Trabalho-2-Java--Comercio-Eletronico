package br.ufjf.dcc.dcc025;

public class CupomQuantidadeLimitada extends CupomBase {
    private int maximoUtilizacoes;      // Máximo de vezes que o cupom pode ser utilizado
    private int utilizacoesAtuais;      // Quantidade de vezes que o cupom já foi utilizado

    public CupomQuantidadeLimitada(String codigo, double percentualDesconto, boolean ativo, int maximoUtilizacoes) {
        super(codigo, percentualDesconto, ativo);           //construtor da superclasse
        this.maximoUtilizacoes = maximoUtilizacoes;         // Inicializa o máximo de utilizações
        this.utilizacoesAtuais = 0;                         // Inicializa a quantidade de utilizações atuais
    }

    public int getMaximoUtilizacoes() {
        return maximoUtilizacoes;               // Retorna o máximo de utilizações
    }

    public int getUtilizacoesAtuais() {
        return utilizacoesAtuais;               // Retorna a quantidade de utilizações atuais
    }

    @Override
    public boolean aplicarCupom(double valorCompra) {                   // funcao para aplicar o cupom
        // Verifica se o cupom está ativo e se ainda pode ser utilizado
        if (isAtivo() && utilizacoesAtuais < maximoUtilizacoes) {       //verifica se o cupom está ativo e ainda pode ser utilizado
            utilizacoesAtuais++;                                        // Incrementa a quantidade de utilizações atuais
            return true;
        }
        return false;
    }
    @Override
    public double calcularDesconto(double valorCompra) {                // funcao para calcular o desconto
        if (aplicarCupom(valorCompra)) {
            return valorCompra * (getPercentualDesconto() / 100);       // Retorna o valor do desconto
        }
        return 0;  // Sem desconto
    }
}
