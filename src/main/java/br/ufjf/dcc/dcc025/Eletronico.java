package br.ufjf.dcc.dcc025;

public class Eletronico extends ProdutoBase  {          //subclasse de ProdutoBase
    private int prazoGarantiaMeses;                     //atributo particular de eletronico

    public Eletronico(int id, String nome, double preco, int prazoGarantiaMeses) {
        super(id, nome, preco);                         //construtor de eletronico
        this.prazoGarantiaMeses = prazoGarantiaMeses;
    }

    @Override
    public String descricaoProduto() {                //retorna a descricao do produto eletronico
        return "Eletrônico: " + getNome() + " - Preço: " + getPreco() + " - Garantia: " + prazoGarantiaMeses + " meses";
    }
}
