//: net/mindview/atunit/ClassNameFinder.java
package net.mindview.atunit;

import java.io.*;
import java.util.*;

import net.mindview.util.*;

import static net.mindview.util.Print.*;

public class ClassNameFinder {
    public static String thisClass(byte[] classBytes) {
        Map<Integer, Integer> offsetTable = new HashMap<Integer, Integer>();
        Map<Integer, String> classNameTable = new HashMap<Integer, String>();
        try {
            DataInputStream data = new DataInputStream(new ByteArrayInputStream(classBytes));
            // 0xcafebabe
            int magic = data.readInt();
            int minorVersion = data.readShort();
            int majorVersion = data.readShort();
            int constant_pool_count = data.readShort();
            int[] constant_pool = new int[constant_pool_count];
            for (int i = 1; i < constant_pool_count; i++) {
                int tag = data.read();
                int tableSize;
                switch (tag) {
                    // UTF
                    case 1:
                        int length = data.readShort();
                        char[] bytes = new char[length];
                        for (int k = 0; k < bytes.length; k++) {
                            bytes[k] = (char) data.read();
                        }
                        String className = new String(bytes);
                        classNameTable.put(i, className);
                        break;
                    // LONG
                    case 5:
                        // DOUBLE
                    case 6:
                        data.readLong(); // discard 8 bytes
                        i++; // Special skip necessary
                        break;
                    // CLASS
                    case 7:
                        int offset = data.readShort();
                        offsetTable.put(i, offset);
                        break;
                    // STRING
                    case 8:
                        data.readShort(); // discard 2 bytes
                        break;
                    // INTEGER
                    case 3:
                    // FLOAT
                    case 4:
                        // FIELD_REF
                    case 9:
                    case 10: // METHOD_REF
                    case 11: // INTERFACE_METHOD_REF
                    case 12: // NAME_AND_TYPE
                        data.readInt(); // discard 4 bytes;
                        break;
                    default:
                        throw new RuntimeException("Bad tag " + tag);
                }
            }
            short access_flags = data.readShort();
            int this_class = data.readShort();
            int super_class = data.readShort();
            return classNameTable.get(
                    offsetTable.get(this_class)).replace('/', '.');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Demonstration:
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            for (String arg : args) {
                print(thisClass(BinaryFile.read(new File(arg))));
            }
        } else {
            // Walk the entire tree:
            for (File klass : Directory.walk(".", ".*\\.class")) {
                print(thisClass(BinaryFile.read(klass)));
            }
        }
    }
} ///:~
