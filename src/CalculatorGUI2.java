/***********************************************************************
 * Windows 10 Programmer Calculator
 * 
 * This is the Windows 10 Programmer calculator that performs 
 * all basic arithmetic calculations, hex, binary, octal conversions, 
 * and word, qword, dword and byte implementation. 
 * 
 * The calculator satisfies the order of precendence of different 
 * operators using which has been made possible by implementation of a stack 
 * and method for precedence. The GUI of the app was implemented using 
 * java swing components. The program uses GridBag layout.
 * 
 * The program uses a 2-D array to store the different labels which act as 
 * buttons for the calculator.
 * 
 * Programmed by Mansha Fatima (MXF170010)
 * The University of Texas at Dallas
 *************************************************************************/
import java.awt.*;
import java.util.Stack;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
import java.awt.event.*;

public class CalculatorGUI2 extends JFrame implements MouseListener
{
	//2-D array to store the different labels that act as buttons for the calculator
	public static final String[][] BTN_LABELS = {

			  {"HEX ", ""},
			  {"DEC ", ""},
			  {"OCT ", ""},
			  {"BIN ", ""},
			  {"", "", "WORD", "", "", ""},
			  {"Lsh", "Rsh", "Or", "Xor", "Not", "And"},
		      {"", "Mod", "CE", "C", "<-", "/"},
		      {"A", "B", "7", "8", "9", "x"},
		      {"C", "D", "4", "5", "6", "-"},      
		      {"E", "F", "1", "2", "3", "+"},
		      {"(", ")", "+", "0", ".", "="}
	};
	
	//Variables to store numbers for calculations
	String operation;
	int first;
	int second;
	int result;

	Stack<Integer> numbers = new Stack<Integer>(); 	//Stack for numbers
	Stack<String> operators = new Stack<String>();	//Stack for operators
	boolean operatorEntered = false;				//Bool to store true if an operator was just clicked else false
	String prevNumber;								//Variable to store previous number
	int numSize = 16;								//Initial size for max input
	
	//Creating objects of Panel, Labels, and text areas.
	JPanel myPanel = new JPanel();
	JLabel [][] label = new JLabel[11][6];
	JLabel title = new JLabel();
	JLabel label1 = new JLabel();
	static JTextArea input1 = new JTextArea(6, 3);
	
	/******************************************************
	 * Constructor to implement the GUI of the calculator 
	 * using the different java swing objects and GridBag 
	 * layout. Places the labels and text areas in their 
	 * appropriate positions
	 *******************************************************/
	public CalculatorGUI2()
	{
		int border = 1;
		myPanel.setBorder(BorderFactory.createEmptyBorder(border, border, border, border));
		
		//Create object of GridBag layout and set the column width and row height
		GridBagLayout gb = new GridBagLayout();
		gb.columnWidths = new int[] {35, 35, 35, 35, 35, 35};
		gb.rowHeights = new int[] {45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45};
		myPanel.setLayout(gb);
		
		//Create objects for GridBagConstraints
		GridBagConstraints constr = new GridBagConstraints();
		
		//Set the constraints for layout of the top label 
		constr.gridx=0;
        constr.gridy=0;
        constr.gridwidth=6;
        constr.weightx=1;
        constr.fill=GridBagConstraints.HORIZONTAL;
        ImageIcon image1 = new ImageIcon("/Users/manshaf/Desktop/40.jpg");
        //c.insets=new Insets(5,5,5,5);
        title = new JLabel(image1);
        title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        myPanel.add(title, constr);
        add(myPanel, BorderLayout.NORTH);
        
        //Set the constraints for layout of the text area to display the calculations
        constr = new GridBagConstraints();
        constr.gridx=0;
        constr.gridy=1;
        constr.gridwidth=6;
        constr.gridheight=1;
        constr.weightx=1;
        constr.fill=GridBagConstraints.HORIZONTAL;
        //c.insets=new Insets(5,5,5,5);
        input1 = new JTextArea(6, 3);
        Color myColor = Color.decode("#f1f0f2");
        input1.setBackground(myColor);
        input1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        Font font = new Font("Courier", Font.BOLD, 18);
		//set font for JTextField
		input1.setFont(font);
        myPanel.add(input1, constr);
        add(myPanel, BorderLayout.NORTH);
        

        //Nested for loops to create labels, apply constraints and place them in appropriate positions
		for (int i = 0; i < 11; i++) 
		{
			 
	         for (int j = 0; j <6; j++) 
	         {
	        	 
	        	 //Create and place labels for WORD, QWORD, DWORD, and BYTE
	        	 if(j == 1 && (i == 0 || i == 1 || i == 2|| i == 3))
	             {
	        		 constr.gridy=i+2;
		        	 constr = new GridBagConstraints();
		        	 constr.gridx=j;
		             constr.gridwidth=5;
		             constr.weightx=1;
		             constr.fill=GridBagConstraints.HORIZONTAL;
		             
		             label[i][j] = new JLabel(BTN_LABELS[i][j]);
		             label[i][j].setFont(new Font("Courier New", Font.BOLD, 16));
		             label[i][j].setHorizontalAlignment(SwingConstants.LEFT);
		             label[i][j].setForeground(Color.gray);
			        	
		             myPanel.add(label[i][j], constr);
			         add(myPanel, BorderLayout.SOUTH);
			         
			         j=6;
	            	 
	            	 continue;
	             }
	        	 
	        	 //Create and place labels for HEX, BIN, OCT, and DEC
	        	 constr.gridy=i+2;
	        	 constr = new GridBagConstraints();
	        	 constr.gridx=j;
	             constr.gridwidth=1;
	             constr.weightx=1;
	             constr.fill=GridBagConstraints.HORIZONTAL;
	             
	             
	             //Create and place label for menu items
	             if(i == 4 && j == 0)
	             {
	            	 
	            	 constr.gridy=i+2;
		        	 constr = new GridBagConstraints();
		        	 constr.gridx=j;
		             constr.gridwidth=1;
		             constr.weightx=1;
		             constr.fill=GridBagConstraints.HORIZONTAL;
		             
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/1.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	             
	             //Create and place label for menu items
	             if(i == 4 && j == 1)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/2.jpg");
	            	 label[i][j] = new JLabel(image);	//Set image in label
	            	 myPanel.add(label[i][j], constr);	//Add label to the panel
	            	 label[i][j].addMouseListener(this);//Add mouse listener to the label to react to mouse clicks
	            	 
	            	 continue;
	             }
	             
	             
	           //Create and place label for menu items
	             if(i == 4 && j == 3)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/4.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for menu items
	             if(i == 4 && j == 4)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/5.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for menu items
	             if(i == 4 && j == 5)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/41.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for arrow
	             if(i == 6 && j == 0)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/7.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for Mod
	             if(i == 6 && j == 1)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/8.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	             
	           //Create and place label for clear element
	             if(i == 6 && j == 2)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/9.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for clear 
	             if(i == 6 && j == 3)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/10.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for backspace
	             if(i == 6 && j == 4)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/11.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	             
	           //Create and place label for Division operator
	             if(i == 6 && j == 5)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/12.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for A
	             if(i == 7 && j == 0)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/13.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for B
	             if(i == 7 && j == 1)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/14.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for 7
	             if(i == 7 && j == 2)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/15.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for 8
	             if(i == 7 && j == 3)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/16.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for 9
	             if(i == 7 && j == 4)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/17.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for multiplication operator
	             if(i == 7 && j == 5)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/18.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for C
	             if(i == 8 && j == 0)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/19.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for D
	             if(i == 8 && j == 1)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/20.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for 4
	             if(i == 8 && j == 2)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/21.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for 5
	             if(i == 8 && j == 3)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/22.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for 6
	             if(i == 8 && j == 4)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/23.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for Minus operator
	             if(i == 8 && j == 5)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/24.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for E
	             if(i == 9 && j == 0)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/25.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for F
	             if(i == 9 && j == 1)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/26.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	             
	           //Create and place label for 1
	             if(i == 9 && j == 2)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/27.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for 2
	             if(i == 9 && j == 3)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/28.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for 3
	             if(i == 9 && j == 4)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/29.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for Plus operator
	             if(i == 9 && j == 5)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/30.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for open parens
	             if(i == 10 && j == 0)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/31.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for closed parens
	             if(i == 10 && j == 1)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/32.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for toggle operator
	             if(i == 10 && j == 2)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/33.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for 0
	             if(i == 10 && j == 3)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/34.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for decimal point
	             if(i == 10 && j == 4)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/35.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	           //Create and place label for Equals operator
	             if(i == 10 && j == 5)
	             {
	            	 ImageIcon image = new ImageIcon("/Users/manshaf/Desktop/36.jpg");
	            	 label[i][j] = new JLabel(image);
	            	 myPanel.add(label[i][j], constr);
	            	 label[i][j].addMouseListener(this);
	            	 
	            	 continue;
	             }
	             
	        	label[i][j] = new JLabel(BTN_LABELS[i][j]);
	        	label[i][j].setFont(new Font("Courier New", Font.BOLD, 16));
	        	label[i][j].setHorizontalAlignment(SwingConstants.CENTER);
	        	label[i][j].setForeground(Color.gray);
	        	
	            myPanel.add(label[i][j], constr);
	            add(myPanel, BorderLayout.SOUTH);
	            
	            label[i][j].addMouseListener(this);

	         }
	     }
		
		}
	
		/***********************************************************************
		 * Method to implement all of the functionality of the calculator
		 * Displays numbers on click of mouse and performs different operations.
		 * Perform conversions between hex, bin, oct, and dec
		 * Perform conversions between WORD, QWORD, DWORD and BYTE
		 ***********************************************************************/
		public void mousePressed (MouseEvent e) 
		{
			int binNum = label[3][1].getText().length();	//Store the length of the most current binary number
			
			//Limits the size of input based on WORD, QWORD, DWORD and BYTE
			//if(binNum <= numSize || input1.getText() == "")
			//{
				
			//If the WORD, QWORD, DWORD or BYTE label is pressed
			//Max size of input for each option:
			//WORD = 16 bits, QWORD = 32 bits, BYTE = 8 bits, DWORD = 64 bits
			if(e.getSource() == label[4][2])
			{
				//Performs conversion if BYTE is chosen
				if(label[4][2].getText() == "WORD")
				{
					label[4][2].setText("BYTE"); //sets label to BYTE
					numSize = 8;	//Sets the size limit
					String numToConvert = DecimalToBinary(Integer.parseInt(input1.getText()));	//Retrieves number to convert
					String newNum = numToConvert.substring(numToConvert.length()-8);	//Stores the last 8 bits of the current number
					input1.setText(Integer.toString(bin2Dec(newNum)));	//Displays the new number based on conversion
					
					String decNum = Integer.toString(bin2Dec(newNum));	//Stores the decimal number
					label[0][1].setText(Integer.toHexString(Integer.parseInt(decNum)));	//Converts to hexadecimal number
					label[1][1].setText(Integer.toString(Integer.parseInt(decNum))); //Converts to decimal number
		            label[2][1].setText(Integer.toOctalString(Integer.parseInt(decNum))); //Converts to octal number
		            label[3][1].setText(DecimalToBinary(Integer.parseInt(decNum))); //Converts to binary number by calling to decimal to binary conversion method

				}
				
				//Performs conversion if QWORD is chosen
				else if(label[4][2].getText() == "BYTE")
				{
					label[4][2].setText("QWORD");
					numSize = 64;
					String numToConvert = DecimalToBinary(Integer.parseInt(input1.getText()));
					String newNum = numToConvert.substring(numToConvert.length()-8);
					input1.setText(Integer.toString(bin2Dec(newNum)));
					
					String decNum = Integer.toString(bin2Dec(newNum));
					label[0][1].setText(Integer.toHexString(Integer.parseInt(decNum)));
					label[1][1].setText(Integer.toString(Integer.parseInt(decNum)));
		            label[2][1].setText(Integer.toOctalString(Integer.parseInt(decNum)));
		            label[3][1].setText(DecimalToBinary(Integer.parseInt(decNum)));
					
					
				}
				
				//Performs conversion if DWORD is chosen
				else if(label[4][2].getText() == "QWORD")
				{
					label[4][2].setText("DWORD");
					numSize = 32;
					String numToConvert = DecimalToBinary(Integer.parseInt(input1.getText()));
					String newNum = numToConvert;
					input1.setText(Integer.toString(bin2Dec(newNum)));
					
					String decNum = Integer.toString(bin2Dec(newNum));
					label[0][1].setText(Integer.toHexString(Integer.parseInt(decNum)));
					label[1][1].setText(Integer.toString(Integer.parseInt(decNum)));
		            label[2][1].setText(Integer.toOctalString(Integer.parseInt(decNum)));
		            label[3][1].setText(DecimalToBinary(Integer.parseInt(decNum)));
					
				}
				
				//Performs conversion if WORD is chosen
				else if(label[4][2].getText() == "DWORD")
				{
					label[4][2].setText("WORD");
					numSize = 16;
					String numToConvert = DecimalToBinary(Integer.parseInt(input1.getText()));
					
					//If the current size if greater than 8, performs the conversion, else does nothing
					if(numToConvert.length() > 8)
					{
						String newNum = numToConvert.substring(numToConvert.length()-16);
						input1.setText(Integer.toString(bin2Dec(newNum)));
						
						String decNum = Integer.toString(bin2Dec(newNum));
						label[0][1].setText(Integer.toHexString(Integer.parseInt(decNum)));
						label[1][1].setText(Integer.toString(Integer.parseInt(decNum)));
			            label[2][1].setText(Integer.toOctalString(Integer.parseInt(decNum)));
			            label[3][1].setText(DecimalToBinary(Integer.parseInt(decNum)));
					}

				}
			}
			
			//Executes if the plus operator is pressed
			if (e.getSource() == label[9][5]) 
			{
            	input1.setText("");	//Empties the text area
            	operation = "+";	//Sets value of operation to "+"
            	operatorEntered = true;	//Sets value to true because an operator was pressed
            	
            	//Pushes the previous number to stack if the previous number is not empty
            	if(prevNumber != "")	
            	{
            		pushToNumStack(prevNumber); 
            	}
            	
            	//Calls the operators method to perform the calculation
            	operators(operation);
				
			}
			
			if (e.getSource() == label[8][5]) 
			{
				input1.setText("");	//Empties the text area
            	operation = "-";	//Sets value of operation to "-"
            	operatorEntered = true;	//Sets value to true because an operator was pressed
            	
            	//Pushes the previous number to stack if the previous number is not empty
            	if(prevNumber != "")	
            	{
            		pushToNumStack(prevNumber); 
            	}
            	
            	//Calls the operators method to perform the calculation
            	operators(operation);
			}
			
			if (e.getSource() == label[7][5]) 
			{
				input1.setText("");	//Empties the text area
            	operation = "*";	//Sets value of operation to "*"
            	operatorEntered = true;	//Sets value to true because an operator was pressed
            	
            	//Pushes the previous number to stack if the previous number is not empty
            	if(prevNumber != "")	
            	{
            		pushToNumStack(prevNumber); 
            	}
            	
            	//Calls the operators method to perform the calculation
            	operators(operation);
			}
			
			if (e.getSource() == label[6][5]) 
			{
				input1.setText("");	//Empties the text area
            	operation = "/";	//Sets value of operation to "/"
            	operatorEntered = true;	//Sets value to true because an operator was pressed
            	
            	//Pushes the previous number to stack if the previous number is not empty
            	if(prevNumber != "")	
            	{
            		pushToNumStack(prevNumber); 
            	}
            	
            	//Calls the operators method to perform the calculation
            	operators(operation);
			}
			
			if (e.getSource() == label[6][1]) 
			{
				input1.setText("");	//Empties the text area
            	operation = "%";	//Sets value of operation to "+"
            	operatorEntered = true;	//Sets value to true because an operator was pressed
            	
            	//Pushes the previous number to stack if the previous number is not empty
            	if(prevNumber != "")	
            	{
            		pushToNumStack(prevNumber); 
            	}
            	
            	//Calls the operators method to perform the calculation
            	operators(operation);
			}
			
			//Clears everything in the calculator
			if (e.getSource() == label[6][2]) 
			{
				input1.setText(" ");
				operation=" ";
				
				while (!operators.empty()) 
				{
					numbers.pop();
					operators.pop();
				
				}
				
				
			}
			
			//Clears everything in the calculator
			if (e.getSource() == label[6][4]) 
			{
				input1.setText(" ");
				operation=" ";
				
				while (!operators.empty()) 
				{
					numbers.pop();
					operators.pop();
				
				}
				
				
			}
			
			//If open parens is pressed
			if (e.getSource() == label[10][0]) 
			{
				input1.setText("");
            	operation = "(";
            	operatorEntered = true;
            	if(prevNumber != "")
            	{
            		pushToNumStack(prevNumber);
            	}
            	operators(operation);
			}
			
			if (e.getSource() == label[10][1]) 
			{
				/*result = Double.parseDouble(input1.getText());
            	input1.setText("");*/
            	//operators(operation);
				input1.setText("");
            	operation = ")";
            	operatorEntered = true;
            	if(prevNumber != "")
            	{
            		pushToNumStack(prevNumber);
            	}
            	operators(operation);
			}
			
			//If 0 is pressed
			if (e.getSource() == label[10][3])
			{
				String entered = "0";
				combineNum(entered, operatorEntered);
	
			}
			
			//If toggle is pressed
			if (e.getSource() == label[10][2])
			{
				String currNum = input1.getText();
				input1.setText("-" + currNum);
				
			}
			
			//If 1 is pressed
			if (e.getSource() == label[9][2])
			{
				String entered = "1";	
				combineNum(entered, operatorEntered);	//Calls combineNum method to display the number in text area
				
			}
		
			//If 2 is pressed
			if (e.getSource() == label[9][3])
			{
				String entered = "2";
				combineNum(entered, operatorEntered);
				
			}
			
			//If 3 is pressed
			if (e.getSource() == label[9][4])
			{
				String entered = "3";
				combineNum(entered, operatorEntered);
				
			}
		
			//If 4 is pressed
			if (e.getSource() == label[8][2])
			{
				String entered = "4";
				combineNum(entered, operatorEntered);
				
			}
			
			//If 5 is pressed
			if (e.getSource() == label[8][3])
			{
				String entered = "5";
				//operatorEntered = false;
				combineNum(entered, operatorEntered);
				
			}
			
			//If 6 is pressed
			if (e.getSource() == label[8][4])
			{
				String entered = "6";
			    combineNum(entered, operatorEntered);
				
			}
		
			//If 7 is pressed
			if (e.getSource() == label[7][2])
			{
				String entered = "7";
				combineNum(entered, operatorEntered);
				
			}
			
			//If 8 is pressed
			if (e.getSource() == label[7][3])
			{
				String entered = "8";
				combineNum(entered, operatorEntered);
				
			}
			
			//If 9 is pressed
			if (e.getSource() == label[7][4])
			{
				String entered = "9";
				combineNum(entered, operatorEntered);
				
	         
			}
			
			//If clear button is pressed
			if (e.getSource() == label[6][2])
			{
            	input1.setText("");
            	
            	label[0][1].setText("");
	            label[1][1].setText("");
	            label[2][1].setText("");
	            label[3][1].setText("");
	         	
			}
			
			//If = is pressed
			if (e.getSource() == label[10][5]) 
			{
				//pushes any remaining numbers to stack
				if(prevNumber != "")
            	{
            		pushToNumStack(prevNumber);
            	}
				
				//Pops any remaining operators and numbers from stack
				while (!operators.empty()) 
				{
					//Performs computation if the numbers stack has more than two numbers in it
					if(numbers.size() >= 2)
					{
						//Push the result of computation in the numbers stack
						numbers.push(compute(operators.pop(), numbers.pop(), numbers.pop()));
						
					}
					
					else
					{
						break;
					}
				
				}
				
				//Pop the result from stack
				int result = numbers.pop();
				
				//Display the result in text area
				input1.setText(Integer.toString(result));
				
				//Display the hex, dec, oct and bin conversions
				label[0][1].setText(Integer.toHexString(result));
				label[1][1].setText(Integer.toString(result));
	            label[2][1].setText(Integer.toOctalString(result));
	            label[3][1].setText(DecimalToBinary(result));

			}
			
			//Store the length of binary number;
			binNum = label[3][1].getText().length();
			//}
			
			//If backspace is pressed
			if (e.getSource() == label[6][3])
			{
            	input1.setText("");
            	
            	label[0][1].setText("");
	            label[1][1].setText("");
	            label[2][1].setText("");
	            label[3][1].setText("");
	            
	            while (!operators.empty()) 
				{
					numbers.pop();
					operators.pop();
				
				}
	         	
			}
     	
	}
		//Method displays numbers in the text area and pushes numbers into the stack
		public void combineNum(String myInput, boolean op)
		{
			{
				//Store the number
				String number = input1.getText() + myInput;
				input1.setText("");	
				input1.setText(number);	//Display in text area
				prevNumber = number;	//Set prev number
				
				//Display the hex, dec, oct and bin conversions
				label[0][1].setText(Integer.toHexString(Integer.parseInt(number)));
	            label[1][1].setText(number);
	            label[2][1].setText(Integer.toOctalString(Integer.parseInt(number)));
	            label[3][1].setText(DecimalToBinary(Integer.parseInt(number)));
					
			}
			
			
		}
		
		//Method to push numbers into stack
		public void pushToNumStack(String myInput)
		{
			
			numbers.push(Integer.parseInt(myInput));
			prevNumber = "";
			
		}
		
		//Method to push operators into stack and pop them when according to their precedence
		public void operators(String myInput)
		{
			//Pushes open parens into stack
			if(myInput == "(")
			{
				operators.push(myInput);
			}
			
			//Computes the expression between open parens and closed parens and push the result into the stack
			else if(myInput == ")")
			{
				while(operators.peek() != "(")
				{
					numbers.push(compute(operators.pop(), numbers.pop(), numbers.pop()));
					
				}
				
				//Pop the operator
				operators.pop();
			}
			
			//Computes +, -, *, / and % operations 
			if(myInput == "+" || myInput == "-" || myInput == "/" || myInput == "*") 
			{
				//If operators stack is empty, push the operator into stack
				if(operators.isEmpty() || (Precedence(myInput) > Precedence(operators.peek())))
				{
					operators.push(myInput);
				}
				
				
				else
				{
					//Compute expression based on precedence 
					while (!operators.empty() && (Precedence(myInput) < Precedence(operators.peek()))) 
					{
						//Performs computation if the numbers stack has more than two numbers in it
						if(numbers.size() >= 2)
						{
							//pushes the result into the stack
							numbers.push(compute(operators.pop(), numbers.pop(), numbers.pop()));
							
						}
						
						else
						{
							break;
						}
					
					}
					
					//Push the operator into stack
					operators.push(myInput);
					
				}
				//Set value to false after operation has been performed
				operatorEntered = false;
				
			}

		}
		
		//Function to check the precedence of operators
		public int Precedence(String op1)
		{
			if(op1 == "(" || op1 == ")")
			{
				return 0;
			}
			
			if((op1 == "+" || op1 == "-"))
			{
				return 1;
			}
			
			if((op1 == "*" || op1 == "/"))
			{
				return 2;
			}
			
			return 0;
		}
		
		//Method to compute each of the five operations: +, -, *, / and %
		public static int compute(String op1, int num1, int num2)
		{
			if(op1 == "+")
			{
				return num1+num2;
			}
			
			if(op1 == "-")
			{
				return num2-num1;
			}
			
			if(op1 == "/")
			{
				if(num2 == 0)
				{
					input1.setText("SYNTAX ERROR");
					return 0;
				}
				
				else
				{
					return num2/num1;
				}
				
				
			}
			
			if(op1 == "*")
			{
				return num1*num2;
			}
			
			if(op1 == "%")
			{
				if(num2 == 0)
				{
					input1.setText("SYNTAX ERROR");
				}
				
				else
				{
					return num2%num1;
				}
			}
			
			return 0;
					
			
		}

		//Method to convert from Decimal to hexadecimal number
		public static String DecimalToHex(int num)
		{
			String s = Double.toHexString(num);
			return s;
		}
		
		//Method to convert from Decimal to binary number
		public static String DecimalToBinary(int num)
		{
			if(num == 0)
			{
				String ans = "0";
				return ans;
			}
			
			String binary = "";
		       while (num > 0) {
		           int rem = num % 2;
		           binary = rem + binary;
		           num = num / 2;
		       }
		       
		       while(binary.length() % 4 != 0 )
		       {
		    	   binary = "0" + binary;
		       }
		    
		    return binary;
		}
		
		//Method to convert from Decimal to octal number
		public static String DecimalToOct(int num)
		{
			String s = Integer.toOctalString(num);
			return s;
		}
		
		//Method to convert from binary to decimal number
		public static int bin2Dec(String binaryString) 
		{
			if(binaryString.length() == 0)
			{
				return 0;
			}
			
			else
			{
				String next = binaryString.substring(1);	//Stores the next string to be converted
				String curr = binaryString.substring(0, 1);	//Stores the first letter of string
				int currNum = Integer.parseInt(curr);	//Converts string to int
				
				return currNum * (int)Math.pow(2, binaryString.length()-1) + bin2Dec(next); //Converts to decimal and recursively calls the function to convert the next part of string

			}
			

		}
		
		public void mouseClicked (MouseEvent e) 
		{
			
		}
		public void mouseEntered (MouseEvent e) {
		
		}
		public void mouseExited (MouseEvent e) {
			
		}
		public void mouseReleased (MouseEvent e) {
		
		}

	

}




