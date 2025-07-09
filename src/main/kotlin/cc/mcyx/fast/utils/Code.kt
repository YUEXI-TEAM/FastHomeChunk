package cc.mcyx.fast.utils

import java.security.MessageDigest


fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(input.toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}