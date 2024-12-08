import java.time.LocalDate;
import java.time.LocalTime;

public class Ocorrencia {
    private String placa;
    private String logradouro;
    private LocalDate data;
    private LocalTime hora;
    private int velocidade;
    private int tipoVeiculo;
    private String tipo; // Velocidade, Rodizio ou CorredorOnibus

    // Ocorrências Relacinadas à velocidade
    public Ocorrencia(String placa, String logradouro, LocalDate data, int velocidade, String tipo, int tipoVeiculo) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.data = data;
        this.velocidade = velocidade;
        this.tipo = tipo;
        this.tipoVeiculo = tipoVeiculo;
    }

    // Ocorrências Relacionadas à horário+
    public Ocorrencia(String placa, String logradouro, LocalDate data, LocalTime hora, String tipo, int tipoVeiculo) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
        this.tipoVeiculo = tipoVeiculo;
    }

    // Ocorrências Relacionadas à velocidade e horário
    public Ocorrencia(String placa, String logradouro, LocalDate data, LocalTime hora, int velocidade, String tipo, int tipoVeiculo) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.data = data;
        this.hora = hora;
        this.velocidade = velocidade;
        this.tipo = tipo;
        this.tipoVeiculo = tipoVeiculo;
    }

    // Ocorrências Gerais
    public Ocorrencia(String placa, String logradouro, LocalDate data, String tipo, int tipoVeiculo) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.data = data;
        this.tipo = tipo;
        this.tipoVeiculo = tipoVeiculo;
    }

    // Getters e Setters
    public String getPlaca() {
        return placa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTipoVeiculo() {
        return tipoVeiculo;
    }
}