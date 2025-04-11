public abstract class Animal {
    public String name;
    public int limitSwim;
    public int limitRun;
    public static int count = 0;


    public Animal(String name, int limitSwim, int limitRun){
        this.name = name;
        this.limitSwim = limitSwim;
        this.limitRun = limitRun;
        count++;
    }
    public abstract void display();
    public abstract void swim(int distance);
    public abstract void run(int distance);

}
