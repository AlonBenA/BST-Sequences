package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BinaryTree {
	
private Node root;
	
	public BinaryTree() {
		
	}
	
	public BinaryTree(int value) {
		add(value);
	}
	
	
	public Node getRoot() {
		return root;
	}
	
	public void add(int value) {
	    root = addRecursive(root, value);
	}
	
	
	private Node addRecursive(Node current, int value) {
	    if (current == null) {
	        return new Node(value);
	    }
	 
	    if (value < current.value) {
	        current.left = addRecursive(current.left, value);
	    } else if (value > current.value) {
	        current.right = addRecursive(current.right, value);
	    } else {
	        // value already exists
	        return current;
	    }
	 
	    return current;
	}
	
	public boolean containsNode(int value) {
	    return containsNodeRecursive(root, value);
	}
	
	private boolean containsNodeRecursive(Node current, int value) {
	    if (current == null) {
	        return false;
	    } 
	    if (value == current.value) {
	        return true;
	    }
	    
	    if(value < current.value)
	    	return containsNodeRecursive(current.left, value);
	    else {
	    	return containsNodeRecursive(current.right, value);
	    }
	}
	
	public void delete(int value) {
	    root = deleteRecursive(root, value);
	}
		
	private Node deleteRecursive(Node current, int value) {
	    if (current == null) {
	        return null;
	    }
	 
	    if (value == current.value) {
	        // Node to delete found
	    	if (current.left == null && current.right == null) {
	    	    return null;
	    	}
	    	if (current.right == null) {
	    	    return current.left;
	    	}
	    	 
	    	if (current.left == null) {
	    	    return current.right;
	    	}
	    	
	    	int smallestValue = findSmallestValue(current.right);
	    	current.value = smallestValue;
	    	current.right = deleteRecursive(current.right, smallestValue);
	    	return current;
	    } 
	    if (value < current.value) {
	        current.left = deleteRecursive(current.left, value);
	        return current;
	    }
	    current.right = deleteRecursive(current.right, value);
	    return current;
	}
	
	
	private int findSmallestValue(Node root) {
	    return root.left == null ? root.value : findSmallestValue(root.left);
	}
	
	
	public void traverseInOrder(Node node) {
	    if (node != null) {
	        traverseInOrder(node.left);
	        System.out.print(" " + node.value);
	        traverseInOrder(node.right);
	    }
	}
	
	
	public void traversePreOrder(Node node) {
	    if (node != null) {
	        System.out.print(" " + node.value);
	        traversePreOrder(node.left);
	        traversePreOrder(node.right);
	    }
	}
	
	
	public void traversePostOrder(Node node) {
	    if (node != null) {
	        traversePostOrder(node.left);
	        traversePostOrder(node.right);
	        System.out.print(" " + node.value);
	    }
	}
	
	//print BFS on tree
	public void traverseLevelOrder() {
		   if (root == null) {
		        return;
		    }
		 
		    Queue<Node> nodes = new LinkedList<>();
		    nodes.add(root);
		 
		    while (!nodes.isEmpty()) {
		 
		        Node node = nodes.remove();
		 
		        System.out.print(" " + node.value);
		 
		        if (node.left != null) {
		            nodes.add(node.left);
		        }
		 
		        if (node.right!= null) {
		            nodes.add(node.right);
		        }
		    }
		
	}
	
	//get a list of nodes with BFS On a Tree
	public List<Integer> getTreeValuesByLevelOrder() {
		   if (root == null) {
		        return null;
		    }
		   
		    Queue<Node> nodes = new LinkedList<>();
		    
		    List<Integer> nodesValues = new ArrayList<Integer>();
		    nodes.add(root);
		 
		    while (!nodes.isEmpty()) {
		 
		        Node node = nodes.remove();
		        nodesValues.add(node.value);
		 
		        if (node.left != null) {
		            nodes.add(node.left);
		        }
		 
		        if (node.right!= null) {
		            nodes.add(node.right);
		        }
		    }
		    
		    
		    return nodesValues;
		
	}
	
	
	//BST Sequences: A binary search tree was created by traversing through an array from left to right
		//and inserting each element.
		//Given a binary search tree with distinct elements,
		//print all possible arrays that could have led to this tree.
		public List<List<Integer>> getAllBTSSequences(){
			//get all the options
			List<List<Integer>> lists = BTSSequencesRecursive(getRoot());
			//delete copys
	        Set<List<Integer>> hash_Set = new HashSet<List<Integer>>(lists);
	        //move to list of lists
	        lists = new ArrayList<List<Integer>>(hash_Set);
			
			return lists;
		}
		
		//
		private List<List<Integer>> BTSSequencesRecursive(Node current){
			
			if(current == null)
			{
				return null;
			}
			
			//self
			List<Integer> listSelf = new ArrayList<Integer>();
			listSelf.add(current.value);
			List<List<Integer>> allLeft = null;
			List<List<Integer>> allRight = null;
			
			//get all The options for building a tree to the left
			if(current.left != null) {
				allLeft = BTSSequencesRecursive(current.left);
			}
			//get all The options for building a tree to the right
			if(current.right != null) {
				allRight = BTSSequencesRecursive(current.right);
			}
			
			List<List<Integer>> allTheLists = new ArrayList<List<Integer>>();
			
			if(allLeft == null && allRight == null) {
				allTheLists.add(listSelf);
			}
			else if(allLeft != null && allRight != null) {
				
				//self left right
				for(List<Integer> ll: allLeft) {		
					for(List<Integer> lr: allRight) {
						List<Integer> newList = new ArrayList<Integer>(listSelf);
						newList.addAll(ll);
						newList.addAll(lr);
						allTheLists.add(newList);
					}
				}
				
				//self right left
				for(List<Integer> lr: allRight ) {
					for(List<Integer> ll: allLeft ) {
						List<Integer> newList = new ArrayList<Integer>(listSelf);
						newList.addAll(lr);
						newList.addAll(ll);
						allTheLists.add(newList);
					}
				}
				

				for(List<Integer> lr: allRight ) {
					for(List<Integer> ll: allLeft ) {
						//self mix right left...
						List<Integer> newList = new ArrayList<Integer>(listSelf);
						newList.addAll(mixLists(lr, ll));
						allTheLists.add(newList);
						//self mix left right...
						newList = new ArrayList<Integer>(listSelf);
						newList.addAll(mixLists(ll,lr));
						allTheLists.add(newList);
						
					}
				}
				
			}
			else if(allLeft != null) {
				for(List<Integer> ll: allLeft ) {
					List<Integer> newList = new ArrayList<Integer>(listSelf);
					newList.addAll(ll);
					allTheLists.add(newList);
				}
			}
			else {
				for(List<Integer> lr: allRight ) {
					List<Integer> newList = new ArrayList<Integer>(listSelf);
					newList.addAll(lr);
					allTheLists.add(newList);
				}
			}
			
			return allTheLists;		
		}
		
		//Take 2 lists and create new list that containing a mix of the 2 given lists
		private List<Integer> mixLists(List<Integer> list1, List<Integer> list2){
			List<Integer> newList = new ArrayList<Integer>();
			int i=0;
			int j=0;
			int mix = 1;
			
			while(i < list1.size() && j < list2.size())
			{
				if(mix%2 == 0) {
					newList.add(list1.get(i));
					i++;
				}
				else {
					newList.add(list2.get(j));
					j++;
				}
				
				mix++;
			}
			
			
			while(i < list1.size())
			{

				newList.add(list1.get(i));
				i++;
			}
			
			while(j < list2.size())
			{
					newList.add(list2.get(j));
					j++;
			}
			
			
			return newList;
		}

}
