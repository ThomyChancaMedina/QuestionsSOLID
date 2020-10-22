package com.architectcoders.grupo2verano2020.ui.testFragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.architectcoders.grupo2verano2020.R
import kotlinx.android.synthetic.main.fragment_test_solid.*
import kotlinx.android.synthetic.main.view_question.*


class TestSolidFragment : Fragment() {


    companion object

    val TAG = TestSolidFragment::class.java.simpleName

    lateinit var navController: NavController


    private lateinit var adapter: TestSolidAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_solid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        adapter= TestSolidAdapter()

        recycler.adapter = adapter

        val questions = resources.getStringArray(R.array.questionArray)

        adapter.questions= questions.toList()




    }


}

