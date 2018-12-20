package com.test.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTool {

	public static ThreadPool instance;
	
	public static ThreadPool getInstance(){
		if (instance == null) {
			synchronized (ThreadPoolTool.class) {
				if (instance == null) {
					int cpuNum = Runtime.getRuntime().availableProcessors();
					int threadNum = cpuNum*2;
					instance = new ThreadPool(threadNum, threadNum +5, 5);
				}
			}
		}
		return instance;
	}
	
	public static class ThreadPool{
		private ThreadPoolExecutor mExecutor;
		private int corePoolSize;
		private int maxPoolSize;
		private long keepAliveTime;
		
		public ThreadPool(int corePoolSize,int maxPoolSize,long keepAliveTime) {
			this.corePoolSize = corePoolSize;
			this.maxPoolSize = maxPoolSize;
			this.keepAliveTime = keepAliveTime;
		}
		public void execute(Runnable runnable){
			if (runnable == null) {
				return ;
			}
			if (mExecutor == null) {
				mExecutor = new ThreadPoolExecutor(corePoolSize,
						maxPoolSize, 
						keepAliveTime, 
						TimeUnit.SECONDS, 
						new LinkedBlockingQueue<>(),
						Executors.defaultThreadFactory(),
						new ThreadPoolExecutor.AbortPolicy(){
							@Override
							public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
								// TODO Auto-generated method stub
								super.rejectedExecution(r, e);
							}
						}
				);
			}
			mExecutor.execute(runnable);
		}
		public void cancel(Runnable runnable){
			if (mExecutor!=null) {
				mExecutor.getQueue().remove(runnable);
			}
		}
	}
}
