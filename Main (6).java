import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseDeDados baseDeDados = new BaseDeDados();
        baseDeDados.inicializaRegras();  

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao sistema de controle de multas de trânsito!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Registrar ocorrência");
        System.out.println("2. Visualizar multas");
        System.out.println("3. Sair");

        while (true) {
            System.out.print("Escolha uma opção (1/2/3): ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            if (opcao == 1) {
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
                // Visualizar as multas
                System.out.print("Digite a placa para buscar as multas: ");
                String placaConsulta = scanner.nextLine();

                // Buscar as multas associadas à placa
                System.out.println("Multas encontradas para a placa " + placaConsulta + ":");
                for (Multa multa : baseDeDados.getMultas()) {
                    if (multa.getplaca().equalsIgnoreCase(placaConsulta)) {
                        System.out.println(multa);
                    }
                }

            } else if (opcao == 3) {
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
