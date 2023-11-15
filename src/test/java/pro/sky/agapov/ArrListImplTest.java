package pro.sky.agapov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrListImplTest {

    Integer[] array = {5, -6, 0, 9, -5};
    ArrListImpl arrList;
    int index;
    Integer item;
    public void setup() {
        index = 4;
        item = 10;
        arrList = new ArrListImpl(array);
    }

    @Test
    void growing() {
        assertTrue(ArrListImpl.GROWING_COEFFICIENT > 1.0);
    }

    @Test
    void add_success() {
        // Подготовка вводных данных
        setup();
        // Подготовка результата
        Integer expectedInt = 10;
        Integer[] expectedArray = {5, -6, 0, 9, 10, -5};
        int capacity = (int) (arrList.getCapacity() * ArrListImpl.GROWING_COEFFICIENT) + 1;
        // Начало теста
        Integer actualInt = arrList.add(index, item);
        assertEquals(expectedInt, actualInt);
        assertArrayEquals(expectedArray, arrList.toArray());
        assertEquals(capacity, arrList.getCapacity());
    }

    @Test
    void add_withNullPointerException() {
        // Подготовка вводных данных
        setup();
        item = null;
        // Подготовка результата
        String expectedString = "Method \"add\" required not NULL parameter.";
        // Начало теста
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> arrList.add(item)
        );
        assertEquals(expectedString, exception.getMessage());
        assertArrayEquals(array, arrList.toArray());
    }

    @Test
    void add_withIndexOutOfBoundsException() {
        // Подготовка вводных данных
        setup();
        index = 7;
        // Подготовка результата
        String expectedString = "Index in method \"add\" must be in range of 0 and 5.";
        // Начало теста
        Exception exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrList.add(index, item)
        );
        assertEquals(expectedString, exception.getMessage());
        assertArrayEquals(array, arrList.toArray());
    }

    @Test
    void set_success() {
        // Подготовка вводных данных
        setup();
        // Подготовка результата
        Integer[] expectedArray = {5, -6, 0, 9, 10};
        Integer expectedInt = 10;
        // Начало теста
        Integer actualInt = arrList.set(index, item);
        assertArrayEquals(expectedArray, arrList.toArray());
        assertEquals(expectedInt, actualInt);
    }

    @Test
    void set_withNullPointerException() {
        // Подготовка вводных данных
        setup();
        item = null;
        // Подготовка результата
        String expectedString = "Method \"set\" required not NULL parameter.";
        // Начало теста
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> arrList.set(index, item)
        );
        assertEquals(expectedString, exception.getMessage());
        assertArrayEquals(array, arrList.toArray());
    }

    @Test
    void set_withIndexOutOfBoundsException() {
        // Подготовка вводных данных
        setup();
        index = 5;
        // Подготовка результата
        String expectedString = "Index in method \"set\" must be in range of 0 and 4.";
        // Начало теста
        Exception exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrList.set(index, item)
        );
        assertEquals(expectedString, exception.getMessage());
        assertArrayEquals(array, arrList.toArray());
    }


    @Test
    void remove_ByValueSuccess() {
        // Подготовка вводных данных
        setup();
        item = 0;
        // Подготовка результата
        Integer[] expectedArray = {5, -6, 9, -5};
        Integer expectedInt = 0;
        // Начало теста
        Integer actualInt = arrList.remove(item);
        assertEquals(expectedInt, actualInt);
        assertArrayEquals(expectedArray, arrList.toArray());
    }

    @Test
    void remove_ByIndexSuccess() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        index = 0;
        // Подготовка результата
        Integer[] expectedArray = {-6, 0, 9, -5};
        Integer expectedInt = 5;
        // Начало теста
        Integer actualInt = arrList.remove(index);
        assertEquals(expectedInt, actualInt);
        assertArrayEquals(expectedArray, arrList.toArray());
    }

    @Test
    void remove_withNullPointerException() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        item = null;
        // Подготовка результата
        String expectedString = "Method \"remove\" required not NULL parameter.";
        // Начало теста
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> arrList.remove(item)
        );
        assertEquals(expectedString, exception.getMessage());
        assertArrayEquals(array, arrList.toArray());
    }

    @Test
    void remove_withIndexOutOfBoundsException() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        index = 5;
        // Подготовка результата
        String expectedString = "Index in method \"remove\" must be in range of 0 and 4.";
        // Начало теста
        Exception exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrList.remove(index)
        );
        assertEquals(expectedString, exception.getMessage());
        assertArrayEquals(array, arrList.toArray());
    }

    @Test
    void contains() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        item = -6;
        Integer notExist = 10;
        // Подготовка результата
        // Начало теста
        assertTrue(arrList.contains(item));
        assertFalse(arrList.contains(notExist));
    }

    @Test
    void binaryContains() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        item = -6;
        Integer notExist = 10;
        // Подготовка результата
        // Начало теста
        assertTrue(arrList.contains(item));
        assertFalse(arrList.contains(notExist));
    }

    @Test
    void indexOf_success() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        item = 5;
        // Подготовка результата
        int expectedIndex = 0;
        // Начало теста
        int actualIndex = arrList.indexOf(item);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void indexOf_notFound() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        // Подготовка результата
        int expectedIndex = -1;
        // Начало теста
        int actualIndex = arrList.indexOf(item);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void lastIndexOf_success() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        arrList.add(9);
        item = 9;
        // Подготовка результата
        int expectedIndex = 5;
        // Начало теста
        int actualIndex = arrList.lastIndexOf(item);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void lastIndexOf_notFound() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        // Подготовка результата
        int expectedIndex = -1;
        // Начало теста
        int actualIndex = arrList.lastIndexOf(item);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void get_success() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        // Подготовка результата
        Integer expectedItem = -5;
        // Начало теста
        Integer actualItem = arrList.get(index);
        assertEquals(expectedItem, actualItem);
    }

    @Test
    void get_withIndexOutOfBoundsException() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        index = 5;
        // Подготовка результата
        String expectedString = "Index in method \"get\" must be in range of 0 and 4.";
        // Начало теста
        Exception exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrList.get(index)
        );
        assertEquals(expectedString, exception.getMessage());
        assertArrayEquals(array, arrList.toArray());
    }

    @Test
    void size() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        // Подготовка результата
        int expectedSize = 5;
        // Начало теста
        int actualSize = arrList.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void isEmpty() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        ArrList arrList1 = new ArrListImpl();
        // Подготовка результата
        // Начало теста
        assertFalse(arrList.isEmpty());
        assertTrue(arrList1.isEmpty());
    }

    @Test
    void clear() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        // Подготовка результата
        arrList.clear();
        // Начало теста
        assertTrue(arrList.isEmpty());
    }

    // Для qSort нет теста, так как она используется внутри binaryIndexOf и является приватным методом
//    @Test
//    void qSort() {
//    }

    @Test
    void binaryIndexOf() {
        // Подготовка вводных данных
        setup(); //{5, -6, 0, 9, -5}
        item = 9;
        // Подготовка результата
        Integer[] expectedArray = {-6, -5, 0, 5, 9};
        int expectedIndex = 4;
        // Начало теста
        int actualIndex = arrList.binaryIndexOf(9);
        assertArrayEquals(arrList.toArray(),expectedArray);
        assertEquals(expectedIndex, actualIndex);
    }
}