/*
 *
 * Author: Anth0nyPereira
 * number 93016
 *
 */

import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {

    private TqsStack<String> emptyStack;
    private TqsStack<String> notEmptyStack;
    private TqsStack<Integer> boundedStack;

    // instantiate/inicialize each stack to perform the tests
    @BeforeEach
    void setUp() {
        // empty stack
        emptyStack = new TqsStack<>();

        // not empty stack
        notEmptyStack = new TqsStack<>();
        notEmptyStack.push("Bioinformática Clínica");
        notEmptyStack.push("Desenvolvimento de Jogos Digitais");
        notEmptyStack.push("Ciências Biomédicas");
        notEmptyStack.push("Bioquímica");

        // stack with a maximum size associated
        boundedStack = new TqsStack<>(2);
        boundedStack.push(1);
        boundedStack.push(2);
    }

    @AfterEach
    void tearDown() {
        ;
    }

    // TESTS
    @Test
    @DisplayName("A stack is empty on construction")
    void isEmptyTest() {
        assertTrue(emptyStack.isEmpty()); // a stack after being created should be empty
    }

    @Test
    @DisplayName("A stack has size 0 on construction")
    void sizeTest() {
        assertEquals(0, emptyStack.size()); // empty stack should have 0 as size
    }

    @Test
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    void checkSizeAfter4PushesTest() {
        assertFalse(notEmptyStack.isEmpty()); // should be false
        assertEquals(4, notEmptyStack.size());  // 4 elements were added to notEmptyStack in setUp()
                                                        // stack should have size 4
    }

    @Test
    @DisplayName("If one pushes x then pops, the value popped is x")
    void valuePoppedTest(){
        String bioeng = "Bioengenharia";
        notEmptyStack.push(bioeng);
        String element = notEmptyStack.pop();
        assertEquals(element, bioeng);  // check if the last element pushed is the one popped
    }
    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    void valuePeekedTest() {
        String bioeng = "Bioengenharia";
        notEmptyStack.push(bioeng);
        int sizeStack = notEmptyStack.size();
        String element = notEmptyStack.peek();
        assertEquals(element, bioeng);  // check if the last element pushed is the one peeked
        assertEquals(notEmptyStack.size(), sizeStack); // check if stack size remains the same, because we did not remove anything
    }

    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    void stackEmptyAfterPopTest() {
        int firstSize = notEmptyStack.size();
        while (firstSize != 0) {                        // while loop to pop all elements from a stack
            String firstElement = notEmptyStack.pop();
            --firstSize;
        }
        assertTrue(notEmptyStack.isEmpty()); // check if the stack is empty
        assertEquals(0, notEmptyStack.size()); // check if the stack has size 0 --> again, empty
    }

    @Test
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    void popThrowException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            emptyStack.pop();
        });
    }

    @Test
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    void peekThrowException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            emptyStack.peek();
        });
    }

    @Test
    @DisplayName("For bounded stacks only, pushing onto a full stack does throw an IllegalStateException")
    void pushIntoFullStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            boundedStack.push(3);
        });

    }
}