package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailFragment : Fragment() {
    private val viewModel by activityViewModels<ShoeViewModel>()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shoe_detail, container, false)

        if(savedInstanceState?.containsKey("shoe") == true) {
            binding.shoe = savedInstanceState.getParcelable("shoe")
        } else {
            binding.shoe = Shoe(name = "", size = 0.0, company = "", description = "")
        }

        binding.cancelButton.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            viewModel.addShoe(binding.shoe!!)
            it.findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("shoe", binding.shoe)
    }
}