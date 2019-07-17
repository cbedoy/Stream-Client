package cbedoy.streamclient.post

import cbedoy.streamclient.UtilsProvider
import io.getstream.core.models.Activity
import io.getstream.core.options.Pagination
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*
import kotlin.random.Random

object PostRepository : AnkoLogger {

    private val moods = arrayListOf("serious", "humorous", "amused", "angry", "playful",
        "cheerful", "sad", "gloomy", "romantic", "realistic", "mournful", "amused")

    private val content = arrayListOf(
        "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2015/03/materialdesign-730x315.jpg",
        "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2018/05/materialdesignfeat-796x398.jpg",
        "https://betanews.com/wp-content/uploads/2015/10/materialdesign_principles_metaphor.png",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_TFmeZloTg2EwyXeeh8tb36TkHy0WMUf1kbHeHiJNLr4lCfp8aQ",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPjHwqm4uJBFOjLSSzOWluplMFD35X5qlYNBk8USO2Q7cM2kMu",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvkQGYLrJBMQRb1buPFIDvfQSc3YeEKpDQsJK-dceF6DsrWgAM",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWQtMUFNd94CvlK09pafvpz0pS5JNP3iA-B1fIvGca4tmRjx6J",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_OBcMscT73SQRW6iG-L5AyVY5iF6DiitWcZ3X8Wrd2C9n7wEZMw",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWq7JVfYEVqpYroZcPoS9iyzKjrhF9d26nCdvYRC0ha54lJIcT",
        "https://storage.googleapis.com/gd-wagtail-prod-assets/original_images/material_design_awards_2016_share.jpg",
        "https://boygeniusreport.files.wordpress.com/2018/09/google-chrome-redesign.jpg?quality=98&strip=all&w=782",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuhCCqoVM0tSSbI22t8A5puux3qA4gRtSKdt-MNY-D3okeAkerlA",
        "https://fruitfulcode.com/wp-content/uploads/2016/08/material-design-main.jpg"
    )

    //  Actor is the user id of the person performing the activity.
    //  Tweet is a custom field containing the message.
    //  Verb is the type of activity the actor is engaging in.
    //  Object is the id of the tweet object in your database.

    fun addActivity(tweet: String) : Activity {
        val userId = UtilsProvider.getNickname()
        val feed = UtilsProvider.getClient().flatFeed("user", userId)
        info(feed)
        val activity = Activity.builder()
            .actor(userId)  //
            .verb("tweet")
            .`object`(UUID.randomUUID().toString())
            .extraField("tweet", tweet)
            .extraField("content", randomContent())
            .extraField("mood", randomMood())
            .extraField("number", randomNumber())
            .build()

        info(activity)

        return feed.addActivity(activity).get()
    }

    private fun randomContent(): String {
        return content.shuffled()[0]
    }

    private fun randomNumber(): Int {
        return Random(System.currentTimeMillis()).nextInt(89) + 10
    }

    private fun randomMood(): String {
        return moods.shuffled()[0]
    }

    fun loadActivities(): MutableList<Activity>? {
        val userId = UtilsProvider.getNickname()
        val feed = UtilsProvider.getClient().flatFeed("user", userId)
        return feed.getActivities(Pagination().limit(10)).get()
    }
}