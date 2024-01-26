package com.cavisson.ata.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Threadinitializer extends HttpServlet {

	public MyThread myth;
	public MyCallableThread myCth;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		createThreads();
		out.write("Threads Created and started.........");

	}

	public void createThreads() {

		myth = new MyThread();
		myth.start();

		myCth = new MyCallableThread();
		myCth.start();
	}
}
