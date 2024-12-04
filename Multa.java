public class Multa {
    private String placa;
    private String descricao;
    private int nivel; // 0 = Sem Multa, 1 = Leve, 2 = Média, 3 = Grave
    private double valor;

    public Multa(String placa, String descricao, int nivel, double valor) {
        this.placa = placa;
        this.descricao = descricao;
        this.nivel = nivel;
        this.valor = valor;
    }

    // Getters e Setters
    public String getplaca() {
        return placa;
    }

    public String getdescricao() {
        return descricao;
    }

    public int getnivel() {
        return nivel;
    }

    public double getvalor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + ", Descrição: " + descricao + 
               ", Nível: " + nivel + ", Valor: R$" + valor;
    }
}
