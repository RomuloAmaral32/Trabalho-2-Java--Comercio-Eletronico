package br.ufjf.dcc.dcc025;

    //informacoes gerais de um cupom
public abstract class CupomBase implements Cupons {
    private String codigo;
    private double percentualDesconto;
    private boolean ativo;

        //construtor de cupom base
    public CupomBase(String codigo, double percentualDesconto, boolean ativo) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.ativo = ativo;
    }

    @Override
    public String getCodigo() {
        return codigo;      //retorna o codigo de um cupom
    }

    @Override
    public double getPercentualDesconto() {
        return percentualDesconto;      //retorna o desconto percentual de um cupom
    }

    @Override
    public boolean isAtivo() {
        return ativo;               //retorna se um cupom esta ativo ou nao
    }

    @Override
    public abstract boolean aplicarCupom(double valorCompra);           //aplica um cupom a uma compra

    public abstract double calcularDesconto(double valorCompra);        //calcula o desconto de um cupom
}
