import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MissaoVoo {

    private int id;
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    private Drone droneUtilizado;
    private AreaAgricola areaVoo;
    private Usuario operador;

    public MissaoVoo(int id, LocalDate data, LocalTime inicio, LocalTime fim, Drone drone, AreaAgricola area, Usuario operador) {
        this.id = id;
        this.data = data;
        this.horarioInicio = inicio;
        this.horarioFim = fim;
        this.droneUtilizado = drone;
        this.areaVoo = area;
        this.operador = operador;
    }
  
    public boolean validarNaoSobreposta() {
        
        // Check de Segurança Pré-Voo
        if (!this.droneUtilizado.checarPreVoo()) {
            return false;
        }

        // Simulação da Consulta ao Banco (MissaoDAO faria a busca)
        List<MissaoVoo> missoesExistentes = simularBuscaMissoesExistentes(this.droneUtilizado.getId(), this.data);
        
        // Lógica de Validação de Sobreposição
        for (MissaoVoo missao : missoesExistentes) {
            
            // Verifica se há qualquer interseção de horários
            boolean conflito = 
                (this.horarioInicio.isBefore(missao.horarioFim) && this.horarioFim.isAfter(missao.horarioInicio));
            
            if (conflito) {
                System.out.println("Validação Falhou: Conflito de horário com a Missão ID " + missao.id);
                return false;
            }
        }
        
        return true; // Validação OK!
    }
    
    // Método simulado para imitar o MissaoDAO
    private List<MissaoVoo> simularBuscaMissoesExistentes(int droneId, LocalDate data) {
        List<MissaoVoo> missoes = new ArrayList<>();
        
        // SIMULAÇÃO: Cria um conflito para a data de amanhã (plusDays(1)) entre 15:00 e 17:00
        if (data.isEqual(LocalDate.now().plusDays(1))) {
            missoes.add(new MissaoVoo(10, data, LocalTime.of(15, 0), LocalTime.of(17, 0), this.droneUtilizado, this.areaVoo, this.operador));
        }
        
        return missoes;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public Drone getDroneUtilizado() {
        return droneUtilizado;
    }
}
