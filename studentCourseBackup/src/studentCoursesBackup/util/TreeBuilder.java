package studentCoursesBackup.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import studentCoursesBackup.myTree.*;
public class TreeBuilder{

	public Node root=null;
	public Node backUpNode1 = null;
	public Node backUpNode2 = null;

	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}

	@SuppressWarnings("rawtypes")
	public void updateListCourses(List listCourses, Node current,String course) {
		listCourses.remove(course);
		current.setCourses(listCourses);
		current.notifyObserver("delete", course);
	}

	public boolean delete(int bNumber, String course){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;

		while(current.getbNumber()!=bNumber){
			parent = current;
			if(current.getbNumber()>bNumber){
				isLeftChild = true;
				current = current.getLeft();
			}else{
				isLeftChild = false;
				current = current.getRight();
			}
			if(current ==null){
				return false;
			}
		}

		/*Start Code for GetCourses and Delete node accordingly*/

		List listCourses = current.getCourses();
		boolean isListCoursesSizeOne = listCourses.size() == 1 ? true : false;

		/*End Code for GetCourses and Delete node accordingly*/

		}