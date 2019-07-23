package cbedoy.streamclient.models

import io.getstream.core.models.Reaction
import java.util.*

data class Message(var reaction: Reaction,
                   var owner: Boolean = false,
                   var nickname: String = "unknown",
                   var createAt: Date = Date())