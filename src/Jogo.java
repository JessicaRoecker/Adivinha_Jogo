public class Jogo {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("servidor")) {
            Servidor.main(args);
        } else {
            Cliente.main(args);
        }
    }
}