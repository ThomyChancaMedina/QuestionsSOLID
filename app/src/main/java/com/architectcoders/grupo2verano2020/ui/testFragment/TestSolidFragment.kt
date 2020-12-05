package com.architectcoders.grupo2verano2020.ui.testFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.databinding.FragmentTestSolidBinding
import com.architectcoders.grupo2verano2020.ui.common.*
import com.architectcoders.grupo2verano2020.ui.testResult.utils.Utils
import kotlinx.android.synthetic.main.fragment_test_solid.*


class TestSolidFragment : Fragment(), TestSolidAdapter.Interaction {

    lateinit var navController: NavController

    private lateinit var adapterQuestion: TestSolidAdapter

    private var binding: FragmentTestSolidBinding?=null

    private lateinit var component: TestFragmentComponent

    private val viewModel:TestSolidViewModel by lazy { getViewModelF { component.testSolidViewModel } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = container?.bindingInflate(R.layout.fragment_test_solid, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        component= appF.component.plus(TestSolidModule())


//        val window = activity?.window

        activity?.window?.let { Utils.darkenStatusBar(it, R.color.blue_bar, true) }


        viewModel.model.observe(viewLifecycleOwner, EventObserver {
            viewModel.getQuestionFromDb()
        })

        setHasOptionsMenu(true)
        adapterQuestion = TestSolidAdapter(this@TestSolidFragment)
        recycler.adapter = adapterQuestion

        binding?.apply {
            viewmodel=viewModel
            lifecycleOwner = this@TestSolidFragment
        }


        val answers = resources.getStringArray(R.array.Answers)



        binding?.bntCheck?.setOnClickListener {
            val answerSummary = viewModel.calculateResult()

            val result = compareValues(answers.toList(), answerSummary)

            val action=TestSolidFragmentDirections.actionTestSolidToMainFragment(result)
            navController.navigate(action)

        }
    }


    private fun compareValues(answers: List<String>, userAnswer: List<String>): Int {
        var c = 0
        answers.forEachIndexed { idx, value ->
            if (userAnswer[idx] == value) {
                ++c
            }
        }


        return 100 * c / answers.size
    }


    override fun onItemSelected(position: Int, selection: String) {

        viewModel.updateAnswer(position, selection)
        adapterQuestion.notifyDataSetChanged()
    }


}
