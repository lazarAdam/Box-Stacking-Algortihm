/**
 * 10/2/2017
 * @author Oussama El aallali	
 * ICS 340, Stein Michael
 * Program B
 * Description: This Program uses the concept of dynamic programming (bottom up approach) to use an input of multiple  boxes with different dimensions and weights
 * and and compute a stack of boxes with heaviest possible weight    
 * Note: This program was adapted from a public sources which was used to implement the dynamic programming concept for the box stacking problem consult the links bellow
 * for references.
 * https://stackoverflow.com/questions/26448352/counting-the-number-of-lines-in-a-text-file-java
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/BoxStacking.java
 * http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/   

 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class BoxStackingAlgoritm {

	// instance variables for the class
	private BoxObject[] boxArray;
	private int numBox;
	private int inputLength;
	private int HeaviestStackValue;

	/**
	 * FileLoader() method uses Swing FileChooser class to prompt the user to
	 * select a text file. the method opens at the same directory where the
	 * program is running from. the method sort the array of boxes the method
	 * passes BoxObject[] boxArray to DynamicAlgorithm().
	 * 
	 * @throws IOException
	 */

	public void ProgramInput() throws IOException {

		/** create a filter that shows only files with .txt extension */

		try{
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text files", "txt");

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileFilter(filter);

		fileChooser.setDialogTitle("Select the input file");

		fileChooser.setCurrentDirectory(new File("."));

		fileChooser.showOpenDialog(fileChooser);

		File inputFile = fileChooser.getSelectedFile();

		Scanner scanner = new Scanner(inputFile);

		// calculate the length of the input file to be used to initialize the
		// arrays in the class
		this.inputLength = (int) Files.lines(inputFile.toPath(),
				Charset.defaultCharset()).count();

		this.boxArray = new BoxObject[this.inputLength];

		while (scanner.hasNextLine()) {

			// create instances of BoxObject
			this.boxArray[this.numBox++] = new BoxObject(scanner.next(),
					scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
					scanner.nextInt());

		}
		// call arrays class to sort the boxArray
		Arrays.sort(boxArray);

		DynamicAlgorithm(boxArray);
		}
		
		catch(NullPointerException e){ System.out.println("No file was selected! "); }
		
		System.exit(0);
		
		}

	

	/**
	 * the following method implements the bottom up approach of dynamic
	 * programming to compute a solution for the box stacking problem
	 * 
	 * @param boxArray
	 * @throws IOException
	 */
	public void DynamicAlgorithm(BoxObject[] boxArray) throws IOException {

		int BoxWeightArray[] = new int[this.inputLength];

		int solution[] = new int[this.inputLength];

		int Maximum = 0;

		int indexOflowestBox = 0;

		for (int i = 0; i < BoxWeightArray.length; i++) {
			// fill BoxWeightArray with weight attributes of each box in the
			// array
			BoxWeightArray[i] = boxArray[i].getWeight();
			// fill solution[] with index of each box by default from 0 to
			// BoxWeightArray.length
			solution[i] = i;

		}

		// calculate the solution using dynamic programming
		for (int i = 1; i < BoxWeightArray.length; i++) {

			for (int j = 0; j < i; j++) {

				if ((boxArray[i].getDepth() <= boxArray[j].getDepth())
						&& (boxArray[i].getWidth() <= boxArray[j].getWidth())
						&& (boxArray[i].getWeight() <= boxArray[j].getWeight())) {

					if (BoxWeightArray[j] + boxArray[i].getWeight() >= BoxWeightArray[i]) {

						BoxWeightArray[i] = BoxWeightArray[j]
								+ boxArray[i].getWeight();

						solution[i] = j;

					}

				}

			}

		}
		// calculate the maximum weight value of the heaviest stack
		for (int i = solution.length - 1; i > 0; i--) {

			if (solution[i] != i) {

				if (Maximum < BoxWeightArray[i]) {

					Maximum = BoxWeightArray[i];
					indexOflowestBox = i;

				}

			}

		}
		this.HeaviestStackValue = Maximum;
		printHeaviestStack(indexOflowestBox, solution, boxArray);

	}

	/**
	 * This method uses the solution array to print the heaviest stack of boxes
	 * starting at the lowest box index in the solution array going all the way
	 * to the top most box in the stack
	 * 
	 * @param indexOflowestBox
	 * @param solution
	 * @param box
	 * @throws IOException
	 */
	public void printHeaviestStack(int indexOflowestBox, int solution[],
			BoxObject[] box) throws IOException {

		String str = " ";

		int k = indexOflowestBox;

		while (k >= 0) {

			if (solution[k] != k) {

				str += box[k].toString() + "\r\n";
				k = solution[k];

				if (solution[k] == k) {

					str += box[k].toString() + "\r\n";

					break;
				}
			}
		}
		programOutput(str);
	}

	/**
	 * this method prints a staring of the box stack to text file
	 * 
	 * @param OutPut
	 * @throws IOException
	 */
	public void programOutput(String OutPut) throws IOException {

		File OutputFile = new File("Data_out.txt");
		FileWriter Out = new FileWriter(OutputFile);

		Out.write(OutPut
				+ "*************************************************************************************");
		Out.write("\r\n The Maximum possible Weight for this Stack of boxes = "
				+ this.HeaviestStackValue);

		Out.close();
		
		System.out.println("The path to the output file: "+OutputFile.getAbsolutePath());
	}

	public static void main(String[] args) throws IOException {

		BoxStackingAlgoritm i = new BoxStackingAlgoritm();

		i.ProgramInput();
		
		

		System.out.println("done!");

	}

}


