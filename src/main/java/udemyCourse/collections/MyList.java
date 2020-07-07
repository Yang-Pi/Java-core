package udemyCourse.collections;

public class MyList {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> SinglyLinkedList = new SinglyLinkedList<>();
        for (int i = 0; i < 10; ++i) {
            SinglyLinkedList.add(i * 10);
        }
        System.out.println(SinglyLinkedList.toString());
        System.out.println("To remove: " + SinglyLinkedList.get(5));
        SinglyLinkedList.remove(5);
        System.out.println(SinglyLinkedList.toString());
    }

    public static class SinglyLinkedList <T> {
        private Node head;
        private int size;

        public SinglyLinkedList() {
            this.head = null;
            this.size = 0;
        }

        public int getSize() {
            return size;
        }

        public boolean add(T value) {
            if (head != null) {
                Node tmp = head;
                while (tmp.getNext() != null) {
                    tmp = tmp.getNext();
                }
                tmp.setNext(new Node(value));
            }
            else {
                head = new Node(value);
            }

            ++size;
            return true;
        }

        public T get(int index) {
            if (index == 0) {
                return head.getValue();
            }
            else if (index > 0 && index < size) {
                Node tmpNode = head.next;
                int currentIndex = 0;
                while (++currentIndex != index) {
                    tmpNode = tmpNode.getNext();
                }
                return tmpNode.getValue();
            }

            throw new IllegalArgumentException();
        }

        public boolean remove(int index) {
            if (index == 0) {
                head = head.next;
                --size;
                return true;
            }
            else if (index > 0 && index < size) {
                Node tmpNode = head;
                int currentIndex = 0;
                while (++currentIndex != index) {
                    tmpNode = tmpNode.getNext();
                }
                tmpNode.setNext(tmpNode.getNext().getNext());

                --size;
                return true;
            }

            throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            String res = "";
            Node tmp = head;

            while (tmp != null) {
                res += "[" + tmp.getValue() + "] ";
                tmp = tmp.getNext();
            }

            return res;
        }

        private class Node {
            private T value;
            private Node next;

            public Node(T value, Node next) {
                this.value = value;
                this.next = null;
            }

            public Node(T value) {
                this.value = value;
                next = null;
            }

            public T getValue() {
                return value;
            }

            public void setValue(T value) {
                this.value = value;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }
        }
    }

}
