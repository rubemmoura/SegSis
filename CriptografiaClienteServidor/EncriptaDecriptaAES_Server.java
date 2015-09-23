import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
  
public class EncriptaDecriptaAES_Server{
	
	static String IV = "AAAAAAAAAAAAAAAA";
	static String textopuro;
	static String chaveencriptacao = "0123456789abcdef";
  
	public static void main(String [] args) {
  
	      try { 
		    ServerSocket servidor = new ServerSocket(5051);
		    System.out.println("Porta aberta!");

		    Socket cliente = servidor.accept();
		    System.out.println("Nova conexão com o cliente " +  cliente.getInetAddress().getHostAddress());

		    Scanner entrada = new Scanner(cliente.getInputStream());
		    String string_encriptada;
		    
		    while (entrada.hasNextLine()){
		      string_encriptada = entrada.nextLine();
		      
		      System.out.println("Mesagem Encriptografada: " + string_encriptada);
		      
		      //converter string para byte
		      byte[] textoencriptado = string_encriptada.getBytes();
		      //System.out.println("Text [Byte Format] : " + textoencriptado);

		      
		      String textodecriptado = decrypt(textoencriptado, chaveencriptacao);
		      System.out.println("Texto Decriptado: " + textodecriptado);
		    }

		    entrada.close();
		    servidor.close();
		    
		    System.out.println("");
	      
	      } catch (Exception e){
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