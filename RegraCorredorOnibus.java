public class RegraCorredorOnibus extends RegraMulta {
    private int horaInicio;
    private int horaFim;
    private String logradouro;

    public RegraCorredorOnibus(int horaInicio, int horaFim, String logradouro) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.logradouro = logradouro;
    }

    public int verificarNivelMulta(Ocorrencia ocorrencia) {
        if (ocorrencia.getlogradouro().equals(logradouro) &&
            ocorrencia.gettipo().equalsIgnoreCase("CorredorOnibus")) {
            int horaRegistrada = Integer.parseInt(ocorrencia.getdatahora().split(":")[0]); // Exemplo fictício
            if (horaRegistrada >= horaInicio && horaRegistrada <= horaFim) {
                return 3; // Multa grave
            }
        }
        return 0; // Sem multa
    }

    public String obterDescricaoMulta() {
        return "Tráfego ilegal no corredor de ônibus na " + logradouro;
    }
}
