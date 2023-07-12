import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {

    private static final int PORT = 12345;

    public static void main(String[] args) {
        runServidor();
    }

    private static void runServidor() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT, 0, InetAddress.getByName("0.0.0.0"));
            System.out.println("Aguardando conexões de jogadores...");

            // Aceita a conexão do primeiro jogador
            Socket jogador1 = serverSocket.accept();
            System.out.println("Jogador 1 conectado.");

            // Gera um número aleatório para o jogador 1 adivinhar
            Random random = new Random();
            int numeroParaAdivinhar = random.nextInt(10) + 1;

            // Inicia o jogo com o jogador 1
            PrintWriter out1 = new PrintWriter(jogador1.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(jogador1.getInputStream()));
            out1.println("Bem-vindo ao jogo Adivinhe o Número!");

            // Aguarda o primeiro jogador jogar
            out1.println("Aguardando jogada do jogador 2...");

            // Aceita a conexão do segundo jogador
            Socket jogador2 = serverSocket.accept();
            System.out.println("Jogador 2 conectado.");

            // Gera um número aleatório para o jogador 2 adivinhar
            int numeroParaAdivinhar2 = random.nextInt(10) + 1;

            // Inicia o jogo com o jogador 2
            PrintWriter out2 = new PrintWriter(jogador2.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(jogador2.getInputStream()));
            out2.println("Bem-vindo ao jogo Adivinhe o Número!");

            boolean jogoEmAndamento = true;

            while (jogoEmAndamento) {
                // Jogador 1 adivinha
                out1.println("Sua vez de adivinhar um número entre 1 e 10:");
                int palpite1 = Integer.parseInt(in1.readLine());

                if (palpite1 == numeroParaAdivinhar2) {
                    out1.println("Parabéns! Você acertou o número.");
                    out2.println("O jogador 1 acertou o número. Fim de jogo.");
                    jogoEmAndamento = false;
                    break;
                } else if (palpite1 < numeroParaAdivinhar2) {
                    out1.println("Seu palpite está abaixo do número.");
                } else {
                    out1.println("Seu palpite está acima do número.");
                }

                // Jogador 2 adivinha
                out2.println("Aguardando jogada do jogador 1...");
                out2.println("Sua vez de adivinhar um número entre 1 e 10:");
                int palpite2 = Integer.parseInt(in2.readLine());

                if (palpite2 == numeroParaAdivinhar) {
                    out2.println("Parabéns! Você acertou o número.");
                    out1.println("O jogador 2 acertou o número. Fim de jogo.");
                    jogoEmAndamento = false;
                    break;
                } else if (palpite2 < numeroParaAdivinhar) {
                    out2.println("Seu palpite está abaixo do número.");
                } else {
                    out2.println("Seu palpite está acima do número.");
                }
            }

            jogador1.close();
            jogador2.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
