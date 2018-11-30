package monad;

import jdk.nashorn.internal.runtime.options.Option;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface ExFunction<T, U> {
  U apply(T t) throws Throwable;
}

class Either<T> {
  private T value;
  private Throwable problem;

  private Either(){}

  public static <T> Either<T> of(T val) {
    Either<T> result = new Either<>();
    result.value = val;
    return result;
  }

  public static Either ofProblem(Throwable t) {
    Either result = new Either<>();
    result.problem = t;
    return result;
  }

  public boolean isSuccess() {
    return value != null;
  }

  public boolean isProblem() {
    return problem != null;
  }

  public T get() {
    return value;
  }

  public Throwable getProblem() {
    return problem;
  }
}
public class StreamExample {
  static final Pattern WORD = Pattern.compile("\\W+");

  public static <T, U> Function<T, Optional<U>> wrap(ExFunction<T, U> op) {
    return a -> {
      try {
        return Optional.of(op.apply(a));
      } catch (Throwable t) {
        return Optional.<U>empty();
      }
    };
  }

  public static <T, U> Function<T, Either<U>> wrapAsEither(ExFunction<T, U> op) {
    return a -> {
      try {
        return Either.of(op.apply(a));
      } catch (Throwable t) {
        return Either.ofProblem(t);
      }
    };
  }

  public static void main(String[] args) throws Throwable {
    List<String> filenames = Arrays.asList(
        "PrideAndPrejudice.txt", "B.txt", "C.txt");
    filenames.stream()
//        .flatMap(name -> {
//          try {
//            return Files.lines(Paths.get(name));
//          } catch (Exception e) {
//            return Stream.<String>empty();
//          }
//        })
        .map(wrapAsEither(n -> Files.lines(Paths.get(n))))
//        .peek(o -> {if (!o.isPresent()) System.err.println("A file failed to open");})
        .peek(o -> {if (o.isProblem()) System.err.println(o.getProblem());})
        .filter(o -> o.isSuccess())
        .flatMap(o -> o.get())
        .flatMap(l -> WORD.splitAsStream(l))
        .map(s -> s.toLowerCase())
        .filter(s -> s.length() > 0)
        .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
        .entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .limit(20)
        .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

  }
}
