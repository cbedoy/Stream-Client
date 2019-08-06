package cbedoy.streamclient


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import cbedoy.streamclient.faye.FayeViewModel
import cbedoy.streamclient.login.LoginView

class MainActivity : AppCompatActivity() {

    private val viewModel : FayeViewModel by lazy {
        ViewModelProviders.of(this).get(FayeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, LoginView())
        transaction.addToBackStack(null)
        transaction.commit()

        val navController = findNavController(R.id.fragment_container)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.open_post_view){
                viewModel.connect()
            }
        }

        viewModel.status.observe(this, Observer { connected ->
            if (connected){
                viewModel.online()
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()

        viewModel.disconnect()
    }
}
