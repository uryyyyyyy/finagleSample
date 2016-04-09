package com.github.uryyyyyyy.finagle.thread

import com.twitter.finagle.{Service, SimpleFilter, http}
import com.twitter.logging.Logger

class LoggingFilter extends SimpleFilter[http.Request, http.Response]  {
	private val log = Logger.get(getClass)

	def apply(request: http.Request, service: Service[http.Request, http.Response]) = {
		log.info(s"request start. thread_id: ${Thread.currentThread.getId}")
		service(request)
	}
}
