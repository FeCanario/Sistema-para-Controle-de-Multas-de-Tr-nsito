public class RegraCorredorOnibus extends RegraMulta {
    private int horaInicio;
    private int horaFim;
    private String logradouro;

    public RegraCorredorOnibus(int horaInicio, int horaFim, String logradouro) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.logradouro = logradouro;
    }

    @Override
    public int verificarNivelMulta(Ocorrencia ocorrencia) {
        if (ocorrencia.getLogradouro().equals(logradouro) &&
            ocorrencia.getTipo().equalsIgnoreCase("CorredorOnibus")) {
            int horaRegistrada = ocorrencia.getHora().getHour();
            if (horaRegistrada >= horaInicio && horaRegistrada <= horaFim) {
                return 3; // Multa grave
            }
        }
        return 0; // Sem multa
    }

    @Override
    public String obterDescricaoMulta() {
        return "Tráfego ilegal no corredor de ônibus na " + logradouro;
    }
}