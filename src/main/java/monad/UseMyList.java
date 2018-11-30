package monad;

public class UseMyList {

  public static void show(MyList ml) {
//    ml.forAll(s -> System.out.println(s));
    ml.forEach(s -> System.out.println(s));
    System.out.println("-------------------");
  }

  public static void main(String[] args) {
    MyList ml = new MyList();
    ml.add("Fred");
    ml.add("Jim");
    ml.add("Sheila");

    show(ml);

    show(ml.map(s -> s.toUpperCase()));
    show(ml.map(s -> "" + s.length()));

    MyList ml2 = new MyList();
    show(ml2.map(s -> s.toLowerCase()));
    // ml2.map... flatMap... map... forEach...
    //                   ^^^ empty! Still no error!!
  }
}
