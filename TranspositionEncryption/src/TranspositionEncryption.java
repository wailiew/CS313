import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class TranspositionEncryption extends JFrame{
	private JPanel panelN;
	private JPanel panelC;
	private JPanel panelS;
	private JTextField text;
	private JButton button;
	private JLabel show;
	private JLabel label;
	
	public TranspositionEncryption() {
		setTitle("Transposition Encryption");
		setSize(450, 144);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		buildpanelN();
		buildpanelC();
		buildpanelS();
		add(panelN,BorderLayout.NORTH);
		add(panelC,BorderLayout.CENTER);
		add(panelS,BorderLayout.SOUTH);
		setVisible(true);		
	}


	private void buildpanelN() {
		panelN = new JPanel();
		show= new JLabel("Encrypt/Decrypt");
		Font f = new Font("Dialog", 1, 20);
		show.setFont(f);
		
		panelN.add(show);
	}
	private void buildpanelC() {
		panelC = new JPanel();
		text = new JTextField(25);
		button = new JButton("Convert");
		button.addActionListener(new EncDec());
		panelC.add(text);
		panelC.add(button);		
	}

	private void buildpanelS() {
		panelS = new JPanel();
		label = new JLabel();
		Font f = new Font("Dialog", 1, 20);
		label.setFont(f);
		panelS.add(label);		
	}
		
	private class EncDec implements ActionListener{
		 public void actionPerformed(ActionEvent a) {
			String input;
			input=text.getText();
			char ED=input.toUpperCase().charAt(0);
			int firstEncrypt,secondEncrypt;
			

			if(ED==('E')) {
			firstEncrypt= (int)input.charAt(1)-'0' ;
			secondEncrypt=(int)input.charAt(2)-'0';
			if(firstEncrypt>9||firstEncrypt<0||secondEncrypt>9||secondEncrypt<0) {
				label.setText("youhavenobusinesshere");
				return;
			}			
			boolean havestars=false;
			
			String raw="";
			for(int x=3;x<input.length();x++) {
				if(input.charAt(x)=='*') {
					havestars=true;
					break;
				}	
				raw+=input.toLowerCase().charAt(x);
			}
			if(havestars==false) {
				label.setText("youhavenobusinesshere");
				return;
			}
			
			int rol=raw.length()/firstEncrypt;
			if(raw.length()%firstEncrypt!=0) {
				rol+=1;
			}
			int col = firstEncrypt;
			
			char Read[][] = new char [rol][col];
			int count=0;
			for(int z=0;z<rol;z++) {
				for(int y=0;y<col;y++) {
					if(count<raw.length()){					
						Read[z][y]=raw.charAt(count);
						count++;
					}
					else{ 
						Read[z][y]='q';
					}					
				}	
			}
			
			String Encrypt="";
			for(int z=0;z<col;z++) {
				for(int y=0;y<rol;y++) {
					Encrypt+= Read[y][z];
				}
			}			
			
			rol=Encrypt.length()/secondEncrypt;
			if(Encrypt.length()%secondEncrypt!=0) {
				rol+=1;
			}
			col = secondEncrypt;
			char Read2[][] = new char [rol][col];
			count=0;
			for(int z=0;z<rol;z++) {
				for(int y=0;y<col;y++) {
					if(count<Encrypt.length()){					
						Read2[z][y]=Encrypt.charAt(count);
						count++;
					}
					else{ 
						Read2[z][y]='q';
					}					
				}	
			}
			Encrypt="";
			for(int z=0;z<col;z++) {
				for(int y=0;y<rol;y++) {
					Encrypt+= Read2[y][z];
				}
			}		
			label.setText(Encrypt);

			}		
			
			if(ED==('D')) {
				firstEncrypt= (int)input.charAt(2)-'0';
				secondEncrypt=(int)input.charAt(1)-'0';
				
				boolean havestars=false;
				
				String raw="";
				for(int z=3;z<input.length();z++) {
					if(input.charAt(z)=='*') {
						havestars=true;
						break;
					}	
					raw+=input.toLowerCase().charAt(z);
				}
				if(havestars==false) {
					label.setText("youhavenobusinesshere");
					return;
				}
				
				int rol=raw.length()/firstEncrypt;
				if(raw.length()%firstEncrypt!=0) {
					rol+=1;
				}
				int col = firstEncrypt;
				
				char Read[][] = new char [rol][col];
				int count=0;
				
				if(raw.length()%firstEncrypt!=0) {
					label.setText("youhavenobusinesshere");
					return;
				}
								
				for(int y=0;y<col;y++) {
					for(int z=0;z<rol;z++) {
							Read[z][y]=raw.charAt(count);
							count++;
					}	
				}
				String Encrypt="";
				for(int y=0;y<rol;y++) {
					for(int z=0;z<col;z++) {
						Encrypt+= Read[y][z];
					}
				}
				rol=Encrypt.length()/secondEncrypt;
				col = secondEncrypt;
				char Read2[][] = new char [rol][col];
				count=0;
				for(int y=0;y<col;y++) {					
					for(int z=0;z<rol;z++) {
							Read2[z][y]=Encrypt.charAt(count);
							count++;					
					}	
				}
				Encrypt="";
				for(int z=0;z<rol;z++) {
					for(int y=0;y<col;y++) {
						Encrypt+= Read2[z][y];
					}
				}		
				label.setText(Encrypt);
			}
		
		 }
	}
	
	
	
	public static void main(String args[]) {
		new TranspositionEncryption();
	}
}