package Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.recevoir;

public class Serveur extends JFrame{
	
	private JTextArea txt;
	private JPanel pn;
	private JLabel lb_serv;
	private JTextField txt_send;
	private JButton btn_send;
	private JLabel lb_1,lb_2;
	Socket s;
	Serveur(){
		
		//Serveur mn=new Serveur();
		txt=new JTextArea();
		try {
			ServerSocket server=new ServerSocket(9002);
			System.out.println("Serveur en etat d'ecoute . . .");
			s= server.accept();
			System.out.println("un client est connect�");
			new recieve3(s,txt).start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setTitle("Application_sockets");
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		lb_serv=new JLabel("SERVEUR");
		lb_1=new JLabel();
		lb_1.setPreferredSize(new Dimension(240,300));
		lb_2=new JLabel();
		lb_2.setPreferredSize(new Dimension(240,300));
		lb_serv.setFont(new Font("Arial",Font.ITALIC,36));
		lb_serv.setOpaque(true);
		lb_serv.setBackground(Color.yellow);
		lb_serv.setHorizontalAlignment(JLabel.CENTER);
		
		//txt.setEditable(false);
	    txt.setPreferredSize(new Dimension(400,400));
		pn=new JPanel();
		//txt.setLineWrap(true);
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
		
		btn_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ch8="Serveur:"+txt_send.getText();
					try {
						//String ch8="Serveur:"+txt_send.getText();
						System.out.println(ch8);
						PrintWriter pw=new PrintWriter(s.getOutputStream());
						pw.println(ch8);
						pw.flush();// envoie 
						//ajouter dans text area
						txt.setText(txt.getText()+"\n"+ch8);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					txt_send.setText("");
			}});
		
		
	}
	
	public static void main(String[] args) {
		Serveur mn=new Serveur();
		mn.setVisible(true);
		
}}
