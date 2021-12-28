package edu.tpu.ruban.lib.build

object Build {
    val IS_DEBUG_MODE: Boolean = System.getenv("BUILD_MODE")?.lowercase()?.trim()?.equals("debug") ?: false
}