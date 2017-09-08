package com.mycompany.myapp.app

import android.annotation.SuppressLint
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object SSLDevelopmentHelper {

    fun buildTrustAllHostnameVerifier(): HostnameVerifier {
        return HostnameVerifier { _, _ -> true }
    }

    fun buildTrustAllSSLContext(): SSLContext {
        // Create a trust manager that does not validate certificate chains to
        // avoid "Trust anchor for certification path not found" exception
        val trustAllCerts = arrayOf<TrustManager>(buildTrustAllTrustManager())

        try {
            val sc = SSLContext.getInstance("TLS")  //NON-NLS
            sc.init(null, trustAllCerts, java.security.SecureRandom())
            return sc
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }

    fun buildTrustAllTrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }
        }
    }
}
