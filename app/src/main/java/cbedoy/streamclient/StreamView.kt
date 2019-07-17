package cbedoy.streamclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_stream_view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class StreamView : Fragment(), AnkoLogger{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stream_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(StreamViewModel::class.java)
        viewModel.userToken.observe(this, Observer {
            stream_text.text = "$it"
        })


    }
}