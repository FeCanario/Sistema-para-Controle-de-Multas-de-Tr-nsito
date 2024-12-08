import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class BaseDeDados {
    private List<Ocorrencia> ocorrenciasNaoProcessadas;
    private List<Ocorrencia> ocorrenciasProcessadas;
    private List<Multa> multas;
    List<RegraMulta> regras;

    public BaseDeDados() {
        ocorrenciasNaoProcessadas = new ArrayList<>();
        ocorrenciasProcessadas = new ArrayList<>();
        multas = new ArrayList<>();
        regras = new ArrayList<>();
    }

    // Inicializa as regras de multas
    public void inicializaRegras() {

        regras.add(new RegraVelocidade(30, "Rua Augusta"));
        regras.add(new RegraVelocidade(40, "Rua da Consolacao"));
        regras.add(new RegraVelocidade(50, "Avenida Morumbi"));
        regras.add(new RegraVelocidade(50, "Avenida Joao Dias"));
        regras.add(new RegraVelocidade(50, "Avenida Paulista"));
        regras.add(new RegraVelocidade(60, "Avenida Washington Luiz"));
        regras.add(new RegraVelocidade(60, "Avenida Faria Lima"));
        regras.add(new RegraVelocidade(60, "Avenida Brasil"));
        regras.add(new RegraVelocidade(80, "Rodovia Castelo Branco"));
        regras.add(new RegraVelocidade(90, "Avenida Nacoes Unidas"));  
        regras.add(new RegraVelocidade(110, "Rodovia dos Bandeirantes"));
        
        regras.add(new RegraRodizio(1, "Avenida Bandeirantes", "Avenida 23 de Maio", 1));
        regras.add(new RegraRodizio(1, "Avenida Faria Lima", "Rua da Consolação", 2));
        regras.add(new RegraRodizio(2, "Professor Luis Ignacio de Anhaia Melo", "Avenida Salim Farah Maluf", 3));
        regras.add(new RegraRodizio(2, "Avenida Paulista", "Rua Augusta", 4));
        regras.add(new RegraRodizio(3, "Marginal do Rio Tiete", "Avenida dos Bandeirantes", 5));
        regras.add(new RegraRodizio(3, "Avenida dos Estados", "Rua da Consolacao", 6));
        regras.add(new RegraRodizio(4, "Avenida do Estado", "Rua Tamandare", 1));
        regras.add(new RegraRodizio(5, "Avenida Nacoes Unidas", "Rua Heitor Penteado", 2));

        regras.add(new RegraCorredorOnibus(5, 17, "Avenida Brigadeiro Faria Lima"));
        regras.add(new RegraCorredorOnibus(6, 18, "Avenida Santo Amaro"));
        regras.add(new RegraCorredorOnibus(6, 20, "Avenida Vereador Jose Diniz"));
        regras.add(new RegraCorredorOnibus(7, 19, "Avenida Ibirapuera"));
        regras.add(new RegraCorredorOnibus(8, 16, "Avenida Reboucas"));
        regras.add(new RegraCorredorOnibus(8, 20, "Avenida Jornalista Roberto Marinho"));
        regras.add(new RegraCorredorOnibus(9, 17, "Avenida dos Bandeirantes"));
        regras.add(new RegraCorredorOnibus(10, 18, "Avenida Washington Luis"));
        regras.add(new RegraCorredorOnibus(11, 20, "Avenida Salim Farah Maluf"));
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
                    // Adiciona a data da multa ao registrá-la E passa o tipo de veículo
                    multa = new Multa(
                        multa.getPlaca(), 
                        multa.getDescricao(), 
                        multa.getNivel(), 
                        multa.getValor(), 
                        multa.getDataMulta(), 
                        ocorrencia.getTipoVeiculo()  // Passa o tipo de veículo da ocorrência
                    );
                    multas.add(multa);
                }
            }
            ocorrenciasProcessadas.add(ocorrencia);  // Marca a ocorrência como processada
        }
        ocorrenciasNaoProcessadas.clear();  // Limpa as ocorrências não processadas
    }

    // Retorna as multas pendentes (não processadas)
    public List<Multa> getMultasPendentes() {
        return multas.stream()
                     .filter(multa -> !multa.isProcessada())  // Filtra as multas não processadas
                     .collect(Collectors.toList());
    }

    // Retorna as multas filtradas por data
    public List<Multa> buscarMultasPorData(LocalDate data) {
        return multas.stream()
                     .filter(multa -> multa.getDataMulta().equals(data))  // Filtra as multas pela data
                     .collect(Collectors.toList());
    }

    // Marcar a multa como processada
    public void processarMulta(Multa multa) {
        multa.setProcessada(true);  // Marca a multa como processada
    }

    // Retorna todas as multas registradas
    public List<Multa> getMultas() {
        return multas;  // Retorna a lista completa de multas
    }
}

