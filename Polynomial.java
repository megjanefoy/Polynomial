import java.util.LinkedList;
import java.util.ListIterator;

/** 
 * Title: Assignment 9 
 * Semester: COP3337 - Fall 2019
 * @author Megan Jane Thompson
 *
 * I affirm that this program is entirely my own work
 * and none of it is the work of any other person.
 * 
 * This program stores a polynomial as a linked list
 * of terms. 
 */

/**
 * A class to represent a polynomial.
 */
public class Polynomial{
   private LinkedList<Term> polynomial;
   
   /**
    * Constructs an empty polynomial
    */
   public Polynomial(){
	   polynomial = new LinkedList<>();   //creates LinkedList
   }

   
   /**
    * Constructs a new polynomial with the given term
    * @param t the term to initialize the polynomial with
    */
   public Polynomial(Term t){
	   polynomial = new LinkedList<>();   //creates LinkedList
	   polynomial.addFirst(t);            //add first term to Linked List
   }

    
   /**
    * Adds the polynomial such that the terms are in sorted order
    * from highest power to lowest
    * @param p the polynomial to add
   */
   public void add(Polynomial p){
	   
	   ListIterator<Term> pIterator = p.polynomial.listIterator();
	   while(pIterator.hasNext()) {
		   Term theTerm = pIterator.next();   //get next term in p linkedList
		   this.polynomial.add(theTerm);      //adds p term to this.polynomial
	   }
	   
	   
	   ListIterator<Term> iterator = this.polynomial.listIterator();
	   while(iterator.hasNext()) {
		   Term firstTerm = iterator.next();        //gets first term of this.polynomial
		   if (iterator.hasNext()) {
			   Term secondTerm = iterator.next();   //gets second term of this.polynomial
			   //compares the exponents of each polynomial
			   if (firstTerm.getPower() < secondTerm.getPower()) {  
				   iterator.previous();
				   iterator.previous();             //pointer is in front of firstTerm again
				   iterator.remove();               //removes firstTerm
				   iterator.next();                 //pointer is after secondTerm
				   iterator.add(firstTerm);         //moves firstTerm after secondTerm
				   iterator.previous();
				   iterator.previous();             //pointer is in front of secondTerm
				   if (iterator.hasPrevious()) {
					   iterator.previous();         //moves backward again if able
				   }
			   }
			   else {
				   iterator.previous();
			   }
		   }
	   }
   }


   
   /**
    * Multiplies the given polynomial with this one and returns the result
    * @param p the polynomial to multiply
    * @return this * p
   */
   public Polynomial multiply(Polynomial p){
	   Polynomial result = new Polynomial();
       ListIterator<Term> iterator = this.polynomial.listIterator();
       ListIterator<Term> pIterator = p.polynomial.listIterator();
       Term thisTerm;
       Term pTerm;

       while (iterator.hasNext()){
    	   thisTerm = iterator.next();           //gets next term of this.polynomial
           while (pIterator.hasNext()){
        	   pTerm = pIterator.next();         //gets next term of p.polynomial
               Term multiTerm = pTerm.multiply(thisTerm);  //multiplies the terms 
               Term theTerm = new Term(multiTerm.getCoefficient(), multiTerm.getPower());
               result.polynomial.add(theTerm);    //adds multiplied term to result.polynomial
           }
       }
       
       return result;                            //returns the new Polynomial
   }

   
   /**
    * Prints the polynomial "nicely" so that it reads
    * from highest term to lowest and doesn't have a
    * leading "+" if the first term is positive.
   */
   public void print(){
	   
	   for (Term currentTerm : polynomial) {
		   if (currentTerm.equals(polynomial.get(0))) {           //if it is the first term:
			   if (currentTerm.getCoefficient() < 0) {
				   System.out.print("- " + currentTerm.toString());   //print out first term if negative
			   }
			   else{
				   System.out.print(currentTerm.toString());          //print out first term if positive
			   }
		   }
		   else {
			   if (currentTerm.getCoefficient() < 0) {           //if after first term:
				   System.out.print(" - " + currentTerm.toString());   //prints out next term if positive
			   }
			   else{
				   System.out.print(" + " + currentTerm.toString());   //prints out next term if negative
			   }
		   }
	   }
   }
}
