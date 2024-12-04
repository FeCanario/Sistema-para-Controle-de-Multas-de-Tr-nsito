import java.time.LocalDate;

public class Multa {
    private String placa;
    private String descricao;
    private int nivel; // 0 = Sem Multa, 1 = Leve, 2 = Média, 3 = Grave
    private double valor;
    private LocalDate dataMulta; // Data da multa
    private boolean processada;  // Status de pendente (false = pendente, true = processada)

    // Construtor com data da multa
    public Multa(String placa, String descricao, int nivel, double valor, LocalDate dataMulta) {
        this.placa = placa;
        this.descricao = descricao;
        this.nivel = nivel;
        this.valor = valor;
        this.dataMulta = dataMulta;  // Define a data da multa
        this.processada = false;  // Inicialmente, a multa é pendente
    }

    // Getters
    public String getPlaca() {
        return placa;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNivel() {
        return nivel;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getDataMulta() {
        return dataMulta;  // Retorna a data da multa
    }

    public boolean isProcessada() {
        return processada;
    }

    public void setProcessada(boolean processada) {
        this.processada = processada;
    }

    // Método toString para exibir as multas
    @Override
    public String toString() {
        return "Placa: " + placa + ", Descrição: " + descricao + 
               ", Nível: " + nivel + ", Valor: R$" + valor + 
               ", Data: " + dataMulta + ", Processada: " + processada;
    }
}
