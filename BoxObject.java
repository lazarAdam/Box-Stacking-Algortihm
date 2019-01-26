

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



// this class contains instantiate a box object with the attributes listed as instance variables
public class BoxObject  implements Comparable<BoxObject> {
	
private String BoxName;
private int Height ;
private int Width ;
private int Depth ;
private int Weight;

public BoxObject(){
	
}

/**
 * Constructor for the class
 * @param name
 * @param Height
 * @param Width
 * @param Depth
 * @param Weight
 */
public BoxObject(String name,int Height, int Width, int Depth, int Weight ){
	
	this.BoxName= name;
	
	this.Height=Height;
	
	this.Width=Width;
	
	this.Depth= Depth;
	
	this.Weight= Weight;
}
/**
 * Getter methods that return the following attributes 
 * @return BoxName , Height , Depth , Weight
 */
	public String getBoxName() {
		return BoxName;
	}
	public int getHeight() {
		return Height;
	}
	public int getWidth() {
		return Width;
	}
	public int getDepth() {
		return Depth;
	}
	public int getWeight() {
		return Weight;
	}
	
	
	/**
	 * compairTo method used to sort the the boxes in descending order  based on their foot print
	 * 
	 */
public int compareTo(BoxObject obj) {
		
		if(this.Width*this.Depth>= obj.Width*obj.Depth){
			
			return -1;
			
		}
		else {
		return 1;}
}

/**
 * toString method to print the attributes of each box
 */
	public String toString(){
		
		return "Name: "+ this.BoxName+ " Height: "+ this.Height+ " Width: "+this.Width+ " Depth: "+ this.Depth+" Weight: "+ this.Weight+"\r\n";
	}
}