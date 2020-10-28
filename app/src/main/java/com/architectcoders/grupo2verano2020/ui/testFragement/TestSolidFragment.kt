package com.architectcoders.grupo2verano2020.ui.testFragement

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.databinding.FragmentTestSolidBinding
import com.architectcoders.grupo2verano2020.ui.common.app


class TestSolidFragment : Fragment() {


    private lateinit var binding: FragmentTestSolidBinding

    val TAG = TestSolidFragment::class.java.simpleName

    var listAnswer: ArrayList<String> = arrayListOf()

    lateinit var navController: NavController


    private lateinit var adapter: TestSolidAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestSolidBinding.inflate(inflater, container, false)
        return binding.root
//        inflater.inflate(R.layout.fragment_test_solid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        adapter = TestSolidAdapter(app)

        binding.recycler.adapter = adapter

        val questions = resources.getStringArray(R.array.questionArray)

        adapter.questions = questions.toList()

        binding.bntCheck.setOnClickListener {

            if (app.resultTest.testIud.contains(",")) {
                val listResult = app.resultTest.testIud.split(",")

                for (i in questions.toList().indices) {
                    listAnswer.add(i,"null")
                }

                listResult.forEach {

                    if (it.contains("::")) {
                        val last = it.split("::").last()
                        val answer = last.split("=")
                        listAnswer[answer.last().toInt()]=last

                    } else {
                        val answer = it.split("=")
                        listAnswer[answer.last().toInt()]=it

                    }

                }
                Log.d(TAG, "onViewCreated: thomy " +listAnswer.toString())
            }
        }
    }
}