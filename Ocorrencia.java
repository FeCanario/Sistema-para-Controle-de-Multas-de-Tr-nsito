import java.time.LocalDate;
import java.time.LocalTime;

public class Ocorrencia {
    private String placa;
    private String logradouro;
    private LocalDate data;
    private LocalTime hora;
    private int velocidade;
    private String tipo; // Velocidade, Rodizio ou CorredorOnibus

    // Ocorrências Relacinadas à velocidade
    public Ocorrencia(String placa, String logradouro, LocalDate data, int velocidade, String tipo) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.data = data;
        this.velocidade = velocidade;
        this.tipo = tipo;
    }

    // Ocorrências Relacionadas à horário+
    public Ocorrencia(String placa, String logradouro, LocalDate data, LocalTime hora, String tipo) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
    }

    // Ocorrências Relacionadas à velocidade e horário
    public Ocorrencia(String placa, String logradouro, LocalDate data, LocalTime hora, int velocidade, String tipo) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.data = data;
        this.hora = hora;
        this.velocidade = velocidade;
        this.tipo = tipo;
    }

    // Ocorrências Gerais
    public Ocorrencia(String placa, String logradouro, LocalDate data, String tipo) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.data = data;
        this.tipo = tipo;
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
}
