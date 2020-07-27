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
import javax.swing.*;

public class TestCalc {

	public static void main(String[] args) 
	{	
		//Creates window for the calc
		CalculatorGUI2 window = new CalculatorGUI2();
		window.setTitle("Programmer Calculator");	//Title for calc
		window.setBounds(100, 100, 340, 600);	//Set dimensions of calc
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);	//Make the window visible
		window.setResizable(true);	//Make the window resizable

	}

}

