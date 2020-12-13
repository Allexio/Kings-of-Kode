package com.example.kingsofkode.boardgame

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

class CardZoomFragment : DialogFragment() {

    companion object {

        const val TAG = "CardZoom"
        private const val POWER_CARD_NAME = "POWER_CARD_NAME"
        private const val CARD_BUYABLE = "CARD_BUYABLE"

        fun newInstance(powerCardName: String, cardIsBuyable: Boolean): CardZoomFragment {
            val args = Bundle()
            args.putString(POWER_CARD_NAME, powerCardName)
            args.putBoolean(CARD_BUYABLE, cardIsBuyable)
            val fragment = CardZoomFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var onPurchaseCallback: () -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.card_zoom_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    fun setOnPurchaseCallback(onPurchaseCallback: () -> Unit) {
        this.onPurchaseCallback = onPurchaseCallback
    }

    private fun setupView(view: View) {
        val powerCardName = arguments?.getString(POWER_CARD_NAME)
        val cardIsBuyable = arguments?.getBoolean(CARD_BUYABLE)
        view.powerCardImage.setImageDrawable(this.context?.let { getDrawableFromString(it, powerCardName!!, resources) })
        if (!cardIsBuyable!!) {
            view.btnPositive.visibility = GONE
        }
    }

    private fun setupClickListeners(view: View) {
        view.btnPositive.setOnClickListener {
            this.onPurchaseCallback()
            dismiss()
        }
        view.btnNegative.setOnClickListener {
            dismiss()
        }
    }

}