package com.rallyhealth.pzn.io

import fs2.{Chunk, Stream}

trait Consumer[-R, F[_], +M] {

  def consume(stream: Stream[F, Chunk[Byte]]): R => Stream[F, M]
}
