package org.brightasu.thread;
/**
 * Jiupai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */

import java.util.concurrent.*;

/**
 * @author darbean
 * @version $Id: FutureTest, v 1.0.0 2017/01/23 下午15:12 darbean Exp $
 */
public abstract class FutureTest {


    public static void main(String[] args) throws Exception {
//        System.out.println(com.jiupai.cornerstone.util.AESUtil.encrypt("",""));
//        System.out.println(com.jiupai.cornerstone.util.AESUtil.decrypt("",""));
        Callable callable =new Callable() {

            public Object call() throws Exception {
                System.out.println("Hello");
                Thread.sleep(5000);
                System.out.println("world");
                String as = new String("jajajj");
                return as;
            }
        };
        Callable callable1 =new Callable() {
            public Object call() throws Exception {
                System.out.println("Hello1");
                Thread.sleep(10000);
                System.out.println("world1");
                String as = new String("jajajj1");
                return as;
            }
        };
//        FutureTask futureTask = new FutureTask(callable);
//        FutureTask futureTask1 = new FutureTask(callable1);
//        new Thread(futureTask).start();
//        new Thread(futureTask1).start();
//        System.out.println(futureTask1.get());
//        System.out.println(futureTask.get());

        Executor executor = Executors.newCachedThreadPool();
//        executor.execute(futureTask1);
        CompletionService completionService = new ExecutorCompletionService(executor);
         completionService.submit(callable);
        System.out.println(completionService.take().get());
    }
}




