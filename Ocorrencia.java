public class Ocorrencia {
    private String placa;
    private String logradouro;
    private String datahora;
    private String tipo; // Velocidade, Rodizio ou CorredorOnibus

    public Ocorrencia(String placa, String logradouro, String datahora, String tipo) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.datahora = datahora;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getplaca() {
        return placa;
    }

    public String getlogradouro() {
        return logradouro;
    }

    public String getdatahora() {
        return datahora;
    }

    public String gettipo() {
        return tipo;
    }
}
