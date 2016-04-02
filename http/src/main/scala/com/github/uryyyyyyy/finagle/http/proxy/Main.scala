package com.github.uryyyyyyy.finagle.http.proxy

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await

object Main {
	def main(args: Array[String]): Unit = {
		val client: Service[Request, Response] =
			Http.newService("www.google.com:80")

		val server = Http.serve(":8080", client)
		Await.ready(server)
	}
}
