public class RegraRodizio extends RegraMulta {
    private int diaSemana;
    private String logradouro1;
    private String logradouro2;
    private int placaFinalPermitida;

    public RegraRodizio(int diaSemana, String logradouro1, String logradouro2, int placaFinalPermitida) {
        this.diaSemana = diaSemana;
        this.logradouro1 = logradouro1;
        this.logradouro2 = logradouro2;
        this.placaFinalPermitida = placaFinalPermitida;
    }

    @Override
    public int verificarNivelMulta(Ocorrencia ocorrencia) {
        if ((ocorrencia.getLogradouro().equals(logradouro1) || ocorrencia.getLogradouro().equals(logradouro2)) &&
            ocorrencia.getTipo().equalsIgnoreCase("Rodizio")) {
            int diaRegistrado = ocorrencia.getData().getDayOfWeek().getValue(); //converter número do dia para data (1=Segunda-Feira)
            int placaFinal = Integer.parseInt(ocorrencia.getPlaca().substring(ocorrencia.getPlaca().length() - 1));
            if (diaRegistrado == diaSemana && placaFinal != placaFinalPermitida) {
                return 2;
            }
        }
        return 0;
    }

    @Override
    public String obterDescricaoMulta() {
        return "Violação de rodízio em " + logradouro1 + " ou " + logradouro2;
    }
}
