package br.ufjf.dcc.dcc025;

public class Alimento extends ProdutoBase {   //subcçasse de ProdutoBase
    private String dataValidade;                //atributo particular de alimento

    public Alimento(int id, String nome, double preco, String dataValidade) {
        super(id, nome, preco);                     //construtor de alimento
        this.dataValidade = dataValidade;           //atribui o valor de validade
    }

    public String getDataValidade() {
        return dataValidade;
    }       //retorna a data de validade

    @Override
    public String descricaoProduto() {              //retorna a descricao do produto alimento
        return "Alimento: " + getNome() + " - Preço: " + getPreco() + " - Validade: " + dataValidade;
    }

    //verifica a data de validade, se contem valores validos
    public void verificarValidade() {
        // Verificar se a data está no formato correto
        if (dataValidade == null || !dataValidade.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Erro: Formato de data inválido. Use o formato YYYY-MM-DD.");
            return;
        }

        // Separar a data
        String[] partesData = dataValidade.split("-");

        try {
            //separa cada data para tratar os valores
            int ano = Integer.parseInt(partesData[0]);
            int mes = Integer.parseInt(partesData[1]);
            int dia = Integer.parseInt(partesData[2]);

            // Verificar mês (deve ser entre 1 e 12)
            if (mes < 1 || mes > 12) {
                System.out.println("Erro: Mês inválido. Deve estar entre 1 e 12.");
                return;
            }

            // Verificar dia (deve ser entre 1 e 30)
            if (dia < 1 || dia > 30) {
                System.out.println("Erro: Dia inválido. Deve estar entre 1 e 30.");
                return;
            }

            // Se todas as verificações passarem, a data é válida
            System.out.println("Data de validade válida: " + dataValidade);
        //faz o tratamento de excecoes e caso ocorra alguma excessao ou erro, imprime a mensagem de erro
        } catch (NumberFormatException e) {
            System.out.println("Erro: Formato de data inválido. Use o formato YYYY-MM-DD.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro: Formato de data inválido. Use o formato YYYY-MM-DD.");
        }
    }
}
