public abstract class RegraMulta {
    protected static final double VALOR_MULTA_LEVE = 50.0;
    protected static final double VALOR_MULTA_MEDIA = 100.0;
    protected static final double VALOR_MULTA_GRAVE = 200.0;

    public abstract int verificarNivelMulta(Ocorrencia ocorrencia);

    public Multa calcularmulta(Ocorrencia ocorrencia) {
        int nivel = verificarNivelMulta(ocorrencia); // Verifica o nível da multa
        if (nivel > 0) { // Se houver multa
            double valor = 0.0; // Initialize the value
            switch (nivel) {
                case 1:
                    valor = VALOR_MULTA_LEVE;
                    break;
                case 2:
                    valor = VALOR_MULTA_MEDIA;
                    break;
                case 3:
                    valor = VALOR_MULTA_GRAVE;
                    break;
                default:
                    valor = 0.0;
            }
            return new Multa(
                ocorrencia.getPlaca(), // Placa do veículo
                obterDescricaoMulta(), // Descrição da multa
                nivel, // Nível da multa
                valor, // Valor da multa
                ocorrencia.getData() // Data da ocorrência
            );
        }
        return null; // Nenhuma multa aplicada
    }

    public abstract String obterDescricaoMulta();
}
