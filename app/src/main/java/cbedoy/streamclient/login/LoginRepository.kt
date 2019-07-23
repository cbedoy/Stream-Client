package cbedoy.streamclient.login

import cbedoy.streamclient.util.StreamUtil
import io.getstream.core.http.Token

object LoginRepository {
    fun createToken(userId: String): Token? {
        return try {
            StreamUtil.frontendToken(userId)
        }catch (exception: Exception){
            null
        }
    }
}