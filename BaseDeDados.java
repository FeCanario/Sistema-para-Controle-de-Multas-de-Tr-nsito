import java.time.LocalDate;
import java.util.ArrayList;
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
        
        regras.add(new RegraRodizio(01, "Avenida Bandeirantes", "Avenida 23 de Maio", 01));
        regras.add(new RegraRodizio(01, "Avenida Faria Lima", "Rua da Consolação", 02));
        regras.add(new RegraRodizio(02, "Professor Luis Ignacio de Anhaia Melo", "Avenida Salim Farah Maluf", 03));
        regras.add(new RegraRodizio(02, "Avenida Paulista", "Rua Augusta", 04));
        regras.add(new RegraRodizio(03, "Marginal do Rio Tiete", "Avenida dos Bandeirantes", 05));
        regras.add(new RegraRodizio(03, "Avenida dos Estados", "Rua da Consolacao", 06));
        regras.add(new RegraRodizio(04, "Avenida do Estado", "Rua Tamandare", 01));
        regras.add(new RegraRodizio(05, "Avenida Nacoes Unidas", "Rua Heitor Penteado", 02));

        regras.add(new RegraCorredorOnibus(05, 17, "Avenida Brigadeiro Faria Lima"));
        regras.add(new RegraCorredorOnibus(06, 18, "Avenida Santo Amaro"));
        regras.add(new RegraCorredorOnibus(06, 20, "Avenida Vereador Jose Diniz"));
        regras.add(new RegraCorredorOnibus(07, 19, "Avenida Ibirapuera"));
        regras.add(new RegraCorredorOnibus(07, 19, "Avenida Reboucas"));
        regras.add(new RegraCorredorOnibus(07, 20, "Avenida Jornalista Roberto Marinho"));
        regras.add(new RegraCorredorOnibus(03, 17, "Avenida dos Bandeirantes"));
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
                // Calcula a multa com base na regra e ocorrência
                int nivel = regra.verificarNivelMulta(ocorrencia);
                
                if (nivel > 0) {
                    // Obtem a descrição da multa
                    String descricao = regra.obterDescricaoMulta();
                    
                    // Define o valor da multa com base no nível
                    double valor = 0.0;
                    if (nivel == 3) {
                        valor = 500.0;  // Multa grave
                    } else if (nivel == 2) {
                        valor = 300.0;  // Multa média
                    } else if (nivel == 1) {
                        valor = 150.0;  // Multa leve
                    }
    
                    // Cria uma multa com base na ocorrência e a descrição, incluindo o tipo de veículo
                    Multa multa = new Multa(
                        ocorrencia.getPlaca(), 
                        descricao, 
                        nivel, 
                        valor, 
                        ocorrencia.getData(), 
                        ocorrencia.getTipoVeiculo()
                    );
                    multas.add(multa);  // Adiciona a multa à lista
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
