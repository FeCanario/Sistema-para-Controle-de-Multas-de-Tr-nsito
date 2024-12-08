import java.time.LocalDate;

public class RegraRodizio extends RegraMulta {
    private int diaSemana;
    private String logradouro1;
    private String logradouro2;
    private int[] placasFinaisPermitidas;

    public RegraRodizio(int diaSemana, String logradouro1, String logradouro2, int placaFinalPermitida) {
        this.diaSemana = diaSemana;
        this.logradouro1 = logradouro1;
        this.logradouro2 = logradouro2;
        
        // Define os dois dígitos finais permitidos para cada dia
        switch(diaSemana) {
            case 1: // Segunda-feira
                this.placasFinaisPermitidas = new int[]{3, 4, 5, 6, 7, 8, 9, 0};
                break;
            case 2: // Terça-feira
                this.placasFinaisPermitidas = new int[]{1, 2, 5, 6, 7, 8, 9, 0};
                break;
            case 3: // Quarta-feira
                this.placasFinaisPermitidas = new int[]{1, 2, 3, 4, 7, 8, 9, 0};
                break;
            case 4: // Quinta-feira
                this.placasFinaisPermitidas = new int[]{1, 2, 3, 4, 5, 6, 9, 0};
                break;
            case 5: // Sexta-feira
                this.placasFinaisPermitidas = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
                break;
            default:
                this.placasFinaisPermitidas = new int[]{};
        }
    }

    @Override
    public int verificarNivelMulta(Ocorrencia ocorrencia) {
        // Obtém o último dígito da placa
        String placa = ocorrencia.getPlaca();
        int placaFinal = Integer.parseInt(placa.substring(placa.length() - 1));

        // Verifica o dia da semana
        LocalDate dataOcorrencia = ocorrencia.getData();
        int diaDaSemana = dataOcorrencia.getDayOfWeek().getValue();
        
        // Ajusta o dia da semana para corresponder à lógica do construtor (1-5)
        diaDaSemana = (diaDaSemana == 7) ? 5 : (diaDaSemana == 6) ? 5 : diaDaSemana;

        // Verifica se o dia da semana corresponde
        if (diaDaSemana != this.diaSemana) {
            return 0;
        }

        // Verifica se o logradouro corresponde
        String logradouro = ocorrencia.getLogradouro();
        if (!logradouro.equals(this.logradouro1) && !logradouro.equals(this.logradouro2)) {
            return 0;
        }

        // Verifica se o último dígito não está nos permitidos
        boolean placaPermitida = false;
        for (int placaPermitidaDigit : placasFinaisPermitidas) {
            if (placaFinal == placaPermitidaDigit) {
                placaPermitida = true;
                break;
            }
        }

        // Se a placa não for permitida, gera multa
        if (!placaPermitida) {
            return 2;  // Nível de multa média
        }

        return 0;
    }

    @Override
    public String obterDescricaoMulta() {
        return "Violação de rodízio em " + logradouro1 + " ou " + logradouro2;
    }
}