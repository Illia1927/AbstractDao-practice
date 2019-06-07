package mate.academy.utill;

public class StringConver {
    public static String toSnakeCase(String camelCase) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return camelCase
                .replaceAll(regex, replacement)
                .toLowerCase();
    }
}
