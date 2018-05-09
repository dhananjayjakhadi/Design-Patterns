## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=INPUT_FILE_PATH -Darg1=DELETE_FILE_PATH -Darg2= OUTPUT1_FILE_PATH -Darg3= OUTPUT2_FILE_PATH -Darg4= OUTPUT3_FILE_PATH
 
-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile src/build.xml tarzip or tar -zcvf dhananjay_jakhadi_assign_3.tar.gz dhananjay_jakhadi_assign_3

-----------------------------------------------------------------------
Provide list of citations (urls, etc.) from where you have taken code
(if any).


http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/


-------------------------------------------------------------------------------------------

Observer pattern implementation
The tree builder is developed such that when program create a node it clone it into two observer nodes which are written into respective output files
 Backup Nodes are instances of the same Node class. As node_orig is the subject it stores the references of backup_Node_1 and backup_Node_2 in a data structure.

--------------------------------------------------------------------------------------------
Data structure

Implemented Observer Pattern in Binary Search Tree. 


--------------------------------------------------------------------------
Why use Binary Search Tree?

If we implement a balanced binary search tree, we can always keep the cost of insert(), delete()
to O(logN) where N is the number of nodes in the tree - so the benefit really is that lookups can be 
done in logarithmic time which matters a lot when N is large.