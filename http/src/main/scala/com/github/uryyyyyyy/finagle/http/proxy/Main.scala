package com.github.uryyyyyyy.finagle.http.proxy

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await

object Main {
	def main(args: Array[String]): Unit = {
		val client: Service[Request, Response] =
			Http.newService("example.com:80")

		val server = Http.serve("localhost:8080", client)
		Thread.sleep(100000)
		server.close()
		Await.ready(server)
	}
}
