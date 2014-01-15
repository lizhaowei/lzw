package utils.struct.tree;

import java.util.Stack;

/**
 * binary tree(������)��ʵ��
 * 
 * @author ����ΰ Create: 3:16:51 PM Jun 29, 2009
 */
public class BinaryTree {
	private Node root;

	public BinaryTree(){
		root = null;
	}

	public Node find(int key){
		Node current = root;
		while (current.iData != key) {
			if (key < current.iData)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null)
				return null;
		}
		return current;
	}

	public void insert(int id, double dd){
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;
		if (root == null) {
			root = newNode;
		} else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (id < current.iData) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) { // insert on right
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public boolean delete(int key){
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;

		while (current.iData != key) {
			parent = current;
			if (key < current.iData) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null)
				return false;
		}

		if (current.leftChild == null && current.rightChild == null) {// Ҫɾ���Ľڵ�û�������ӽڵ�
			if (current == root) // Ҫɾ���Ľڵ���root
				root = null; // �������
			else if (isLeftChild)
				parent.leftChild = null; // Ҫɾ���Ľڵ�����ڵ�
			else
				parent.rightChild = null; // Ҫɾ���Ľڵ����ҽڵ�
		} else if (current.rightChild == null) { // Ҫɾ���Ľڵ�û���ҽڵ�
			if (current == root)
				root = current.leftChild;
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;
		} else if (current.leftChild == null) { // Ҫɾ���Ľڵ�û����ڵ�
			if (current == root)
				root = current.rightChild;
			else if (isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		} else { // Ҫɾ���Ľڵ��������ӽڵ�
			Node successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;

			// ��̽ڵ����Ҫɾ���Ľڵ��λ�ã�Ҫ�̳�ɾ���ڵ���ӽڵ�
			successor.leftChild = current.leftChild;
		}
		return true;
	}

	/*
	 * ��ѯ�����̽ڵ㣬���ڴ��汻ɾ���Ľڵ�
	 */
	private Node getSuccessor(Node delNode){
		Node successorParent = delNode; // ��̽ڵ㸸�ڵ�
		Node successor = delNode; // ��̽ڵ�
		Node current = delNode.rightChild;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		// ��̽ڵ�϶�û�����ӽڵ�
		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	public void traverse(int traverseType){
		switch (traverseType) {
			case 1:
				System.out.print("\nPreorder traversal: ");
				preOrder(root);
				break;
			case 2:
				System.out.print("\nInorder traversal:  ");
				inOrder(root);
				break;
			case 3:
				System.out.print("\nPostorder traversal: ");
				postOrder(root);
				break;
		}
		System.out.println();
	}

	private void preOrder(Node localRoot){
		if (localRoot != null) {
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	private void inOrder(Node localRoot){
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);
		}
	}

	private void postOrder(Node localRoot){
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}

	public void displayTree(){
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................");
		while (isRowEmpty == false) {
			Stack localStack = new Stack();
			isRowEmpty = true;

			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');

			while (globalStack.isEmpty() == false) {
				Node temp = (Node)globalStack.pop();
				if (temp != null) {
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		} // end while isRowEmpty is false
		System.out.println("......................................................");
	} // end displayTree()

} // end class Tree
