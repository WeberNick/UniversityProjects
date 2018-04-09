import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchTree 
{
	private BinarySearchTree left, right, parent;
	private int key;
	
	public BinarySearchTree()
	{
		key = 0;
		left = null;
		right = null;
		parent = null;
	}
	
	public BinarySearchTree(BinarySearchTree p)
	{
		this.key = 0;
		this.parent = p;
		this.left = null;
		this.right = null;
	}
	
	public int minRek()
	{
		BinarySearchTree help = this;
		if(help.key != 0)
		{
			if(help.left.key == 0)
			{
				return help.key;
			}
			else return help.left.minRek();
		} else return 0;
	}
	
	public int maxRek()
	{
		BinarySearchTree help = this;
		if(help.key != 0)
		{
			if(help.right.key == 0)
			{
				return help.key;
			}
			else return help.right.maxRek();
		} else return 0;
	}
	
	public int min()
	{
		BinarySearchTree help = this;
		while(help.left.key != 0)
		{
			help = help.left;
		}
		return help.key;
	}
	
	public int max()
	{
		BinarySearchTree help = this;
		while(help.right.key != 0)
		{
			help = help.right;
		}
		return help.key;
	}
	
	public void insert(int key)
	{
		if(this.key == 0)
		{
			this.key = key;
			left = new BinarySearchTree(this);
			right = new BinarySearchTree(this);
		}	else{
			BinarySearchTree next;
			if (key < this.key)
				next = left;
			else
				next = right;
			next.insert(key);
		}
	}
	
	public void insert()
	{
		Scanner sc = new Scanner(System.in);
		int help = 0;
		System.out.println("Bitte Zahlen zum hinzufuegen eingeben, zum beenden -1 eingeben.");
		while(help != -1)
		{
			help = sc.nextInt();
			if(help == -1)
				break;
			this.insert(help);
		}
	}
	
	public void insert(int[] array)
	{
		for (int i = 0; i < array.length; i++) 
		{
			this.insert(array[i]);
		}
	}
	
	public int successor()
	{
		BinarySearchTree help = this;
		BinarySearchTree temp;
		if(help.right.key != 0)
			return help.right.min();
		temp = help.parent;
		while(temp.key != 0 && help.key == temp.right.key)
		{
			help = temp;
			temp = help.parent;
		}
		return temp.key;
	}
	
	public int predecessor()
	{
		BinarySearchTree help = this;
		BinarySearchTree temp;
		if(help.left.key != 0)
			return help.left.max();
		temp = help.parent;
		while(temp.key != 0 && help.key == temp.left.key)
		{
			help = temp;
			temp = help.parent;
		}
		return temp.key;
	}
	
	public void inOrderTreeWalk()
	{
		BinarySearchTree help = this;
		if(help.key != 0)
		{
			help.left.inOrderTreeWalk();
			System.out.println(help.key + " ");
			help.right.inOrderTreeWalk();
		}
	}
	
	public void preOrderTreeWalk()
	{
		BinarySearchTree help = this;
		if(help.key != 0)
		{
			System.out.println(help.key + " ");
			help.left.preOrderTreeWalk();
			help.right.preOrderTreeWalk();
		}
	}
	
	
	public int search(int k)
	{
		BinarySearchTree help = this;
		if(help.key == 0 || k == help.key)
		{
			return help.key;
		}
		if(k < help.key)
		{
			return help.left.search(k);
		}
		else return help.right.search(k);
	}
	
//	public void transplant(BinarySearchTree u, BinarySearchTree v)
//	{
//		if(u.parent == null)
//		{
//			u.left.parent = v;
//			u.left = null;
//			u.right = null;
//			v.parent = null;
//		}
//		else if(u == u.parent.left)
//		{
//			u.parent.left = v;
//		}
//		else
//		{
//			u.parent.right = v;
//		}
//		if(v != null)
//		{
//			v.parent = u.parent;
//		}
//	}
//	
//	public void delete(int z)
//	{
//		if
//	}
	
	
	public String toString()
	{
		if (key == 0)
			return "";
		return left.toString() + key +  " " + right.toString();
	}
	
	public static void main(String[] args) 
	{
		BinarySearchTree tree = new BinarySearchTree();
		int[] array = {10,4,8,6,26,16,18,22,14,12};
		tree.insert(array);
		System.out.println(tree);
		tree.preOrderTreeWalk();
//		tree.inOrderTreeWalk();
//		tree.max();
//		tree.min();
//		tree.maxRek();
//		tree.minRek();
//		tree.toString();
//		System.out.println(tree.search(26));
//		System.out.println(tree);
		
//		
	}
}
