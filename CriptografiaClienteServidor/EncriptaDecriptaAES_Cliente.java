import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.Cipher;
  
public class EncriptaDecriptaAES_Cliente{
	
	static String IV = "AAAAAAAAAAAAAAAA";
	static String chaveencriptacao = "0123456789abcdef";
	static String textodigitado;
	static String textoencriptado_string;
	
	public static void main(String [] args) {
  
	      try {
		    Socket cliente = new Socket("127.0.0.1", 5051);
		    System.out.println("O cliente se conectou ao servidor!");

		    Scanner teclado = new Scanner(System.in);
		    PrintStream saida = new PrintStream(cliente.getOutputStream());

		    while (teclado.hasNextLine()) {
		      textodigitado = teclado.nextLine();
		      System.out.println("Testo digitado: " + textodigitado);

		      byte[] textoencriptado = encrypt(textodigitado, chaveencriptacao);
		      
		      System.out.println("Text : " + textodigitado);
		      System.out.println("Text [Byte Format] : " + textoencriptado);
		      System.out.println("Text [Byte Format] : " + textoencriptado.toString());

		      String string_encriptografada = new String(textoencriptado);
		      System.out.println("Text Decryted : " + string_encriptografada);
		           
		      saida.println(string_encriptografada);
		    }

		    saida.close();
		    teclado.close();
	      
	      } catch (Exception e) {
		    e.printStackTrace();
	      }
	}
  
	public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
	      Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
	      SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
	      encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	      return encripta.doFinal(textopuro.getBytes("UTF-8"));
	}
  
	public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception{
	      Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
	      SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
	      decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	      return new String(decripta.doFinal(textoencriptado),"UTF-8");
	}
	
}