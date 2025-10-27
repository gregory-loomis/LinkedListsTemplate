package com.loomis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private LinkedList list;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setup() {
        list = new LinkedList();
        StringNode a = new StringNode("Amy");
        StringNode b = new StringNode("Bobby");
        StringNode c = new StringNode("Carrie");
        StringNode d = new StringNode("David");
        a.next = b;
        b.next = c;
        c.next = d;
        list.head = a;

        // Redirect System.out
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Restore normal System.out
        System.setOut(originalOut);
    }

    @Test
    public void testNode(){
        StringNode n = new StringNode("Greg");
        String expected = "Greg";
        String actual = n.data;
        assertEquals(expected, actual);
        assertNull(n.next);
    }

    @Test
    public void testInsertAtBeginning(){
        list.insertAtBeginning("John");
        list.insertAtBeginning("Mary");
        list.insertAtBeginning("Steven");
        String expected = "Steven -> Mary -> John -> Amy -> Bobby -> Carrie -> David";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testInsertAtEnd(){
        list.insertAtEnd("John");
        list.insertAtEnd("Mary");
        list.insertAtEnd("Steven");
        String expected = "Amy -> Bobby -> Carrie -> David -> John -> Mary -> Steven";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testInsertAfter(){
        list.insertAfter("Carrie", "Jennifer");
        String expected = "Amy -> Bobby -> Carrie -> Jennifer -> David";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testInsertAfterNotFound(){
        list.insertAfter("Judy", "Jennifer");
        String output = outContent.toString().trim();
        assertTrue(output.contains("Node with name Judy not found"),
            "Expected message was not printed");
    }

    @Test
    public void testDeleteActual_mid(){
        list.deleteNode("Bobby");
        String expected = "Amy -> Carrie -> David";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteActual_end(){
        list.deleteNode("David");
        String expected = "Amy -> Bobby -> Carrie";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteActual_head(){
        list.deleteNode("Amy");
        String expected = "Bobby -> Carrie -> David";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteNotFound(){
        list.deleteNode("Judy");
        String output = outContent.toString().trim();
        assertTrue(output.contains("node with value Judy not found"),
            "Expected message was not printed");
    }

    @Test
    public void testSearchFound(){
        assertTrue(list.search("Carrie"));
    }

    @Test
    public void testSearchNotFound(){
        assertFalse(list.search("Stephanie"));
    }
}
