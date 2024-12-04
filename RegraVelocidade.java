public class RegraVelocidade extends RegraMulta {
    private int limiteVelocidade;
    private String logradouro;

    public RegraVelocidade(int limiteVelocidade, String logradouro) {
        this.limiteVelocidade = limiteVelocidade;
        this.logradouro = logradouro;
    }

    
    public int verificarNivelMulta(Ocorrencia ocorrencia) {
        if (ocorrencia.getlogradouro().equals(logradouro) && 
            ocorrencia.gettipo().equals("Velocidade")) {
            int velocidadeRegistrada = Integer.parseInt(ocorrencia.getdatahora()); // Exemplo de dado fictício
            if (velocidadeRegistrada > limiteVelocidade + 20) return 3; // Grave
            else if (velocidadeRegistrada > limiteVelocidade + 10) return 2; // Média 
            else if (velocidadeRegistrada > limiteVelocidade) return 1; // Leve
        }
        return 0; 
    }

    
    public String obterDescricaoMulta() {
        return "Excesso de velocidade no logradouro " + logradouro;
    }
}
