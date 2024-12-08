import java.time.LocalDate;
import java.time.LocalTime;
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
        
        // Loop de interação com o usuário
        while (true) {
            System.out.println("");
            System.out.println("1. Registrar ocorrência");
            System.out.println("2. Visualizar multas pendentes");
            System.out.println("3. Filtrar multas por data");
            System.out.println("4. Processar ocorrências");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            if (opcao == 1) {
                // Registrar ocorrência
                System.out.print("Digite a placa do veículo: ");
                String placa = scanner.nextLine();

                System.out.print("Digite o logradouro da ocorrência(sem caracteres especiais como 'ç' e 'ã'): ");
                String logradouro = scanner.nextLine();

                System.out.print("Digite a data da ocorrência (AAAA-MM-DD): ");
                String dataStr = scanner.nextLine();
                LocalDate data = LocalDate.parse(dataStr);

                System.out.print("Digite a hora da ocorrência (HH:MM): ");
                String horaStr = scanner.nextLine();
                LocalTime hora = LocalTime.parse(horaStr);

                System.out.print("Digite o tipo de multa (ex: Velocidade, Rodizio, CorredorOnibus): ");
                String tipo = scanner.nextLine();

                System.out.print("Digite o tipo de veículo (1 - Veículo leve; 2 - Caminhão; 3 - Moto): ");
                int tipoVeiculo = scanner.nextInt();

                int velocidade = 0;
                if (tipo.equalsIgnoreCase("Velocidade")) {
                    System.out.print("Digite a velocidade registrada (km/h): ");
                    velocidade = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                }

                // Criar a ocorrência e adicionar à base de dados
                Ocorrencia ocorrencia;
                if (tipo.equalsIgnoreCase("Velocidade")) {
                    ocorrencia = new Ocorrencia(placa, logradouro, data, hora, velocidade, tipo, tipoVeiculo);
                } else {
                    ocorrencia = new Ocorrencia(placa, logradouro, data, hora, tipo, tipoVeiculo);
                }

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
                // Processar ocorrências
                baseDeDados.processarOcorrencias();
                System.out.println("Ocorrências processadas e multas geradas!");

            } else if (opcao == 5) {
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
