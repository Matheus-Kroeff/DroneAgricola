public class Administrador extends Usuario {
    
    public Administrador(int id, String nome, String email, String senhaPura) {
        super(id, nome, email, senhaPura);
    }
    
    // Implementação da Abstração: O Administrador PODE agendar
    @Override
    public boolean temPermissaoParaAgendar() {
        return true; 
    }
    
    @Override
    public String getTipoAcesso() {
        return "Administrador do Sistema";
    }
    
    // Método específico do Admin (Polimorfismo de Inclusão)
    public void gerenciarPermissoes() {
        System.out.println("Administrador " + getNome() + " está revisando permissões.");
    }
}
