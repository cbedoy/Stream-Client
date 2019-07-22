package cbedoy.streamclient.providers

import kotlin.random.Random

object ContentProvider {
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

    private val quotes = arrayListOf(
        "It’s not about ideas. It’s about making ideas happen.",
        "Always deliver more than expected.",
        "The most courageous act is still to think for yourself. Aloud.",
        "Don’t be intimidated by what you don’t know. That can be your greatest strength and ensure that you do things differently from everyone else.",
        "Fearlessness is like a muscle. I know from my own life that the more I exercise it, the more natural it becomes to not let my fears run me.",
        "One does not discover new lands without consenting to lose sight of the shore for a very long time.Surround yourself with only people who are going to lift you higher."
    )

    private val girls = arrayListOf(
        "https://pbs.twimg.com/profile_images/963283083003744256/AWGHZqGQ_400x400.jpg",
        "https://pbs.twimg.com/profile_images/800158152188719104/xW6g4Gqt_400x400.jpg",
        "https://pbs.twimg.com/profile_images/587751679388336128/8v-hFIHK_400x400.jpg",
        "https://pbs.twimg.com/profile_images/1108530483707764738/WAD80_6A_400x400.jpg",
        "https://pbs.twimg.com/profile_images/1108530483707764738/WAD80_6A_400x400.jpg",
        "https://pbs.twimg.com/profile_images/766439373638205440/l4bMv3ub_400x400.jpg",
        "https://pbs.twimg.com/profile_images/1115067853466677249/KvaF30I__400x400.jpg",
        "https://pbs.twimg.com/profile_images/1117085128012881921/R8AoETKC_400x400.jpg",
        "https://pbs.twimg.com/profile_images/1128283268917252096/rgEsnesc_400x400.jpg",
        "https://pbs.twimg.com/profile_images/1143894793703034884/kRt3XkBI_400x400.jpg",
        "https://pbs.twimg.com/profile_images/1059815799542767617/D8sX_AMY_400x400.jpg",
        "https://pbs.twimg.com/profile_images/984900845648171009/Y4-puDDN_400x400.jpg"
    )

    private val girlsNames = arrayListOf(
        "ELVIE ANDERSON",
        "GRACE EVAN",
        "FREYA SIMPOSON",
        "HEIDI THOMBSON",
        "LACEY TAYLOR",
        "LYLA SMITH",
        "VICTORIA WILLIAMS",
        "LEAH BROWN",
        "IRIS JONES",
        "KATIE BRIDGET",
        "ZOE ANGES",
        "AURORA MILLEY"
    )

    fun randomName(): String {
        return girlsNames.shuffled()[0]
    }

    fun randomAvatar(): String {
        return girls.shuffled()[0]
    }

    fun randomQuote(): String {
        return quotes.shuffled()[0]
    }

    fun randomContent(): String {
        return content.shuffled()[0]
    }

    fun randomNumber(): Int {
        return Random(System.currentTimeMillis()).nextInt(89) + 10
    }

    fun randomMood(): String {
        return moods.shuffled()[0]
    }

}