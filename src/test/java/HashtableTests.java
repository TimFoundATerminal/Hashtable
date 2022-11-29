import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashtableTests {
    @Test
    public void precheck1() {
        Hashtable table = new Hashtable(8);
        table.add("apple0");
        table.add("apple2");
        table.add("apple4");
        Assertions.assertEquals("apple2", table.get(0));
        Assertions.assertNull(table.get(1));
        Assertions.assertEquals("apple4", table.get(2));
        Assertions.assertNull(table.get(3));
        Assertions.assertNull(table.get(4));
        Assertions.assertNull(table.get(5));
        Assertions.assertEquals("apple0", table.get(6));
        Assertions.assertNull(table.get(7));
    }

    @Test
    public void precheck2() {
        Hashtable table = new Hashtable(8);
        Assertions.assertFalse(table.search("apple0"));
        table.add("apple0");
        Assertions.assertTrue(table.search("apple0"));
        Assertions.assertFalse(table.search("Apple0"));
        table.add("apple2");
        Assertions.assertTrue(table.search("apple2"));
        table.add("apple4");
        Assertions.assertTrue(table.search("apple4"));
    }

    @Test
    public void precheck3() {
        Hashtable table = new Hashtable(8);
        table.add("apple0");
        table.add("apple2");
        table.add("apple4");
        Assertions.assertTrue(table.search("apple2"));
        table.delete("apple2");
        Assertions.assertFalse(table.search("apple2"));
        Assertions.assertTrue(table.search("apple4"));
    }

    @Test
    public void collisions1() {
        Hashtable table = new Hashtable(4);
        String[] words = {"AaAa", "BBBB", "AaBB", "BBAa"};

        for (String s : words) {
            table.add(s);
        }
        Assertions.assertEquals("AaAa", table.get(0));
        Assertions.assertEquals("BBBB", table.get(1));
        Assertions.assertEquals("AaBB", table.get(2));
        Assertions.assertEquals("BBAa", table.get(3));
    }

    @Test
    public void wrapAround1() {
        Hashtable table = new Hashtable(5);
        String[] words = {"abcdef", "abcdefg", "abcdegH", "abcdeh)"};

        for (String s : words) {
            table.add(s);
        }
        Assertions.assertEquals("abcdeh)", table.get(0));
        Assertions.assertNull(table.get(1));
        Assertions.assertEquals("abcdefg", table.get(2));
        Assertions.assertEquals("abcdegH", table.get(3));
        Assertions.assertEquals("abcdef", table.get(4));
    }

    @Test
    public void delete1() {
        Hashtable table = new Hashtable(8);
        String[] words = {"apple0", "apple2", "apple9", "Apple1", "Apple2"};
        Assertions.assertFalse(table.search("Apple1"));

        for (String s : words) {
            table.add(s);
        }
        Assertions.assertEquals("Apple2", table.get(2));
        table.delete("Apple1");
        Assertions.assertTrue(table.search("Apple2"));
    }

    @Test
    public void delete2() {
        Hashtable table = new Hashtable(8);
        String[] words = {"apple0", "apple1", "apple2", "Apple1", "Apple2"};

        for (String s : words) {
            table.add(s);
        }
        table.delete("apple1");
        Assertions.assertEquals("apple2", table.get(0));
        Assertions.assertEquals("Apple2", table.get(1));
        Assertions.assertEquals("Apple1", table.get(7));
        Assertions.assertTrue(table.search("Apple2"));
    }

    @Test
    public void deleteFromFullTableOfSameValues() {
        Hashtable table = new Hashtable(8);
        String[] words = {"AaAaAa" , "AaAaBB",  "AaBBAa",  "AaBBBB",  "BBAaAa",  "BBAaBB",  "BBBBAa", "BBBBBB"};

        for (String s : words) {
            table.add(s);
        }

        table.delete("AaAaAa");
        Assertions.assertEquals("AaAaBB", table.get(0));
        Assertions.assertEquals("AaBBAa", table.get(1));
        Assertions.assertEquals("AaBBBB", table.get(2));
        Assertions.assertEquals("BBAaAa", table.get(3));
        Assertions.assertEquals("BBAaBB", table.get(4));
        Assertions.assertEquals("BBBBAa", table.get(5));
        Assertions.assertEquals("BBBBBB", table.get(6));
        Assertions.assertNull(table.get(7));
    }

    @Test
    public void deleteFromEndAndMoveStartBack() {
        Hashtable table = new Hashtable(8);
        String[] words = {"AaAaAaa" , "AaAaBBa",  "AaBBAaa",  "AaBBBBa",  "BBAaAaa",  "BBAaBBa",  "BBBBAaa", "apple0"};

        for (String s : words) {
            table.add(s);
        }
        table.delete("AaAaAaa");
        Assertions.assertNull(table.get(0));
        Assertions.assertEquals("AaAaBBa", table.get(1));
        Assertions.assertEquals("AaBBAaa", table.get(2));
        Assertions.assertEquals("AaBBBBa", table.get(3));
        Assertions.assertEquals("BBAaAaa", table.get(4));
        Assertions.assertEquals("BBAaBBa", table.get(5));
        Assertions.assertEquals("BBBBAaa", table.get(6));
        Assertions.assertEquals("apple0", table.get(7));
    }

    @Test
    public void addToFullTable() {
        Hashtable table = new Hashtable(4);
        String[] words = {"apple1", "apple2", "apple3", "apple4"};

        for (String s : words) {
            table.add(s);
        }
        table.add("apple5");
        Assertions.assertEquals(words[1], table.get(0));
        Assertions.assertEquals(words[2], table.get(1));
        Assertions.assertEquals(words[3], table.get(2));
        Assertions.assertEquals(words[0], table.get(3));
        Assertions.assertFalse(table.search("apple5"));
    }

    @Test
    public void addSameString() {
        Hashtable table = new Hashtable(6);
        String[] words = {"Hello", "Hello", "Hello", "Hello", "Hello", "Hello"};

        for (String s : words) {
            table.add(s);
        }
        Assertions.assertNull(table.get(0));
        Assertions.assertNull(table.get(1));
        Assertions.assertEquals("Hello", table.get(2));
        Assertions.assertNull(table.get(3));
        Assertions.assertNull(table.get(4));
        Assertions.assertNull(table.get(5));
    }

    @Test
    public void caseSensitiveAdd() {
        Hashtable table = new Hashtable(6);
        String[] words = {"Hello", "hello", "HELLO", "HeLlO", "hElLo", "HEllO"};

        for (String s : words) {
            table.add(s);
        }
        Assertions.assertEquals("HELLO", table.get(0));
        Assertions.assertEquals("hElLo", table.get(1));
        Assertions.assertEquals("Hello", table.get(2));
        Assertions.assertEquals("HEllO", table.get(3));
        Assertions.assertEquals("hello", table.get(4));
        Assertions.assertEquals("HeLlO", table.get(5));
    }

    @Test
    public void searchingFullTable() {
        Hashtable table = new Hashtable(4);
        String[] words = {"apple1", "apple2", "apple3", "apple4"};

        for (String s : words) {
            table.add(s);
        }
        table.add("apple5");
        Assertions.assertFalse(table.search("apple5"));
    }

    @Test
    public void checkInitialValuesAreNull() {
        Hashtable table = new Hashtable(10);

        for (int i = 0; i < 10; i++) {
            Assertions.assertNull(table.get(i));
        }
    }

    @Test
    public void deleteEmptyTable() {
        Hashtable table = new Hashtable(5);
        table.delete("");

        for (int i = 0; i < 5; i++) {
            Assertions.assertNull(table.get(i));
        }
    }
}
