//-----------------------------------------------------------------------------------------------------------------------------------
// Title: Bag class
// Author: Begüm Şara Ünal
// Question: 2
// Assignment: 1
// Description: The Bag class was created to store a collection of items and allow efficient aggregation and iteration across items.
//------------------------------------------------------------------------------------------------------------------------------------
import java.util.Iterator;

    public class Bag<Item> implements Iterable<Item> 
    {
        public BagNode<Item> firstNode; //first node
        public int nodeSize; //size of node

          //--------------------------------------------------------
         // Summary:Constructor of class.
        //--------------------------------------------------------
        public Bag() 
        {
            this.nodeSize = 0;
        }
                                                                    
           //----------------------------------------------------------------------------------------------------
          // Summary: The Add() function provides a linked list data structure to store the items added to it.
         // Precondition: item is a generic type.
        // Postcondition: Values added to the node
       //----------------------------------------------------------------------------------------------------
        public void add(Item item) 
        {  
            if(firstNode == null){
                firstNode = new BagNode<Item>();
                firstNode.item = item;
                firstNode.next = null;
                nodeSize++;
            }
            else{
               
                BagNode<Item> temp = new BagNode<Item>();
                temp.item = item;
                temp.next = firstNode;
                firstNode = temp;
                nodeSize++;
            }
        }

        //--------------------------------------------------------
       // Summary:calls This is for call the BagIterator class.
      //--------------------------------------------------------
        public Iterator<Item> iterator()
        {
            return new BagIterator(firstNode);
        }

        //--------------------------------------------------------
       // Summary: This is for return the size of node
      //--------------------------------------------------------
        public int nodeSize() 
        {                                                                              
            return nodeSize;                                                               
        }              

         //----------------------------------------------------------------------------------------------------
        // Summary: This class returns an iterator object that allows iteration through the items in the Bag.
       //----------------------------------------------------------------------------------------------------
        public class BagIterator implements Iterator<Item> 
        {
            public BagNode<Item> temp;

          //--------------------------------------------------------
         // Summary:Constructor of class.
        //--------------------------------------------------------
            public BagIterator(BagNode<Item> firstNode) 
            {
                temp = firstNode;
            }

              //--------------------------------------------------------
             // Summary:This is for check the next node
            // Precondition: -
           // Postcondition: Returns the checking
          //--------------------------------------------------------
            public boolean hasNext() 
            {
                return temp != null;
            }
    
             //--------------------------------------------------------
            // Summary: This allows to go to the next node
           // Precondition: -
          // Postcondition: Returns item 
         //--------------------------------------------------------
            public Item next() 
            {   
                Item item = temp.item;
                temp = temp.next;
                return item;
            }
        }
    }
    