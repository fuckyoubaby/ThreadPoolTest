package com.test.thread;

public class TestThreadPool {

	public static void main(String[] args) {
		
		for(int i = 0;i<15;i++)
		{
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					System.out.println("线程开始");
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程结束");				}
			};
			ThreadPoolTool.getInstance().execute(runnable);
		}
		
		
	}
}
