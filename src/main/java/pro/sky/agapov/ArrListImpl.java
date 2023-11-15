package pro.sky.agapov;

import java.util.Arrays;

public class ArrListImpl implements ArrList {
    public static final double GROWING_COEFFICIENT = 1.5;
    private final int DEFAULT_CAPACITY = 10;
    private int capacity = DEFAULT_CAPACITY;
    private int size;
    private Integer[] elements;

    public ArrListImpl(int capacity) {
        this.capacity = capacity;
        elements = new Integer[capacity];
    }

    public ArrListImpl() {
        elements = new Integer[DEFAULT_CAPACITY];
    }

    public ArrListImpl(Integer[] array) {
        capacity = array.length;
        size = capacity;
        elements = Arrays.copyOf(array, capacity);
    }

    @Override
    public Integer add(Integer item) {
        return add(size, item);
    }

    /**
     * Метод добавляет элемент на позицию index, сдвигая остальные элементы вправо.
     * Если места не хватает, то массив увеличивается по формуле:
     * новая_вместимость = старая_вместимость * GROWING_COEFFICIENT + 1
     */
    @Override
    public Integer add(int index, Integer item) {
        if (item != null) {
            if (index >= 0 && index <= size) {
                if (size == capacity) {
                    grow();
                }
                for (int i = index; i < size; i++) {
                    elements[i + 1] = elements[i];
                }
                elements[index] = item;
                size++;
                return item;
            } else {
                throw new IndexOutOfBoundsException("Index in method \"add\" must be in range of 0 and " + size + '.');
            }
        } else {
            throw new NullPointerException("Method \"add\" required not NULL parameter.");
        }
    }

    private void grow() {
        capacity = (int) (capacity * GROWING_COEFFICIENT) + 1;
        elements = Arrays.copyOf(elements, capacity);
    }

    /**
     * Метод устанавливает (заменяет) элемент item на позицию index, сдвигая остальные элементы вправо.
     * Если места не хватает, то массив увеличивается по формуле:
     * новая_вместимость = старая_вместимость * GROWING_COEFFICIENT + 1
     */
    @Override
    public Integer set(int index, Integer item) {
        if (item != null) {
            if (index >= 0 && index < size) {
                elements[index] = item;
                return item;
            } else {
                throw new IndexOutOfBoundsException("Index in method \"set\" must be in range of 0 and " + (size - 1) + '.');
            }
        } else {
            throw new NullPointerException("Method \"set\" required not NULL parameter.");
        }
    }

    @Override
    public Integer remove(Integer item) {
        if (item == null) {
            throw new NullPointerException("Method \"remove\" required not NULL parameter.");
        } else {
            return remove(indexOf(item));
        }
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index in method \"remove\" must be in range of 0 and " + (size - 1) + '.');
        } else {
            Integer item = elements[index];
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            size--;
            return item;
        }
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) != -1;
    }

    /**
     * Метод binaryContains по ходу работы отсортировывает массив методом быстрой сортировки.
     * В существующем листе будет изменен порядок элементов
     */
    public boolean binaryContains(Integer item) {
        return binaryIndexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index >= 0 && index < size) {
            return elements[index];
        } else {
            throw new IndexOutOfBoundsException("Index in method \"get\" must be in range of 0 and " + (size - 1) + '.');
        }
    }

    @Override
    public boolean equals(ArrList otherList) {
        if (otherList == null) {
            return size == 0;
        }

        if (otherList.size() == size) {
            for (int i = 0; i < size; i++) {
                if (!elements[i].equals(otherList.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("ArrList@" + Integer.toHexString(hashCode()) + " { ");
        for (int i = 0; i < size; i++) {
            s.append(elements[i]).append(" ");
        }
        s.append("}");
        return s.toString();
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Метод отсортировывает массив быстрой сортировкой.
     * В существующем листе будет изменен порядок элементов
     */
    private void qSort(int start, int end) {
        if (start >= end) {
            return;
        }
        int comparingIndex = end;
        int comparingValue = elements[end];
        int i = start;
        while (i < comparingIndex) {
            if (elements[i] > elements[comparingIndex]) {
                elements[comparingIndex] = elements[i];
                elements[i] = elements[comparingIndex - 1];
                elements[comparingIndex - 1] = comparingValue;
                comparingIndex--;
            } else {
                i++;
            }
        }
        qSort(start, comparingIndex - 1);
        qSort(comparingIndex + 1, end);
    }

    /**
     * Метод ищет индекс элемента посредством бинарного поиска или возвращает -1, если элемент не найден
     * Предварительно внутренний массив сортируется qSort.
     * В существующем листе будет изменен порядок элементов.
     */
    public int binaryIndexOf(Integer item) {
        qSort(0, size-1);
        int lIndex = 0;
        int rIndex = size - 1;
        int index = (lIndex + rIndex) / 2;

        while (lIndex < rIndex) {
            index = (lIndex + rIndex) / 2;
            if (elements[index].intValue() == item.intValue()) {
                return index;
            }

            if (elements[index] > item) {
                rIndex = index-1;
            } else {
                lIndex = index+1;
            }
        }

        if (elements[lIndex].intValue() == item.intValue()) {
            return lIndex;
        } else if (elements[rIndex].intValue() == item.intValue()) {
            return rIndex;
        } else if (elements[index].intValue() == item.intValue()) {
            return index;
        }
        return -1;
    }
}
