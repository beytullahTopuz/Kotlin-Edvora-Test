package com.t4zb.edvora.ui.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.t4zb.edvora.R
import com.t4zb.edvora.databinding.FiltersDialogBinding
import com.t4zb.edvora.databinding.FragmentHome2Binding
import com.t4zb.edvora.ui.MyFilterDialog
import com.t4zb.edvora.ui.adapter.EdvoraAdapter
import com.t4zb.edvora.ui.viewmodel.SharedViewModel
import com.t4zb.edvora.util.showLogDebug


class Home2Fragment : Fragment() {
    private lateinit var mBinding: FragmentHome2Binding
    private lateinit var mContext: FragmentActivity
    private lateinit var mSharedViewModel: SharedViewModel

    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentHome2Binding.bind(view)

        mSharedViewModel =
            ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(
                SharedViewModel::class.java
            )


        mBinding.filtersBtn.setOnClickListener{

            initDialog()
        }



        mBinding.mRecyclerView.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

        mSharedViewModel.dataRepo.edvoraData.observe(viewLifecycleOwner, {

            if (it != null && it.isNotEmpty()) {

                var mAdapter = EdvoraAdapter(mContext, it, mSharedViewModel)
                mBinding.mRecyclerView.adapter = mAdapter

                //  showLogDebug(TAG,it.toString())
            }

        })


    }

    private fun initDialog() {

        MyFilterDialog().show(parentFragmentManager,"MyFilterDialog")
        /*dialog = Dialog(mContext)
        val window = dialog.window
        window!!.attributes.windowAnimations = type
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        window.setLayout(width, height)
        val wpl = window.attributes
        wpl.gravity = Gravity.CENTER
        window.attributes = wpl
        dialog.setContentView(R.layout.filters_dialog)
        dialog.setCanceledOnTouchOutside(true)


        dialog.show()
        */
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home2, container, false)
    }

    companion object {

        const val TAG = "Home fragment"
    }
}