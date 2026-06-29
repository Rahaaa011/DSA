
class Node {
    int data;
    int height;
    Node left, right;

    Node(int data) {
        this.data = data;
        height = 1;
    }
}

class AVLTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

  
  

    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {

        // Normal BST insertion
        if (node == null)
            return new Node(data);

        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);
        else
            return node; // duplicate values not allowed

        // Update height
        node.height = 1 + Math.max(height(node.left),
                                   height(node.right));

        // Get balance factor
        int balance = getBalance(node);

        // LL Case
        if (balance > 1 && data < node.left.data)
            return rightRotate(node);

        // RR Case
        if (balance < -1 && data > node.right.data)
            return leftRotate(node);

        // LR Case
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Case
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    private Node rightRotate(Node y) {

        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {

        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

     
        return y;
    }

 

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {

        if (node == null)
            return false;

        if (key == node.data)
            return true;

        if (key < node.data)
            return search(node.left, key);

        return search(node.right, key);
    }

  

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

   
    public void displayTree() {
        displayTree(root, 0);
    }

    private void displayTree(Node node, int level) {

        if (node == null)
            return;

        displayTree(node.right, level + 1);

        for (int i = 0; i < level; i++)
            System.out.print("    ");

        System.out.println(node.data);

        displayTree(node.left, level + 1);
    }

  

    public int[] toArray() {
        int size = countNodes(root);
        int[] arr = new int[size];
        fillArray(root, arr, new int[]{0});
        return arr;
    }

    private int countNodes(Node node) {
        if (node == null)
            return 0;

        return 1 + countNodes(node.left)
                 + countNodes(node.right);
    }

    private void fillArray(Node node, int[] arr, int[] index) {

        if (node == null)
            return;

        fillArray(node.left, arr, index);
        arr[index[0]++] = node.data;
        fillArray(node.right, arr, index);
    }
}
public class AVL_Test {

    public static void main(String[] args) {

        int passed = 0;
        int totalTests = 10;

        // Test 1: Single insertion
        AVLTree t1 = new AVLTree();
        t1.insert(10);
        if (t1.getRoot() != null && t1.getRoot().data == 10)
            passed++;

        // Test 2: Left rotation (10,20,30)
        AVLTree t2 = new AVLTree();
        t2.insert(10);
        t2.insert(20);
        t2.insert(30);
        if (t2.getRoot() != null && t2.getRoot().data == 20)
            passed++;

        // Test 3: Right rotation (30,20,10)
        AVLTree t3 = new AVLTree();
        t3.insert(30);
        t3.insert(20);
        t3.insert(10);
        if (t3.getRoot() != null && t3.getRoot().data == 20)
            passed++;

        // Test 4: Left-Right rotation
        AVLTree t4 = new AVLTree();
        t4.insert(30);
        t4.insert(10);
        t4.insert(20);
        if (t4.getRoot() != null && t4.getRoot().data == 20)
            passed++;

        // Test 5: Right-Left rotation
        AVLTree t5 = new AVLTree();
        t5.insert(10);
        t5.insert(30);
        t5.insert(20);
        if (t5.getRoot() != null && t5.getRoot().data == 20)
            passed++;

        // Test 6: Search existing
        AVLTree t6 = new AVLTree();
        t6.insert(50);
        t6.insert(30);
        t6.insert(70);
        if (t6.search(70))
            passed++;

        // Test 7: Search non-existing
        if (!t6.search(100))
            passed++;

        // Test 8: In-order should be sorted
        AVLTree t8 = new AVLTree();
        t8.insert(40);
        t8.insert(20);
        t8.insert(60);
        t8.insert(10);
        t8.insert(30);

        int[] expected = {10, 20, 30, 40, 60};
        int[] actual = t8.toArray();

        boolean same = true;
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) {
                same = false;
                break;
            }
        }
        if (same)
            passed++;

        // Test 9: Empty tree search
        AVLTree t9 = new AVLTree();
        if (!t9.search(5))
            passed++;

        // Test 10: Root should not be null after many inserts
        AVLTree t10 = new AVLTree();
        for (int i = 1; i <= 100; i++)
            t10.insert(i);

        if (t10.getRoot() != null)
            passed++;

        System.out.println("\n" + passed + "/" + totalTests + " tests passed.");
    }
}