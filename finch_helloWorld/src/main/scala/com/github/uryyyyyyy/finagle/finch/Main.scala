package com.github.uryyyyyyy.finagle.finch

import io.finch._
import com.twitter.finagle.Http
import com.twitter.util.Await

object Main {
	def main(args: Array[String]) {
		val api: Endpoint[String] = get("hoge") { Ok("Hello, World!") }
		val api2 = get(long) { id: Long => Ok(s"Hello, ${id}!")}

		val s = Http.serve(":8080", (api :+: api2).toService)
		Await.ready(s)
	}
}
