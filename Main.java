import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("2. Visualizar multas registradas");
            System.out.println("3. Filtrar multas por data");
            System.out.println("4. Processar ocorrências");
            System.out.println("5. Registrar nova regra");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            if (opcao == 1) {
                // Registrar ocorrência
                String placa = "";
                while (placa.isEmpty() || !placa.matches("[A-Z]{3}[0-9]{4}")) {
                    System.out.print("Digite a placa do veículo (ex: ABC1234): ");
                    placa = scanner.nextLine();
                    if (placa.isEmpty() || !placa.matches("[A-Z]{3}[0-9]{4}")) {
                        System.out.println("⚠️ Placa inválida. Use o formato ABC1234.");
                    }
                }

                System.out.print("Digite o logradouro da ocorrência (sem caracteres especiais como 'ç' e 'ã'): ");
                String logradouro = scanner.nextLine();

                // Validar data da ocorrência
                LocalDate data = null;
                while (data == null) {
                    System.out.print("Digite a data da ocorrência (AAAA-MM-DD): ");
                    String dataStr = scanner.nextLine();
                    try {
                        data = LocalDate.parse(dataStr);
                    } catch (DateTimeParseException e) {
                        System.out.println("⚠️ Data inválida! Por favor, use o formato AAAA-MM-DD.");
                    }
                }

                // Validar a hora da ocorrência
                LocalTime hora = null;
                while (hora == null) {
                    System.out.print("Digite a hora da ocorrência (HH:MM): ");
                    String horaStr = scanner.nextLine();
                    try {
                        hora = LocalTime.parse(horaStr);
                    } catch (DateTimeParseException e) {
                        System.out.println("⚠️ Hora inválida! Por favor, use o formato HH:MM.");
                    }
                }

                System.out.print("Digite o tipo de multa (ex: Velocidade, Rodizio, CorredorOnibus): ");
                String tipo = scanner.nextLine();

                // Validar tipo de veículo
                int tipoVeiculo = 0;
                while (tipoVeiculo < 1 || tipoVeiculo > 3) {
                    System.out.print("Digite o tipo de veículo (1 - Veículo leve; 2 - Veículo Pesado; 3 - Moto): ");
                    tipoVeiculo = scanner.nextInt();
                    if (tipoVeiculo < 1 || tipoVeiculo > 3) {
                        System.out.println("⚠️ Tipo de veículo inválido! Escolha entre 1, 2 ou 3.");
                    }
                }

                // Criar a ocorrência e adicionar à base de dados
                Ocorrencia ocorrencia;
                if (tipo.equalsIgnoreCase("Velocidade")) {
                    ocorrencia = new Ocorrencia(placa, logradouro, data, hora, tipo, tipoVeiculo);
                } else {
                    ocorrencia = new Ocorrencia(placa, logradouro, data, hora, tipo, tipoVeiculo);
                }

                baseDeDados.adicionarOcorrencia(ocorrencia);
                System.out.println("Ocorrência registrada com sucesso!");

            } else if (opcao == 2) {
                // Visualizar multas registradas
                System.out.println("=== Multas Registradas ===");
                if (baseDeDados.getMultas().isEmpty()) {
                    System.out.println("⚠️ Nenhuma multa registrada.");
                } else {
                    for (Multa multa : baseDeDados.getMultas()) {
                        System.out.println(multa);  // Exibe cada multa registrada
                    }
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
                // Registrar nova regra
                System.out.println("=== Registrar Nova Regra ===");
                System.out.println("1. Regra de Velocidade");
                System.out.println("2. Regra de Rodízio");
                System.out.println("3. Regra de Corredor de Ônibus");
                System.out.print("Escolha o tipo de regra (1, 2 ou 3): ");
                int tipoRegra = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                if (tipoRegra == 1) {
                    // Registrar nova regra de velocidade
                    System.out.print("Digite o limite de velocidade (em km/h): ");
                    int limiteVelocidade = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    System.out.print("Digite o logradouro para a regra de velocidade: ");
                    String logradouro = scanner.nextLine();

                    baseDeDados.regras.add(new RegraVelocidade(limiteVelocidade, logradouro));
                    System.out.println("Regra de velocidade registrada com sucesso!");
                } else if (tipoRegra == 2) {
                    // Registrar nova regra de rodízio
                    System.out.print("Digite o dia do mês para a regra de rodízio (1 a 31): ");
                    int diaDoMes = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    System.out.print("Digite o logradouro inicial para a regra de rodízio: ");
                    String logradouroInicial = scanner.nextLine();

                    System.out.print("Digite o logradouro final para a regra de rodízio: ");
                    String logradouroFinal = scanner.nextLine();

                    System.out.print("Digite os dígitos finais das placas permitidas (ex: 1, 2, 3): ");
                    String digitosStr = scanner.nextLine();
                    String[] digitosArray = digitosStr.split(",");
                    List<Integer> digitosPermitidos = new ArrayList<>();
                    for (String digito : digitosArray) {
                        digitosPermitidos.add(Integer.parseInt(digito.trim()));
                    }

                    baseDeDados.regras.add(new RegraRodizio(diaDoMes, logradouroInicial, logradouroFinal, digitosPermitidos));
                    System.out.println("Regra de rodízio registrada com sucesso!");
                } else if (tipoRegra == 3) {
                    // Registrar nova regra de corredor de ônibus
                    System.out.print("Digite o número do horário de início (ex: 5): ");
                    int horarioInicio = scanner.nextInt();
                    System.out.print("Digite o número do horário de fim (ex: 9): ");
                    int horarioFim = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    System.out.print("Digite o logradouro para a regra de corredor de ônibus: ");
                    String logradouro = scanner.nextLine();

                    baseDeDados.regras.add(new RegraCorredorOnibus(horarioInicio, horarioFim, logradouro));
                    System.out.println("Regra de corredor de ônibus registrada com sucesso!");
                } else {
                    System.out.println("Opção inválida para registrar a regra.");
                }

            } else if (opcao == 6) {
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
