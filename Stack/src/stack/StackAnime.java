/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

/**
 *
 * @author yogeshwar
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class StackAnime extends JFrame
{
	Stack<Label> labels;
	String str;
	Label label;
	JTextField text;
	JPanel sPanel;
	JButton popB,pushB;
	int top_Y;
	public StackAnime()
	{
		super("Stack");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("stack.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setResizable(false);
		//setBackground(Color.green);
		setLayout(null);
		
		top_Y=299;
		labels=new Stack<Label>();
		
		sPanel=new JPanel();
		sPanel.setBounds(40,30,210,330);
		sPanel.setLayout(null);
		sPanel.setBackground(Color.white);
		sPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(sPanel);
		
		text=new JTextField("");
		text.setBounds(270,200,80,25);
		add(text);
		text.setEditable(true);
		
		popB=new JButton("POP");
		pushB=new JButton("PUSH");
		popB.setBounds(270,250,80,25);
		pushB.setBounds(270,300,80,25);
		add(popB);
		add(pushB);
		popB.setEnabled(false);
		setVisible(true);
		
		ActionListener act=new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				switch(e.getActionCommand())
				{
					case "POP":
					popLabel();
					break;
					case "PUSH":
					pushLabel();
					break;
				}
			}
		};
		popB.addActionListener(act);
		pushB.addActionListener(act);
	}
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new StackAnime();
			}
			
		});
		
	}
	public boolean pushLabel()
	{
		str=text.getText();
		setFont(new Font("Lucida Fax",Font.BOLD,20));
		if(str.equals(""))
			return false;
		Graphics g=getGraphics();
		FontMetrics fm=g.getFontMetrics();
		if(fm.stringWidth(text.getText())>90)
		{
			text.setText("outofbounds");
			return false;
		}
		label=new Label(text.getText());
		//label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setFont(new Font("Lucida Fax",Font.BOLD,20));
		if(hasSpace())
		{
			labels.push(label);
			text.setText("");
			addLabel();
			popB.setEnabled(true);
			if(!hasSpace())
				pushB.setEnabled(false);
			return true;
		}
		else
			text.setText("FULL");
		return false;
	}
	public boolean popLabel()
	{
		if(!labels.empty())
		{
			label=labels.pop();
			removeLabel();
			text.setText(label.getText());
			if(labels.empty())
				popB.setEnabled(false);
			pushB.setEnabled(true);
		}
		else
			text.setText("EMPTY");
		return true;
	}
	public boolean hasSpace()
	{
		if(top_Y>0)
			return true;
		return false;
	}
	public void addLabel()
	{
		//label.setBackground(Color.red);
		label.setAlignment(Label.CENTER);
		try{
			sPanel.add(label);
			label.setBounds(60,30,90,30);
			for(int y=30;y<=top_Y;y++)
			{
				label.setBounds(60,y,90,30);
				Thread.sleep(5);
				//sPanel.revalidate();
			}
		}
		catch(Exception e){e.printStackTrace();}
		label.setBounds(60,top_Y,90,30);
		//label.setEnabled(false);
		top_Y-=40;
	}
	public void removeLabel()
	{
		top_Y+=40;
		try{
			for(int x=60;x>0;x--)
			{
				label.setBounds(x,top_Y,90,30);
				Thread.sleep(5);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		sPanel.remove(label);
	}
}
