import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    /**
     * Retrieves all the string constants defined in the given class.
     * Therefore, only static and final strings will be retrieved.
     *
     * @param myClass the class containing the string constants
     * @return a set of string constants
     * @throws RuntimeException Throws a runtime exception when an IllegalAccessException is caught
     */
    public static Set<String> getStringConstantsInClassAsSet(Class<?> myClass) {
        return Stream.of(myClass.getDeclaredFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()) &&
                        Modifier.isFinal(field.getModifiers()) &&
                        field.getType().equals(String.class))
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return (String) field.get(null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toCollection(HashSet::new));
    }
}
