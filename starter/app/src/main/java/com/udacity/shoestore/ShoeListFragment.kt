package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import java.util.function.Consumer

class ShoeListFragment : Fragment() {
    private val viewModel by activityViewModels<ShoeViewModel>()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shoe_list, container, false)

        binding.fab.setOnClickListener {
            it.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        viewModel.shoeList.observe(viewLifecycleOwner) { it ->
            it.forEach(this::addShoe)
        }

        return binding.root
    }

    private fun addShoe(shoe: Shoe) {
        val shoeItemBinding = DataBindingUtil.inflate<ShoeListItemBinding>(layoutInflater, R.layout.shoe_list_item, binding.listLayout, false)
        shoeItemBinding.shoe = shoe
        binding.listLayout.addView(shoeItemBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout) {
            logout()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        activity?.viewModelStore?.clear() // is this the right place for this?
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
    }
}