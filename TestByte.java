public class TestByte
{    
	public static void main(String[] argv) {

		    String example = "This is an example";
		    byte[] bytes = example.getBytes();

		    System.out.println("Text : " + example);
		    System.out.println("Text [Byte Format] : " + bytes);
		    //System.out.println("Text [Byte Format] : " + bytes.toString());

		    String s = new String(bytes);
		    System.out.println("Text Decryted : " + s);
		    

	}
}
