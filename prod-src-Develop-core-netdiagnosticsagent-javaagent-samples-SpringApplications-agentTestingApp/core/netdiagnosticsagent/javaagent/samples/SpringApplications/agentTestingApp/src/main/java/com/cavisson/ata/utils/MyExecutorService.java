package com.cavisson.ata.utils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyExecutorService implements ExecutorService {

	static Threadinitializer threadInit;
	static {
		threadInit = new Threadinitializer();
		threadInit.createThreads();
	}

	@Override
	public void execute(Runnable command) {
		System.out.println("At execute...");

		threadInit.myth.setRunnable(command);

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		System.out.println("At submit Callable ....");
		// TODO Auto-generated method stub
		threadInit.myCth.setCallable(task);
		return null;
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		System.out.println("At submit Runnable,2 args ....");
		// TODO Auto-generated method stub
		threadInit.myth.setRunnable(task);

		return null;
	}

	@Override
	public Future<?> submit(Runnable task) {
		System.out.println("At submit Runnable,1 args ....");
		// TODO Auto-generated method stub
		threadInit.myth.setRunnable(task);
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

}
