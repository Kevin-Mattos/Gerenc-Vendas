package com.example.vendasmae.baseClass

import okhttp3.ResponseBody


class Resource<T>(var dado: T?, val success: Boolean,var erro: MyError? = null)

class MyError(val errNo: Int?, val code: ResponseBody?)