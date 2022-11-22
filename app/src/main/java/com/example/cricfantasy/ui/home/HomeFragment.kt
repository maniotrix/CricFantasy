package com.example.cricfantasy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.ViewModelProvider
import com.example.cricfantasy.BaseFragment
import com.example.cricfantasy.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        fragmentViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            setContent {
//                // In Compose world
//                MaterialTheme {
//                    val entrees: MutableList<ItemViewState> = mutableListOf<ItemViewState>()
//                    entrees.add(ItemViewState("item1"))
//                    entrees.add(ItemViewState("item2"))
//                    entrees.add(ItemViewState("item3"))
//                    entrees.add(ItemViewState("item4"))
//                    MessageList(messages = entrees)
//                }
//            }
            setContent { // In here, we can call composables!
                MaterialTheme {
                    Greeting(name = "compose")
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
// A data object which describes how the list item should look
data class ItemViewState(
    val text: String
)



@Composable
fun MessageList(messages: List<ItemViewState>) {
    LazyColumn {
        items(messages) { message ->
            MySimpleListItem(message)
        }
    }
}
// The UI for each list item can be generated by a reusable composable
@Composable
fun MySimpleListItem(itemViewState: ItemViewState) {
    Text(text = itemViewState.text)
}