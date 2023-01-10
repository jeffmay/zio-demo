package com.rallyhealth.pzn.io
package server

import zio._

/**
  * Example from https://di-in-scala.github.io/
  */
object DIExample extends ZIOAppDefault {

  val prepareAndDispatchNextTrain: URIO[TrainShunter & TrainLoader & TrainDispatch, Unit] = for {
    shunter <- ZIO.service[TrainShunter]
    loader <- ZIO.service[TrainLoader]
    dispatch <- ZIO.service[TrainDispatch]
    _ <- ZIO.log("prepared and dispatched next train!")
  } yield ()

  override val run: ZIO[ZIOAppArgs, Any, Any] = prepareAndDispatchNextTrain.provide(
    TrainCarCoupler.layer,
    TrainDispatch.layer,
    TrainShunter.layer,
    TrainLoader.layer,
    CraneController.layer,
    PointSwitcher.layer,
  )
}

class PointSwitcher

object PointSwitcher {

  val layer = ZLayer.succeed {
    new PointSwitcher
  }
}

class TrainCarCoupler

object TrainCarCoupler {

  val layer = ZLayer.succeed {
    new TrainCarCoupler
  }
}

class TrainShunter(
  pointSwitcher: PointSwitcher,
  trainCarCoupler: TrainCarCoupler,
)

object TrainShunter {

  val layer = ZLayer.fromZIO {
    for {
      pointSwitcher <- ZIO.service[PointSwitcher]
      trainCarCoupler <- ZIO.service[TrainCarCoupler]
    } yield new TrainShunter(pointSwitcher, trainCarCoupler)
  }
}

class CraneController

object CraneController {

  val layer = ZLayer.succeed {
    new CraneController
  }
}

class TrainLoader(
  craneController: CraneController,
  pointSwitcher: PointSwitcher,
)

object TrainLoader {

  val layer = ZLayer.fromZIO {
    for {
      craneController <- ZIO.service[CraneController]
      pointSwitcher <- ZIO.service[PointSwitcher]
    } yield new TrainLoader(craneController, pointSwitcher)
  }
}

class TrainDispatch

object TrainDispatch {

  val layer = ZLayer.succeed {
    new TrainDispatch
  }
}
