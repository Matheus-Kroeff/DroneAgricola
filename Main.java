import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("--- INICIANDO SISTEMA DE AGENDAMENTO DE MISSÕES ---");

        // 1. Setup: Criar as Entidades Base
        Usuario operador = new Usuario(1, "Ana Operadora", "ana@drone.com", Usuario.TIPO_OPERADOR, "senha123");
        AreaAgricola area1 = new AreaAgricola(101, 50.0, "Fazenda Central", "Soja");
        
        // Drone OK e Drone com Problema (para teste de segurança)
        Drone droneOK = new Drone(1, "Sensor Infra, Câmera HD", 85, true);
        Drone droneComProblema = new Drone(2, "Sensor Umidade, GPS", 30, true);

        
        // -----------------------------------------------------
        // TESTE 1: REJEIÇÃO POR FALHA NO CHECKLIST DE SEGURANÇA
        // -----------------------------------------------------
        System.out.println("\n--- TESTE 1: REJEIÇÃO (Bateria Baixa) ---");
        
        MissaoVoo missaoFalhaChecklist = new MissaoVoo(
            2, LocalDate.now().plusDays(3), LocalTime.of(10, 0), LocalTime.of(12, 0), 
            droneComProblema, area1, operador
        );
        
        if (!missaoFalhaChecklist.validarNaoSobreposta()) {
            System.out.println("AGENDAMENTO REJEITADO: Motivo: Falha no Checklist do Drone (Requisito de Segurança).");
        }


        // -----------------------------------------------------
        // TESTE 2: REJEIÇÃO POR SOBREPOSIÇÃO (Regra de Negócio)
        // -----------------------------------------------------
        System.out.println("\n--- TESTE 2: REJEIÇÃO (Missão Sobreposta) ---");
        
        // Data de conflito (A simulação em MissaoVoo tem conflito AMANHÃ)
        LocalDate dataConflito = LocalDate.now().plusDays(1);
        
        // Tenta agendar no horário de conflito (15:00-17:00)
        MissaoVoo missaoConflito = new MissaoVoo(
            3, dataConflito, LocalTime.of(15, 30), LocalTime.of(16, 30), 
            droneOK, area1, operador
        );

        if (!missaoConflito.validarNaoSobreposta()) {
            System.out.println("AGENDAMENTO REJEITADO: Motivo: Conflito de Horário (Regra de Negócio).");
        }
        
        
        // -----------------------------------------------------
        // TESTE 3: AGENDAMENTO BEM-SUCEDIDO E COMPOSIÇÃO
        // -----------------------------------------------------
        System.out.println("\n--- TESTE 3: AGENDAMENTO BEM-SUCEDIDO E GERAÇÃO DE DADOS ---");
        
        // Data fora do conflito
        LocalDate dataVooSucesso = LocalDate.now().plusDays(2);
        
        MissaoVoo missaoSucesso = new MissaoVoo(
            1, dataVooSucesso, LocalTime.of(9, 0), LocalTime.of(11, 0), 
            droneOK, area1, operador
        );
        
        if (missaoSucesso.validarNaoSobreposta()) {
            System.out.println("\nAGENDAMENTO CONCLUÍDO: Missão ID " + missaoSucesso.getId() + " está pronta.");
            
            // Demonstração da Composição: Missão gerando dados
            System.out.println("Demonstração de Composição:");
            DadoColetado dado = new DadoColetado(100, missaoSucesso.getId(), "url/img_01.jpg", 28.5, 65.2, 987.5);
            dado.registrarDado();
        }
    }
}
