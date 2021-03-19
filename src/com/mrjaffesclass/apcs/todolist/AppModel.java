package com.mrjaffesclass.apcs.todolist;

import java.util.*;
import com.mrjaffesclass.apcs.messenger.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class AppModel implements MessageHandler {
  // Instance variables
  // messenger:   The Messenger object that handles messaging between classes
  // toDoList:    Keeps the list of to do items
  // nextId:      Keeps track of the ID that should be given to the next 
  //              ToDoItem that's added to the list
  private final Messenger messenger;
  private final ArrayList<ToDoItem> toDoList;   
  private int nextId = 0;      
  private int order = DateComparator.ASCENDING;
  
  /**
   * Model constructor: Create the data representation of the program
   * @param _messenger Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public AppModel(Messenger _messenger) {
    messenger = _messenger;
    toDoList = new ArrayList<>();   // We start with an empty list
  }
  
  /**
   * Initialize the model here, subscribe to any required messages and
   * load with some initial to do list items
   * 
   */
  public void init() {
    messenger.subscribe("getItems", this);
    messenger.subscribe("getItem", this);
    messenger.subscribe("saveItem", this);
    messenger.subscribe("deleteItem", this);
    messenger.subscribe("removeCompletedItems", this);
    messenger.subscribe("sortAscending", this);
    messenger.subscribe("sortDescending", this);
  }

  // This method implements the messageHandler method defined in
  // the MessageHandler interface
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    // Handle messages sent from other modules
    ToDoItem item;
    
    // Process the message
    switch (messageName) {
      // View has asked for the list of to do items
      // Send back the items
      case "getItems":
        messenger.notify("items", this.getItems(), true);
        break;
        
      // Somebody has asked for an individual item
      // Find the item and send it back
      case "getItem":
        messenger.notify("item", this.getItem((int)messagePayload), true);
        break;
        
      // Somebody wants us to save an item
      // Grab the to do item from the payload, save or update it to the 
      // to do list, send a confirmation message that it was saved
      // and send the updated to do list to others
      case "saveItem":        
        item = (ToDoItem)messagePayload;                  
        putItem(item);                                    
        messenger.notify("saved", null, true); 
        sortAndGetItems();
        break;
        
      // Somebody wants us to delete an item
      // Grab the to do item from the payload, delete it from
      // to do list, send a confirmation message that it was saved
      // and send the updated to do list to others
      case "deleteItem":
        item = (ToDoItem)messagePayload;                  
        this.deleteItem(item);
        messenger.notify("saved", null, true);
        messenger.notify("items", this.getItems(), true);
        break;
        
      // We've been told to remove all items that have their 'done' flag
      // set.  Do it, then send a confirmation message, then send the
      // updated to do list to others
      case "removeCompletedItems":
        removeCompletedItems();
        messenger.notify("saved");
        messenger.notify("items", this.getItems());
        break;
        
      case "sortAscending":
        order = DateComparator.ASCENDING;
        sortAndGetItems();
        break;

      case "sortDescending":
        order = DateComparator.DESCENDING;
        sortAndGetItems();
        break;
    }
  }
  
  /**
   * Sort items, then retrieve them and send them with a message
   */
  public void sortAndGetItems() {
    sort(order);
    messenger.notify("items", this.getItems());    
  }

  /** 
   * Find item by id number
   * @param id ID of the item to fine
   * @return The item with id that was found in the list, null if the ID wasn't found
   */
  private ToDoItem find(int id) {
    ToDoItem foundItem = null;
    for (ToDoItem item : toDoList) {
      if (item.getId() == id) {
        foundItem = item;
        break;    // Break the for loop when item is found
      }
    }  
    return foundItem;
  }
  
  /**
   * Get an item with the supplied ID
   * @param id ID of the item to return
   * @return The desired item; null if item ID is not found
   */
  public ToDoItem getItem(int id) {
    return find(id);
  }
  
  /**
   * Get the to do list
   * @return The to do list
   */
  public ArrayList getItems() {
    return toDoList;
  }
  
  /** 
   * Add a new to do item to the list
   * @param item To do item to add
   * @return Item added
   */
  public ToDoItem putItem(ToDoItem item) {
    if (item.getId() == -1) {
      // Item is new, add it to the list
      item.setId(nextId);
      toDoList.add(item);
      nextId++;
    } else {
      // Item already exists, modify the fields of the existing item
      ToDoItem storedItem = this.find(item.getId());
      storedItem.merge(item);
    }
    return item;
  }
  
  /**
   * Delete a to do item
   * @param id ID of the item to delete
   * @return True if the item was found and removed
   */
  public boolean deleteItem(int id) {
    ToDoItem item = find(id);
    return toDoList.remove(item);
  }

  /**
   * Delete a to do item
   * @param item ToDoItem to delete
   * @return true if the item was found and removed
   */
  public boolean deleteItem(ToDoItem item) {
    return deleteItem(item.getId());
  }
  
  /**
   * Removes all completed items from the to do list
   */
  public void removeCompletedItems() {
    // Create an empty list
    ArrayList<ToDoItem> newList = new ArrayList<>();
    
    // Loop through the to do list and if isDone is false
    // add it to the new list
    for (ToDoItem item : toDoList) {
      if (!item.isDone()) {
        newList.add(item);
      }
    }
    
    // Clear the to do list and add the items that were not completed
    toDoList.clear();
    for (ToDoItem item : newList) {
      toDoList.add(item);
    }
  }
  
  public void sort(int order) {
    Collections.sort(toDoList, new DateComparator(order));
  }
}
