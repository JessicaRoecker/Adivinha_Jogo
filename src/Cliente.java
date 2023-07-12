import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private static final int PORT = 12345;

    public static void main(String[] args) {
        runCliente();
    }

    private static void runCliente() {
        try {
            Socket socket = new Socket("Informar IP do Servidor", PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String message;

            while ((message = in.readLine()) != null) {
                System.out.println(message);

                if (message.equals("Bem-vindo ao jogo Adivinhe o Número!")) {
                    while (true) {
                        String prompt = in.readLine();
                        System.out.println(prompt);

                        if (prompt.equals("Aguardando jogada do jogador 2...")) {
                            // Aguarda a jogada do jogador 2
                            continue;
                        }

                        System.out.print("Digite um número entre 1 e 10: ");
                        int palpite = Integer.parseInt(reader.readLine());
                        out.println(palpite);

                        // Recebe a resposta do servidor
                        String resposta = in.readLine();
                        System.out.println(resposta);

                        if (resposta.startsWith("Parabéns!") || resposta.startsWith("O jogador")) {
                            break;
                        }
                    }
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

