package DesignPatterns;

/*Observer pattern has a concept of a set of observers that are subscribed to
an event or change in state of a subject. Thus when ever a change is made to the
state of a subject, correspondingly all the observers are made aware of this change.*/

import java.util.ArrayList;

// All observers must implement this interface
interface Observer  
{
	public void update(float fee);
}
/* all subjects must implements this interface 
   registerObserver method is used to subscribe an observer to this subject
   removeObserver method removes an observer from subscription list
   notifyObservers method is used to alert all observers of any changes in subject state*/
interface Subject  
{
	public void registerObserver(Observer observer); 

	public void removeObserver(Observer observer);  

	public void notifyObservers(); 
}

class TuitionFee implements Subject 
{
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private float tuitionAmount;

	public TuitionFee(float tuitionAmount)  {
		this.tuitionAmount = tuitionAmount;
	}

	public float getTuitionAmount() {
		return tuitionAmount;
	}

	public void setTuitionAmount(float tuitionAmount) {
		this.tuitionAmount = tuitionAmount;
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() 
	{
		for (Observer observer : observers) {
			System.out.println("Notifying Students of Fee change!!");
			observer.update(this.tuitionAmount);
		}
	}
}

class Students implements Observer {
	@Override
	public void update(float tuitionAmount) {
		System.out.println("Students have been made aware of Fees change, new Fee: "+ tuitionAmount);
	}
}

class AccountsEmployees implements Observer 
{
	@Override
	public void update(float tuitionAmount) {
		System.out.println("Employees in accounts section have been made aware of Fees change, new Fee: "+ tuitionAmount);
	}
	
}

public class ObserverPattern {

	public static void main(String args[]) 
	{
		// create a subject
		TuitionFee tuition = new TuitionFee(12000.50f);
		// create two observers
		Students student = new Students();
		AccountsEmployees employee = new AccountsEmployees();
		// register the observers
		tuition.registerObserver(student);
		tuition.registerObserver(employee);
		// all observers are notified of the fee change
		tuition.setTuitionAmount(12500.50f); 
	}
}