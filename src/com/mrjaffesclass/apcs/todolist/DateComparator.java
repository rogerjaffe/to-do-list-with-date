package com.mrjaffesclass.apcs.todolist;
import java.util.*;

/**
 * Date comparator to order the to do items by date.
 * @author Roger Jaffe
 * @version 1.0
 */
public class DateComparator implements Comparator<ToDoItem> {

  static int ASCENDING = 1;
  static int DESCENDING = -1;
  
  int order;
  
  public DateComparator(int direction) {
    order = direction;
  }
  
  @Override
  public int compare(ToDoItem object1, ToDoItem object2) {
    return (order == DateComparator.ASCENDING) ? object1.getDate().compareTo(object2.getDate()) : 
      object2.getDate().compareTo(object1.getDate());
  }

}
