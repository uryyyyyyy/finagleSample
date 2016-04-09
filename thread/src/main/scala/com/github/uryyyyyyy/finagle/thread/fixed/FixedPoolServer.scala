package com.github.uryyyyyyy.finagle.thread.fixed

import java.util.concurrent.Executors

import com.github.uryyyyyyy.finagle.thread.LoggingFilter
import com.twitter.concurrent.NamedPoolThreadFactory
import com.twitter.finagle.{Http, Service, http}
import com.twitter.logging.Logger
import com.twitter.util.{Await, Future, FuturePool}

class HelloService extends Service[http.Request, http.Response] {
	private val log = Logger.get(getClass)

	val fixedThreadExecutor = Executors.newFixedThreadPool(5,
		new NamedPoolThreadFactory("FixedFuturePool", makeDaemons = true)
	)
	val futurePool: FuturePool = FuturePool(fixedThreadExecutor)


	override def apply(request: http.Request): Future[http.Response] = futurePool {
		val response = http.Response()
		val message  = "Hello, Finagle."
		log.info(s"message: ${message} thread_id: ${Thread.currentThread.getId}")
		response.setContentString(s"${message}\n")
		response
	}
}

object FixedPoolServer {

	private val log = Logger.get(getClass)

	val loggingFilter = new LoggingFilter
	val helloService  = new HelloService

	def main(args: Array[String]):Unit = {
		val service = loggingFilter andThen helloService
		val server = Http.serve(":8080", service)
		Await.ready(server)
	}
}