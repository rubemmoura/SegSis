import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

  public static void main(String[] args)  throws UnknownHostException, IOException {

    Socket cliente = new Socket("127.0.0.1", 5051);
    System.out.println("O cliente se conectou ao servidor!");

    Scanner teclado = new Scanner(System.in);
    PrintStream saida = new PrintStream(cliente.getOutputStream());

    while (teclado.hasNextLine()) {
      //saida.println(encrypt(teclado.nextLine(), chaveencriptacao));
      saida.println(teclado.nextLine());
      //chama encrypt
      //
    }

    saida.close();
    teclado.close();
  }
}