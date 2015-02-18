/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jNetworking.jNetworkInterface;

/**
 * Compiler class implements static compiler sub system and queue. Events fire
 * every 5 seconds and will not run again until the previous event is finished.
 *
 * @author Jacob Gorney
 */
public class Compiler {
    private static final int THREAD_WAIT = 5000;
    private static Compiler compiler;
    
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(THREAD_WAIT);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        // Do nothing there
                    }
                    // Process the queue
                    // @todo process queue here
                    System.out.println("Processing code queue.");
                }
            }
        }).start();
    }
    
    private Compiler() {
        // Setup compiler system
    }
    
    public static Compiler getCompiler() {
        if (compiler == null) {
            compiler = new Compiler();
            return compiler;
        } else return compiler;
    }
}
