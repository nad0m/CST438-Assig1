package com.csumb.cst438.a1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of Game class
 * @author david wisneski
 * @veraion 1.0
 */
public class GameTest {
    
    public GameTest() {
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
     * Test of getState method, of class Game.
     * at start of game, state should be 1.
     * a correct guess will not change the state
     * an incorrect guess will increase state by 1
     */
    @org.junit.Test
    public void testGetState() {
        System.out.println("getState");
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int j = 0;
        Game instance = new Game();
        int expResult = 1;
        int result = instance.getState();
        assertEquals(expResult, result);
        
        String word = instance.getWord();
        
        while (word.indexOf(alphabet.charAt(j)) > -1)
        {
            j++;
        }
        // First guess: if incorrect, increase expResult by 1, else do nothing
        int returnValue = instance.playGame(word.charAt(j)); // guess letter not in word     
        if (returnValue > 1 && returnValue < 4){
            expResult++;
        }
      
        // Second guess: if incorrect, increase expResult by 1, else do nothing
        returnValue = instance.playGame(word.charAt(0)); // guess first letter of word    
        if (returnValue > 1 && returnValue < 4){
            expResult++;
        }
        
        result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWord method, of class Game.
     */
    @org.junit.Test
    public void testGetWord() {
        System.out.println("getWord");
        Game instance = new Game();
        String expResult = instance.randomWord();
        String result = instance.getWord();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDisplayWord method, of class Game.
     */
    @org.junit.Test
    public void testGetDisplayWord() {
        System.out.println("getDisplayWord");
        Game instance = new Game();
        int size = instance.getWord().length();
        String expResult = "";
        
        for (int i = 0; i < size; i++)
        {
            if (i == size-1)
            {
                expResult += "_";
            }else{
                   expResult += "_ ";
                    }
        }
        System.out.println(expResult);
        
        String result = instance.getDisplayWord();
        assertEquals(expResult, result);
        
        char guess = instance.getWord().charAt(0);
        instance.playGame(guess);
        expResult = expResult.substring(1);
        
        String guessString = Character.toString(guess);
        expResult = guessString + expResult;
        System.out.println(expResult);        
        result = instance.getDisplayWord();
        assertEquals(expResult, result);
    }

    /**
     * Test of startNewGame method, of class Game.
     */
    @org.junit.Test
    public void testStartNewGame() {
        System.out.println("startNewGame");
        Game instance = new Game();
        instance.startNewGame();
        instance.playGame('c');
        instance.playGame('d');
        instance.startNewGame();
        int result = instance.getState();
        assertEquals(1,result);
 
    }

    /**
     * Test of playGame method, of class Game.
     *   correct guess should return 0 , or 1 when game is won
     *   incorrect guess should return 2, or 3 when game is lost
     */
    @org.junit.Test
    public void testPlayGame() {
        System.out.println("playGame");       
        Game instance = new Game();
        String word = instance.getWord(); 
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int j = 0;
        int expResult = 2;
        
        for (int i = 0; i < 6; i++)
        {
            if (i == 5)
            {
                System.out.println(i);
                expResult = 3;
            }
            
            while (word.indexOf(alphabet.charAt(j)) > -1)
            {
                j++;
            }
            
            char guess = alphabet.charAt(j);
            j++;
            int result = instance.playGame(guess);
            assertEquals(expResult, result);
        }
        

        instance = new Game();
        word = instance.getWord();
        // ---- Test for correct guess ----
        char guess = word.charAt(0); // first letter of word
        int result = instance.playGame(guess); //initialize
        
        expResult = 0;
        
        assertEquals(expResult, result);
        
        // ---- Test for game win ----
        String playedWords = "";
        expResult = 1;       
        for (int i = 1; i < word.length(); i++)
        {
            guess = word.charAt(i);
            if (playedWords.indexOf(guess) == -1)
            {
                playedWords += guess;
                result = instance.playGame(guess);
            }
                                    
        }       
        assertEquals(expResult, result);
         
        
        

    }
    
}
