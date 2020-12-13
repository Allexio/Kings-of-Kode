package com.example.kingsofkode.characterselect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.kingsofkode.R
import kotlinx.android.synthetic.main.card_zoom_fragment.view.*
import com.example.kingsofkode.Utils.Companion.getDrawableFromString
import kotlinx.android.synthetic.main.help_fragment.view.*

class HelpFragment : DialogFragment() {

    companion object {

        const val TAG = "help"

        fun newInstance(): HelpFragment {
            return HelpFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.help_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    private fun setupClickListeners(view: View) {
        view.helpText.setOnClickListener {
            dismiss()
        }
    }
}