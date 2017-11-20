package com.xu.spider4j.base;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 赛跑
 * 主线程等待其他多个子线程完成某个任务后，再去执行下面的逻辑
 */
public class CountDownLatchTest {
	private CountDownLatch countDownLatch = new CountDownLatch(4);

	public static void main(String[] args) {
		CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
		countDownLatchTest.begin();
	}

	private void begin() {
		System.out.println("赛跑开始");

		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 4; i++) {
			//随机设置每个运动员跑多少秒结束
			int result = random.nextInt(3) + 1;
			new Thread(new Runner(result)).start();
		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("所有人都跑完了，裁判开始算成绩");
	}
	/**
	 * 运动员
	 */
	private class Runner implements Runnable {
		private int result;
		public Runner(int result) {
			this.result = result;
		}

		@Override
		public void run() {
			try {
				//模拟跑了多少秒，1-3之间随机一个数
				Thread.sleep(result * 1000);

				System.out.println("运动员" + Thread.currentThread().getId() + "跑了" + result + "秒");

				//跑完了就计数器减1
				countDownLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
