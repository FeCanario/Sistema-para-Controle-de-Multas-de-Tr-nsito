public class RegraVelocidade extends RegraMulta {
    private int limiteVelocidade;
    private String logradouro;

    public RegraVelocidade(int limiteVelocidade, String logradouro) {
        this.limiteVelocidade = limiteVelocidade;
        this.logradouro = logradouro;
    }

    @Override
    public int verificarNivelMulta(Ocorrencia ocorrencia) {
        if (ocorrencia.getLogradouro().equals(logradouro) && 
            ocorrencia.getTipo().equals("Velocidade")) {
            int velocidadeRegistrada = ocorrencia.getVelocidade();
            if (velocidadeRegistrada > limiteVelocidade + 20) return 3; // Grave
            else if (velocidadeRegistrada > limiteVelocidade + 10) return 2; // Média
            else if (velocidadeRegistrada > limiteVelocidade) return 1; // Leve
        }
        return 0;
    }

    @Override
    public String obterDescricaoMulta() {
        return "Excesso de velocidade no logradouro " + logradouro;
    }
}
