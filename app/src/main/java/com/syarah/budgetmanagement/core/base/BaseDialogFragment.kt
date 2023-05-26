package com.syarah.budgetmanagement.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseDialogFragment<B : ViewDataBinding> : BottomSheetDialogFragment() {

    private var _binding: B? = null

    protected val binding get() = _binding!!

    private lateinit var navController: NavController

    @get:LayoutRes
    protected abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<B>(inflater, layout, container, false).also {
            it.lifecycleOwner = this.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}