package com.me.finalization

import com.me.finalization.Domain.{Container, Junction}

/**
  * Created by puspendu on 8/1/16.
  */
object Decisions {
  def whereShouldContainerGo(junction: Junction, container: Container): String = {
    Thread.sleep(5) // just to simulate resource hunger
    val seed = util.Random.nextInt(10000)
    s"CVR_${junction.id}_$seed"
  }
}
