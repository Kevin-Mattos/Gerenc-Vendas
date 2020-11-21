package com.example.vendasmae.baseClass


class Resource<T>(var dado: T?, val success: Boolean,var erro: MyError? = null)

class MyError(val errNo: Int?, val code: String?)