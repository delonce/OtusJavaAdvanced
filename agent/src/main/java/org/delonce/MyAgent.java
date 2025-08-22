package org.delonce;

import java.lang.instrument.Instrumentation;

public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain method started");

        MyTransformer myTransformer = new MyTransformer();
        inst.addTransformer(myTransformer);
    }
}