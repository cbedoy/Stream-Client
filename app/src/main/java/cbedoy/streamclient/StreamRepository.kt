package cbedoy.streamclient

import io.getstream.core.http.Token
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error


object StreamRepository : AnkoLogger{
    fun createToken(userId: String): Token? {
        return try {
            UtilsProvider.getClient().frontendToken(userId)
        }catch (exception: Exception){
            error(exception)
            null
        }
    }
}