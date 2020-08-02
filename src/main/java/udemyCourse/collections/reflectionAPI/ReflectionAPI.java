package udemyCourse.collections.reflectionAPI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectionAPI {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Scanner scanner = new Scanner(System.in);
        //read classes names and method name - "classA classB method"
        Class classA = Class.forName(scanner.next());
        Class classB = Class.forName(scanner.next());
        String methodName = scanner.next();
        //Input example: udemyCourse.collections.reflectionAPI.Person java.lang.String setName

        Method method = classA.getMethod(methodName, classB);

        Object objectA = classA.newInstance();
        Object objectB = classB.getConstructor(String.class).newInstance("String value");

        method.invoke(objectA, objectB);
        System.out.println(objectA);
    }
}
