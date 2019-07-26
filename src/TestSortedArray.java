
class TestSortedArray {

    private static final Storage sortArray = new SortedArrayStorage();

    public static void main(String[] args) {

        Resume r1 = new Resume();
        Resume r2 = new Resume();
        Resume r3 = new Resume();
        Resume r4 = new Resume();
        Resume r5 = new Resume();

        r1.setUuid("uuid3");
        r2.setUuid("uuid6");
        r3.setUuid("uuid23");
        r4.setUuid("uuid12");
        r5.setUuid("uuid9");

        sortArray.save(r3);
        sortArray.save(r1);
        sortArray.save(r5);
        sortArray.save(r4);
        sortArray.save(r2);

        printAll();

        System.out.println("Get r5: " + sortArray.get(r5.getUuid()));
        System.out.println("Get dummy: " + sortArray.get("dummy"));
        System.out.println("Get r3: " + sortArray.get(r4.getUuid()));

        sortArray.delete("uuid6");
        printAll();
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : sortArray.getAll()) {
            System.out.println(r);
        }
    }
}
