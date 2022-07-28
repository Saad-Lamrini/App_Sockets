package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Server.Serveur;
//import Server.chat;

public class Client extends JFrame{
	private JTextArea txt;
	private JPanel pn;
	private JLabel lb_serv;
	private JTextField txt_send;
	private JButton btn_send;
	private JLabel lb_1,lb_2;
	Socket s;
	String ligne;
	Client(){
		
		txt=new JTextArea();	
		//txt.setLineWrap(true);
		//tx/t.setEditable(false);
Scanner sc=new Scanner(System.in);
	System.out.println("Demarrage Client . . .");

	try {
		s=new Socket("127.0.0.1",9002);
		System.out.println("je suis connecte");
		
		new recevoir(s,txt).start();
		
		//new chat(s).start();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		System.out.println("erreur @ip:"+e.getMessage());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("erreur connexion:"+e.getMessage());
	}
	
	
		this.setTitle("Application_sockets");
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		lb_serv=new JLabel("CLIENT");
		lb_1=new JLabel();
		lb_1.setPreferredSize(new Dimension(240,300));
		lb_2=new JLabel();
		lb_2.setPreferredSize(new Dimension(240,300));
		lb_serv.setFont(new Font("Arial",Font.ITALIC,36));
		lb_serv.setOpaque(true);
		lb_serv.setBackground(Color.yellow);
		lb_serv.setHorizontalAlignment(JLabel.CENTER);
		
		//txt.setText("dfgdfgdhfg");
		//txt.setText("aaaaaaaaaaaazzz");
	    txt.setPreferredSize(new Dimension(400,400));
		pn=new JPanel();
		txt.setText(ligne);
		txt.setFont(new Font("Comic Sans",Font.ITALIC,20));
		txt_send=new JTextField();
		txt_send.setPreferredSize(new Dimension(300,30));
		btn_send=new JButton("Envoyer");
		pn.setLayout(new FlowLayout());
		pn.add(txt_send);
		pn.add(btn_send);
		
		
		this.add(txt,BorderLayout.CENTER);
		this.add(lb_serv,BorderLayout.NORTH);
		this.add(pn, BorderLayout.SOUTH);
		this.add(lb_1,BorderLayout.WEST);
		this.add(lb_2, BorderLayout.EAST);
		//String ch3=txt_send.getText();
		
		btn_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//String ch3="Serveur:"+txt_send.getText();
				try {
					String ch4="Client:"+txt_send.getText();
					System.out.println(ch4);
					PrintWriter pw=new PrintWriter(s.getOutputStream());
					pw.println(ch4);
					pw.flush();// envoie 
					//ajouter dans text area
					txt.setText(txt.getText()+"\n"+ch4);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
			txt_send.setText("");
			}});
	}
	
	

public static void main(String[] args) {

	Client m=new Client();
	m.setVisible(true);
	
	
	
}
}


