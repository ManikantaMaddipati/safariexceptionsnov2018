package monad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MyList implements Iterable<String> {
  private List<String> data = new ArrayList<>();

//  public void forAll(Consumer<String> operation) {
//    for (String s : data) {
//      operation.accept(s);
//    }
//  }
//
  // Function <String, String> equivalent to UnaryOperator<String>
  public MyList map(Function<String, String> operation) {
    MyList output = new MyList();

    // for (int idx = o; idx < this.length(); idx++)
    for (String s : data) {
      output.add(operation.apply(s));
    }
    return output;
  }

  public boolean add(String s) {
    return data.add(s);
  }

  @Override
  public Iterator<String> iterator() {
    return data.iterator();
  }
}
