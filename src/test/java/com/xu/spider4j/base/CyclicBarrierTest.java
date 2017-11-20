package com.xu.spider4j.base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 并发辅助类 测试
 */
public class CyclicBarrierTest {
  public static void main(String[] args) {

    CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(2000);
          System.out.println("休息2s！");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    });

    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 60,
        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    for (int i = 1; i <= 30; i++) {
      threadPool.execute(new TestThread(i, cyclicBarrier));
    }

    threadPool.shutdown();
    // CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    // for (int i = 0; i < 4; i++) {
    // new Thread(new Player(i, cyclicBarrier)).start();
    // }
  }

}

class TestThread implements Runnable {

  private CyclicBarrier cyclicBarrier;
  private int id;

  public TestThread(int id, CyclicBarrier cyclicBarrier) {
    this.cyclicBarrier = cyclicBarrier;
    this.id = id;
  }

  @Override
  public void run() {
    try {
      System.out.println("线程" + id + "正在执行...线程Id: "+Thread.currentThread().getId());
      cyclicBarrier.await();
      System.out.println("线程" + id + "继续执行...线程Id: "+Thread.currentThread().getId());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}
