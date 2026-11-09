
package org.example;

public class RedBlackTree {

    private final Node NIL;
    private Node root;
    private int size;

    public RedBlackTree() {
        NIL = new Node(0);
        NIL.color = Color.BLACK;
        NIL.left = NIL;
        NIL.right = NIL;
        NIL.parent = NIL;

        root = NIL;
        size = 0;
    }

    // Перевірка, чи дерево порожнє
    public boolean isEmpty() {
        return root == NIL;
    }

    // Повернення кількості елементів
    public int getSize() {
        return size;
    }

    // Додавання нового елемента
    public boolean insert(int data) {
        Node newNode = new Node(data);

        newNode.left = NIL;
        newNode.right = NIL;
        newNode.parent = NIL;

        Node parent = NIL;
        Node current = root;

        while (current != NIL) {
            parent = current;

            if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            } else {
                return false;
            }
        }

        newNode.parent = parent;

        if (parent == NIL) {
            root = newNode;
        } else if (data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        newNode.color = Color.RED;

        fixInsert(newNode);
        size++;

        return true;
    }

    // Балансування після додавання
    private void fixInsert(Node node) {

        while (node.parent.color == Color.RED) {

            if (node.parent == node.parent.parent.left) {

                Node uncle = node.parent.parent.right;

                // Випадок 1: дядько червоний
                if (uncle.color == Color.RED) {

                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;

                    node = node.parent.parent;

                } else {

                    // Випадок 2: трикутна конфігурація
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }

                    // Випадок 3: лінійна конфігурація
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;

                    rightRotate(node.parent.parent);
                }

            } else {

                Node uncle = node.parent.parent.left;

                // Дзеркальний випадок 1
                if (uncle.color == Color.RED) {

                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;

                    node = node.parent.parent;

                } else {

                    // Дзеркальний випадок 2
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }

                    // Дзеркальний випадок 3
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;

                    leftRotate(node.parent.parent);
                }
            }
        }

        root.color = Color.BLACK;
    }

    // Лівий поворот
    private void leftRotate(Node x) {

        Node y = x.right;
        x.right = y.left;

        if (y.left != NIL) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    // Правий поворот
    private void rightRotate(Node y) {

        Node x = y.left;
        y.left = x.right;

        if (x.right != NIL) {
            x.right.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == NIL) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

    // Пошук вузла за значенням
    private Node search(Node node, int data) {

        if (node == NIL || data == node.data) {
            return node;
        }

        if (data < node.data) {
            return search(node.left, data);
        }

        return search(node.right, data);
    }

    // Видалення елемента
    public boolean delete(int data) {

        Node z = search(root, data);

        if (z == NIL) {
            return false;
        }

        Node y = z;
        Node x;

        Color originalColor = y.color;

        if (z.left == NIL) {

            x = z.right;
            transplant(z, z.right);

        } else if (z.right == NIL) {

            x = z.left;
            transplant(z, z.left);

        } else {

            y = minimum(z.right);
            originalColor = y.color;
            x = y.right;

            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);

                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);

            y.left = z.left;
            y.left.parent = y;

            y.color = z.color;
        }

        if (originalColor == Color.BLACK) {
            fixDelete(x);
        }

        size--;

        NIL.parent = NIL;

        return true;
    }

    // Заміна одного піддерева іншим
    private void transplant(Node u, Node v) {

        if (u.parent == NIL) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }

        v.parent = u.parent;
    }

    // Пошук мінімального вузла
    private Node minimum(Node node) {

        while (node.left != NIL) {
            node = node.left;
        }

        return node;
    }

    // Балансування після видалення
    private void fixDelete(Node x) {

        while (x != root && x.color == Color.BLACK) {

            if (x == x.parent.left) {

                Node sibling = x.parent.right;

                // Випадок 1: брат червоний
                if (sibling.color == Color.RED) {

                    sibling.color = Color.BLACK;
                    x.parent.color = Color.RED;

                    leftRotate(x.parent);

                    sibling = x.parent.right;
                }

                // Випадок 2: обидва нащадки брата чорні
                if (sibling.left.color == Color.BLACK &&
                        sibling.right.color == Color.BLACK) {

                    sibling.color = Color.RED;
                    x = x.parent;

                } else {

                    // Випадок 3: правий нащадок чорний
                    if (sibling.right.color == Color.BLACK) {

                        sibling.left.color = Color.BLACK;
                        sibling.color = Color.RED;

                        rightRotate(sibling);

                        sibling = x.parent.right;
                    }

                    // Випадок 4: правий нащадок червоний
                    sibling.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    sibling.right.color = Color.BLACK;

                    leftRotate(x.parent);

                    x = root;
                }

            } else {

                // Дзеркальні випадки
                Node sibling = x.parent.left;

                // Випадок 1
                if (sibling.color == Color.RED) {

                    sibling.color = Color.BLACK;
                    x.parent.color = Color.RED;

                    rightRotate(x.parent);

                    sibling = x.parent.left;
                }

                // Випадок 2
                if (sibling.right.color == Color.BLACK &&
                        sibling.left.color == Color.BLACK) {

                    sibling.color = Color.RED;
                    x = x.parent;

                } else {

                    // Випадок 3
                    if (sibling.left.color == Color.BLACK) {

                        sibling.right.color = Color.BLACK;
                        sibling.color = Color.RED;

                        leftRotate(sibling);

                        sibling = x.parent.left;
                    }

                    // Випадок 4
                    sibling.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    sibling.left.color = Color.BLACK;

                    rightRotate(x.parent);

                    x = root;
                }
            }
        }

        x.color = Color.BLACK;
    }

    // Симетричний обхід дерева (Inorder)
    public void inorderTraversal() {

        if (isEmpty()) {
            System.out.println("Дерево порожнє.");
            return;
        }

        System.out.print("Inorder обхід: ");
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {

        if (node != NIL) {

            inorder(node.left);

            System.out.print(
                    node.data + "(" + node.color + ") "
            );

            inorder(node.right);
        }
    }

    // Візуальне відображення дерева
    public void printTree() {

        if (isEmpty()) {
            System.out.println("Дерево порожнє.");
            return;
        }

        System.out.println("\nСтруктура червоно-чорного дерева:");
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isTail) {

        if (node != NIL) {

            System.out.println(
                    prefix +
                            (isTail ? "└── " : "├── ") +
                            node.data +
                            (node.color == Color.RED ? " [RED]" : " [BLACK]")
            );

            boolean hasLeft = node.left != NIL;
            boolean hasRight = node.right != NIL;

            if (hasLeft || hasRight) {

                if (hasLeft && hasRight) {
                    printTree(
                            node.left,
                            prefix + (isTail ? "    " : "│   "),
                            false
                    );

                    printTree(
                            node.right,
                            prefix + (isTail ? "    " : "│   "),
                            true
                    );

                } else if (hasLeft) {
                    printTree(
                            node.left,
                            prefix + (isTail ? "    " : "│   "),
                            true
                    );

                } else {
                    printTree(
                            node.right,
                            prefix + (isTail ? "    " : "│   "),
                            true
                    );
                }
            }
        }
    }

    // Очищення дерева
    public void clear() {
        root = NIL;
        NIL.parent = NIL;
        size = 0;
    }


    // Автоматична перевірка властивостей червоно-чорного дерева
    public boolean validateTree() {

        System.out.println("\n========== ПЕРЕВІРКА ДЕРЕВА ==========\n");

        // 1. Перевірка кольору кореня
        boolean rootCheck =
                root.color == Color.BLACK &&
                        root.parent == NIL;

        printCheck("Корінь чорний", rootCheck);

        // 2. Перевірка NIL-вузла
        boolean nilCheck =
                NIL.color == Color.BLACK &&
                        NIL.left == NIL &&
                        NIL.right == NIL;

        printCheck("Усі NIL-вузли чорні", nilCheck);

        // 3. Перевірка червоних вузлів
        boolean redCheck = checkRedNodes(root);

        printCheck(
                "Немає двох червоних вузлів поспіль",
                redCheck
        );

        // 4. Перевірка властивості бінарного дерева пошуку
        boolean bstCheck = checkBST(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE
        );

        printCheck(
                "Властивість бінарного дерева пошуку",
                bstCheck
        );

        // 5. Перевірка однакової чорної висоти
        boolean blackHeightCheck =
                checkBlackHeight(root) != -1;

        printCheck(
                "Чорна висота однакова",
                blackHeightCheck
        );

        boolean result =
                rootCheck &&
                        nilCheck &&
                        redCheck &&
                        bstCheck &&
                        blackHeightCheck;

        System.out.println("\n======================================");

        if (result) {
            System.out.println("Результат: ДЕРЕВО КОРЕКТНЕ!");
        } else {
            System.out.println("Результат: ВИЯВЛЕНО ПОМИЛКИ!");
        }

        System.out.println("======================================");

        return result;
    }

    // Виведення результату окремої перевірки
    private void printCheck(String description, boolean result) {

        System.out.println(
                description + ": " + (result ? "OK" : "ERROR")
        );
    }

    // Перевірка, що червоний вузол не має червоних дітей
    private boolean checkRedNodes(Node node) {

        if (node == NIL) {
            return true;
        }

        if (node.color == Color.RED) {

            if (node.left.color == Color.RED ||
                    node.right.color == Color.RED) {

                return false;
            }
        }

        return checkRedNodes(node.left) &&
                checkRedNodes(node.right);
    }

    // Перевірка властивості бінарного дерева пошуку
    private boolean checkBST(
            Node node,
            long min,
            long max
    ) {

        if (node == NIL) {
            return true;
        }

        if (node.data <= min || node.data >= max) {
            return false;
        }

        return checkBST(node.left, min, node.data) &&
                checkBST(node.right, node.data, max);
    }

    // Перевірка чорної висоти
    // Повертає кількість чорних вузлів або -1 при помилці
    private int checkBlackHeight(Node node) {

        if (node == NIL) {
            return 1;
        }

        int leftHeight = checkBlackHeight(node.left);
        int rightHeight = checkBlackHeight(node.right);

        // Перевірка однакової чорної висоти
        if (leftHeight == -1 ||
                rightHeight == -1 ||
                leftHeight != rightHeight) {

            return -1;
        }

        // Чорний вузол збільшує чорну висоту на 1
        return leftHeight +
                (node.color == Color.BLACK ? 1 : 0);
    }

}
