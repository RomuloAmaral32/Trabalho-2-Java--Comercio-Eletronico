package br.ufjf.dcc.dcc025;

public abstract class ProdutoBase implements Produto {          //classe abstrata ProdutoBase implementa a interface Produto
    private int id;
    private String nome;
    private double preco;

    public ProdutoBase(int id, String nome, double preco) {             //construtor da classe ProdutoBase
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }       //retorna o id do produto

    public String getNome() {
        return nome;
    }       //retorna o nome do produto

    public double getPreco() {
        return preco;
    }           //retorna o preço do produto

    @Override
    public String descricaoProduto() {
        return "Produto: " + nome + " - Preço: " + preco;
    }           //retorna a descrição do produto
}