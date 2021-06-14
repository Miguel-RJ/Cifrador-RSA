import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCifrar = new JButton("Cifrar ");
		
		btnCifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String currentPath = System.getProperty("user.dir");
				System.out.println(currentPath);
				List<String> listaSinCifrar = new ArrayList<String>();
				List<String> listaCifrada = new ArrayList<String>();
				Scanner entrada = null;
		        JFileChooser fileChooser = new JFileChooser(currentPath);
		        fileChooser.showOpenDialog(fileChooser);
		        
		        try 
		        {
		            String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
		            File f = new File(ruta);
		            entrada = new Scanner(f);
		            Cifrador cifrador = new Cifrador();
		            while (entrada.hasNext()) 
		            {
		            	listaSinCifrar.add(entrada.nextLine());
		            }
		            
		            for(int i = 0; i <= listaSinCifrar.size() - 1; i++) {
		       
		            	listaCifrada.add(cifrador.Cifrar(listaSinCifrar.get(i)));
		            }
		            
		            try {
		                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentPath+"/Examples/cifrar.rsa"), "UTF-8"));
		                for(int i = 0; i <= listaCifrada.size()-1; i++) 
		                {   
			                out.write(listaCifrada.get(i));
			                out.write(System.lineSeparator());
			            }
		                out.close();
		            } catch (Exception ex) {
		                // TODO: handle exception
		            	System.out.println(ex.getMessage());
		            }
		            
		            
		        } 
		        catch (FileNotFoundException fileEx) 
		        {
		            System.out.println(fileEx.getMessage());
		        } 
		        catch (NullPointerException nullEx) 
		        {
		            System.out.println("No se ha seleccionado ningún fichero");
		        } 
		        catch (Exception exc) 
		        {
		            System.out.println(exc.getMessage());
		        } 
		        finally 
		        {
		            if (entrada != null) 
		            {
		                entrada.close();
		            }
		        }
		        
		        
		        
			}
		});
		btnCifrar.setBounds(173, 91, 117, 29);
		contentPane.add(btnCifrar);
		
		JButton btndescifrar = new JButton("Descrifrar");
		btndescifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentPath = System.getProperty("user.dir");
				System.out.println(currentPath);
				List<String> listaDescifrada = new ArrayList<String>();
				List<String> listaCifrada = new ArrayList<String>();
				Scanner entrada = null;
		        JFileChooser fileChooser = new JFileChooser(currentPath);
		        fileChooser.showOpenDialog(fileChooser);
		        
		        try 
		        {
		            String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
		            File f = new File(ruta);
		            entrada = new Scanner(f);
		            Cifrador cifrador = new Cifrador();
		            while (entrada.hasNext()) 
		            {
		            	listaCifrada.add(entrada.nextLine());
		            }
		            
		            for(int i = 0; i <= listaCifrada.size() - 1; i++) {
		       
		            	listaDescifrada.add(cifrador.Descifrar(listaCifrada.get(i)));
		            }
		            
		            try {
		            	String message = "";
		                for(int i = 0; i <= listaDescifrada.size()-1; i++) 
		                {   
		                	message = listaDescifrada.get(i);
			                message += System.lineSeparator();
			            }
		                System.out.println(message);
		                
		                
		                JOptionPane.showMessageDialog(null, message);
		            } catch (Exception ex) {
		                // TODO: handle exception
		            	System.out.println(ex.getMessage());
		            }
		            
		            
		        } 
		        catch (FileNotFoundException fileEx) 
		        {
		            System.out.println(fileEx.getMessage());
		        } 
		        catch (NullPointerException nullEx) 
		        {
		            System.out.println("No se ha seleccionado ningún fichero");
		        } 
		        catch (Exception exc) 
		        {
		            System.out.println(exc.getMessage());
		        } 
		        finally 
		        {
		            if (entrada != null) 
		            {
		                entrada.close();
		            }
		        }
			}
		});
		btndescifrar.setBounds(173, 136, 117, 29);
		contentPane.add(btndescifrar);
		
		JLabel lblNewLabel = new JLabel("Sistema RSA");
		lblNewLabel.setBounds(194, 6, 77, 37);
		contentPane.add(lblNewLabel);
	}
}
