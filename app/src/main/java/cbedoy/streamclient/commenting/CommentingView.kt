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
import cbedoy.streamclient.models.Message
import io.getstream.core.models.EnrichedActivity
import kotlinx.android.synthetic.main.fragment_commenting.*

class CommentingView : Fragment(){

    lateinit var viewModel: CommentingViewModel
    private var identifiers = ArrayList<String>()
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
            addEntities(dataModel)

            viewModel.loadComments()
        })
        viewModel.receivedMessage.observe(this, Observer { message ->
            addEntities(arrayListOf(message))
        })
        viewModel.receivedMessages.observe(this, Observer { messages ->
            addEntities(messages)
        })
        fragment_commenting_action_send.setOnClickListener {
            val messageText = fragment_commenting_input_view.text.toString()
            fragment_commenting_input_view.setText("")

            viewModel.sendMessage(messageText)
        }
    }

    private fun addEntities(dataModel: List<Any>) {
        dataModel.forEach {
            if ((it is EnrichedActivity) && !identifiers.contains(it.id)){
                adapter.dataModel.add(it)

                identifiers.add(it.id)

                adapter.notifyItemInserted(identifiers.size)
            }else if ((it is Message) && !identifiers.contains(it.reaction.id)){
                adapter.dataModel.add(it)

                identifiers.add(it.reaction.id)

                adapter.notifyItemInserted(identifiers.size)
            }
        }

        fragment_commenting_recycler_view.postDelayed({
            fragment_commenting_recycler_view.scrollToPosition(
                adapter.dataModel.size
            )
        }, 300)
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadActivity()
    }
}