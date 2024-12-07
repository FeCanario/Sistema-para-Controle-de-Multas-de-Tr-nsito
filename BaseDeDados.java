import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

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

    // Inicializa as regras de multas
    public void inicializaRegras() {
        regras.add(new RegraVelocidade(60, "Avenida Washington Luiz"));
        regras.add(new RegraVelocidade(90, "Avenida Nações Unidas"));
        regras.add(new RegraVelocidade(50, "Avenida Morumbi"));
        regras.add(new RegraVelocidade(50, "Avenida João Dias"));
        regras.add(new RegraVelocidade(60, "Avenida Brasil"));
        regras.add(new RegraVelocidade(110, "Rodovia dos Bandeirantes"));

        regras.add(new RegraRodizio(3, "Marginal do Rio Tietê", "Avenida dos Bandeirantes", 5));
        regras.add(new RegraRodizio(2, "Professor Luís Ignácio de Anhaia Melo", "Avenida Salim Farah Maluf", 3));
        regras.add(new RegraRodizio(1, "Avenida Bandeirantes", "Avenida 23 de Maio", 1));

        regras.add(new RegraCorredorOnibus(6, 18, "Avenida Santo Amaro"));
        regras.add(new RegraCorredorOnibus(6, 20, "Avenida Vereador José Diniz"));
        regras.add(new RegraCorredorOnibus(8, 16, "Avenida Rebouças"));
        regras.add(new RegraCorredorOnibus(8, 20, "Avenida Jornalista Roberto Marinho"));
    }

    // Adiciona uma ocorrência na lista de não processadas
    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        ocorrenciasNaoProcessadas.add(ocorrencia);
    }

    // Processa as ocorrências e gera as multas
    public void processarOcorrencias() {
        for (Ocorrencia ocorrencia : ocorrenciasNaoProcessadas) {
            for (RegraMulta regra : regras) {
                Multa multa = regra.calcularmulta(ocorrencia);
                if (multa != null) {
                    // Adiciona a data da multa ao registrá-la
                    multa = new Multa(multa.getPlaca(), multa.getDescricao(), multa.getNivel(), multa.getValor(), LocalDate.now());
                    multas.add(multa);
                }
            }
            ocorrenciasProcessadas.add(ocorrencia);
        }
        ocorrenciasNaoProcessadas.clear(); // Limpa as ocorrências não processadas
    }

    // Retorna as multas pendentes (não processadas)
    public List<Multa> getMultasPendentes() {
        return multas.stream()
                     .filter(multa -> !multa.isProcessada())
                     .collect(Collectors.toList());
    }

    // Retorna as multas filtradas por data
    public List<Multa> buscarMultasPorData(LocalDate data) {
        return multas.stream()
                     .filter(multa -> multa.getDataMulta().equals(data))
                     .collect(Collectors.toList());
    }

    // Marcar a multa como processada
    public void processarMulta(Multa multa) {
        multa.setProcessada(true);
    }

    public List<Multa> getMultas() {
        return multas;
    }
}
