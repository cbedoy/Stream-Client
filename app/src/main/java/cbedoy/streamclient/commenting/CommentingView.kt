package cbedoy.streamclient.commenting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cbedoy.streamclient.R
import kotlinx.android.synthetic.main.fragment_commenting.*

class CommentingView : Fragment(){

    lateinit var viewModel: CommentingViewModel
    var adapter = CommentingAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_commenting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_commenting_recycler_view.layoutManager = LinearLayoutManager(activity)
        fragment_commenting_recycler_view.adapter = adapter
        fragment_commenting_recycler_view.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(CommentingViewModel::class.java)
        viewModel.currentActivity.observe(this, Observer { dataModel ->
            if(dataModel.isNotEmpty()){
                adapter.dataModel.addAll(dataModel)

                adapter.notifyDataSetChanged()
            }
        })
        viewModel.receivedMessage.observe(this, Observer {
            adapter.dataModel.add(it)

            adapter.notifyItemInserted(adapter.dataModel.size)
        })
        fragment_commenting_action_send.setOnClickListener {
            val messageText = fragment_commenting_input_view.text.toString()
            fragment_commenting_input_view.setText("")

            viewModel.sendMessage(messageText)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadActivity()
    }
}