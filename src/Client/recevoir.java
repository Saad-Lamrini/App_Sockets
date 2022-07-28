package Client;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class recevoir extends Thread {
Socket s;
JTextArea b;
public recevoir(Socket s,JTextArea b){
	
	this.s=s;
	this.b=b;
}


	@Override
public void run() {
	// TODO Auto-generated method stub
		while (true) {
			try {
				InputStreamReader input=new InputStreamReader(s.getInputStream());
				BufferedReader br=new BufferedReader(input);

				String ligne=br.readLine();
				System.out.println(ligne);
				//b.setLineWrap(true);
				b.setText(b.getText()+"\n"+ligne);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
}

}
