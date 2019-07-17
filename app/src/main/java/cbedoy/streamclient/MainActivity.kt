package cbedoy.streamclient


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cbedoy.streamclient.login.LoginView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, LoginView())
        transaction.addToBackStack(null)

        transaction.commit()
    }
}
