package com.example.webapplication.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.webapplication.databinding.FragmentContentListBinding
import com.example.webapplication.domain.WebItemEntity
import com.example.webapplication.presentation.activity.WebActivity
import com.example.webapplication.presentation.adapter.WebAdapter
import com.example.webapplication.presentation.view_model.MainViewModel
import com.example.webapplication.presentation.view_model.ViewModelFactory

class ContentListFragment : Fragment() {

    private var _binding: FragmentContentListBinding? = null
    private val binding: FragmentContentListBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentContentListBinding is null")

    private val viewModelFactory by lazy {
        ViewModelFactory(requireActivity().application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    val list = ArrayList<WebItemEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WebAdapter()
        initList()
        adapter.submitList(list)
        binding.recyclerViewWebItemsList.layoutManager =
            GridLayoutManager(context, getWindowWidth())
        binding.recyclerViewWebItemsList.adapter = adapter
        adapter.setOnItemClickListener(object : WebAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val item = list[position]
                val intent = Intent(context, WebActivity::class.java)
                intent.putExtra("URL", item.siteURL)
                startActivity(intent)
            }
        })
    }

    private fun initList() {
        viewModel.itemsList.observe(viewLifecycleOwner) {
            it?.let {
                list.addAll(it)
            }
        }
    }

    private fun getWindowWidth(): Int {
        val displayManager = DisplayMetrics()
        requireActivity().windowManager.getDefaultDisplay().getMetrics(displayManager)
        val windowWidth = (displayManager.widthPixels / displayManager.density).toInt()
        return if (windowWidth / 185 > 2) windowWidth / 185 else 2
    }

    override fun onDestroyView() {
        if (_binding != null) {
            _binding = null
        }
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = ContentListFragment()
    }
}