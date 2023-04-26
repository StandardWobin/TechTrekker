package com.nw.security

import com.nw.const.Constants
import io.ktor.util.hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val HASH_KEY = hex(Constants.SECRET_KEY)
private val HMAC_KEY = SecretKeySpec(HASH_KEY, Constants.ALGORITHM)

fun hash(password: String): String {
    val hmac = Mac.getInstance(Constants.ALGORITHM)
    hmac.init(HMAC_KEY)
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}
