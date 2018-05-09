package studentCoursesBackup.myTree;

public interface SubjectI {
	public void register(ObserverI o);
	public void unregister();
	public void notifyObserver(String insertOrDelete, String course);	
}
