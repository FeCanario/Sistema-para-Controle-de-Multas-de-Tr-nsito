import java.time.LocalDate;

public class Multa {
    private String placa;
    private String descricao;
    private int nivel; // 0 = Sem Multa, 1 = Leve, 2 = Média, 3 = Grave
    private double valor;
    private int tipoVeiculo;
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
        this.tipoVeiculo = 1; // Valor padrão se não especificado
    }

    // Novo construtor que também recebe o tipo de veículo
    public Multa(String placa, String descricao, int nivel, double valor, LocalDate dataMulta, int tipoVeiculo) {
        this(placa, descricao, nivel, valor, dataMulta);
        this.tipoVeiculo = tipoVeiculo;
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

    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    // Método estático para converter tipo de veículo
    public static String converterTipoVeiculo(int tipoVeiculo) {
        switch (tipoVeiculo) {
            case 1:
                return "Veículo Leve";
            case 2:
                return "Veículo Pesado";
            case 3:
                return "Moto";
            default:
                return "Tipo de Veículo Desconhecido";
        }
    }

    public static String converterNivelMulta(int nivel) {
        switch (nivel) {
            case 1:
                return "multa leve";
            case 2:
                return "multa média";
            case 3:
                return "multa grave";
            default:
                return "sem multa";
        }
    }

    // Método de instância para obter a descrição do tipo de veículo
    public String getDescricaoTipoVeiculo() {
        return converterTipoVeiculo(this.tipoVeiculo);
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
        return "Placa: " + placa + ", Tipo do Veículo: " + getDescricaoTipoVeiculo() + ", Descrição: " + descricao + 
               ", Nível: " + converterNivelMulta(nivel) + ", Valor: R$" + valor + 
               ", Data: " + dataMulta + ", Processada: " + processada;
    }
}