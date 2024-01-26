package com.cavisson.ata.services;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Vishal Singh
 *
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import com.cavisson.ata.utils.CallableMsg;
import com.cavisson.ata.utils.HelloJob;
import com.cavisson.ata.utils.HelloJobListener;
import com.cavisson.ata.utils.MultiProdCons;
import com.cavisson.ata.utils.MyExecutorService;
import com.cavisson.ata.utils.MyPoolCreator;
import com.cavisson.ata.utils.ProducerConsumerEx;
import com.cavisson.ata.utils.ShowMessage;
import com.cavisson.ata.utils.TestTask;
import com.cavisson.ata.utils.ThreadPool;

import rx.Observable;
import rx.Observer;
import rx.Emitter.BackpressureMode;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

public class ThreadCall implements Runnable {

	private static ThreadPool threadPool;

	public static String callForkJoinThreadService() {
		int nThreads = Runtime.getRuntime().availableProcessors();
		int[] numbers = new int[1000];

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
		}

		ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
		Long result = forkJoinPool.invoke(new Sum(numbers, 0, numbers.length));
		System.out.println("Fork Join : thread count = " + nThreads + ", join pool time = " + result);
		return "Fork Join : thread count = " + nThreads + ", join pool time = " + result;
	}

	public static String callSimpleThreadPoolExecutorService() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

		for (int i = 1; i <= 5; i++) {
			Task task = new Task("Task");
			System.out.println("Created : " + task.getName());

			executor.execute(task);
		}
		executor.shutdown();

		return "tasks created. see console";
	}

	// scheduled thread pool executor using ruunable interface
	public static String callScheduledThreadPoolExecutorRunnableService() {
		try {

			Runnable runnabledelayedTask = new Runnable() {
				@Override
				public void run() {
					printStackTrace();
					System.out.println(Thread.currentThread().getName() + " is Running Delayed Task");
				}
			};

			ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(4);

			// scheduledPool.scheduleWithFixedDelay(runnabledelayedTask, 1, 1,
			// TimeUnit.SECONDS);

			ScheduledFuture sf = scheduledPool.schedule(runnabledelayedTask, 4, TimeUnit.SECONDS);

			String value = (String) sf.get();

			System.out.println("Callable returned" + value);

			scheduledPool.shutdown();

			System.out.println("Is ScheduledThreadPool shutting down? " + scheduledPool.isShutdown());

		} catch (Exception e) {
			System.err.println(e);
		}

		return "DONE";
	}

	// scheduled thread pool executor using callable interface
	public static String callScheduledThreadPoolExecutorCallableService() {
		try {

			Callable callabledelayedTask = new Callable() {

				@Override
				public String call() throws Exception {
					printStackTrace();
					return "GoodBye! See you at another invocation...";
				}
			};

			ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(4);

			// scheduledPool.scheduleWithFixedDelay(runnabledelayedTask, 1, 1,
			// TimeUnit.SECONDS);

			ScheduledFuture sf = scheduledPool.schedule(callabledelayedTask, 4, TimeUnit.SECONDS);

			String value = (String) sf.get();

			System.out.println("Callable returned" + value);

			scheduledPool.shutdown();

			System.out.println("Is ScheduledThreadPool shutting down? " + scheduledPool.isShutdown());

		} catch (Exception e) {
			System.err.println(e);
		}
		return "DONE";

	}

	public static String myExceuter() {
		try {

			Callable callabledelayedTask = new Callable() {

				@Override
				public String call() throws Exception {
					printStackTrace();
					System.out.println("call done");
					return "GoodBye! See you at another invocation...";
				}
			};

			MyExecuter myexecuter = new MyExecuter();

			// scheduledPool.scheduleWithFixedDelay(runnabledelayedTask, 1, 1,
			// TimeUnit.SECONDS);

			Future sf = myexecuter.submit(callabledelayedTask);

			// String value = (String) sf.get();

			// System.out.println("Callable returned" + value);

			// myexecuter.shutdown();

			System.out.println("Is ScheduledThreadPool shutting down? " + myexecuter.isShutdown());

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return "DONE";

	}

	public static void printStackTrace() {
		StringBuilder sb = new StringBuilder();
		StackTraceElement[] stk = Thread.currentThread().getStackTrace();
		for (StackTraceElement stacktrace : stk) {
			sb.append(stacktrace.toString());
			sb.append("\n");
		}

		System.out.println("Stacktrace :" + sb.toString());

	}

	static class Task implements Runnable {
		private String name;

		public Task(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void run() {
			try {
				Long duration = (long) (Math.random() * 10);
				System.out.println("Executing : " + name);
				TimeUnit.SECONDS.sleep(duration);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	static class Sum extends RecursiveTask<Long> {
		private static final long serialVersionUID = 1L;
		int low;
		int high;
		int[] array;

		Sum(int[] array, int low, int high) {
			this.array = array;
			this.low = low;
			this.high = high;
		}

		protected Long compute() {

			if (high - low <= 10) {
				long sum = 0;

				for (int i = low; i < high; ++i)
					sum += array[i];
				return sum;
			} else {
				int mid = low + (high - low) / 2;
				Sum left = new Sum(array, low, mid);
				Sum right = new Sum(array, mid, high);
				left.fork();
				long rightResult = right.compute();
				long leftResult = left.join();
				return leftResult + rightResult;
			}
		}
	}

	public static String createThreadPool() {
		setThreadPool(new ThreadPool(3, 4));
		return "numThreads = 4, Queue Size = 3, thread pool creation success !";
	}

	public static ThreadPool getThreadPool() {
		return threadPool;
	}

	public static void setThreadPool(ThreadPool threadPool) {
		MyPoolCreator.threadPool = threadPool;
	}

	public static String testThreadPool() {
		try {
			for (int taskNumber = 1; taskNumber <= 5; taskNumber++) {
				TestTask task = new TestTask(taskNumber);

				MyPoolCreator.getThreadPool().submitTask(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "thread pool created !";
	}

	public static String makeThreadPoolUsingExecutor() {

		try {
			ExecutorService eService = new MyExecutorService();

			try {
				ShowMessage sm = new ShowMessage("1st runnable message");
				eService.execute(sm);
				Thread.sleep(2000);

				sm = new ShowMessage("2nd runnable message");
				eService.submit(sm, null);
				Thread.sleep(2000);

				sm = new ShowMessage("3rd runnable message");
				eService.submit(sm);
				Thread.sleep(2000);

				CallableMsg cmsg = new CallableMsg("1st callable message");
				eService.submit(cmsg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "thread pool completed !";
	}

	public static String executeMyJob() {

		try {
			JobKey jobKey = new JobKey("dummyJobName", "group1");
			JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity(jobKey).build();

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();

			// Listener attached to jobKey
			scheduler.getListenerManager().addJobListener(new HelloJobListener(), KeyMatcher.keyEquals(jobKey));

			// Listener attached to group named "group 1" only.
			// scheduler.getListenerManager().addJobListener(
			// new HelloJobListener(), GroupMatcher.jobGroupEquals("group1")
			// );

			scheduler.start();
			scheduler.scheduleJob(job, trigger);

			Thread.sleep(18000);

			scheduler.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "quartz call completed!";
	}

	// JavaRx

	private static void createConnectableObservable() {

		ConnectableObservable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6).publish();
		observable.subscribe(item -> System.out.println("observer 1 ConnectableObservable: " + item));

		observable.connect();
		// Second observer is subscribing late so is unable to get emmited data
		observable.subscribe(item -> System.out.println("observer 2 ConnectableObservable: " + item));
	}

	private static void createObservableUsingCreate() {

		Observable<Integer> observable = Observable.create(emitter -> {
			emitter.onNext(1);
			emitter.onNext(2);
		}, BackpressureMode.BUFFER);
		observable.subscribe(item -> System.out.println("Item UsingCreate: " + item));
	}

	private static void createObservableFromIterable() {
		List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 7, 8);
		Observable<Integer> observable = Observable.from(list);

		/*
		 * Subscribes to an Observable and provides an Observer that implements
		 * functions to handle the items the Observable emits and any error or
		 * completion notification it issues.
		 */
		observable.subscribe(item -> System.out.println("Item FromIterable : " + item));

	}

	private static void createObservableWithJust() {

		Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6);
		observable.subscribe(item -> System.out.println("Item WithJust : " + item));
	}

	// JavaRx example 2

	static Observer<Integer> observer = new Observer<Integer>() {
		@Override
		public void onCompleted() {
			threadInfo6("onCompleted");
		}

		@Override
		public void onError(Throwable e) {
			System.out.println("onError error");
			e.printStackTrace();
		}

		@Override
		public void onNext(Integer length) {
			threadInfo4(length, "onNext");
		}
	};

	public static <T> T threadInfo1(T item, String operator) {
		System.out.println("threadInfo1 called for " + operator + " || Current Item : " + item
				+ " ||| Thread Name is : " + Thread.currentThread().getName() + "||");

		return item;
	}

	public static <T> T threadInfo2(T item, String operator) {
		System.out.println("threadInfo2 called for " + operator + " || Current Item : " + item
				+ " ||| Thread Name is : " + Thread.currentThread().getName() + "||");

		return item;
	}

	public static <T> T threadInfo3(T item, String operator) {
		System.out.println("threadInfo3 called for " + operator + " || Current Item : " + item
				+ " ||| Thread Name is : " + Thread.currentThread().getName() + "||");

		return item;
	}

	public static <T> T threadInfo4(T item, String operator) {
		System.out.println("threadInfo4 called for " + operator + " || Current Item : " + item
				+ " ||| Thread Name is : " + Thread.currentThread().getName() + "||");

		return item;
	}

	public static <T> T threadInfo5(T item, String operator) {
		System.out.println("threadInfo5 called for " + operator + " || Current Item : " + item
				+ " ||| Thread Name is : " + Thread.currentThread().getName() + "||");

		return item;
	}

	public static void threadInfo6(String operator) {
		System.out.println("threadInfo6 called for " + operator + " || Current Item : NA ||| Thread Name is : "
				+ Thread.currentThread().getName() + "||");

	}

	public static String syncCall() {

		System.out.println("SYNC CALL");
		Observable.just("long", "longer", "longest").doOnNext(c -> threadInfo1(c, "doOnNext"))
				.subscribeOn(Schedulers.newThread()).map(String::length).subscribe(observer);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "Rx java Sync call executed";
	}

	public static String asyncCallOnSingle() {

		/*
		 * flatMap() wraps each item being emitted by an Observable letting you apply
		 * its own RxJava operators including assigning a new Scheduler using
		 * subscribeOn() to handle those operators. Once all items inside flatMap() have
		 * been processed, the individual Observables are then merged back into a single
		 * Observable in no particular order.
		 */
		System.out.println("AYNC CALL ON SINGLE THREAD");
		Observable
				.just("long", "longer", "longest").flatMap(v -> performLongOperation(v)
						.doOnNext(s -> threadInfo1(s, "doOnNext")).subscribeOn(Schedulers.immediate()))
				.subscribe(observer);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "Rx java Async call on Single Thread executed";

	}

	public static String asyncCall() {

		System.out.println("AYNC CALL");
		Observable
				.just("long", "longer", "longest").flatMap(v -> performLongOperation(v)
						.doOnNext(s -> threadInfo1(s, "doOnNext")).subscribeOn(Schedulers.newThread()))
				.subscribe(observer);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "Rx java Async call executed";

	}

	public static String asyncCallMultipleThreads() {

		System.out.println("AYNC CALL ON MULTIPLE THREAD");
		Observable.just("long", "longer", "longest")
				.flatMap(v -> performLongOperation(v).doOnNext(s -> threadInfo1(s, "doOnNext1"))
						.subscribeOn(Schedulers.newThread()).flatMap(t -> performAddOperation(t)
								.doOnNext(s -> threadInfo5(s, "doOnNext2")).subscribeOn(Schedulers.computation())))
				.observeOn(Schedulers.io()).subscribe(observer);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "Rx java Async call on Multi Thread executed";

	}

	/**
	 * Returns length of each param wrapped into an Observable.
	 */

	protected static Observable<Integer> performLongOperation(String v) {
		threadInfo2(v, "performLongOperation");
		Random random = new Random();
		try {
			Thread.sleep(1000);
			return Observable.just(v.length());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns length of each param wrapped into an Observable.
	 */
	protected static Observable<Integer> performAddOperation(Integer v) {
		threadInfo3(v, "performAddOperation");
		return Observable.just(v + 15);

	}

	int num;
	private final Object lock = new Object();
	private int counter = 0;

	public void run() {
		/*
		 * if (num == 1) { foo.do_wait(); } else { foo.do_notify(); }
		 */
		System.out.println("Current Thread name : " + Thread.currentThread().getName() + " hashcode : "
				+ Thread.currentThread().hashCode());
		for (int i = 0; i < 200; i++) {
			synchronized (lock) {
				System.out.println("****** Current Thread name : " + Thread.currentThread().getName());
				try {
					Thread.currentThread().sleep(900);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				counter++;
			}

			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	ThreadCall(int num) {
		this.num = num;
	}

	public static String mutexthread() {

		ThreadCall testobj = new ThreadCall(1);

		Thread[] threads = new Thread[4];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(testobj);
			threads[i].start();
		}
		try {
			for (Thread thread : threads) {
				thread.join();
			}

		} catch (Exception e) {
		}
		return "mutex lock uisng thread done !";

	}

	public static String prodConExample() {

		ProducerConsumerEx prConExample = new ProducerConsumerEx();
		return prConExample.startProdCon();
	}

	public static String multiProdCon() {

		MultiProdCons mProdCon = new MultiProdCons();
		return mProdCon.startMultiProdCon();
	}
}
