import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.*;
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
				String nums=RemoveCharacters(rawNumbers);
				if(rawNumbers.length()<1||nums.equals(" ")) 
				{
					lblNewLabel.setText("You need to type at least one number!!!");
					return;
				}
				
				int[] numbers=Numbers(nums);
				int[] sorted=Sort(numbers);
			    int[] counted=Count(sorted);
			    String[] words= {"Number: ","is winning with count of: "};
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
							if(i%2==0) {
							endResult+=counted[i]+"  ";
							}
						}
						endResult+="with count of :"+counted[counted.length-1];
					}
				lblNewLabel.setText(endResult);
			}
		});
		btnNewButton.setBounds(131, 155, 165, 65);
		frame.getContentPane().add(btnNewButton);
		
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
	private int[] Numbers(String raw) 
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
  int[] numbers=new int[arraySize];
  String temp="";
  for(int i=0;i<raw.length();i++) 
  {
	  if(raw.charAt(i)!=' ') {
	  temp+=raw.charAt(i);
	  }else 
	  {
		  
		  numbers[currIndex]=Integer.parseInt(temp);
		  currIndex++;
		  temp="";
	  }
		if(i==raw.length()-1) 
		{
			
			numbers[currIndex]=Integer.parseInt(temp);
			
		}  

  }

  return numbers;
	}
	private int[] Sort(int[] nums) 
	{
		int temp=0;
		int[] sorted=nums;
		for(int i=0;i<sorted.length-1;i++) 
		{
			for(int j=0;j<sorted.length-1;j++) 
			{
				int num1=nums[j];
				int num2=nums[j+1];
				if(num1>num2) 
				{
					temp=nums[j];
					sorted[j]=nums[j+1];
					sorted[j+1]=temp;
				}
			}
		}
		return sorted;
	}
	private int[] Count(int[] sort) 
	{
		ArrayList<Integer> sizes=new ArrayList<>();
		int tempSize=0;
		int tempEl=sort[0];
		for(int i=0;i<sort.length;i++) 
		{
			if(tempEl==sort[i]) 
			{
				tempSize++;
			}else 
			{
				sizes.add(tempEl);
				sizes.add(tempSize);
				tempSize=1;
				tempEl=sort[i];
			}
			if(i==sort.length-1) 
			{
				sizes.add(tempEl);
				sizes.add(tempSize);
			}
		}
		ArrayList<Integer> ends=new ArrayList<>();
		int temSize=0;
	for(int i=0;i<sizes.size();i++) 
	{
		if(i%2!=0) 
		{
			if(sizes.get(i)==temSize) 
			{
				ends.add(sizes.get(i-1));
				ends.add(sizes.get(i));
			}else if(sizes.get(i)>temSize) 
			{
					ends.clear();
					ends.add(sizes.get(i-1));
					ends.add(sizes.get(i));
				temSize=sizes.get(i);
			}
		}
	}
	
	
		int[] End=new int[ends.size()];
for(int i=0;i<End.length;i++) 
{
	End[i]=ends.get(i);
	}
	return End;
	}
}
