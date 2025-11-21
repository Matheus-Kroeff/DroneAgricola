public abstract class Usuario { 
   // Constantes e métodos isX() removidos pois as subclasses definem o tipo
   // A responsabilidade de login é mantida aqui.

   private int id;
   private String nome;
   private String email;
   private String senhaHash;

   // Construtor: REMOVIDO o argumento String tipoAcesso
    public Usuario(int id, String nome, String email, String senhaPura) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = gerarHashSeguro(senhaPura);
    }

   // Método que simula a lógica de autenticação
    public boolean login(String email, String senhaPura) {
        if (!this.email.equals(email)) {
            return false;
        }
        String hashTentativa = gerarHashSeguro(senhaPura);
        return this.senhaHash.equals(hashTentativa);
    }

   // MÉTODO ABSTRATO DE PERMISSÃO (Polimorfismo)
   public abstract boolean temPermissaoParaAgendar();
   // MÉTODO ABSTRATO DE TIPO DE ACESSO (Polimorfismo)
   public abstract String getTipoAcesso(); 


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
}
