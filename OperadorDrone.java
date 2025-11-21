public class OperadorDrone extends Usuario {
    
    public OperadorDrone(int id, String nome, String email, String senhaPura) {
        super(id, nome, email, senhaPura);
    }

    // Implementação da Abstração: O Operador PODE agendar (retorna true)
    @Override
    public boolean temPermissaoParaAgendar() {
        return true; 
    }
    
    @Override
    public String getTipoAcesso() {
        return "Operador de Drone";
    }
    
    // Método específico do Operador
    public void executarVoo() {
        System.out.println(getNome() + " está executando o voo.");
    }
}
