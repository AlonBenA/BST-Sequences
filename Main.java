package Graph;

public class Main {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(4);
		
		System.out.println("tree with a single node ");
		System.out.println(tree.getAllBTSSequences());
		System.out.println();
		
		
		BinaryTree tree2 = new BinaryTree(4);
		tree2.add(2);
		tree2.add(5);
		
		System.out.println("tree with a 3 nodes that create a perfect tree ");
		System.out.println(tree2.getAllBTSSequences());
		System.out.println();
		
		
		BinaryTree tree3 = new BinaryTree(4);
		tree2.add(2);
		tree2.add(5);
		tree2.add(6);
		
		
		System.out.println("tree with a 4 nodes ");
		System.out.println(tree2.getAllBTSSequences());
		System.out.println();
		

	}

}
