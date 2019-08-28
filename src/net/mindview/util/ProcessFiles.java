package net.mindview.util;


import java.io.File;
import java.io.IOException;

/**
 * @author fuzifeng
 */
public class ProcessFiles {
    public interface Strategy {
        /**
         * 处理
         * @param file 处理的文件引用
         */
        void process(File file);
    }

    private Strategy strategy;
    private String ext;

    public ProcessFiles(Strategy strategy, String ext) {
        this.strategy = strategy;
        this.ext = ext;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0) {
                processDirectoryTree(new File("."));
            } else {
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory()) {
                        processDirectoryTree(fileArg);
                    } else {
                        // Allow user to leave off extension:
                        if (!arg.endsWith("." + ext)) {
                            arg += "." + ext;
                        }
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Demonstration of how to use it:*/
    public static void main(String[] args) {
        new ProcessFiles(new Strategy() {
            @Override
            public void process(File file) {
                System.out.println(file);
            }
        }, "java").start(args);
    }

    public void processDirectoryTree(File root) throws IOException {
        String regex = ".*\\.";
        for (File file : Directory.walk(root.getAbsolutePath(), regex + ext)) {
            strategy.process(file.getCanonicalFile());
        }
    }
} /* (Execute to see output) *///:~
