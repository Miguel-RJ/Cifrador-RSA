import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Cifrador {

    static RSA rsa = new RSA();

	public Cifrador() {
		// TODO Auto-generated constructor stub
	}
	
	public static String Cifrar(String valor) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException, NoSuchProviderException 
	{
	 //Definimos un texto a cifrar

	    System.out.println(valor);
	    
	    //Instanciamos la clase
	    
	    //Generamos un par de claves
	    //Admite claves de 512, 1024, 2048 y 4096 bits
	    rsa.genKeyPair(4096);
	    
	    
	    String file_private = "/tmp/rsa.pri";
	    String file_public = "/tmp/rsa.pub";
	    
	    //Las guardamos asi podemos usarlas despues
	    //a lo largo del tiempo
	    rsa.saveToDiskPrivateKey("/tmp/rsa.pri");
	    rsa.saveToDiskPublicKey("/tmp/rsa.pub");
	    
	    //Ciframos y e imprimimos, el texto cifrado
	    //es devuelto en la variable secure
	    String secure = rsa.Encrypt(valor);
	    
	    System.out.println("\nCifrado:");
	    System.out.println(secure);
	    
	    return secure;
	}
    
	public static String Descifrar(String valor) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
	    //A modo de ejemplo creamos otra clase rsa
	    
	    //A diferencia de la anterior aca no creamos
	    //un nuevo par de claves, sino que cargamos
	    //el juego de claves que habiamos guadado
	    rsa.openFromDiskPrivateKey("/tmp/rsa.pri");    
	    rsa.openFromDiskPublicKey("/tmp/rsa.pub");
	    
	    //Le pasamos el texto cifrado (secure) y nos 
	    //es devuelto el texto ya descifrado (unsecure) 
	    String unsecure = rsa.Decrypt(valor);
	    
	    //Imprimimos
	    System.out.println("\nDescifrado:");
	    System.out.println(unsecure);   
	    return unsecure;
	}
}
