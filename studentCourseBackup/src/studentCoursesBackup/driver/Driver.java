package studentCoursesBackup.driver;

import studentCoursesBackup.util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import studentCoursesBackup.util.*;
import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.ObserverI;
import studentCoursesBackup.util.*;
class Driver{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String args[]) throws IOException {
				if(args.length != 5) {
			System.out.println("Number of arguments are not valid");
			System.exit(0);
		}
		FileProcessor fp = new FileProcessor();
		fp.readingNodesFromFile(args[0]);
		fp.deletingNodesFromFile(args[1]);
		Results resultOriginalNode = new Results(args[2]);
		Results resultBackUpNode1 = new Results(args[3]);
		Results resultBackUpNode2 = new Results(args[4]);

		TreeBuilder tb = fp.getTb();
		System.out.println("---------------------------Original Tree-------------------------------------------");
		tb.printNodes(resultOriginalNode, tb.getRoot());
		System.out.println("---------------------------Cloned Tree 1-------------------------------------------");
			tb.printNodes(resultBackUpNode1, tb.getRoot());
			System.out.println("----------------------------Cloned Tree 2------------------------------------------");
			tb.printNodes(resultBackUpNode2, tb.getRoot());
	}
}
