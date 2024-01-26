package com.cavisson.ata.utils;

import java.util.LinkedList;
import java.util.List;

/**
* Producer Class.
*/
class Producer implements Runnable
{

  private List<Integer> sharedQueue;
  private int maxSize = 2; //maximum number of products which sharedQueue can hold at a time.
  int productionSize = 40; //Total no of items to be produced by each producer
  int producerNo;

  public Producer(List<Integer> sharedQueue, int producerNo)
  {
    this.sharedQueue = sharedQueue;
    this.producerNo = producerNo;
  }

  @Override
  public void run()
  {
    for(int i = 1; i <= productionSize; i++)
    { //produce products.
      try
      {
        produce(i);
      }
      catch(InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  private void produce(int i) throws InterruptedException
  {

    synchronized(sharedQueue)
    {
      //if sharedQuey is full wait until consumer consumes.
      while(sharedQueue.size() == maxSize)
      {
        System.out.println(Thread.currentThread().getName() + ", Queue is full, producerThread is waiting for " + "consumerThread to consume, sharedQueue's size= " + maxSize);
        sharedQueue.wait();
      }

      //Bcz each producer must produce unique product
      //Ex= producer0 will produce 1-5  and producer1 will produce 6-10 in random order
      int producedItem = (productionSize * producerNo) + i;

      System.out.println(Thread.currentThread().getName() + " Produced : " + producedItem);
      sharedQueue.add(producedItem);
      Thread.sleep((long) (5 * 1000));
      sharedQueue.notify();
    }
  }
}

/**
* Consumer Class.
*/
class Consumer implements Runnable
{
  private List<Integer> sharedQueue;

  public Consumer(List<Integer> sharedQueue)
  {
    this.sharedQueue = sharedQueue;
  }

  @Override
  public void run()
  {
    while(true)
    {
      try
      {
        consume();
        Thread.sleep(100);
      }
      catch(InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  private void consume() throws InterruptedException
  {

    synchronized(sharedQueue)
    {
      //if sharedQuey is empty wait until producer produces.
      while(sharedQueue.size() == 0)
      {
        System.out.println(Thread.currentThread().getName() + ", Queue is empty, consumerThread is waiting for " + "producerThread to produce, sharedQueue's size= 0");
        sharedQueue.wait();
      }

      Thread.sleep((long) (5 * 1000));
      System.out.println(Thread.currentThread().getName() + ", CONSUMED : " + sharedQueue.remove(0));
      sharedQueue.notify();
    }
  }

}

public class MultiProdCons
{

  public static void main(String args[])
  {
    List<Integer> sharedQueue = new LinkedList<Integer>(); //Creating shared object

    Producer producer0 = new Producer(sharedQueue, 0);
    Consumer consumer0 = new Consumer(sharedQueue);

    Thread producerThread0 = new Thread(producer0, "ProducerThread0");
    Thread consumerThread0 = new Thread(consumer0, "ConsumerThread0");
    producerThread0.start();
    consumerThread0.start();

    Producer producer1 = new Producer(sharedQueue, 1);
    Consumer consumer1 = new Consumer(sharedQueue);

    Thread producerThread1 = new Thread(producer1, "ProducerThread1");
    Thread consumerThread1 = new Thread(consumer1, "ConsumerThread1");
    producerThread1.start();
    consumerThread1.start();
  }
  public String startMultiProdCon()
  {
    List<Integer> sharedQueue = new LinkedList<Integer>(); //Creating shared object

    Producer producer0 = new Producer(sharedQueue, 0);
    Consumer consumer0 = new Consumer(sharedQueue);

    Thread producerThread0 = new Thread(producer0, "ProducerThread0");
    Thread consumerThread0 = new Thread(consumer0, "ConsumerThread0");
    producerThread0.start();
    consumerThread0.start();

    Producer producer1 = new Producer(sharedQueue, 1);
    Consumer consumer1 = new Consumer(sharedQueue);

    Thread producerThread1 = new Thread(producer1, "ProducerThread1");
    Thread consumerThread1 = new Thread(consumer1, "ConsumerThread1");
    producerThread1.start();
    consumerThread1.start();
    return "Multiple Producer Consumer started";
  }
}
