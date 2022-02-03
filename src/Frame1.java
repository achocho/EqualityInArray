import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.*;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
public class Frame1 {

	private JFrame frame;
	private JTextField textField;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Frame1() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 449, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(103, 41, 221, 50);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(131, 248, 258, 121);
		frame.getContentPane().add(lblNewLabel);
		JButton btnNewButton = new JButton("FindNumbers");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rawNumbers=textField.getText();
		print(rawNumbers,lblNewLabel);
			}
		});
		btnNewButton.setBounds(131, 155, 165, 65);
		frame.getContentPane().add(btnNewButton);
		
	}
	private void print(String rawNumbers,JLabel lblNewLabel) 
	{
		String nums=RemoveCharacters(rawNumbers);
		if(rawNumbers.length()<1||nums.equals(" ")) 
		{
			lblNewLabel.setText("You need to type at least one number!!!");
			return;
		}
		
		String[] numbers=Numbers(nums);
		String[] sorted=Sort(numbers);
	    String[] counted=Count(sorted);
	    String[] words= {"Number: ","wins with count of: "};
		String endResult="";
		if(counted.length==2) {
			for(int i=0;i<counted.length;i++) 
			{
				endResult+=words[i]+counted[i]+"  ";
			}
			}else 
			{
				endResult="Equality between: ";
				for(int i=0;i<counted.length;i++) 
				{
					if(i%2==0&&i<counted.length-2) {
					endResult+=counted[i]+" and ";
					}else if(i%2==0&&i>=counted.length-2) 
					{
						endResult+=counted[i]+" ";
					}
				}
				endResult+="with count of "+counted[counted.length-1];
			}
		lblNewLabel.setText("<html>"+endResult+"</html>");
	}
	private String RemoveCharacters(String raw) 
	{
		String tempString="";
		int countSpaces=0;
for(int i=0;i<raw.length();i++) 
{
		if(Character.isDigit(raw.charAt(i))) 
		{
			if(i>0&&raw.charAt(i-1)=='-') 
			{
				countSpaces=0;
				tempString+=raw.charAt(i-1);
			}
		
			countSpaces=0;
			tempString+=raw.charAt(i);
			if(i<raw.length()-1&&raw.charAt(i+1)=='.') 
			{
				countSpaces=1;
				tempString+=raw.charAt(i+1);
			
			}
		}else 
		{
			countSpaces++;
			if(countSpaces==1&&tempString.length()>0) 
			{
				tempString+=" ";
			}
		}
}

if(tempString.charAt(tempString.length()-1)==' ') 
{
	String tm="";
for(int i=0;i<tempString.length()-1;i++) 
{
	tm+=tempString.charAt(i);
	}
tempString=tm;
}
return tempString;
	}
	private String[] Numbers(String raw) 
	{	
		int arraySize=1;
	for(int i=0;i<raw.length();i++) 
	{
		if(raw.charAt(i)==' ') 
		{
			arraySize++;
		}
	
	}	
	int currIndex=0;
  String[] numbers=new String[arraySize];
  String temp="";
  for(int i=0;i<raw.length();i++) 
  {
	  if(raw.charAt(i)!=' ') {
	  temp+=raw.charAt(i);
	  }else 
	  {
		  
		  numbers[currIndex]=temp;
		  currIndex++;
		  temp="";
	  }
		if(i==raw.length()-1) 
		{
			
			numbers[currIndex]=temp;
			
		}  

  }

  return numbers;
	}
	private String[] Sort(String[] nums) 
	{
		String temp="";
		
		String[] sorted=nums;
		for(int i=0;i<sorted.length-1;i++) 
		{
			for(int j=0;j<sorted.length-1;j++) 
			{
				String num1=nums[j];
				String num2=nums[j+1];
			
				if(Double.parseDouble(num1)>Double.parseDouble(num2)) 
				{
					temp=nums[j];
					sorted[j]=nums[j+1];
					sorted[j+1]=temp;
				}
			
			}
		}
		return sorted;
	}
	private String[] Count(String[] sort) 
	{
		ArrayList<String> sizes=new ArrayList<>();
		int tempSize=0;
		String tempEl=sort[0];
		for(int i=0;i<sort.length;i++) 
		{
			if(tempEl.equals(sort[i])) 
			{
				tempSize++;
			}else 
			{
				sizes.add(tempEl);
				sizes.add(Integer.toString(tempSize));
				tempSize=1;
				tempEl=sort[i];
			}
			if(i==sort.length-1) 
			{
				sizes.add(tempEl);
				sizes.add(Integer.toString(tempSize));
			}
		}
		ArrayList<String> ends=new ArrayList<>();
		int temSize=0;
	for(int i=0;i<sizes.size();i++) 
	{
		if(i%2!=0) 
		{
			if(Double.parseDouble(sizes.get(i))==temSize) 
			{
				ends.add(sizes.get(i-1));
				ends.add(sizes.get(i));
			}else if(Double.parseDouble(sizes.get(i))>temSize) 
			{
					ends.clear();
					ends.add(sizes.get(i-1));
					ends.add(sizes.get(i));
				temSize=Integer.parseInt(sizes.get(i));
			}
		}
	}
	
	
		String[] End=new String[ends.size()];
for(int i=0;i<End.length;i++) 
{
	End[i]=ends.get(i);
	}
	return End;
	}
}
