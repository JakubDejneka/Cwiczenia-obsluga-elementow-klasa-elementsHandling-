public class Child extends Parent {

    String name = "Child";

    public Child(){
        super(); //this "super" should be always at first line
        System.out.println("Im child constructor");
    }

    public void getStringData() {
        System.out.println(name);
        System.out.println(super.name);
    }

    public void getData(){
        System.out.println("im in child class");
        super.getData();
    }


    public static void main (String[] args){
        Child c= new Child();
        c.getStringData();
        c.getData();

    }


}
