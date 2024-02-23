package projectmd2.business.design;

public interface IDesign<T> {
    void create();
    void update();
    void delete();
    void displayAll();
    T inputData();
    void displayData();
    int getNewId();
}
