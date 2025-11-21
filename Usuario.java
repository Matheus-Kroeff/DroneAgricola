public abstract class Usuario { 

   public static final String TIPO_ADMIN = "Administrador";
   public static final String TIPO_OPERADOR = "Operador";

   private int id;
   private String nome;
   private String email;
   private String senhaHash;

   // Construtor
    public Usuario(int id, String nome, String email,  String senhaPura) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        // Simulação de segurança: Hash da senha deve ser feito aqui
        this.senhaHash = gerarHashSeguro(senhaPura);
    }

   // Método que simula a lógica de autenticação
    public boolean login(String email, String senhaPura) {
        // Verificar se o e-mail corresponde ao usuário
        if (!this.email.equals(email)) {
            return false;
        }
        
        // Simular a verificação do hash (Comparar a senha pura com o hash armazenado)
        String hashTentativa = gerarHashSeguro(senhaPura);
        return this.senhaHash.equals(hashTentativa);
    }


   public abstract boolean podeAgendarMissao();


    // Método simulado para hash seguro (demonstração)
    private String gerarHashSeguro(String senha) {
        return "HASH_" + senha.hashCode();
    }
    
    // Getters 
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoAcesso() {
        return tipoAcesso;
    }
}
