package com.cavisson.ata.services;

/**
 * @author Vishal Singh
 *
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.springframework.scheduling.annotation.EnableAsync;

//Apache HTTP ASYNC exchange using callback interface

@EnableAsync
@WebServlet(name = "myServlet", urlPatterns = { "/asyncHTTPCallback" }, asyncSupported = true)
public class AsyncService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String url = null;
	PrintWriter out;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		url = request.getParameter("url");
		out = response.getWriter();
		AsyncContext ac = request.startAsync();
		apacheAsyncServlet(url, ac);

	}

	public void apacheAsyncServlet(String url, AsyncContext ac) {

		try {

			out.println("<html>" + "<head>" + "<link rel=\"stylesheet\" href=\"static/css/style.css\">"
					+ "<title>Spring Boot : Output</title>" + "</head>" + "<body>" + "<ul>"
					+ "<li><a href=\"/\">Home</a></li>" + "<li><a class=\"active\"href=\"http\">HTTP</a></li>"
					+ "<li><a href=\"database\">DATABASES</a></li>" + "<li><a href=\"\">THREADS</a></li>"
					+ "<li><a href=\"ldap\">LDAP</a></li>" + "<li><a href=\"jms\">JMS</a></li>"
					+ "<li><a href=\"completable\">COMPLETABLE FUTURE</a></li>"
					+ "<li><a href=\"exception\">EXCEPTIONS</a></li>" + "<li><a href=\"reactive\">REACTIVE</a></li>"
					+ "<li><a href=\"solr\">APACHE SOLR</a></li>"
					+ "<li><a href=\"reqres\">REQUEST RESPONSE DUMP</a></li>"
					+ "<li><a href=\"memcached\">MEMCACHED</a></li>" + "<li><a href=\"restlet\">RESTLET</a></li>"
					+ "<li><a href=\"dynamicLog\">DYNAMIC LOGGING</a></li>"
					+ "<li><a href=\"hazelcast\">HAZELCAST</a></li>" + "<li><a href=\"soap\">SOAP</a></li>"
					+ "<li><a href=\"hazelcast\">LOGGERMDC</a></li>" + "<li><a href=\"" + '"' + "quotes" + '"' + "\">QUOTED URL</a></li>" + "</ul>"
					+ "<div style=\"margin-left: 25%; padding: 1px 16px; height: 500px;\">");
			out.println("<h2>" + "Asynchronous HTTP exchange using a callback interface and Servlet 3.0" + "</h2>");

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
			CloseableHttpAsyncClient httpclient2 = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig)
					.build();

			httpclient2.start();

			long startTime = System.currentTimeMillis();
			HttpGet request2 = new HttpGet(url);
			out.println("<p>" + "Response Time for get:" + (System.currentTimeMillis() - startTime) + "</p>");

			out.println("<p>" + "Thread id : " + Thread.currentThread().getId() + "Thread Name : "
					+ Thread.currentThread().getName() + "</p>");
			long startExeTime = System.currentTimeMillis();
			out.println("<p>" + "Before Execute" + "</p>");
			httpclient2.execute(request2, new FutureCallback<HttpResponse>() {

				public void completed(final HttpResponse response) {

					out.println("<p>" + "Completing the Async request" + "</p>");
					ac.complete();
					out.println("<p>" + "Thread id : " + Thread.currentThread().getId() + "Thread Name : "
							+ Thread.currentThread().getName() + "</p>");

					out.println("<p>" + " Response Time for execute :" + (System.currentTimeMillis() - startExeTime)
							+ " " + request2.getRequestLine() + "->" + response.getStatusLine() + "</p>");

					out.println("</div>" + "</body>" + "</html>");
					Throwable throwable = new IllegalArgumentException("Blah");
					out.println(getStackTrace(throwable));
				}

				public void failed(final Exception ex) {
					out.println("<p>" + "Thread id : " + Thread.currentThread().getId() + "Thread Name : "
							+ Thread.currentThread().getName() + "</p>");

					out.println("<p>" + " Response Time for execute :" + (System.currentTimeMillis() - startExeTime)
							+ " " + request2.getRequestLine() + "->" + ex + "</p>");
					Throwable throwable = new IllegalArgumentException("Blah");
					out.println(getStackTrace(throwable));
				}

				public void cancelled() {
					out.println("<p>" + "Thread id : " + Thread.currentThread().getId() + "Thread Name : "
							+ Thread.currentThread().getName() + "</p>");
					// out.println("<h3>" + " Response Time for execute :" +
					// (System.currentTimeMillis() - startExeTime) + "</h3>");
					out.println("<p>" + " Response Time for execute :" + (System.currentTimeMillis() - startExeTime)
							+ " " + request2.getRequestLine() + " cancelled" + "</p>");
					Throwable throwable = new IllegalArgumentException("Blah");
					out.println(getStackTrace(throwable));
				}

				public String getStackTrace(Throwable aThrowable) {
					java.io.Writer result = new java.io.StringWriter();
					PrintWriter printWriter = new PrintWriter(result);
					aThrowable.printStackTrace(printWriter);
					return result.toString();
				}

			});
			out.println("<p>" + "After Execute" + "</p>");
			System.out.println("Apache Async Http Exchange Using Callback called...");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	// Asynchronous Servlet 3.0
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final long startTime = System.nanoTime();
		final AsyncContext asyncContext = request.startAsync(request, response);

		new Thread() {

			@Override
			public void run() {
				try {
					ServletResponse response = asyncContext.getResponse();
					response.setContentType("text/plain");
					PrintWriter out = response.getWriter();
					Thread.sleep(2000);
					System.out.println("-------async servlet call completed---------------");
					out.print("Your Asynchronous Servlet 3.0 callout is done . Time elapsed: "
							+ (System.nanoTime() - startTime));
					out.flush();
					asyncContext.complete();
				} catch (IOException | InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}.start();

	}
}
