package com.github.uryyyyyyy.finagle.http.server

import com.twitter.finagle.http.Version
import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Future}

object Main {
	def main(args: Array[String]): Unit = {
		val service = new Service[http.Request, http.Response] {
			override def apply(req: http.Request): Future[http.Response] =
				Future.value {
					val res = http.Response(Version.Http11, http.Status.Ok)
					res.setContentString("hello world!")
					res
				}
		}
		val server = Http.serve(":8080", service)
		Await.ready(server)
	}
}
