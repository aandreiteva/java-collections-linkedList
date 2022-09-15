package com.endava.internship.collections;

import java.util.*;

public class MyLinkedList implements List<Object> {

    private Node start;
    private Node end;
    private int size = 0;

    // First stage
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (start == null);
    }

    public boolean contains(Object o) {
        return indexOf(o)!=-1;

    }

    public boolean add(Object o) {
        if(this.start==null) {
            this.start = new Node(o,null,null);
            this.end = this.start;
            this.size++;
            return true;
        }
        Node newNode = new Node(o,this.end,null);
        this.end.next = newNode;
        this.end = newNode;
        this.size++;
        return true;
    }

    public boolean remove(Object o) {
        /*int index = indexOf(o);
        if(index == -1) {
            return false;
        }
        Node div = this.start;
        for(int i = 0;i < index; i++) {
            div = div.next;
        }
        if(div == this.start) {
            if(div == this.end) {
                this.start = this.end = null;
            }else {
                Node tmp = this.start;
                this.start = tmp.next;
                this.start.prev = null;
                tmp.next = null;
                tmp = null;
            }
        }
        else if(div == this.end) {
            Node tmp = this.end;
            this.end = tmp.prev;
            this.end.next = null;
            tmp.prev = null;
            tmp = null;
        }else {
            div.prev.next = div.next;
            div.next.prev = div.prev;
            div.prev = null;
            div.next = null;
        }
        this.size--;
        return true;*/
        /*Node node = start;
        for(int i=0; i<size(); i++){
            if(node.equals(o)){
                node.getPrev().next = node.getNext();
                return true;
            }
        }
        return false;*/
        if (Objects.isNull(o)) {
            for (Node startNode = start; startNode != null; startNode = startNode.next) {
                if (startNode.item == null) {
                    unlink(startNode);
                    return true;
                }
            }
        } else {
            for (Node startNode = start; startNode != null; startNode = startNode.next) {
                if (o.equals(startNode.item)) {
                    unlink(startNode);
                    return true;
                }
            }
        }
        return false;
    }

    private Object unlink(Node x) {
        final Object element = x.item;
        final Node next = x.next;
        final Node prev = x.prev;
        // Если есть только один узел, то будет выполняться код перехода prev == null и next == null
        // Если prev == null, это означает, что удален головной узел, который в основном отвечает за обработку ссылок узла x и узла-предшественника
        if (Objects.isNull(prev)) {
            start = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        // Если next пусто, это означает, что удален хвостовой узел, который в основном отвечает за обработку ссылок x и следующего узла
        if (Objects.isNull(next)) {
            end = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    public boolean addAll(Collection c) {
        boolean modified = false;
        for (Object e : c) if (add(e)) modified = true;
        return modified;
    }

    public int indexOf(Object o) {
        int index = 0;
        Node cur = this.start;
        if(o == null) {
            while(cur != null) {
                if(cur.item==null) {
                    return index;
                }
                cur=cur.next;
                index++;
            }
        }else {
            while(cur!=null) {
                if(o.equals(cur.item)) {
                    return index;
                }
                cur=cur.next;
                index++;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
            int index = 0;
        Node node =start;
        for(int i=0; i<size;i++) {
            node=node.next;
            if(node.item == o){
                i += 1;
                index = i + 1;
                return index + 1;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        Node cur = this.start;
        while(cur != null) {
            Node next = cur.next;
            cur.item=null;
            cur.prev=null;
            cur.next=null;
            cur=next;
        }
        this.start = null;
        this.end = null;
        this.size = 0;

    }


    // my methods
    private Node node(int index) {
        Node cur = this.start;
        if (index < (this.size >> 1)) {
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
        } else {
            cur = this.end;
            for (int i = this.size - 1; i > index; i--) {
                cur = cur.prev;
            }
        }
        return cur;
    }

    public void print() {
        Node cur = this.start;
        while (cur != null) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public void addFirst (Object data) {
        Node node = new Node();
        node.item = data;
        node.next = null;
        node.next = start;
        start = node;
        size++;
    }

    public void addLast (Object data) {
        Node node = new Node();
        node.item = data;
        node.next = null;
        node.next = end;
        end = node;
        size++;
    }

    private void checkAddArgument(Object o) {
        if (o == null) throw new IllegalArgumentException("null недопустим");
    }

        // Second stage because we start to play with Indexes
        @Override
    public boolean addAll(int index, Collection c) {
        for (Object o : c) checkAddArgument(o);
        return addAll(c);
    }

    @Override
    public Object get(int index) {
        Node node = null;
        if (!isEmpty() && (index >= 0 && index < size)) {
            node = start;
            for(int i=1; i<=index; i++){
                node = node.next;
            }
        }
        return node;
    }

    @Override
    public Object set(int index, Object element) {
        if(index>=this.size||index<0) {
            return false;
        }
        Node cur = node(index);
        cur.item = element;
        return true;
    }

    @Override
    public void add(int index, Object element) {
        Node node = new Node();
        node.item = element;
        node.next = null;
        if (index == 0) addFirst(element);
        Node node1 = start;
        for (int i = 0; i < index - 1; i++) {
            node1 = node1.next;
        }
        node.next = node1.next;
        node1.next = node;
        size++;
    }


    @Override
    public Object remove(int index) {
        if (index == 0) {
            start = start.next;
        } else {
            Node node1 = start;
            Node node2 = null;
            for (int i = 0; i < index - 1; i++) {
                node1 = node1.next;
            }
            node2 = node1.next;
            node1.next = node2.next;
            System.out.println("Removed node : " + node2.item);
            node1 = null;
        }
        size--;
        return null;

    }

    // Third stage casting/subListing
    @Override
    public Object[] toArray() {
        if(this.size == 0) {
            return null;
        }
        Object[] objects =new Object[this.size];
        Node cur = this.start;
        for(int i = 0;i < this.size; i++) {
            objects[i] = cur.item;
            cur = cur.next;
        }
        return objects;
    }

    @Override
    public Object[] toArray(Object[] a) {
        Node cur = this.start;
        if (a.length < size) {
            for (int i = 0; i < a.length; i++) {
                a[i] = cur.item;
                cur = cur.next;
            }
        }
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {

        Object[] objects =new Object[this.size];
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex)  throw new IndexOutOfBoundsException();

        Object subarray[] = new Object[toIndex - fromIndex];

        for (int i = 0; i < subarray.length; i++) {
            subarray[i] = objects[fromIndex + i];
        }
        List<Object> subList = Arrays.asList((Object) subarray);
        return subList;
    }


    // Well..... Iterators will be a bonus if we want our collection to be able to foreach
    public Iterator<Object> iterator() {
        Node current = start;

        return new Iterator<Object>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public Object next() {
                this.index++;
            //    index++;
                return get(index);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    @Override
    public ListIterator listIterator() {
        Node current;
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        int cursor; //index next elementa
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return null;
    }



    // We can skip this
    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean containsAll(Collection c) {
        return false;
    }

    private class Node{
        Object item;
        private Node prev;
        private Node next;


        private Node(Object item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Object getItem() {
            return item;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }

        public Node() {
            this.next = null;
            this.prev = null;
        }
    }
}
