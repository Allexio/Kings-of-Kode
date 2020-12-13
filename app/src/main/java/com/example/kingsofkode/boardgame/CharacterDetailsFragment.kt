package com.example.kingsofkode.boardgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.kingsofkode.R
import com.example.kingsofkode.Utils
import com.example.kingsofkode.core.Character
import kotlinx.android.synthetic.main.character_details_fragment.view.*
import kotlinx.android.synthetic.main.character_details_fragment.view.popularity_points

class CharacterDetailsFragment(private val character: Character) : DialogFragment() {
    companion object {
        const val TAG = "CharacterDetails"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_details_fragment, container, false)
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
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    private fun setupView(view: View) {
        view.character_image.setImageDrawable(this.context?.let {
            Utils.getDrawableFromString(it, character.name, resources)
        })
        view.popularity_points.text = character.score.toString()
        view.research_points.text = character.energy.toString()
        view.breaking_change_points.text = character.health.toString()
    }

    private fun setupClickListeners(view: View) {
        view.btnBack.setOnClickListener {
            dismiss()
        }
    }
}