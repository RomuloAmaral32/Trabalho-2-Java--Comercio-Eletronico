package br.ufjf.dcc.dcc025;

public class Roupa extends ProdutoBase {                //subclasse de ProdutoBase
    private String cor;                                 //atributo particular de roupa
    private String tamanho;                             //atributo particular de roupa

    public Roupa(int id, String nome, double preco, String cor, String tamanho) {
        super(id, nome, preco);                         //construtor de roupa
        this.cor = cor;
        this.tamanho = tamanho;
    }
}
