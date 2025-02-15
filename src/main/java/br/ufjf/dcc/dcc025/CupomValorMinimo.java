package br.ufjf.dcc.dcc025;

public class CupomValorMinimo extends CupomBase {      //subclasse de CupomBase
    private double valorMinimo;                        //atributo particular de CupomValorMinimo

    public CupomValorMinimo(String codigo, double percentualDesconto, boolean ativo, double valorMinimo) {
        super(codigo, percentualDesconto, ativo);           //construtor de cupimValorMinimo
        this.valorMinimo = valorMinimo;
    }

    public double getValorMinimo() {
        return valorMinimo;                             //retorna o valor minimo par desconto
    }

    @Override
    public boolean aplicarCupom(double valorCompra) {
        return isAtivo() && valorCompra >= valorMinimo;             //verifica se o cupom é ativo e se o valor da compra é maior que o valor minimo
    }

    @Override
    public double calcularDesconto(double valorCompra) {            //calcula o desconto
        if (aplicarCupom(valorCompra)) {
            return valorCompra * (getPercentualDesconto() / 100);       //retorna o valor do desconto
        }
        return 0;  // Sem desconto
    }
}
