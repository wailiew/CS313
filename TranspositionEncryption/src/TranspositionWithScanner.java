import java.util.*;
import java.io.*;

public class TranspositionWithScanner {
	public static void main(String args[]) throws FileNotFoundException {
		File inputFile = new File("who.txt");
		
		Scanner in = new Scanner(inputFile);
		String [] Rinput = new String[20];
		PrintWriter out = new PrintWriter("Flip.txt");
		
		int co= 0;
		while(in.hasNextLine()) {
			Rinput[co]=in.nextLine();
			co++;
		}
		
		String [] input = new String[co];
		for(int x=0;x<co;x++) {
			input[x]=Rinput[x];
		}
		
		String [] str = new String[co];
		String [] line = new String[co];
		char ED=' ';
		int firstEncrypt,secondEncrypt=0;

		for(int x=0;x<input.length;x++) {
			str[x]="";			
			boolean haveStars=false;
			for(int y=0;y<input[x].length();y++) {				
				if (input[x].charAt(y)=='*') {
					haveStars=true;
					break;
				}
				else if(input[x].charAt(y)!=' ') {
					str[x]+=input[x].charAt(y);
				}				
				
			}				
			ED=str[x].toUpperCase().charAt(0);
			if(haveStars==false||(ED!='E'&&ED!='D')) {
				out.println("Why Are You Here?");
				in.close();
				out.close();
				return;
			}	
			
			
			if(ED==('E')) {										
				firstEncrypt= (int)str[x].charAt(1)-'0';
				secondEncrypt=(int)str[x].charAt(2)-'0';
				
				if(firstEncrypt>9||firstEncrypt<0||secondEncrypt>9||secondEncrypt<0){
					out.println("Why Are You Here?");
					in.close();
					out.close();
					return;
				}
				
				String raw="";
				for(int z=3;z<str[x].length();z++) {
					raw+=str[x].toLowerCase().charAt(z);
				}

				int rol=raw.length()/firstEncrypt;
				if(raw.length()%firstEncrypt!=0) {
					rol+=1;
				}
				int col = firstEncrypt;
				int count=0;
				char Read[][] = new char [rol][col];
				
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
				line[x]=Encrypt;
			}
			
			else if(ED==('D')) {
				firstEncrypt= (int)str[x].charAt(2)-'0';
				secondEncrypt=(int)str[x].charAt(1)-'0';
				if(firstEncrypt>9||firstEncrypt<0||secondEncrypt>9||secondEncrypt<0){
					out.println("Why Are You Here?");
					in.close();
					out.close();
					return;
				}
				String raw="";
				for(int z=3;z<str[x].length();z++) {
					raw+=str[x].toLowerCase().charAt(z);
				}
								
				int rol=raw.length()/firstEncrypt;
				if(raw.length()%firstEncrypt!=0) {
					rol+=1;
				}
				int col = firstEncrypt;
				char Read[][] = new char [rol][col];
				int count=0;
				
				if(raw.length()%firstEncrypt!=0) {
					out.println("Why Are You Here?");
					in.close();
					out.close();
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
				line[x]=Encrypt;
				
			}
			out.println(line[x]);			
		}
		
		in.close();
		out.close();
		
	}
}
