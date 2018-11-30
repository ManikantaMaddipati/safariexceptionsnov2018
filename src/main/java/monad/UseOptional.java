package monad;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UseOptional {
  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();
    map.put("Fred", "Jones");
    map.put("Alice", "Smith");

    // lookup name X, convert to upper case, print..
    String name = map.get("Fred");
    if (name != null) {
      name = name.toUpperCase();
      System.out.println(name);
    }

    Optional<Map<String, String>> myMap = Optional.of(map);
    myMap
        .map(m -> m.get("Freda"))
        .map(n -> n.toUpperCase())
        .ifPresent(s -> System.out.println(s));
    ;
  }
}
