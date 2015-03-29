/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Jacob Gorney, Max Savard, Matt Mossner, Spencer Kokaly
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package businessobjects;

import compiler.CodeProcessor;
import jNetworking.jNetworkInterface.ServerLogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static HashMap<Integer, ArrayList<CompilerRunner>> completedRuns = 
            new HashMap<>();
    private static ScoreMachine scoreMach = new ScoreMachine();

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
                // Add to the completed runs hash
                if (completedRuns.containsKey(r.getTeamId()))
                    // Add to existing key
                    completedRuns.get(r.getTeamId()).add(r);
                else {
                    // Create the key
                    ArrayList<CompilerRunner> runs = new ArrayList<>();
                    runs.add(r);
                    completedRuns.put(r.getTeamId(), runs);
                }
                // Score it if we can
                if (r.isSubmission()) {
                    try {
                        // Score the solution/runner
                        scoreMach.score(r);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Do nothing
                    }
                }
            }
        } else {
            ScrapServerLogger.getLogger().write("No jobs in compiler queue.",
                    ServerLogger.LOG_NOTICE);
        }
    }
    
    /**
     * Search for a completed run.
     * @param token Runner token
     * @param teamId Team ID
     * @return CompilerRunner if found, null if not
     */
    public CompilerRunner searchCompletedRunners(long token, int teamId) {
        if (completedRuns.isEmpty())
            return null;
        ArrayList<CompilerRunner> runs = completedRuns.get(teamId);
        if (runs.isEmpty())
            return null;
        for (CompilerRunner r : runs) {
            if (r.getToken() == token)
                return r;
        }
        return null;
    }
    
    /**
     * Compiler thread that runs every THREAD_WAIT seconds.
     */
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
    
    /**
     * Hiding constructor.
     */
    private Compiler() {

    }
    
    /**
     * Return the current compiler object.
     * @return System compiler
     */
    public static Compiler getCompiler() {
        if (compiler == null) {
            compiler = new Compiler();
            return compiler;
        } else {
            return compiler;
        }
    }
}
