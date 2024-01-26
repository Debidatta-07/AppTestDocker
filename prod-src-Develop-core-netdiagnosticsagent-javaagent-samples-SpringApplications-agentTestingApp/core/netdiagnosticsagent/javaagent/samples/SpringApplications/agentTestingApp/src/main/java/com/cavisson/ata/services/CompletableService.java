package com.cavisson.ata.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.cavisson.ata.models.Order;

public class CompletableService {

	public static String callAsync(int orderId, String addr, int qty) {
		CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> submitOrder(orderId, addr, qty))
				.thenApplyAsync(placedOrder -> updateOrder(placedOrder))
				.thenApplyAsync(placedOrder -> performPayment(placedOrder))
				.thenApplyAsync(placedOrder -> dispatchOrder(placedOrder))
				.thenApplyAsync(placedOrder -> sendStatusRepo(placedOrder));
		String res = null;
		try {
			System.out.println("result :: " + result.get());
			res = result.get();
		} catch (InterruptedException e) {
			res = e.toString();
		} catch (ExecutionException e) {
			res = e.toString();
		}

		return res;
	}

	public static Order submitOrder(int id, String add, int itemQuantity) {
		System.out.println("At submitOrder");
		Order odr = new Order(id, add, itemQuantity);

		return odr;
	}

	public static Order updateOrder(Order odr) {
		System.out.println("At updateOrder");
		odr.setOrderItemQty(odr.getOrderItemQty() + 1);
		return odr;
	}

	public static Order performPayment(Order odr) {
		System.out.println("At performPayment");
		odr.setOrderPrice(odr.getOrderItemQty() * 30);
		return odr;
	}

	public static Order dispatchOrder(Order odr) {
		System.out.println("Oder Dispatched to : " + odr.getOrderAdd());
		return odr;
	}

	public static String sendStatusRepo(Order odr) {
		return odr.toString();
	}
}