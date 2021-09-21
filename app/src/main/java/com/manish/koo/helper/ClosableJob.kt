package com.manish.koo.helper

import kotlinx.coroutines.Job


class ClosableJob(job: Job) : Job by job, Closable {
    override fun close() {
        cancel()
    }
}

fun Job.asClosableJob(): ClosableJob = ClosableJob(this)

fun Job.asClosable(): Closable = ClosableJob(this)