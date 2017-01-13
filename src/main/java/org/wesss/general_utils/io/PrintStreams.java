package org.wesss.general_utils.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class PrintStreams {
    public static final PrintStream nullOutputStream = new PrintStream(
            new OutputStream() {
                @Override
                public void write(int b) throws IOException {

                }
            }
    );
}
