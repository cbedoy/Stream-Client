package cbedoy.streamclient.explorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cbedoy.streamclient.R
import cbedoy.streamclient.post.PostAdapter
import kotlinx.android.synthetic.main.fragment_explorer.*

class ExplorerView : Fragment(){

    lateinit var viewModel: ExplorerViewModel
    var adapter = PostAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explorer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        explorer_recycler_view.layoutManager = LinearLayoutManager(activity)
        explorer_recycler_view.adapter = adapter
        explorer_recycler_view.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(ExplorerViewModel::class.java)
        viewModel.activities.observe(this, Observer { activities ->
            if (adapter.dataModel.isEmpty()){
                adapter.dataModel.addAll(activities)

                adapter.notifyDataSetChanged()
            }else{
                activities.forEach {
                    if (!adapter.dataModel.contains(it)){
                        adapter.dataModel.add(it)

                        adapter.notifyItemChanged(adapter.dataModel.indexOf(it))
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadExplorer(arrayListOf("alex", "vanessa"))
    }
}