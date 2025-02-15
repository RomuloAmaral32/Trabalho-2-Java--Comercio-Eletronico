package br.ufjf.dcc.dcc025;

    //informacoes gerais que todos cupons independente do tipo herdam
public interface Cupons {
    String getCodigo();
    double getPercentualDesconto();
    boolean isAtivo();
    boolean aplicarCupom(double valorCompra);
    double calcularDesconto(double valorCompra);
}
