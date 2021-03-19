package com.mrjaffesclass.apcs.todolist;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the ToDoItem class
 * 
 * @author Roger Jaffe
 * @version 1.0
 */
public class ToDoItemTest {
    
  public ToDoItemTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of first constructor
   */
  @Test
  public void testToDoItem1() {
    ToDoItem item = new ToDoItem(-1, "Test description");
    assertEquals("getDescription", "Test description", item.getDescription());
    assertEquals("getId", -1, item.getId());
    assertFalse("getDone", item.isDone());
  }

  /**
   * Test of second constructor
   */
  @Test
  public void testToDoItem2() {
    ToDoItem item = new ToDoItem(-1, "Test description 2", true);
    assertEquals("getDescription", "Test description 2", item.getDescription());
    assertEquals("getId", -1, item.getId());
    assertTrue("getDone", item.isDone());
  }

  /**
   * Test of third constructor
   */
  @Test
  public void testToDoItem3() {
    Date date = new Date();
    ToDoItem item = new ToDoItem(-1, "Test description 3", date);
    assertEquals("getDescription", "Test description 3", item.getDescription());
    assertEquals("getId", -1, item.getId());
    assertEquals("date", date, item.getDate());
  }

  /**
   * Test of fourth constructor
   */
  @Test
  public void testToDoItem4() {
    Date date = new Date();
    ToDoItem item = new ToDoItem(-1, "Test description 4", date, true);
    assertEquals("getDescription", "Test description 4", item.getDescription());
    assertEquals("getId", -1, item.getId());
    assertEquals("date", date, item.getDate());
    assertTrue("getDone", item.isDone());
  }

  /**
   * Test of getId method, of class ToDoItem.
   */
  @Test
  public void testGetId() {
    ToDoItem item = new ToDoItem(22, "Test description");
    assertEquals("getId", 22, item.getId());
  }

  /**
   * Test of getDescription method, of class ToDoItem.
   */
  @Test
  public void testGetDescription() {
    ToDoItem item = new ToDoItem(2, "Test description");
    assertEquals("getDescription", "Test description", item.getDescription());
  }

  /**
   * Test of setDescription method, of class ToDoItem.
   */
  @Test
  public void testSetDescription() {
    ToDoItem item = new ToDoItem(2, "Test description");
    item.setDescription("A new description");
    assertEquals("setDescription", "A new description", item.getDescription());
  }

  /**
   * Test of setDone method, of class ToDoItem.
   */
  @Test
  public void testIsDone() {
    ToDoItem item = new ToDoItem(2, "Test description");
    assertFalse("isDone", item.isDone());
  }
  
  /**
   * Test of isDone method, of class ToDoItem.
   */
  @Test
  public void testSetDone() {
    ToDoItem item = new ToDoItem(2, "Test description");
    item.setDone(true);
    assertTrue("setDone set done to true", item.isDone());
    item.setDone(false);
    assertFalse("setDone set done to false", item.isDone());
  }

  /**
   * Test of toggleDone method
   */
  @Test
  public void testToggleDone() {
    ToDoItem item = new ToDoItem(2, "Test description", true);
    item.toggleDone();
    assertFalse("setDone set done to false", item.isDone());
    item.toggleDone();
    assertTrue("setDone set done to true", item.isDone());
  }

}
