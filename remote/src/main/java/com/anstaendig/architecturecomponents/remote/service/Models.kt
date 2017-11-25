package com.anstaendig.architecturecomponents.remote.service

data class PageModel<out T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)
