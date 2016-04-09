package com.github.uryyyyyyy.finagle.thread.unbound

import com.github.uryyyyyyy.finagle.thread.LoggingFilter
import com.twitter.finagle.{Http, Service, SimpleFilter, http}
import com.twitter.logging.Logger
import com.twitter.util.{Await, Future, FuturePool}

class HelloService extends Service[http.Request, http.Response] {
	private val log = Logger.get(getClass)

	override def apply(request: http.Request): Future[http.Response] = FuturePool.unboundedPool{
		val response = http.Response()
		val message  = "Hello, Finagle."
		log.info(s"message: ${message} thread_id: ${Thread.currentThread.getId}")
		response.setContentString(s"${message}\n")
		response
	}
}

object UnboundedPoolServer {

	private val log = Logger.get(getClass)

	val loggingFilter = new LoggingFilter
	val helloService  = new HelloService

	def main(args: Array[String]):Unit = {
		val service = loggingFilter andThen helloService
		val server = Http.serve(":8080", service)
		Await.ready(server)
	}
}