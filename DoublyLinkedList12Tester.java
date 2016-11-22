package hw2;

/*
 * Name: <Ashwin Ganesh>
 * ID: <A10210060>
 * Login: <asganesh@ucsd.edu>
 */


import org.junit.*;
import static org.junit.Assert.*;
import java.util.ListIterator;


/**
 *
 * JUnit test class for DoublyLinkedList12 class
 * @since 2015-10-08
 * @version 1.0
 * @author Ashwin Ganesh
 */

public class DoublyLinkedList12Tester<E>
{
  private DoublyLinkedList12<Double>  sizeZero ;
  private DoublyLinkedList12<String>  sizeOne ;
  private DoublyLinkedList12<Double>  sizeTwo ;


  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and
   * a list with several entries (2,4,6)
   */
  @Before
  public void setUp()
  {
    sizeZero = new DoublyLinkedList12();
    sizeOne = new DoublyLinkedList12();
    sizeOne.add(0,"1");  //0 is an index, 1 is an element to add.
    sizeTwo = new DoublyLinkedList12() ;
    sizeTwo.add(0, 2.0);
    sizeTwo.add(1, 4.0);
    sizeTwo.add(2, 6.0);

  }
  /**
   * Test if heads of the lists are correct
   */
  @Test
  public void testGetHead()
  {
    assertEquals("Check 0", "1", sizeOne.get(0)) ;
    assertEquals("Check 0", 2.0, (E)sizeTwo.get(0)) ;
  }

  /**
   * Test if size of lists are correct
   */

  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size", 0, sizeZero.size());
    assertEquals("Check One Size", 1, sizeOne.size());
  }


  /**
   * Test isEmpty
   */
  @Test
  public void testEmpty()
  {
    assertTrue("sizeZero is empty",sizeZero.isEmpty()) ;
    assertTrue("sizeOne is not empty",!sizeOne.isEmpty()) ;
    assertTrue("sizeSeveral is not empty",!sizeTwo.isEmpty()) ;
  }

  /**
   * Test out of bounds exception on get
   */
  @Test
  public void testGetException()
  {
    try
    {
      sizeZero.get(0);
      fail("Should have generated an exception");
    }
    catch(IndexOutOfBoundsException e)
    {
      System.out.println(e.getMessage());
    }
    catch(NullPointerException e){
    	System.out.println(e.getMessage());
    }
  }


  /**
   * Test iterator on empty list
   */
  @Test
  public void testIterator()
  {
    int counter = 0 ;
    ListIterator<Double> iter;
    for (iter = sizeZero.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = sizeTwo.listIterator() ; iter.hasNext(); iter.next())
      counter++;

  }
  /**
   * tests add in iterator to make sure it is functional
   */
  @Test
  public void testAddIterator(){

	  ListIterator a = sizeZero.listIterator();
	  a.add(3.0);
	  ListIterator b = sizeOne.listIterator();
	  b.add("5");

	  assertEquals("Check add", 3.0, (E)sizeZero.get(0));
	  assertEquals("Check add", "5", sizeOne.get(0));
  }

  /**
   * tests hasNext method
   */
  @Test
  public void testHasNext(){
	  ListIterator c = sizeZero.listIterator();
	  ListIterator d = sizeTwo.listIterator();

	  assertFalse("Check next", c.hasNext());
	  assertTrue("Check next", d.hasNext());

  }
  /**
   * tests hasPrevious method
   */
  @Test
  public void testHasPrevious(){
	  ListIterator e = sizeZero.listIterator();
	  ListIterator f = sizeOne.listIterator();
	  f.next();

	  assertFalse("Check has previous", e.hasPrevious());
	  assertTrue("Check has previous", f.hasPrevious());
  }

  /**
   * tests nextIndex method
   */
  @Test
  public void testNextIndex(){
	  ListIterator h = sizeTwo.listIterator();

	  assertEquals("Check next index", 1, h.nextIndex());
	  h.next();
	  assertEquals("Check next index", 2, h.nextIndex());
  }

  /**
   * tests next method
   */
  @Test
  public void testNext(){
	  ListIterator g = sizeTwo.listIterator();
	  g.next();
	  assertEquals("Check next", 2, g.nextIndex());
	  assertEquals("Check next", 6.0, (E)sizeTwo.get(2));
  }

  /**
   * tests previous method
   */
  @Test
  public void testPrevious(){
	  ListIterator i = sizeTwo.listIterator();
	  i.next();
	  i.next();
	  i.previous();
	  assertEquals("Check previous",2 ,i.nextIndex());
	  assertEquals("Check previous", 0, i.previousIndex());
  }

  /**
   * tests previousIndex method
   */
  @Test
  public void testPreviousIndex(){
	  ListIterator h = sizeTwo.listIterator();
	  h.next();

	  assertEquals("Check next index", 0, h.previousIndex());
  }

  /**
   * tests remove method in iterator
   */
  @Test
  public void testRemoveIterator(){
	  ListIterator j = sizeTwo.listIterator();
	  ListIterator z = sizeOne.listIterator();
	  j.next();
	  j.remove();
	  z.next();
	  z.remove();
	  assertEquals("Check remove", 4.0 , (E)sizeTwo.get(0));
	  assertTrue("Check remove", sizeOne.isEmpty());
  }

  /**
   * tests add method in DoublyLinkedList
   */
  @Test
  public void testAdd(){
	  sizeTwo.add(3.0);
	  assertEquals("Check add", 3.0, (E)sizeTwo.get(3));
  }
  /**
   * tests add method in DoublyLinkedList at certain index
   */
  @Test
  public void testAdd2(){
	  sizeTwo.add(3, 2.0);

	  assertEquals("Check add", 2.0, (E)sizeTwo.get(3));
  }
  /**
   * tests set method to make sure element value is set
   */
  @Test
  public void testSet(){
	  sizeTwo.set(1, 22.0);

	  assertEquals("Check set", 22.0, (E)sizeTwo.get(1));
  }

  /**
   * tests remove to make sure element is removed
   */

@Test
  public void testRemove(){
	  sizeTwo.remove(1);

	  assertEquals("Check remove", 6.0, (E)sizeTwo.get(1));
  }

  /**
   * tests clear method to see if list is empty
   */
  @Test
  public void testClear(){
	  sizeTwo.clear();
	  sizeTwo.add(1.0);

	  assertEquals("Check clear", 1.0, (E)sizeTwo.get(0));
  }

  /**
   * tests if list is empty
   */
  @Test
  public void testIsEmpty(){
	  sizeTwo.clear();

	  assertTrue("Check empty", sizeTwo.isEmpty());
  }
}
