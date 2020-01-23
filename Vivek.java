import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
class Vivek extends JFrame
{
	
		static ServerSocket ss;
		static Socket s;
		static DataOutputStream dos;
		static DataInputStream dis;
	static JTextArea area1=new JTextArea();
	static JTextField field1=new JTextField("Text Here");
	static JButton button1=new JButton("Send");
	static JLabel label1=new JLabel();
  
	static JScrollPane scr=new JScrollPane(area1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	Container c;
     public void sita(){
		 
		Thread t1=new Thread(new Runnable(){
			public void run(){
		try{
		s=new Socket("localhost",1000);
		label1.setText("Client Connected");
		dos=new DataOutputStream(s.getOutputStream());
		dis=new DataInputStream(s.getInputStream());
		String msgin="";
		while(!msgin.equals("exit")){
		msgin=dis.readUTF();
		area1.setText(area1.getText().trim()+"\n Server:\t"+msgin);
		}
		}
		catch(Exception eksa){}	
			}});
       t1.start();			
		 
	 }
	 public void ram(){
		try{	
		Thread ta=new Thread(new Runnable(){
			public void run(){
				try{
				ss=new ServerSocket(1000);
            s=ss.accept();		
		label1.setText("Server Connected");
		
		dos=new DataOutputStream(s.getOutputStream());
		dis=new DataInputStream(s.getInputStream());
		String msgin="";
		while(!msgin.equals("exit")){
		msgin=dis.readUTF();
		area1.setText(area1.getText().trim()+"\n Server:\t"+msgin);
		}
		}catch(IOException jire){}
			}
		});
			ta.start();	
		
		
		}
		catch(Exception e){}
		}
	Vivek()
	{
				c=this.getContentPane();
				c.setLayout(null);
				scr.setBounds(10,10,400,300);
				field1.setBounds(10,350,270,30);
				
				button1.setBounds(320,350,80,30);
				label1.setBounds(10,400,400,50);
						
				button1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
						String msgout="";
							msgout=field1.getText();
							dos.writeUTF(msgout);
				
						}
						catch(Exception event){}
					}});
			scr.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
				public void adjustmentValueChanged(AdjustmentEvent ird){
					ird.getAdjustable().setValue(ird.getAdjustable().getMaximum());
				}
			});
				
				c.add(scr);
				c.add(field1);
				c.add(button1);
				c.add(label1);
	}
	public static void main(String args[])
	{
		
		 JFrame fra=new JFrame();
	fra.setVisible(true);
	fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fra.setBounds(200,100,300,300);
	Container c=fra.getContentPane();
	fra.setResizable(false);
	JButton b1=new JButton("HOST");
	JButton b2=new JButton("JOIN");
	fra.setLayout(null);
	b1.setBounds(100,50,100,50);
	b2.setBounds(100,150,100,50);
	c.add(b1);
	c.add(b2);

	
		Vivek frame=new Vivek();
		frame.setVisible(false);
		frame.setResizable(false);
		frame.setTitle("VKS Chatting server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(1,2,450,500);
		ImageIcon icon=new ImageIcon("Logo1.jpg");
		frame.setIconImage(icon.getImage()); 		
		b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		fra.setVisible(false);	
		frame.setVisible(true);
		

		frame.ram();
		}
		
	});
	
						
					
b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		
		fra.setVisible(false);
		frame.setVisible(true);
	    frame.sita();
}});
		
				 
		 
	   
	
	
	}
}