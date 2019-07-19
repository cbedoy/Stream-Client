package cbedoy.streamclient.post.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View.OnClickListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.DialogFragment
import cbedoy.streamclient.R
import cbedoy.streamclient.post.dialogs.ReactionsDialog.ReactionsDialogListener.REACTION
import cbedoy.streamclient.post.dialogs.ReactionsDialog.ReactionsDialogListener.REACTION.*

class ReactionsDialog : DialogFragment() {

    var listener : ReactionsDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val layoutInflater = activity!!.layoutInflater

        val view = layoutInflater.inflate(R.layout.reaction_view, null)


        val onClickListener = OnClickListener { view ->
            val reaction: REACTION = when (view.id) {
                R.id.reaction_angry -> ANGRY
                R.id.reaction_enjoy -> ENJOY
                R.id.reaction_like -> LIKE
                R.id.reaction_love -> LOVE
                R.id.reaction_sad -> SAD
                R.id.reaction_surprised -> SURPRISED
                else -> NONE
            }

            listener?.onSelectedReactionType(reaction)

            if (fragmentManager != null){
                fragmentManager!!.beginTransaction()
                    .remove(this)
                    .addToBackStack(null)
                    .commit()
            }
        }

        val animation: Animation = AnimationUtils.loadAnimation(activity, R.anim.reaction_animation)

        arrayListOf(R.id.reaction_angry, R.id.reaction_enjoy, R.id.reaction_like, R.id.reaction_love,
            R.id.reaction_sad, R.id.reaction_love, R.id.reaction_surprised).forEach {id ->

            val reactionView = view.findViewById<Button>(id)

            reactionView.setOnClickListener(onClickListener)
            reactionView.startAnimation(animation)
        }

        builder.setView(view)

        return builder.create()
    }

    interface ReactionsDialogListener {
        fun onSelectedReactionType(type: REACTION)

        enum class REACTION {
            ANGRY, ENJOY, LIKE, LOVE, SAD, SURPRISED, NONE
        }
    }
}