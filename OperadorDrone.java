public class OperadorDrone extends Usuario {

    public OperadorDrone(int id, String nome, String email) {
        super(id, nome, email);
    }

    @Override
    public boolean podeAgendarMissao() {
        return true; 
    }
}
