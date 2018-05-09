package studentCoursesBackup.myTree;

import java.util.*;

import studentCoursesBackup.util.Results;

public class Node implements SubjectI,ObserverI{

	int bNumber;
	List<String> courses;
	Node left;
	Node right;
	ArrayList<ObserverI> observers;
	Results result;
	String printDetails="";

	@SuppressWarnings("rawtypes")
	public Node(int bNumber) {
		this.bNumber = bNumber;
		courses = new ArrayList();
		left=null;
		right=null;
		observers = new ArrayList<>();
	}

	public Results getResult() {
		return result;
	}

	public void setResult(Results result) {
		this.result = result;
	}

	public ArrayList<ObserverI> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<ObserverI> observers) {
		this.observers = observers;
	}

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}

	public List getCourses() {
		return courses;
	}

	public void setCourses(List courses) {
		this.courses = courses;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node [bNumber=" + bNumber + ", " + (courses != null ? "courses=" + courses + ", " : "")
				+ (left != null ? "left=" + left + ", " : "") + (right != null ? "right=" + right : "") + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(String insertOrDelete,String course) {

		for(ObserverI o : (this).getObservers()) {
			if("insert".equals(insertOrDelete)) {
				Node backupNode1 = (Node)o;
				backupNode1.getCourses().add(course);
			}
			else if("delete".equals(insertOrDelete)) {
				Node backupNode1 = (Node)o;
				backupNode1.getCourses().remove(course);
			}
		}
	}

	@Override
	public void register(ObserverI observer) {
		observers.add(observer);
	}

	@Override
	public void unregister() {
		for(ObserverI o : (this).getObservers()) {
			observers.remove(o);
		}
	}

	@Override
	public void notifyObserver(String insertOrDelete,String course) {
		update(insertOrDelete,course);
	}

	public void print(Results result) {
		String printDetails="";
		printDetails += "BNumber : "+bNumber+"\n"+" Courses : ";
		for(Object O:courses) {
			printDetails+=(String)O+" ";
		}
		printDetails+="\n";
		result.writeToFile(printDetails);
	}
}
