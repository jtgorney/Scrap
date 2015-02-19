/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jNetworking.jNetworkInterface;

import businessobjects.CompilerRunner;
import compiler.CodeProcessor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import util.ScrapServerLogger;

/**
 * Compiler class implements static compiler sub system and queue. Events fire
 * every 5 seconds and will not run again until the previous event is finished.
 *
 * @author Jacob Gorney
 */
public class Compiler {

    private static final int THREAD_WAIT = 5000;
    private static Compiler compiler;
    private static Queue<CompilerRunner> compilerQueue = new LinkedList<>();

    /**
     * Process the current queue of jobs. This is executed on each tick of the
     * thread below.
     */
    private synchronized void processQueue() {
        ScrapServerLogger.getLogger().write("Processing compiler queue.",
                    ServerLogger.LOG_NOTICE);
        // Setup compiler system
        if (!compilerQueue.isEmpty()) {
            ScrapServerLogger.getLogger().write("Jobs in queue: " + 
                    compilerQueue.size(), ServerLogger.LOG_NOTICE);
            CodeProcessor processor = CodeProcessor.getInstance();
            // Get the jobs
            ArrayList<File> jobs = new ArrayList<>();
            // For each process the file
            while (!compilerQueue.isEmpty()) {
                // Get the job id and team id
                CompilerRunner r = compilerQueue.poll();
                processor.add(new File[]{r.getSourceCode()});
                // Process the job
                try {
                    processor.process();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // Record the results to the results queue
                r.setResultFile(processor.getResult());
                // Results.add(r);
            }
        } else {
            ScrapServerLogger.getLogger().write("No jobs in compiler queue.",
                    ServerLogger.LOG_NOTICE);
        }
    }

    public void run() {
        // Just in case the thread drops
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(THREAD_WAIT);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        // Do nothing there
                    }
                    // Process the queue
                    processQueue();
                }
            }
        }).start();
    }
    
    /**
     * Add a compiler job to the queue.
     * @param c Compiler runner
     */
    public void add(CompilerRunner c) {
        compilerQueue.add(c);
    }
    
    private Compiler() {

    }

    public static Compiler getCompiler() {
        if (compiler == null) {
            compiler = new Compiler();
            return compiler;
        } else {
            return compiler;
        }
    }
}
