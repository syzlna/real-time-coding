import java.io.File;

public class JavaFile {

    public static void main(String[] args) {
        File directory = new File(".");

        int javaFileCount = countJavaFiles(directory);
        int issueCount = countIssue(directory);

        System.out.println("Number of Java Files = " + javaFileCount);
        System.out.println("Number of Issues = " + issueCount);
    }

    public static int countJavaFiles(File directory) {
        int count = 0;
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".java")) {
                count++;
            }
        }
        return count;
    }

    public static int countIssue(File directory) {
        int count = 0;
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith("issue")) {
                count++;
            }
        }
        return count;
    }
}

