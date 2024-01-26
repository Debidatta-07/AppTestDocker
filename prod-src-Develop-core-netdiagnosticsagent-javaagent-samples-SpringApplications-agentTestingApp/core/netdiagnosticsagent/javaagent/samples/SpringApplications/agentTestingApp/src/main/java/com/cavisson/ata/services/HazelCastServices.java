package com.cavisson.ata.services;

import com.hazelcast.core.*;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;

import java.util.Map;
import java.util.Queue;

import com.hazelcast.core.HazelcastInstance;
//import com.hazelcast.map.IMap;

public class HazelCastServices {

	public static String hazelInstance() {
		com.hazelcast.config.Config cfg = new com.hazelcast.config.Config();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
		Map<Integer, String> mapCustomers = instance.getMap("customers");
		mapCustomers.put(1, "Joe");
		mapCustomers.put(2, "Ali");
		mapCustomers.put(3, "Avi");

		System.out.println("Customer with key 1: " + mapCustomers.get(1));
		System.out.println("Map Size:" + mapCustomers.size());

		Queue<String> queueCustomers = instance.getQueue("customers");
		queueCustomers.offer("Tom");
		queueCustomers.offer("Mary");
		queueCustomers.offer("Jane");
		System.out.println("First customer: " + queueCustomers.poll());
		System.out.println("Second customer: " + queueCustomers.peek());
		System.out.println("Queue size: " + queueCustomers.size());
		try {
			System.out.println("hazel instance is created successfully");
		} catch (Exception e) {
		}
		return "hazel instance is created successfully";
	}

	public static String hazelClient() {
		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.addAddress("127.0.0.1");
		HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		IMap map = client.getMap("customers");
		System.out.println("Map Size:---->" + map.size());
		try {
			System.out.println("hazel client called successfully");
		} catch (Exception e) {
		}
		return "hazel client called successfully";

	}

}
