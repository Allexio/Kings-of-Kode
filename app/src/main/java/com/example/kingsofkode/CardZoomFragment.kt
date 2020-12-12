package com.example.kingsofkode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.kingsofkode.R
import kotlinx.android.synthetic.main.card_zoom_fragment.view.*
import com.example.kingsofkode.utils.Companion.getDrawableFromString



class CardZoomFragment : DialogFragment() {

    companion object {

        const val TAG = "CardZoom"
        private const val POWER_CARD_NAME = "POWER_CARD_NAME"

        fun newInstance(powerCardName: String): CardZoomFragment {
            val args = Bundle()
            args.putString(POWER_CARD_NAME, powerCardName)
            val fragment = CardZoomFragment()
            fragment.arguments = args
            return fragment
        }

    }

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.card_zoom_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        setupClickListeners(view)
        setupView(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView(view: View) {
        // TODO: fix image not showing up
        var powerCardName = arguments?.getString(POWER_CARD_NAME)
        view.powerCardImage.setImageDrawable(this.context?.let { getDrawableFromString(it, powerCardName!!, resources) })
    }

    private fun setupClickListeners(view: View) {
        view.btnPositive.setOnClickListener {
            viewModel.purchase()
            dismiss()
        }
        view.btnNegative.setOnClickListener {
            dismiss()
        }
    }

}