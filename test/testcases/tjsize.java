/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcases;

import interfaces.home;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASUS
 */
public class tjsize {
    
    public tjsize() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
      String str = "public class FibonacciMain {\n" +
"public static long fibonacci( long number ) {\n" +
"if (( number == 0) || ( number == 1)) {\n" +
"return number ;\n" +
"}\n" +
"\n" +
"else {\n" +
"return fibonacci( number - 1) + fibonacci( number - 2);\n" +
"\n" +
"\n" +
"}\n" +
"\n" +
"public static void main(String args[]) {\n" +
"\n" +
"for ( int count = 0; count <= 10; count ++){\n" +
"System.out.println(\"Fibonacci of \" + count + \" is \" + fibonacci( count ));\n" +
"\n" +
"\n" +
"FibonacciMain f =  new  FibonacciMain()\n" +
"f .fibonacci(  )\n" +
"\n" +
"\n" +
"}\n" +
"}\n" +
"}";
      
      home h = new home();
      int expectResult = 57;
      int actualResult = h.caljava(str);
         assertEquals(expectResult, actualResult);
     }
     
     
     
}
