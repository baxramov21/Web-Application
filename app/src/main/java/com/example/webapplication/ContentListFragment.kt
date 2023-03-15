package com.example.webapplication

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.webapplication.databinding.FragmentContentListBinding

class ContentListFragment : Fragment() {

    private var _binding: FragmentContentListBinding? = null
    private val binding: FragmentContentListBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentContentListBinding is null")

    val list = ArrayList<WebItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        val adapter = WebAdapter()
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
        list.add(WebItem("Binance", "https://www.binance.com", R.drawable.binance))
        list.add(WebItem("Netflix", "https://www.netflix.com", R.drawable.netflix))
        list.add(WebItem("Tweeter", "https://twitter.com", R.drawable.twitter))
        list.add(WebItem("Spotify", "https://open.spotify.com", R.drawable.spotify))
        list.add(WebItem("YouTube", "https://www.binance.com", R.drawable.youtube))
        list.add(WebItem("Pinterest", "https://www.pinterest.com", R.drawable.pinterest))
        list.add(WebItem("Instagram", "https://www.instagram.com", R.drawable.insta))
        list.add(WebItem("RoboForex", "https://roboforex.com", R.drawable.roboforex))
        list.add(WebItem("PocketOption", "https://pocketoption.com", R.drawable.pocketoption))
        list.add(WebItem("StackOverflow", "https://stackoverflow.com", R.drawable.stackoverflow))
        list.add(WebItem("Facebook", "https://www.facebook.com", R.drawable.facebook))
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