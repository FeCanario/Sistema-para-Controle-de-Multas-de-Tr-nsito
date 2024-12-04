import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criação da base de dados
        BaseDeDados baseDeDados = new BaseDeDados();
        baseDeDados.inicializaRegras();  // Inicializa as regras de multas

        // Criar o scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Mostrar um menu inicial
        System.out.println("Bem-vindo ao sistema de controle de multas de trânsito!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Registrar ocorrência");
        System.out.println("2. Visualizar multas pendentes");
        System.out.println("3. Filtrar multas por data");
        System.out.println("4. Sair");
        
        // Loop de interação com o usuário
        while (true) {
            System.out.print("Escolha uma opção (1/2/3/4): ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            if (opcao == 1) {
                // Registrar ocorrência
                System.out.print("Digite a placa do veículo: ");
                String placa = scanner.nextLine();

                System.out.print("Digite o logradouro da ocorrência: ");
                String logradouro = scanner.nextLine();

                System.out.print("Digite o tipo de multa (ex: Velocidade, Rodizio, CorredorOnibus): ");
                String tipo = scanner.nextLine();

                System.out.print("Digite o valor relacionado à ocorrência (ex: velocidade, dia da semana, etc.): ");
                String dados = scanner.nextLine();

                // Criar a ocorrência e adicionar à base de dados
                Ocorrencia ocorrencia = new Ocorrencia(placa, logradouro, dados, tipo);
                baseDeDados.adicionarOcorrencia(ocorrencia);

                System.out.println("Ocorrência registrada com sucesso!");

            } else if (opcao == 2) {
                // Visualizar multas pendentes
                System.out.println("Multas pendentes:");
                for (Multa multa : baseDeDados.getMultasPendentes()) {
                    System.out.println(multa);
                }

            } else if (opcao == 3) {
                // Filtrar multas por data
                System.out.print("Digite a data (AAAA-MM-DD) para filtrar as multas: ");
                String dataStr = scanner.nextLine();
                LocalDate data = LocalDate.parse(dataStr);

                System.out.println("Multas em " + data + ":");
                for (Multa multa : baseDeDados.buscarMultasPorData(data)) {
                    System.out.println(multa);
                }

            } else if (opcao == 4) {
                // Sair
                System.out.println("Saindo do sistema...");
                break;  // Encerra o loop
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        // Fechar o scanner
        scanner.close();
    }
}
