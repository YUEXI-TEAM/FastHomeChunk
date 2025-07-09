package cc.mcyx.fast.service

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

object ThreadService {
    private val executors: ExecutorService = Executors.newCachedThreadPool()
    fun submit(runnable: Runnable): Future<*> = executors.submit(runnable)
}