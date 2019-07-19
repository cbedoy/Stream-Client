package cbedoy.streamclient.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import cbedoy.streamclient.R
import cbedoy.streamclient.UtilsProvider
import kotlinx.android.synthetic.main.fragmet_login.*

class LoginView : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmet_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.userToken.observe(this, Observer {
            if (it != null){
                UtilsProvider.setToken(it.toString())

                findNavController().navigate(R.id.open_post_view)
            }
        })
        nickname_field.setText("system")
        action_login.setOnClickListener {
            if (nickname_field.text.isNotEmpty()){
                val nickname = nickname_field.text.toString()
                UtilsProvider.setNickname(nickname)

                viewModel.performLoginWithUser(nickname)
            }
        }
    }
}