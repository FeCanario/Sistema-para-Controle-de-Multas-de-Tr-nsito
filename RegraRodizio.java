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

    public int verificarNivelMulta(Ocorrencia ocorrencia) {
        if ((ocorrencia.getlogradouro().equals(logradouro1) || ocorrencia.getlogradouro().equals(logradouro2)) &&
            ocorrencia.gettipo().equalsIgnoreCase("Rodizio")) {
            String data = ocorrencia.getdatahora(); 
            int diaRegistrado = Integer.parseInt(data.split("-")[2]); 
            int placaFinal = Integer.parseInt(ocorrencia.getplaca().substring(ocorrencia.getplaca().length() - 1));
            if (diaRegistrado == diaSemana && placaFinal != placaFinalPermitida) {
                return 2; 
            }
        }
        return 0; 
    }

    public String obterDescricaoMulta() {
        return "Violação de rodízio em " + logradouro1 + " ou " + logradouro2;
    }
}
