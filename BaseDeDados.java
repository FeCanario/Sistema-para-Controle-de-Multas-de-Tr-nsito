import java.util.ArrayList;
import java.util.List;

public class BaseDeDados {
    private List<Ocorrencia> ocorrenciasNaoProcessadas;
    private List<Ocorrencia> ocorrenciasProcessadas;
    private List<Multa> multas;
    private List<RegraMulta> regras;

    public BaseDeDados() {
        ocorrenciasNaoProcessadas = new ArrayList<>();
        ocorrenciasProcessadas = new ArrayList<>();
        multas = new ArrayList<>();
        regras = new ArrayList<>();
    }

    public void inicializaRegras() {
        regras.add(new RegraVelocidade(60, "Avenida Washington Luiz"));
        regras.add(new RegraVelocidade(70, "Avenida Nações Unidas"));
        regras.add(new RegraRodizio(1, "Avenida Bandeirantes", "Avenida 23 de Maio", 1));
        regras.add(new RegraCorredorOnibus(6, 23, "Avenida Santo Amaro"));
        regras.add(new RegraCorredorOnibus(0, 24, "Avenida Vereador José Diniz"));
    }

    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        ocorrenciasNaoProcessadas.add(ocorrencia);
    }

    public void processarOcorrencias() {
        for (Ocorrencia ocorrencia : ocorrenciasNaoProcessadas) {
            for (RegraMulta regra : regras) {
                Multa multa = regra.calcularmulta(ocorrencia); 
                if (multa != null) { // Se não for nulo, adiciona a multa
                    multas.add(multa);
                    break; // Uma ocorrência só pode gerar uma multa
                }
            }
            ocorrenciasProcessadas.add(ocorrencia);  // Marca a ocorrência como processada
        }
        ocorrenciasNaoProcessadas.clear();  // Limpa a lista de ocorrências não processadas
    }

    public List<Multa> getMultas() {
        return multas;
    }
}
